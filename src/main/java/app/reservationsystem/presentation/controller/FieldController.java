package app.reservationsystem.presentation.controller;

import app.reservationsystem.presentation.dto.fields.FieldRequestDTO;
import app.reservationsystem.presentation.dto.fields.FieldResponseDTO;
import app.reservationsystem.services.interfaces.FieldService;
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
            @RequestBody FieldRequestDTO fieldRequest,
            @RequestHeader(name = "Authorization") String token
    ) {
        return ResponseEntity.ok(fieldService.addField(idClub, fieldRequest, token));
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

    @DeleteMapping("/fields/{id-field}")
    public ResponseEntity<Void> deleteField(
            @PathVariable (name = "id-field") Integer idField,
            @RequestHeader(name = "Authorization") String token
    ) {
        fieldService.deleteField(idField, token);
        return ResponseEntity.noContent().build();
    }

}
