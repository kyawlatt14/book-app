package net.kkl.bookapp.repository;

import net.kkl.bookapp.entity.Book;
import net.kkl.bookapp.entity.Genres;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByBookTitle(String bookTitle);
    List <Book> findByIsDeleted(boolean b, Pageable page);

    Optional<Book> findByIdAndIsDeleted(Long bookId, boolean b);

    List<Book> findByAuthorOrGenresOrRatingsBetween(String author, Genres genres, int i, int i1);

    List<Book> findByIsDeletedAndAuthorOrGenresOrRatingsBetween(boolean b, String author, Genres genres, int i, int i1, Pageable page);

    List<Book> findByIsDeletedAndAuthorAndGenresAndRatingsBetween(boolean b, String author, Genres genres, int i, int i1, Pageable page);

    List<Book> findByIsDeletedAndAuthorAndGenresOrRatingsBetween(boolean b, String author, Genres genres, int i, int i1, Pageable page);

    List<Book> findByIsDeletedAndAuthorOrGenresAndRatingsBetween(boolean b, String author, Genres genres, int i, int i1, Pageable page);
}
