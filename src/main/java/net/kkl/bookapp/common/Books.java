package net.kkl.bookapp.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kkl.bookapp.entity.Genres;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    private Long id;
    private String bookTitle;
    private String summary;
    private String coverImage;
    @JsonIgnore
    private Genres genres;
    private String author;
    private int ratings;
}
