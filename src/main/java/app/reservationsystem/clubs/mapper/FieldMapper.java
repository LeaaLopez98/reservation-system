package app.reservationsystem.clubs.mapper;

import app.reservationsystem.clubs.entity.Field;
import app.reservationsystem.clubs.dto.FieldRequestDTO;
import app.reservationsystem.clubs.dto.FieldResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    @Mapping(source = "club.idClub", target = "idClub")
    @Mapping(source = "club.address", target = "address")
    FieldResponseDTO entityToDto(Field field);
    Field dtoToEntity(FieldRequestDTO fieldDTO);

}
