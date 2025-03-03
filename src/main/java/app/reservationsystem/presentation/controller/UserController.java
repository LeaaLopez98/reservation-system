package app.reservationsystem.presentation.controller;

import app.reservationsystem.presentation.dto.*;
import app.reservationsystem.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
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
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {
        return userService.login(request);
    }

    @PostMapping("/register/player")
    public RegisterResponse registerPlayer(
            @RequestBody PlayerRegisterRequest registerRequest
    ) {
        return userService.registerPlayer(registerRequest);
    }

    @PostMapping("/register/owner")
    public RegisterResponse registerOwner(
            @RequestBody RegisterRequest registerRequest
    ) {
        return userService.registerOwner(registerRequest);
    }



}
