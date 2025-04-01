package app.reservationsystem.clubs.mapper;

import app.reservationsystem.clubs.entity.Club;
import app.reservationsystem.clubs.dto.ClubRequestDTO;
import app.reservationsystem.clubs.dto.ClubResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = FieldMapper.class)
public interface ClubMapper {


    Club DtoToEntity(ClubRequestDTO clubDTO);
    ClubResponseDTO entityToDto(Club club);

}
