package net.kkl.bookapp.common.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class CommentDTO {
    @NotNull(message = "Id must not be null.")
    private long id;
    @NotNull(message = "Your comment must not be null.")
    private String comment;
    @Email(message = "Your email is not email-format.")
    @NotNull(message = "Your email must not be null.")
    private String email;
}
