package app.reservationsystem.presentation.controller;

import app.reservationsystem.presentation.dto.clubs.ClubRequestDTO;
import app.reservationsystem.presentation.dto.clubs.ClubResponseDTO;
import app.reservationsystem.presentation.dto.clubs.ClubUpdateDTO;
import app.reservationsystem.services.interfaces.ClubService;
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
            @RequestBody ClubRequestDTO clubRequest,
            @RequestHeader(name = "Authorization") String token
    ) {
        return ResponseEntity.ok(clubService.addClub(clubRequest, token));
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
            @RequestHeader(name = "Authorization") String token
    ) {
        List<ClubResponseDTO> clubs = clubService.getMyClubs(token);

        if (clubs.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(clubs);
    }

    @PutMapping("/{id-club}")
    public ResponseEntity<ClubResponseDTO> updateClub(
            @PathVariable(name = "id-club") Integer idClub,
            @RequestHeader(name = "Authorization") String token,
            @RequestBody ClubUpdateDTO clubUpdateDTO
    ) {
        return ResponseEntity.ok(clubService.updateClub(idClub, clubUpdateDTO, token));
    }

    @DeleteMapping("/{id-club}")
    public ResponseEntity<Void> deleteClub(
            @PathVariable(name = "id-club") Integer idClub,
            @RequestHeader(name = "Authorization") String token
    ) {
        clubService.deleteClub(idClub, token);
        return ResponseEntity.noContent().build();
    }

}
