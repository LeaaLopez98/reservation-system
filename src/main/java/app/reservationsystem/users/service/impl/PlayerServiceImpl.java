package app.reservationsystem.users.service.impl;

import app.reservationsystem.shared.util.constants.ExceptionMessages;
import app.reservationsystem.users.entity.Player;
import app.reservationsystem.users.exception.PlayerNotFoundException;
import app.reservationsystem.users.repository.PlayerRepository;
import app.reservationsystem.users.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player getPlayerEntityById(Long idPlayer) {
        return playerRepository.findById(idPlayer).orElseThrow(
                () -> new PlayerNotFoundException(String.format(ExceptionMessages.PLAYER_NOT_FOUND, idPlayer))
        );
    }
}
