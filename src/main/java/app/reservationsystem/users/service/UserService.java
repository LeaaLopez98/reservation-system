package app.reservationsystem.auth.service;

import app.reservationsystem.auth.dto.*;

public interface UserService {

    LoginResponse login(LoginRequest request);
    RegisterResponse registerPlayer(PlayerRegisterRequest registerRequest);
    RegisterResponse registerOwner(RegisterRequest registerRequest);

}
