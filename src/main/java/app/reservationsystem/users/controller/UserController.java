package app.reservationsystem.auth.controller;

import app.reservationsystem.auth.dto.*;
import app.reservationsystem.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/register/player")
    public ResponseEntity<RegisterResponse> registerPlayer(
            @RequestBody PlayerRegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(userService.registerPlayer(registerRequest));
    }

    @PostMapping("/register/owner")
    public ResponseEntity<RegisterResponse> registerOwner(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(userService.registerOwner(registerRequest));
    }



}
