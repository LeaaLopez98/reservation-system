package app.reservationsystem.services.interfaces;

import app.reservationsystem.presentation.dto.auth.*;

public interface UserService {

    LoginResponse login(LoginRequest request);
    RegisterResponse registerPlayer(PlayerRegisterRequest registerRequest);
    RegisterResponse registerOwner(RegisterRequest registerRequest);

}
