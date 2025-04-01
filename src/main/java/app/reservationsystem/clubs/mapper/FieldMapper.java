package app.reservationsystem.util.mapper;

import app.reservationsystem.persistence.entity.Field;
import app.reservationsystem.presentation.dto.fields.FieldRequestDTO;
import app.reservationsystem.presentation.dto.fields.FieldResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    @Mapping(source = "club.idClub", target = "idClub")
    @Mapping(source = "club.address", target = "address")
    FieldResponseDTO entityToDto(Field field);
    Field dtoToEntity(FieldRequestDTO fieldDTO);

}
