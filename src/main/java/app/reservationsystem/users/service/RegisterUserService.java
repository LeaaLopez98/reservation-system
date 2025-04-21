package app.reservationsystem.users.service;

import app.reservationsystem.users.dto.PlayerRegisterRequest;
import app.reservationsystem.users.dto.RegisterRequest;
import app.reservationsystem.users.dto.RegisterResponse;

public interface RegisterUserService {

    RegisterResponse registerPlayer(PlayerRegisterRequest registerRequest);
    RegisterResponse registerOwner(RegisterRequest registerRequest);

}
