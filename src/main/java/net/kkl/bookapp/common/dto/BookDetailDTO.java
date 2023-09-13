package net.kkl.bookapp.common.dto;

import lombok.Builder;
import lombok.Data;
import net.kkl.bookapp.common.Books;
import net.kkl.bookapp.entity.Genres;

import java.util.List;

@Data
@Builder
public class BookDetailDTO {
    public String bookTitle;
    public String summary;
    public String coverImage;
    public Genres genres;
    public String author;
    public String pdfLink;
    public int ratings;
    List<CommentDTO> comments;
    List<Books> relatedBookList;
}
