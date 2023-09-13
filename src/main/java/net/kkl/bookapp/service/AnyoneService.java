package net.kkl.bookapp.service;

import net.kkl.bookapp.common.BookAppResponse;
import net.kkl.bookapp.common.dto.CommentDTO;

public interface AnyoneService {
    BookAppResponse getBooks(int pageNo, int pageSize);

    BookAppResponse commentOnBook(CommentDTO commentDTO);

    BookAppResponse bookDetail(Long bookId);
}
