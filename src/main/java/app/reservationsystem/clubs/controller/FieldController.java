package app.reservationsystem.clubs.controller;

import app.reservationsystem.clubs.dto.FieldRequestDTO;
import app.reservationsystem.clubs.dto.FieldResponseDTO;
import app.reservationsystem.clubs.dto.FieldUpdateDTO;
import app.reservationsystem.clubs.service.FieldService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FieldController {

    private final FieldService fieldService;

    @PostMapping("/clubs/{id-club}/fields")
    public ResponseEntity<FieldResponseDTO> addField(
            @PathVariable (name = "id-club") Integer idClub,
            @Valid @RequestBody FieldRequestDTO fieldRequest
    ) {
        return ResponseEntity.ok(fieldService.addField(idClub, fieldRequest));
    }

    @GetMapping("/fields/{id-field}")
    public ResponseEntity<FieldResponseDTO> getFieldById(
            @PathVariable (name = "id-field") Integer idField
    ) {
        return ResponseEntity.ok(fieldService.getFieldById(idField));
    }

    @GetMapping("/fields")
    public ResponseEntity<List<FieldResponseDTO>> getAllFields() {

        List<FieldResponseDTO> fields = fieldService.getAllFields();

        return !fields.isEmpty()
                ? ResponseEntity.ok(fieldService.getAllFields())
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/clubs/{id-club}/fields")
    public ResponseEntity<List<FieldResponseDTO>> getFieldByClub(
            @PathVariable (name = "id-club") Integer idClub
    ) {

        List<FieldResponseDTO> fields = fieldService.getFieldByClub(idClub);

        return !fields.isEmpty()
                ? ResponseEntity.ok(fields)
                : ResponseEntity.noContent().build();
    }

    @PutMapping("/fields/{id-field}")
    public ResponseEntity<FieldResponseDTO> updateField(
            @PathVariable (name = "id-field") Integer idField,
            @Valid @RequestBody FieldUpdateDTO fieldRequest
    ) {
        return ResponseEntity.ok(fieldService.updateField(idField, fieldRequest));
    }

    @DeleteMapping("/fields/{id-field}")
    public ResponseEntity<Void> deleteField(
            @PathVariable (name = "id-field") Integer idField
    ) {
        fieldService.deleteField(idField);
        return ResponseEntity.noContent().build();
    }

}
