package app.reservationsystem.services.implementation;

import app.reservationsystem.persistence.entity.Club;
import app.reservationsystem.persistence.entity.Owner;
import app.reservationsystem.persistence.repository.ClubRepository;
import app.reservationsystem.persistence.repository.OwnerRepository;
import app.reservationsystem.presentation.dto.clubs.ClubRequestDTO;
import app.reservationsystem.presentation.dto.clubs.ClubResponseDTO;
import app.reservationsystem.presentation.dto.clubs.ClubUpdateDTO;
import app.reservationsystem.services.interfaces.ClubService;
import app.reservationsystem.services.interfaces.JwtService;
import app.reservationsystem.util.mapper.ClubMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final OwnerRepository ownerRepository;

    private final JwtService jwtService;

    private final ClubMapper clubMapper;

    @Override
    public ClubResponseDTO addClub(ClubRequestDTO clubRequest, String token) {
        Long idOwner = jwtService.extractIdUser(token.substring(7));

        Owner owner = ownerRepository.findById(idOwner).orElseThrow(
                () -> new RuntimeException(String.format("Owner with id %s, Not found", idOwner))
        );

        Club club = clubMapper.DtoToEntity(clubRequest);
        club.setOwner(owner);

        Club savedClub = clubRepository.save(club);

        return clubMapper.entityToDto(savedClub);
    }

    @Override
    public ClubResponseDTO getClubById(Integer idClub) {

        Club club = clubRepository.findById(idClub).orElseThrow(
                () -> new RuntimeException(String.format("Club with id %s, Not found", idClub)));

        return clubMapper.entityToDto(club);
    }

    @Override
    public List<ClubResponseDTO> getAllClubs() {
        return clubRepository.findAll()
                .stream()
                .map(clubMapper::entityToDto)
                .toList();
    }

    @Override
    public List<ClubResponseDTO> getMyClubs(String token) {

        Long idOwner = jwtService.extractIdUser(token.substring(7));

        return clubRepository.findAllByOwnerIdUser(idOwner)
                .stream()
                .map(clubMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClubResponseDTO updateClub(Integer idClub, ClubUpdateDTO clubUpdateDTO, String token) {
        //TODO
        return null;
    }

    @Override
    public void deleteClub(Integer idClub, String token) {

        Long idOwner = jwtService.extractIdUser(token.substring(7));

        Club club = clubRepository.findById(idClub).orElseThrow(
                () -> new RuntimeException(String.format("Club with id %s, Not found", idClub))
        );

        if (!club.getOwner().getIdUser().equals(idOwner)) {
            throw new RuntimeException("You are not the owner of this club");
        }

        clubRepository.delete(club);
    }
}
