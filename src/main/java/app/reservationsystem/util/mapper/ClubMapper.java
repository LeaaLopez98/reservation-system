package app.reservationsystem.util.mapper;

import app.reservationsystem.persistence.entity.Club;
import app.reservationsystem.presentation.dto.clubs.ClubRequestDTO;
import app.reservationsystem.presentation.dto.clubs.ClubResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClubMapper {

    Club DtoToEntity(ClubRequestDTO clubDTO);
    ClubResponseDTO entityToDto(Club club);

}
