package app.reservationsystem.clubs.mapper;

import app.reservationsystem.clubs.dto.FieldUpdateDTO;
import app.reservationsystem.clubs.entity.Field;
import app.reservationsystem.clubs.dto.FieldRequestDTO;
import app.reservationsystem.clubs.dto.FieldResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FieldMapper {

    @Mapping(source = "club.idClub", target = "idClub")
    @Mapping(source = "club.address", target = "address")
    FieldResponseDTO entityToDto(Field field);
    Field dtoToEntity(FieldRequestDTO fieldDTO);

    void updateFieldFromDto(FieldUpdateDTO dto, @MappingTarget Field field);

}
