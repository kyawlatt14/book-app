package net.kkl.bookapp.service;
import net.kkl.bookapp.common.dto.UserDTO;
import net.kkl.bookapp.common.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse registeredByUser(UserDTO userDTO);
    AuthenticationResponse login(UserDTO userDTO);
}
