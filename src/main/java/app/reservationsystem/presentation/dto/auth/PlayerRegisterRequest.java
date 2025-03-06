package app.reservationsystem.presentation.dto.auth;

import lombok.Getter;

@Getter
public class PlayerRegisterRequest extends RegisterRequest {
    private String name;
}
