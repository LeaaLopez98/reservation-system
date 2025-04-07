package app.reservationsystem.users.service.impl;

import app.reservationsystem.shared.util.constants.ExceptionMessages;
import app.reservationsystem.users.entity.Owner;
import app.reservationsystem.users.exception.OwnerNotFoundException;
import app.reservationsystem.users.repository.OwnerRepository;
import app.reservationsystem.users.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public Owner getOwnerEntityById(Long idOwner) {
        return ownerRepository.findById(idOwner).orElseThrow(
                () -> new OwnerNotFoundException(String.format(ExceptionMessages.OWNER_NOT_FOUND, idOwner))
        );
    }
}
