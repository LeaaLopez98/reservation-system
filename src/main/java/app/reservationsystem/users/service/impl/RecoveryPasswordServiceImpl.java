package app.reservationsystem.users.service.impl;

import app.reservationsystem.emails.builder.RecoveryPasswordBuilder;
import app.reservationsystem.emails.service.EmailService;
import app.reservationsystem.shared.util.constants.ExceptionMessages;
import app.reservationsystem.users.dto.ForgotPasswordRequest;
import app.reservationsystem.users.dto.ResetPasswordRequest;
import app.reservationsystem.users.entity.UserAccount;
import app.reservationsystem.users.exception.EmailNotFoundException;
import app.reservationsystem.users.exception.InvalidOrExpiredTokenException;
import app.reservationsystem.users.repository.UserRepository;
import app.reservationsystem.users.service.RecoveryPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecoveryPasswordServiceImpl implements RecoveryPasswordService {

    @Value("${recovery-password.expiration-time}")
    private Long EXPIRATION_TIME;

    private final UserRepository userRepository;
    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    private final StringRedisTemplate redisTemplate;

    private String createToken(String email) {
        String token = UUID.randomUUID().toString();
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(token, email, Duration.ofMinutes(EXPIRATION_TIME));

        return token;
    }

    private String getEmailFromToken(String token) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.get(token);
    }

    private void removeToken(String token) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.getOperations().delete(token);
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {

        boolean exists = userRepository.existsByEmail(request.email());

        if (!exists) {
            throw new EmailNotFoundException(ExceptionMessages.EMAIL_NOT_FOUND);
        }

        String token = createToken(request.email());
        emailService.sendEmail(new RecoveryPasswordBuilder(request.email(), token));
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {

        String email = getEmailFromToken(request.token());

        if (email == null) {
            throw new InvalidOrExpiredTokenException(ExceptionMessages.INVALID_OR_EXPIRED_TOKEN);
        }

        UserAccount userAccount = userRepository.findByEmail(email).orElseThrow(
                () -> new EmailNotFoundException(ExceptionMessages.EMAIL_NOT_FOUND)
        );

        userAccount.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(userAccount);

        removeToken(request.token());
    }
}
