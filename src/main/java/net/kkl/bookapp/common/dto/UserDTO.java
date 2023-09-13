package net.kkl.bookapp.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    @NotBlank(message = "Username must not be null...")
    private String username;
    @NotBlank(message = "PhoneNumber must not be null...")
    private String password;
}
