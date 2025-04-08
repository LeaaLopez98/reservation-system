package app.reservationsystem.clubs.controller;

import app.reservationsystem.clubs.dto.ClubRequestDTO;
import app.reservationsystem.clubs.dto.ClubResponseDTO;
import app.reservationsystem.clubs.dto.ClubUpdateDTO;
import app.reservationsystem.clubs.service.ClubService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clubs")
@RequiredArgsConstructor
@RestController
public class ClubController {

    private final ClubService clubService;

    @PostMapping
    public ResponseEntity<ClubResponseDTO> addClub(
            @Valid @RequestBody ClubRequestDTO clubRequest
    ) {
        return ResponseEntity.ok(clubService.addClub(clubRequest));
    }

    @GetMapping("/{id-club}")
    public ResponseEntity<ClubResponseDTO> getClubById(
            @PathVariable(name = "id-club" ) Integer idClub
    ) {
        return ResponseEntity.ok(clubService.getClubById(idClub));
    }

    @GetMapping
    public ResponseEntity<List<ClubResponseDTO>> getAllClubs() {

        List<ClubResponseDTO> clubs = clubService.getAllClubs();

        if (clubs.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(clubs);
    }

    @GetMapping("/me")
    public ResponseEntity<List<ClubResponseDTO>> getClubByOwner(
    ) {
        List<ClubResponseDTO> clubs = clubService.getMyClubs();

        if (clubs.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(clubs);
    }

    @PutMapping("/{id-club}")
    public ResponseEntity<ClubResponseDTO> updateClub(
            @PathVariable(name = "id-club") Integer idClub,
            @Valid @RequestBody ClubUpdateDTO clubUpdateDTO
    ) {
        return ResponseEntity.ok(clubService.updateClub(idClub, clubUpdateDTO));
    }

    @DeleteMapping("/{id-club}")
    public ResponseEntity<Void> deleteClub(
            @PathVariable(name = "id-club") Integer idClub
    ) {
        clubService.deleteClub(idClub);
        return ResponseEntity.noContent().build();
    }

}
