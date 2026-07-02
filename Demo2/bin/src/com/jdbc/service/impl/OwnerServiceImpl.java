package com.jdbc.service.impl;

import com.jdbc.dto.OwnerDTO;
import com.jdbc.exception.DuplicateOwnerException;
import com.jdbc.exception.OwnerNotFoundException;
import com.jdbc.repository.OwnerRepository;
import com.jdbc.repository.impl.OwnerRepositoryImpl;
import com.jdbc.service.OwnerService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class OwnerServiceImpl implements OwnerService {

	private static final String OWNER_ALREADY_EXISTS = "Owner already exists with ID : ";
	private static final String OWNER_NOT_FOUND = "Owner not found with ID : ";

	private final OwnerRepository ownerRepository;

	public OwnerServiceImpl() {
		ownerRepository = new OwnerRepositoryImpl();
	}

	@Override
	public void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerException {

		OwnerDTO owner = ownerRepository.findOwner(ownerDTO.getId());

		if (Objects.nonNull(owner)) {
			throw new DuplicateOwnerException(OWNER_ALREADY_EXISTS + ownerDTO.getId());
		}

		ownerRepository.saveOwner(ownerDTO);
	}

	@Override
	public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {

		OwnerDTO owner = ownerRepository.findOwner(ownerId);

		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(OWNER_NOT_FOUND + ownerId);
		}

		return owner;
	}

	@Override
	public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {

		OwnerDTO owner = ownerRepository.findOwner(ownerId);

		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(OWNER_NOT_FOUND + ownerId);
		}

		ownerRepository.updatePetDetails(ownerId, petName);
	}

	@Override
	public void deleteOwner(int ownerId) throws OwnerNotFoundException {

		OwnerDTO owner = ownerRepository.findOwner(ownerId);

		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(OWNER_NOT_FOUND + ownerId);
		}

		ownerRepository.deleteOwner(ownerId);
	}

	@Override
	public List<OwnerDTO> findAllOwners() {
		return ownerRepository.findAllOwners();
	}

	@Override
	public List<OwnerDTO> findOwner(String ownerEmailId, LocalDate petBirthDate) {
		return ownerRepository.findOwner(ownerEmailId, petBirthDate);
	}
}