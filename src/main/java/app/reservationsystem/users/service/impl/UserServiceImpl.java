package app.reservationsystem.users.service.impl;

import app.reservationsystem.emails.builder.ConfirmRegistrationBuilder;
import app.reservationsystem.emails.service.EmailService;
import app.reservationsystem.users.dto.*;
import app.reservationsystem.users.entity.Owner;
import app.reservationsystem.users.entity.Player;
import app.reservationsystem.users.entity.Role;
import app.reservationsystem.users.entity.UserAccount;
import app.reservationsystem.users.exception.EmailAlreadyExistsException;
import app.reservationsystem.users.exception.UsernameAlreadyExistsException;
import app.reservationsystem.users.repository.OwnerRepository;
import app.reservationsystem.users.repository.PlayerRepository;
import app.reservationsystem.users.repository.UserRepository;
import app.reservationsystem.users.service.*;
import app.reservationsystem.shared.util.constants.ExceptionMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthService authService;
    private final RegisterUserService registerService;

    private final RecoveryPasswordService recoveryPasswordService;

    @Override
    public LoginResponse login(LoginRequest request) {
        return authService.login(request);
    }

    @Override
    public RegisterResponse registerPlayer(PlayerRegisterRequest registerRequest) {

        return registerService.registerPlayer(registerRequest);
    }

    @Override
    public RegisterResponse registerOwner(RegisterRequest registerRequest) {
        return registerService.registerOwner(registerRequest);
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {
        recoveryPasswordService.forgotPassword(request);
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        recoveryPasswordService.resetPassword(request);
    }
}
