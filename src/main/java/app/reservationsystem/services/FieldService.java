package app.reservationsystem.services;

import app.reservationsystem.presentation.dto.fields.FieldRequestDTO;
import app.reservationsystem.presentation.dto.fields.FieldResponseDTO;

import java.util.List;

public interface FieldService {

    FieldResponseDTO addField(Integer idClub, FieldRequestDTO fieldRequest);
    FieldResponseDTO getFieldById(Integer idField);

    List<FieldResponseDTO> getAllFields();
    List<FieldResponseDTO> getFieldByClub(Integer idClub);

    FieldResponseDTO updateField(Integer idField, FieldRequestDTO fieldRequest);
    void deleteField(Integer idField);



}
