package app.reservationsystem.clubs.mapper;

import app.reservationsystem.clubs.dto.ClubUpdateDTO;
import app.reservationsystem.clubs.entity.Club;
import app.reservationsystem.clubs.dto.ClubRequestDTO;
import app.reservationsystem.clubs.dto.ClubResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = FieldMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClubMapper {


    Club DtoToEntity(ClubRequestDTO clubDTO);
    ClubResponseDTO entityToDto(Club club);

    void updateClubFromDto(ClubUpdateDTO dto, @MappingTarget Club club);

}
