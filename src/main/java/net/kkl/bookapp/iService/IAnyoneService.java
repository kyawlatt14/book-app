package net.kkl.bookapp.iService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kkl.bookapp.common.*;
import net.kkl.bookapp.common.dto.BookDetailDTO;
import net.kkl.bookapp.common.dto.CommentDTO;
import net.kkl.bookapp.entity.Book;
import net.kkl.bookapp.entity.Comment;
import net.kkl.bookapp.exception.ApplicationErrorException;
import net.kkl.bookapp.repository.BookRepository;
import net.kkl.bookapp.repository.CommentRepository;
import net.kkl.bookapp.service.AnyoneService;
import net.kkl.bookapp.utils.DateUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class IAnyoneService implements AnyoneService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    public BookAppResponse getBooks(int pageNo, int pageSize) {
        return getBookAppResponse(pageNo, pageSize, bookRepository);
    }

    @Override
    public BookAppResponse commentOnBook(CommentDTO commentDTO) {
        var book = bookRepository.findByIdAndIsDeleted(commentDTO.getId(),false).orElseThrow(
                ()-> new ApplicationErrorException(Constant.BOOK_NOT_FOUND)
        );
        var comment = Comment.builder()
                .comment(commentDTO.getComment())
                .email(commentDTO.getEmail())
                .createdAt(DateUtils.getNowDate())
                .build();
        book.add(comment);
        commentRepository.save(comment);
        return BookAppResponse.success(Constant.APP_MSG,"*****");
    }

    @Override
    public BookAppResponse bookDetail(Long bookId) {
        var bookDetail = bookRepository.findByIdAndIsDeleted(bookId,false).orElseThrow(
                ()-> new ApplicationErrorException(Constant.BOOK_NOT_FOUND)
        );
        return getBookAppResponse(bookDetail);
    }

    private BookAppResponse getBookAppResponse(Book bookDetail) {
        List<CommentDTO> commentDTOS = new ArrayList<>();
        bookDetail.getComments().forEach(
                comment -> commentDTOS.add(CommentDTO.builder()
                        .id(comment.getId())
                        .comment(comment.getComment())
                        .email(comment.getEmail())
                        .build())
        );
        Pageable page= PageRequest.of(0,7);
        List<Book> books = bookRepository.findByIsDeletedAndAuthorAndGenresOrRatingsBetween(false,bookDetail.getAuthor()
                , bookDetail.getGenres()
                ,3,6,page);
        List<Books> relatedBooksList = new ArrayList<>();
        books.forEach(book -> relatedBooksList.add(Books.builder()
                .id(book.getId())
                .summary(book.getSummary())
                .bookTitle(book.getBookTitle())
                .genres(book.getGenres())
                .author(book.getAuthor())
                .coverImage(book.getCoverImage())
                .ratings(book.getRatings())
                .build()));
        log.info("=====> Book-Detail...");
        return BookAppResponse.success(Constant.APP_MSG, BookDetailDTO.builder()
                .bookTitle(bookDetail.getBookTitle())
                .summary(bookDetail.getSummary())
                .coverImage(bookDetail.getCoverImage())
                .genres(bookDetail.getGenres())
                .author(bookDetail.getAuthor())
                .pdfLink(bookDetail.getPdfLink())
                .ratings(bookDetail.getRatings())
                .comments(commentDTOS)
                .relatedBookList(relatedBooksList)
                .build());
    }

    static BookAppResponse getBookAppResponse(int pageNo, int pageSize, BookRepository bookRepository) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable page= PageRequest.of(pageNo,pageSize,sort);
        var books = bookRepository.findByIsDeleted(false,page);
        List<Books> booksList = new ArrayList<>();
        books.forEach( book -> booksList.add(Books.builder()
                .id(book.getId())
                .summary(book.getSummary())
                .bookTitle(book.getBookTitle())
                .genres(book.getGenres())
                .author(book.getAuthor())
                .coverImage(book.getCoverImage())
                .ratings(book.getRatings())
                .build()));
        return BookAppResponse.success(Constant.GET_BOOKS,booksList);
    }
}
