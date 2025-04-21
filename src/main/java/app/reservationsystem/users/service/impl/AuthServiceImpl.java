package app.reservationsystem.users.service.impl;

import app.reservationsystem.shared.util.constants.ExceptionMessages;
import app.reservationsystem.users.dto.LoginRequest;
import app.reservationsystem.users.dto.LoginResponse;
import app.reservationsystem.users.entity.UserAccount;
import app.reservationsystem.users.repository.UserRepository;
import app.reservationsystem.users.service.AuthService;
import app.reservationsystem.users.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserAccount user = userRepository.findByUsername(request.getUsername()).get();

            String token = jwtService.generateToken(user);

            return new LoginResponse(token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(ExceptionMessages.BAD_CREDENTIALS);
        }
    }
}
