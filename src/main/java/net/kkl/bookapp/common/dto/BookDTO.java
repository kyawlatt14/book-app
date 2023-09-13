package net.kkl.bookapp.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import net.kkl.bookapp.entity.Genres;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class BookDTO {
    @NotNull(message = "BookTitle must not be null...")
    private String bookTitle;
    private String summary;
    private MultipartFile coverImage;
    @NotNull(message = "Genres must not be null...")
    private Genres genres;
    @NotNull(message = "Author must not be null...")
    private String author;
    @NotNull(message = "Ratings must not be null...")
    private int ratings;
    private MultipartFile pdf;
}
