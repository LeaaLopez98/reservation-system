package app.reservationsystem.clubs.service;

import app.reservationsystem.clubs.dto.ClubRequestDTO;
import app.reservationsystem.clubs.dto.ClubResponseDTO;
import app.reservationsystem.clubs.dto.ClubUpdateDTO;

import java.util.List;

public interface ClubService {

    ClubResponseDTO addClub(ClubRequestDTO clubRequestDTO);
    ClubResponseDTO getClubById(Integer idClub);


    List<ClubResponseDTO> getAllClubs();
    List<ClubResponseDTO> getMyClubs();

    ClubResponseDTO updateClub(Integer idClub, ClubUpdateDTO clubUpdateDTO);
    void deleteClub(Integer idClub);


}
