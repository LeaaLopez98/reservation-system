package app.reservationsystem.users.dto;

import lombok.Getter;

@Getter
public class PlayerRegisterRequest extends RegisterRequest {
    private String name;
}
