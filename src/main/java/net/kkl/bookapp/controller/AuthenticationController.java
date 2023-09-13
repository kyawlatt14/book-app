package net.kkl.bookapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.kkl.bookapp.common.AuthenticationResponse;
import net.kkl.bookapp.common.dto.UserDTO;
import net.kkl.bookapp.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid UserDTO userDTO){
        return ResponseEntity.ok(authenticationService.registeredByUser(userDTO));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login (@RequestBody @Valid UserDTO userDTO){
        return ResponseEntity.ok(authenticationService.login(userDTO));
    }
}
