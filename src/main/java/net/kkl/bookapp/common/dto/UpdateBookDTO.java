package net.kkl.bookapp.common.dto;

import lombok.Builder;
import lombok.Data;
import net.kkl.bookapp.entity.Genres;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class UpdateBookDTO {
    private long id;
    private String bookTitle;
    private String summary;
    private MultipartFile coverImage;
    private Genres genres;
    private String author;
    private int ratings;
    private MultipartFile pdf;
}
