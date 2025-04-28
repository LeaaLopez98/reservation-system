package app.reservationsystem.users.service;

import app.reservationsystem.users.dto.ForgotPasswordRequest;
import app.reservationsystem.users.dto.ResetPasswordRequest;

public interface RecoveryPasswordService {
    void forgotPassword(ForgotPasswordRequest request);
    void resetPassword(ResetPasswordRequest request);
}
