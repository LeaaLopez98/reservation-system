package app.reservationsystem.services.implementation;

import app.reservationsystem.persistence.entity.Owner;
import app.reservationsystem.persistence.entity.Player;
import app.reservationsystem.persistence.entity.Role;
import app.reservationsystem.persistence.repository.OwnerRepository;
import app.reservationsystem.persistence.repository.PlayerRepository;
import app.reservationsystem.presentation.dto.*;
import app.reservationsystem.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PlayerRepository playerRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        return null;
    }

    @Override
    public RegisterResponse registerPlayer(PlayerRegisterRequest registerRequest) {

        Player player = Player.builder()
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
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
                .password(registerRequest.getPassword())
                .email(registerRequest.getEmail())
                .role(Role.OWNER)
                .build();

        Owner savedOwner = ownerRepository.save(owner);

        return new RegisterResponse(savedOwner.getUsername());
    }
}
