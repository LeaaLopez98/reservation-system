package app.reservationsystem.services.implementation;

import app.reservationsystem.persistence.entity.Owner;
import app.reservationsystem.persistence.entity.Player;
import app.reservationsystem.persistence.entity.Role;
import app.reservationsystem.persistence.entity.UserAccount;
import app.reservationsystem.persistence.repository.OwnerRepository;
import app.reservationsystem.persistence.repository.PlayerRepository;
import app.reservationsystem.persistence.repository.UserRepository;
import app.reservationsystem.presentation.dto.auth.*;
import app.reservationsystem.services.interfaces.JwtService;
import app.reservationsystem.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        } catch (Exception e) {
            throw new RuntimeException("Username or password incorrect");
        }
    }

    @Override
    public RegisterResponse registerPlayer(PlayerRegisterRequest registerRequest) {

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
