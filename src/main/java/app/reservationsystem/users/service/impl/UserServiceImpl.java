package app.reservationsystem.users.service.impl;

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
import app.reservationsystem.users.service.JwtService;
import app.reservationsystem.users.service.UserService;
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

    private final PasswordEncoder encoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserAccount user = userRepository.findByUsername(request.getUsername()).get();

            String token = jwtService.generateToken(user);

            return new LoginResponse(token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(ExceptionMessages.BAD_CREDENTIALS);
        }
    }

    @Override
    public RegisterResponse registerPlayer(PlayerRegisterRequest registerRequest) {

        if (playerRepository.existsByUsername(registerRequest.getUsername())) {
            throw new UsernameAlreadyExistsException(ExceptionMessages.USERNAME_EXISTS);
        }

        if (playerRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsException(ExceptionMessages.EMAIL_EXISTS);
        }

        Player player = Player.builder()
                .username(registerRequest.getUsername())
                .password(encoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .role(Role.PLAYER)
                .build();

        Player savedPlayer = playerRepository.save(player);

        return new RegisterResponse(savedPlayer.getUsername());
    }

    @Override
    public RegisterResponse registerOwner(RegisterRequest registerRequest) {

        if (ownerRepository.existsByUsername(registerRequest.getUsername())) {
            throw new UsernameAlreadyExistsException(ExceptionMessages.USERNAME_EXISTS);
        }

        if (ownerRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsException(ExceptionMessages.EMAIL_EXISTS);
        }

        Owner owner = Owner.builder()
                .username(registerRequest.getUsername())
                .password(encoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(Role.OWNER)
                .build();

        Owner savedOwner = ownerRepository.save(owner);

        return new RegisterResponse(savedOwner.getUsername());
    }
}
