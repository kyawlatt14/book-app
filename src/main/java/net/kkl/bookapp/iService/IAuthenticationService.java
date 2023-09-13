package net.kkl.bookapp.iService;

import lombok.RequiredArgsConstructor;
import net.kkl.bookapp.common.AuthenticationResponse;
import net.kkl.bookapp.common.dto.UserDTO;
import net.kkl.bookapp.entity.Role;
import net.kkl.bookapp.entity.Token;
import net.kkl.bookapp.entity.TokenType;
import net.kkl.bookapp.entity.User;
import net.kkl.bookapp.exception.ApplicationErrorException;
import net.kkl.bookapp.repository.TokenRepository;
import net.kkl.bookapp.repository.UserRepository;
import net.kkl.bookapp.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class IAuthenticationService implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    @Override
    public AuthenticationResponse registeredByUser(UserDTO userDTO) {
        var savedUser = mapUserRequestToUser(userDTO);
        var jwtToken = jwtService.generateToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(savedUser);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(UserDTO userDTO) {
        var jwtToken = "";
        var refreshToken ="";
        var user = userRepository.findByUsernameIgnoreCaseLike(userDTO.getUsername()).orElseThrow(
                ()->new ApplicationErrorException("User not found in system..."));
        if (!ObjectUtils.isEmpty(user) && !passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new ApplicationErrorException("Password is not match...");
        } else {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
            jwtToken = jwtService.generateToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
        }
    }

    private User mapUserRequestToUser(UserDTO userDTO) {
        var userExisting = userRepository.findByUsername(userDTO.getUsername());
        if(!ObjectUtils.isEmpty(userExisting))
            throw new ApplicationErrorException("fail...");
        return userRepository.save(User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(Role.USER)
                .build());
    }

    private void saveUserToken(User savedUser, String jwtToken) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
