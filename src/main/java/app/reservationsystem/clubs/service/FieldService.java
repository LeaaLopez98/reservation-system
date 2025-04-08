package app.reservationsystem.clubs.service;

import app.reservationsystem.clubs.dto.FieldRequestDTO;
import app.reservationsystem.clubs.dto.FieldResponseDTO;
import app.reservationsystem.clubs.dto.FieldUpdateDTO;
import app.reservationsystem.clubs.entity.Field;

import java.util.List;

public interface FieldService {

    FieldResponseDTO addField(Integer idClub, FieldRequestDTO fieldRequest);
    FieldResponseDTO getFieldById(Integer idField);

    List<FieldResponseDTO> getAllFields();
    List<FieldResponseDTO> getFieldByClub(Integer idClub);

    FieldResponseDTO updateField(Integer idField, FieldUpdateDTO fieldRequest);
    void deleteField(Integer idField);

    Field getFieldEntityById(Integer idField);

}
