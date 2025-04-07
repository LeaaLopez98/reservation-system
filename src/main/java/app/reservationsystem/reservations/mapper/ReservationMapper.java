package app.reservationsystem.reservations.mapper;

import app.reservationsystem.reservations.entity.Reservation;
import app.reservationsystem.reservations.dto.ReservationRequestDTO;
import app.reservationsystem.reservations.dto.ReservationResponseDTO;
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
