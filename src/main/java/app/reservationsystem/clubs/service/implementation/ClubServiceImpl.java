package app.reservationsystem.clubs.service.implementation;

import app.reservationsystem.clubs.entity.Club;
import app.reservationsystem.users.entity.Owner;
import app.reservationsystem.clubs.exception.ClubNotFoundException;
import app.reservationsystem.clubs.repository.ClubRepository;
import app.reservationsystem.users.repository.OwnerRepository;
import app.reservationsystem.clubs.dto.ClubRequestDTO;
import app.reservationsystem.clubs.dto.ClubResponseDTO;
import app.reservationsystem.clubs.dto.ClubUpdateDTO;
import app.reservationsystem.clubs.service.ClubService;
import app.reservationsystem.shared.exception.UnauthorizedAccessException;
import app.reservationsystem.shared.util.ClaimsUtil;
import app.reservationsystem.clubs.mapper.ClubMapper;
import app.reservationsystem.shared.util.constants.ExceptionMessages;
import app.reservationsystem.users.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final OwnerService ownerService;

    private final ClubMapper clubMapper;

    @Override
    public ClubResponseDTO addClub(ClubRequestDTO clubRequest) {
        Long idOwner = ClaimsUtil.getUserId();

        Owner owner = ownerService.getOwnerEntityById(idOwner);

        Club club = clubMapper.DtoToEntity(clubRequest);
        club.setOwner(owner);

        Club savedClub = clubRepository.save(club);

        return clubMapper.entityToDto(savedClub);
    }

    @Override
    public ClubResponseDTO getClubById(Integer idClub) {

        Club club = getClubEntityById(idClub);

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
    public List<ClubResponseDTO> getMyClubs() {

        Long idOwner = ClaimsUtil.getUserId();

        return clubRepository.findAllByOwnerIdUser(idOwner)
                .stream()
                .map(clubMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClubResponseDTO updateClub(Integer idClub, ClubUpdateDTO clubUpdateDTO) {
        //TODO
        return null;
    }

    @Override
    public void deleteClub(Integer idClub) {

        Long idOwner = ClaimsUtil.getUserId();

        Club club = clubRepository.findById(idClub).orElseThrow(
                () -> new ClubNotFoundException(String.format(ExceptionMessages.CLUB_NOT_FOUND, idClub))
        );

        if (!club.getOwner().getIdUser().equals(idOwner)) {
            throw new UnauthorizedAccessException(ExceptionMessages.CLUB_NOT_OWNER);
        }

        clubRepository.delete(club);
    }

    @Override
    public Club getClubEntityById(Integer idClub) {
        return clubRepository.findById(idClub).orElseThrow(
                () -> new ClubNotFoundException(String.format(ExceptionMessages.CLUB_NOT_FOUND, idClub))
        );
    }
}
