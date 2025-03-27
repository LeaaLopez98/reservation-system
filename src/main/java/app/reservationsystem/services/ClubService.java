package app.reservationsystem.services;

import app.reservationsystem.presentation.dto.clubs.ClubRequestDTO;
import app.reservationsystem.presentation.dto.clubs.ClubResponseDTO;
import app.reservationsystem.presentation.dto.clubs.ClubUpdateDTO;

import java.util.List;

public interface ClubService {

    ClubResponseDTO addClub(ClubRequestDTO clubRequestDTO);
    ClubResponseDTO getClubById(Integer idClub);


    List<ClubResponseDTO> getAllClubs();
    List<ClubResponseDTO> getMyClubs();

    ClubResponseDTO updateClub(Integer idClub, ClubUpdateDTO clubUpdateDTO);
    void deleteClub(Integer idClub);


}
