package app.reservationsystem.users.service.impl;

import app.reservationsystem.emails.builder.ConfirmRegistrationBuilder;
import app.reservationsystem.emails.service.EmailService;
import app.reservationsystem.shared.util.constants.ExceptionMessages;
import app.reservationsystem.users.dto.PlayerRegisterRequest;
import app.reservationsystem.users.dto.RegisterRequest;
import app.reservationsystem.users.dto.RegisterResponse;
import app.reservationsystem.users.entity.Owner;
import app.reservationsystem.users.entity.Player;
import app.reservationsystem.users.entity.Role;
import app.reservationsystem.users.entity.UserAccount;
import app.reservationsystem.users.exception.EmailAlreadyExistsException;
import app.reservationsystem.users.exception.UsernameAlreadyExistsException;
import app.reservationsystem.users.repository.OwnerRepository;
import app.reservationsystem.users.repository.PlayerRepository;
import app.reservationsystem.users.repository.UserRepository;
import app.reservationsystem.users.service.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserServiceImpl implements RegisterUserService {

    private final PasswordEncoder encoder;
    private final EmailService emailService;

    private final PlayerRepository playerRepository;
    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;

    @Override
    public RegisterResponse registerPlayer(PlayerRegisterRequest registerRequest) {
        validateUserCredentials(registerRequest.getUsername(), registerRequest.getEmail());

        Player player = Player.builder()
                .username(registerRequest.getUsername())
                .password(encoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .role(Role.PLAYER)
                .build();

        Player savedPlayer = playerRepository.save(player);

        emailService.sendEmail(new ConfirmRegistrationBuilder(player));

        return new RegisterResponse(savedPlayer.getUsername());
    }

    @Override
    public RegisterResponse registerOwner(RegisterRequest registerRequest) {
        validateUserCredentials(registerRequest.getUsername(), registerRequest.getEmail());

        Owner owner = Owner.builder()
                .username(registerRequest.getUsername())
                .password(encoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(Role.OWNER)
                .build();

        Owner savedOwner = ownerRepository.save(owner);

        return new RegisterResponse(savedOwner.getUsername());
    }

    private void validateUserCredentials(String username, String email) {
        if (!userRepository.existsByUsername(username)){
            throw new UsernameAlreadyExistsException(ExceptionMessages.USERNAME_EXISTS);
        }

        if (!userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(ExceptionMessages.EMAIL_EXISTS);
        }
    }
}
