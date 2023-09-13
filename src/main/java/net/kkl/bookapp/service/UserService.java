package net.kkl.bookapp.service;

import net.kkl.bookapp.common.BookAppResponse;
import net.kkl.bookapp.common.dto.BookDTO;
import net.kkl.bookapp.common.dto.UpdateBookDTO;

import java.io.IOException;

public interface UserService {
    BookAppResponse addBook(BookDTO bookDTO) throws IOException;

    BookAppResponse deleteBook(Long bookId);

    BookAppResponse getBooks(int pageNo, int pageSize);

    BookAppResponse updateBook(UpdateBookDTO updateBookDTO) throws IOException;
}
