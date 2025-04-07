package app.reservationsystem.auth.dto;

import lombok.Getter;

@Getter
public class PlayerRegisterRequest extends RegisterRequest {
    private String name;
}
