package app.reservationsystem.reservation.mapper;

import app.reservationsystem.reservation.entity.Reservation;
import app.reservationsystem.reservation.dto.ReservationRequestDTO;
import app.reservationsystem.reservation.dto.ReservationResponseDTO;
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
