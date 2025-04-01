package app.reservationsystem.util.mapper;

import app.reservationsystem.persistence.entity.Reservation;
import app.reservationsystem.presentation.dto.reservations.ReservationRequestDTO;
import app.reservationsystem.presentation.dto.reservations.ReservationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation dtoToEntity(ReservationRequestDTO reservationRequestDTO);

    @Mapping(target = "clubName", source = "field.club.name")
    @Mapping(target = "address", source = "field.club.address")
    @Mapping(target = "playerName", source = "player.name")
    @Mapping(target = "fieldNumber", source = "field.fieldNumber")
    ReservationResponseDTO entityToDto(Reservation reservation);

}
