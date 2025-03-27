package app.reservationsystem.services.interfaces;

import app.reservationsystem.presentation.dto.clubs.ClubRequestDTO;
import app.reservationsystem.presentation.dto.clubs.ClubResponseDTO;
import app.reservationsystem.presentation.dto.clubs.ClubUpdateDTO;

import java.util.List;

public interface ClubService {

    ClubResponseDTO addClub(ClubRequestDTO clubRequestDTO, String token);
    ClubResponseDTO getClubById(Integer idClub);


    List<ClubResponseDTO> getAllClubs();
    List<ClubResponseDTO> getMyClubs(String token);

    ClubResponseDTO updateClub(Integer idClub, ClubUpdateDTO clubUpdateDTO, String token);
    void deleteClub(Integer idClub, String token);


}
