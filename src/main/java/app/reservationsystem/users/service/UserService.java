package app.reservationsystem.users.service;

import app.reservationsystem.users.dto.*;

public interface UserService {

    LoginResponse login(LoginRequest request);
    RegisterResponse registerPlayer(PlayerRegisterRequest registerRequest);
    RegisterResponse registerOwner(RegisterRequest registerRequest);

    void forgotPassword(ForgotPasswordRequest request);
    void resetPassword(ResetPasswordRequest request);

}
