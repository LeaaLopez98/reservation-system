package app.reservationsystem.users.service;

import app.reservationsystem.users.dto.LoginRequest;
import app.reservationsystem.users.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
