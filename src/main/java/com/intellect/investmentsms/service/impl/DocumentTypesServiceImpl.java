package com.intellect.investmentsms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CommonCategories;
import com.intellect.investmentsms.domain.DocumentTypes;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.DocumentTypesRepository;
import com.intellect.investmentsms.service.DocumentTypesService;
import com.intellect.investmentsms.service.dto.DocumentTypesDTO;
import com.intellect.investmentsms.service.dto.DocumentTypesDTO;
import com.intellect.investmentsms.service.dto.DocumentTypesDTO;
import com.intellect.investmentsms.service.mapper.DocumentTypesMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

/**
 * Service Implementation for managing
 * {@link com.intellect.DocumentTypes.erp.domain.KycDocTypes}.
 */
@Service
@Transactional
public class DocumentTypesServiceImpl implements DocumentTypesService {

	private final DocumentTypesRepository documentTypesRepository;

	private final DocumentTypesMapper documentTypesMapper;

	public DocumentTypesServiceImpl(DocumentTypesRepository documentTypesRepository, DocumentTypesMapper documentTypesMapper) {
		this.documentTypesRepository = documentTypesRepository;
		this.documentTypesMapper = documentTypesMapper;
	}

	@Override
	public DocumentTypesDTO save(DocumentTypesDTO documentTypesDTO) throws InvestmentsBusinessException {
		if (documentTypesDTO != null) {
			duplicateCheck(documentTypesDTO);
			if (documentTypesDTO.getId() != null) {
				DocumentTypes documentTypes = null;
				Optional<DocumentTypes> documentType = documentTypesRepository.findById(documentTypesDTO.getId());
				if (documentType.isPresent()) {
					documentTypes = documentType.get();
					documentTypesDTO.setCreatedOn(documentTypes.getCreatedOn());
					documentTypesDTO.setCreatedBy(documentTypes.getCreatedBy());
				}
			}
		}
		DocumentTypes documentTypes = documentTypesMapper.toEntity(documentTypesDTO);
		documentTypes = documentTypesRepository.save(documentTypes);
		return documentTypesMapper.toDto(documentTypes);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DocumentTypesDTO> findAll() {
		List<DocumentTypesDTO> documentTypesDTOList = documentTypesRepository.findAll().stream().map(documentTypesMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
		if (documentTypesDTOList != null && !documentTypesDTOList.isEmpty()) {
			for (DocumentTypesDTO documentTypesDTO : documentTypesDTOList) {
				if (documentTypesDTO.getId() != null) {
					if (documentTypesDTO.getStatus() == ApplicationConstants.ACTIVE) {
						documentTypesDTO.setStatusName(ApplicationConstants.ACTIVE_STATUS);
					} else {
						documentTypesDTO.setStatusName(ApplicationConstants.IN_ACTIVE_STATUS);
					}
				}
			}
		}
		return documentTypesDTOList;
	}

	@Override
	@Transactional(readOnly = true)
	public DocumentTypesDTO findOne(Long id) {

		DocumentTypesDTO documentTypesDTO = null;
		Optional<DocumentTypes> documentTypes = documentTypesRepository.findById(id);
		if (documentTypes.isPresent()) {
			documentTypesDTO = documentTypesMapper.toDto(documentTypes.get());
			if (null != documentTypesDTO.getId()) {
				if (documentTypesDTO.getStatus() == ApplicationConstants.ACTIVE) {
					documentTypesDTO.setStatusName(ApplicationConstants.ACTIVE_STATUS);
				} else {
					documentTypesDTO.setStatusName(ApplicationConstants.IN_ACTIVE_STATUS);
				}
			}
		}
		return documentTypesDTO;

	}

	@Override
	public void delete(Long id) throws InvestmentsBusinessException {
		DocumentTypesDTO documentTypesDTO = null;
		Optional<DocumentTypes> DocumentTypes = documentTypesRepository.findById(id);
		if (DocumentTypes.isPresent()) {
			documentTypesDTO = documentTypesMapper.toDto(DocumentTypes.get());
				documentTypesDTO.setStatus(ApplicationConstants.IN_ACTIVE);
				save(documentTypesDTO);
		}
	}
	
	public void duplicateCheck(DocumentTypesDTO documentTypesDTO) throws InvestmentsBusinessException {
		List<DocumentTypes> documentTypesList = documentTypesRepository.findByName(documentTypesDTO.getName());
		if (documentTypesList != null && !documentTypesList.isEmpty()) {
			for (DocumentTypes documentTypes : documentTypesList) {
				if (documentTypes.getId() != documentTypesDTO.getId())
					throw new InvestmentsBusinessException(ApplicationConstants.DOCUMENT_TYPES_DUPLICATE);
			}
		}
	}

}
