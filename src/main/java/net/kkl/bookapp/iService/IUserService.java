package net.kkl.bookapp.iService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kkl.bookapp.common.*;
import net.kkl.bookapp.common.dto.BookDTO;
import net.kkl.bookapp.common.dto.UpdateBookDTO;
import net.kkl.bookapp.entity.Book;
import net.kkl.bookapp.exception.ApplicationErrorException;
import net.kkl.bookapp.repository.BookRepository;
import net.kkl.bookapp.service.UserService;
import net.kkl.bookapp.utils.DateUtils;
import net.kkl.bookapp.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import static net.kkl.bookapp.iService.IAnyoneService.getBookAppResponse;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class IUserService implements UserService {

    @Value("${image.file.path.absolutePath}")
    private String imageAbsolutePath;

    @Value("${image.file.path.relativePath}")
    private String imageRelativePath;

    @Value("${pdf.file.path.absolutePath}")
    private String pdfAbsolutePath;

    @Value("${pdf.file.path.relativePath}")
    private String pdfRelativePath;

    private final BookRepository bookRepository;
    private final FileUtil fileUtil;
    @Override
    public BookAppResponse addBook(BookDTO bookDTO) throws IOException {
        var book = saveBook(bookDTO);
        log.info("=====> {} is successfully inserted.",book.getBookTitle());
        return BookAppResponse.success(Constant.SUCCESS_MSG,book.getBookTitle());
    }

    @Override
    public BookAppResponse deleteBook(Long bookId) {
        var existBook = bookRepository.findById(bookId).orElseThrow(
                ()-> new ApplicationErrorException(Constant.BOOK_NOT_FOUND)
        );
        existBook.setDeleted(true);
        bookRepository.save(existBook);
        log.info("=====> {} is successfully deleted.",existBook.getBookTitle());
        return BookAppResponse.success(Constant.BOOK_DELETED,existBook.getBookTitle());
    }

    @Override
    public BookAppResponse getBooks(int pageNo, int pageSize) {
        return getBookAppResponse(pageNo, pageSize, bookRepository);
    }

    @Override
    public BookAppResponse updateBook(UpdateBookDTO updateBookDTO) throws IOException {
        var existedBook = bookRepository.findById(updateBookDTO.getId()).orElseThrow(
                ()-> new ApplicationErrorException(Constant.BOOK_NOT_FOUND)
        );
        Book updateBook = null;
        if(!ObjectUtils.isEmpty(existedBook)){
            String imagePath = null;
            String pdfPath = null;
            if (null != updateBookDTO.getCoverImage() && !updateBookDTO.getCoverImage().isEmpty()) {
                imagePath = fileUtil.writeMediaFile(updateBookDTO.getCoverImage(), imageAbsolutePath, imageRelativePath);
            }
            if (null != updateBookDTO.getPdf() && !updateBookDTO.getPdf().isEmpty()) {
                pdfPath = fileUtil.writeMediaFile(updateBookDTO.getPdf(), pdfAbsolutePath, pdfRelativePath);
            }
            existedBook.setBookTitle(ObjectUtils.isEmpty(updateBookDTO.getBookTitle())?existedBook.getBookTitle():updateBookDTO.getBookTitle());
            existedBook.setGenres(ObjectUtils.isEmpty(updateBookDTO.getGenres())?existedBook.getGenres():updateBookDTO.getGenres());
            existedBook.setCoverImage(ObjectUtils.isEmpty(updateBookDTO.getCoverImage())?existedBook.getCoverImage():imagePath);
            existedBook.setPdfLink(ObjectUtils.isEmpty(updateBookDTO.getPdf())?existedBook.getPdfLink():pdfPath);
            existedBook.setAuthor(ObjectUtils.isEmpty(updateBookDTO.getAuthor())?existedBook.getAuthor():updateBookDTO.getAuthor());
            existedBook.setSummary(ObjectUtils.isEmpty(updateBookDTO.getSummary())?existedBook.getSummary():updateBookDTO.getSummary());
            existedBook.setUpdatedAt(DateUtils.getNowDate());
            existedBook.setRatings(ObjectUtils.isEmpty(updateBookDTO.getRatings())?existedBook.getRatings():updateBookDTO.getRatings());
            updateBook = bookRepository.save(existedBook);
        }
        assert updateBook != null;
        log.info("=====> {} is successfully updated.",updateBook.getBookTitle());
        return BookAppResponse.success(Constant.BOOK_UPDATED,updateBook.getBookTitle());
    }

    private Book saveBook(BookDTO bookDTO) throws IOException {
        var existedBook = bookRepository.findByBookTitle(bookDTO.getBookTitle());
        if(!ObjectUtils.isEmpty(existedBook))
            throw new ApplicationErrorException(Constant.BOOK_EXISTED);
        Book saveBook = null;
        if (ObjectUtils.isEmpty(existedBook)) {
            String imagePath = null;
            String pdfPath = null;
            if (null != bookDTO.getCoverImage() && !bookDTO.getCoverImage().isEmpty()) {
                imagePath = fileUtil.writeMediaFile(bookDTO.getCoverImage(), imageAbsolutePath, imageRelativePath);
            }
            if (null != bookDTO.getPdf() && !bookDTO.getPdf().isEmpty()) {
                pdfPath = fileUtil.writeMediaFile(bookDTO.getPdf(), pdfAbsolutePath, pdfRelativePath);
            }
            saveBook = bookRepository.save(Book.builder()
                    .bookTitle(bookDTO.getBookTitle())
                    .genres(bookDTO.getGenres())
                    .coverImage(imagePath)
                    .pdfLink(pdfPath)
                    .author(bookDTO.getAuthor())
                    .summary(bookDTO.getSummary())
                    .createdAt(DateUtils.getNowDate())
                    .ratings(bookDTO.getRatings())
                    .updatedAt(0)
                    .isDeleted(false)
                    .build());
        }
        return saveBook;
    }
}
