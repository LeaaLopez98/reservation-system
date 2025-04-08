package app.reservationsystem.reservations.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequestDTO {

    @NotBlank(message = "Field id is required")
    @NotNull(message = "Field id is required")
    private Integer idField;

    @NotNull(message = "Date begin is required")
    @NotBlank(message = "Date begin is required")
    @Future(message = "Date begin must be in the future")
    private LocalDateTime dateBegin;

    @NotNull(message = "Date end is required")
    @NotBlank(message = "Date end is required")
    @Future(message = "Date end must be in the future")
    private LocalDateTime dateEnd;

}
