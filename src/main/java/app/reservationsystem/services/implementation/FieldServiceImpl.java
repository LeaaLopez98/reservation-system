package app.reservationsystem.services.implementation;

import app.reservationsystem.persistence.entity.Club;
import app.reservationsystem.persistence.entity.Field;
import app.reservationsystem.persistence.repository.ClubRepository;
import app.reservationsystem.persistence.repository.FieldRepository;
import app.reservationsystem.presentation.dto.fields.FieldRequestDTO;
import app.reservationsystem.presentation.dto.fields.FieldResponseDTO;
import app.reservationsystem.services.interfaces.FieldService;
import app.reservationsystem.services.interfaces.JwtService;
import app.reservationsystem.util.mapper.FieldMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FieldServiceImpl implements FieldService {

    private final JwtService jwtService;

    private final FieldRepository fieldRepository;
    private final ClubRepository clubRepository;

    private final FieldMapper fieldMapper;

    @Override
    public FieldResponseDTO addField(Integer idClub, FieldRequestDTO fieldRequest, String token) {

        Long idUser = jwtService.extractIdUser(token.substring(7));

        Club club = clubRepository.findById(idClub).orElseThrow(
                () -> new RuntimeException(String.format("Club with id %s, Not found", idClub))
        );

        if (!club.getOwner().getIdUser().equals(idUser)) {
            throw new RuntimeException("You are not the owner of this club");
        }

        Field field = fieldMapper.dtoToEntity(fieldRequest);
        field.setClub(club);

        return fieldMapper.entityToDto(fieldRepository.save(field));
    }

    @Override
    public FieldResponseDTO getFieldById(Integer idField) {
        return fieldMapper.entityToDto(fieldRepository.findById(idField).orElseThrow(
                () -> new RuntimeException(String.format("Field with id %s, Not found", idField)))
        );
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
    public void deleteField(Integer idField, String token) {
        Long idUser = jwtService.extractIdUser(token.substring(7));

        Field field = fieldRepository.findById(idField).orElseThrow(
                () -> new RuntimeException(String.format("Field with id %s, Not found", idField))
        );

        if (!field.getClub().getOwner().getIdUser().equals(idUser)) {
            throw new RuntimeException("You are not the owner of this field");
        }

        fieldRepository.delete(field);

    }
}
