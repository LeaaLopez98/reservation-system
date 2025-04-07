package app.reservationsystem.clubs.service.implementation;

import app.reservationsystem.clubs.entity.Club;
import app.reservationsystem.clubs.entity.Field;
import app.reservationsystem.clubs.exception.ClubNotFoundException;
import app.reservationsystem.clubs.exception.FieldNotFoundException;
import app.reservationsystem.clubs.repository.ClubRepository;
import app.reservationsystem.clubs.repository.FieldRepository;
import app.reservationsystem.clubs.dto.FieldRequestDTO;
import app.reservationsystem.clubs.dto.FieldResponseDTO;
import app.reservationsystem.clubs.service.FieldService;
import app.reservationsystem.shared.exception.UnauthorizedAccessException;
import app.reservationsystem.shared.util.ClaimsUtil;
import app.reservationsystem.clubs.mapper.FieldMapper;
import app.reservationsystem.shared.util.constants.ExceptionMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final ClubRepository clubRepository;

    private final FieldMapper fieldMapper;

    @Override
    public FieldResponseDTO addField(Integer idClub, FieldRequestDTO fieldRequest) {

        Long idUser = ClaimsUtil.getUserId();

        Club club = clubRepository.findById(idClub).orElseThrow(
                () -> new ClubNotFoundException(String.format(ExceptionMessages.CLUB_NOT_FOUND, idClub))
        );

        if (!club.getOwner().getIdUser().equals(idUser)) {
            throw new UnauthorizedAccessException(ExceptionMessages.CLUB_NOT_OWNER);
        }

        Field field = fieldMapper.dtoToEntity(fieldRequest);
        field.setClub(club);

        return fieldMapper.entityToDto(fieldRepository.save(field));
    }

    @Override
    public FieldResponseDTO getFieldById(Integer idField) {
        return fieldMapper.entityToDto(getFieldEntityById(idField));
    }

    @Override
    public List<FieldResponseDTO> getAllFields() {
        return fieldRepository.findAll()
                .stream()
                .map(fieldMapper::entityToDto)
                .toList();
    }

    @Override
    public List<FieldResponseDTO> getFieldByClub(Integer idClub) {

        boolean clubExists = clubRepository.existsById(idClub);

        if (!clubExists) {
            throw new ClubNotFoundException(String.format(ExceptionMessages.CLUB_NOT_FOUND, idClub));
        }

        return fieldRepository.findAllByClubIdClub(idClub)
                .stream()
                .map(fieldMapper::entityToDto)
                .toList();
    }

    @Override
    public FieldResponseDTO updateField(Integer idField, FieldRequestDTO fieldRequest) {
        return null;
    }

    @Override
    public void deleteField(Integer idField) {
        Long idUser = ClaimsUtil.getUserId();

        Field field = fieldRepository.findById(idField).orElseThrow(
                () -> new FieldNotFoundException(String.format(ExceptionMessages.FIELD_NOT_FOUND, idField))
        );

        if (!field.getClub().getOwner().getIdUser().equals(idUser)) {
            throw new UnauthorizedAccessException(ExceptionMessages.CLUB_NOT_OWNER);
        }

        fieldRepository.delete(field);
    }

    @Override
    public Field getFieldEntityById(Integer idField) {
        return fieldRepository.findById(idField).orElseThrow(
                () -> new FieldNotFoundException(String.format(ExceptionMessages.FIELD_NOT_FOUND, idField)));
    }
}
