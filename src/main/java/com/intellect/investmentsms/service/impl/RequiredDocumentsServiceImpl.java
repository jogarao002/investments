package com.intellect.investmentsms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.Product;
import com.intellect.investmentsms.domain.RequiredDocuments;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.ProductRepository;
import com.intellect.investmentsms.repository.RequiredDocumentsRepository;
import com.intellect.investmentsms.service.RequiredDocumentsService;
import com.intellect.investmentsms.service.dto.RequiredDocumentsDTO;
import com.intellect.investmentsms.service.mapper.RequiredDocumentsMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

/**
 * Service Implementation for managing
 * {@link com.intellect.investmentsms.domain.RequiredDocuments}.
 */
@Service
@Transactional
public class RequiredDocumentsServiceImpl implements RequiredDocumentsService {

	private final RequiredDocumentsRepository requiredDocumentsRepository;

	private final RequiredDocumentsMapper requiredDocumentsMapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CommonStatusRepository commonStatusRepository;

	public RequiredDocumentsServiceImpl(RequiredDocumentsRepository requiredDocumentsRepository,
			RequiredDocumentsMapper requiredDocumentsMapper) {
		this.requiredDocumentsRepository = requiredDocumentsRepository;
		this.requiredDocumentsMapper = requiredDocumentsMapper;
	}

	/**
	 *@implNote: save the RequiredDocuments based on "requiredDocumentsDTO".
	 * @param requiredDocumentsDTO.
	 * @return RequiredDocumentsDTO.
	 * @author Dileep_Kumar.Gedela
	 */
	@Override
	public RequiredDocumentsDTO save(RequiredDocumentsDTO requiredDocumentsDTO) throws InvestmentsBusinessException {
		if (requiredDocumentsDTO != null) {
			duplicateCheck(requiredDocumentsDTO);
			if (requiredDocumentsDTO.getId() != null) {
				Optional<RequiredDocuments> requiredDocumentsOpt = requiredDocumentsRepository
						.findById(requiredDocumentsDTO.getId());
				RequiredDocuments requiredDocuments = null;
				if (requiredDocumentsOpt.isPresent() && requiredDocumentsOpt.get() != null)
					requiredDocuments = requiredDocumentsOpt.get();
				if (requiredDocuments != null) {
					if (requiredDocuments.getCreatedBy() != null)
						requiredDocumentsDTO.setCreatedBy(requiredDocuments.getCreatedBy());
					if (requiredDocuments.getCreatedOn() != null)
						requiredDocumentsDTO.setCreatedOn(requiredDocuments.getCreatedOn());
				}
			}
		}
		RequiredDocuments requiredDocuments = requiredDocumentsMapper.toEntity(requiredDocumentsDTO);
		requiredDocuments = requiredDocumentsRepository.save(requiredDocuments);
		RequiredDocumentsDTO result = requiredDocumentsMapper.toDto(requiredDocuments);
		return result;
	}

	/**
   	 *@implNote: get All the RequiredDocuments.
   	 * @param 
   	 * @return List<RequiredDocumentsDTO>.
   	 * @author Dileep_Kumar.Gedela
   	 */
	@Override
	@Transactional(readOnly = true)
	public List<RequiredDocumentsDTO> findAll() {
		List<RequiredDocumentsDTO> requiredDocumentsList = requiredDocumentsRepository.findAll().stream()
				.map(requiredDocumentsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		if (requiredDocumentsList != null && !requiredDocumentsList.isEmpty()) {
			for (RequiredDocumentsDTO requiredDocumentsDTO : requiredDocumentsList) {
				if (null != requiredDocumentsDTO) {
					if(null != requiredDocumentsDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository.findById(requiredDocumentsDTO.getStatus().longValue());
						if(optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							requiredDocumentsDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if(null != requiredDocumentsDTO.getProductId()) {
						Optional<Product> optProduct = productRepository.findById(requiredDocumentsDTO.getProductId());
						if(optProduct.isPresent() && null != optProduct.get().getName()) {
							requiredDocumentsDTO.setProductName(optProduct.get().getName());
						}
					}
					
				}
			}
		}
		return requiredDocumentsList;

	}

	/**
   	 *@implNote: get the RequiredDocuments based on "id".
   	 * @param  id.
   	 * @return RequiredDocumentsDTO.
   	 * @author Dileep_Kumar.Gedela
   	 */
	@Override
	public RequiredDocumentsDTO findOne(Long id) {
		RequiredDocumentsDTO requiredDocumentsDTO = null;
		Optional<RequiredDocuments> optRequiredDocuments = requiredDocumentsRepository.findById(id);
		if (optRequiredDocuments.isPresent()) {
			requiredDocumentsDTO = requiredDocumentsMapper.toDto(optRequiredDocuments.get());
			if (null != requiredDocumentsDTO) {
				if(null != requiredDocumentsDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository.findById(requiredDocumentsDTO.getStatus().longValue());
					if(optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						requiredDocumentsDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
				if(null != requiredDocumentsDTO.getProductId()) {
					Optional<Product> optProduct = productRepository.findById(requiredDocumentsDTO.getProductId());
					if(optProduct.isPresent() && null != optProduct.get().getName()) {
						requiredDocumentsDTO.setProductName(optProduct.get().getName());
					}
				}
				
			}
		}
		return requiredDocumentsDTO;
	}

	/**
   	 *@implNote: remove the RequiredDocuments based on "id".
   	 * @param  id.
   	 * @return void.
   	 * @author Dileep_Kumar.Gedela
   	 */
	@Override
	public void delete(Long id) {
		requiredDocumentsRepository.deleteById(id);
	}

	/**
   	 *@implNote: get all the RequiredDocuments based on "productId".
   	 * @param productId.
   	 * @return List<RequiredDocumentsDTO>.
   	 * @author Dileep_Kumar.Gedela
   	 */
	@Override
	public List<RequiredDocumentsDTO> findByProductId(Long productId) {
		List<RequiredDocumentsDTO> requiredDocumentsDTOList = null;
		requiredDocumentsDTOList = requiredDocumentsMapper
				.toDto(requiredDocumentsRepository.findByProductId(productId));
		if (requiredDocumentsDTOList != null && !requiredDocumentsDTOList.isEmpty()) {
			for (RequiredDocumentsDTO requiredDocumentsDTO : requiredDocumentsDTOList) {
				if (null != requiredDocumentsDTO) {
					if(null != requiredDocumentsDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository.findById(requiredDocumentsDTO.getStatus().longValue());
						if(optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							requiredDocumentsDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if(null != requiredDocumentsDTO.getProductId()) {
						Optional<Product> optProduct = productRepository.findById(requiredDocumentsDTO.getProductId());
						if(optProduct.isPresent() && null != optProduct.get().getName()) {
							requiredDocumentsDTO.setProductName(optProduct.get().getName());
						}
					}
				}
			}
		}
		return requiredDocumentsDTOList;
	}

	/**
   	 *@implNote: get all the RequiredDocuments based on "productId" and "documentTypeId".
   	 * @param productId,documentTypeId.
   	 * @return List<RequiredDocumentsDTO>.
   	 * @author Dileep_Kumar.Gedela
   	 */
	@Override
	public List<RequiredDocumentsDTO> findByProductIdAndDocumentTypeId(Long productId, Long documentTypeId) {
		List<RequiredDocumentsDTO> requiredDocumentsDTOList = null;
		requiredDocumentsDTOList = requiredDocumentsMapper
				.toDto(requiredDocumentsRepository.findByProductIdAndDocumentTypeId(productId, documentTypeId));
		if (requiredDocumentsDTOList != null && !requiredDocumentsDTOList.isEmpty()) {
			for (RequiredDocumentsDTO requiredDocumentsDTO : requiredDocumentsDTOList) {
				if (null != requiredDocumentsDTO) {
					if(null != requiredDocumentsDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository.findById(requiredDocumentsDTO.getStatus().longValue());
						if(optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							requiredDocumentsDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if(null != requiredDocumentsDTO.getProductId()) {
						Optional<Product> optProduct = productRepository.findById(requiredDocumentsDTO.getProductId());
						if(optProduct.isPresent() && null != optProduct.get().getName()) {
							requiredDocumentsDTO.setProductName(optProduct.get().getName());
						}
					}
				}
			}
		}
		return requiredDocumentsDTOList;
	}

	/**
	 *@implNote: duplicateCheck the RequiredDocuments based on "requiredDocumentsDTO".
	 * @param requiredDocumentsDTO.
	 * @return void
	 * @author Dileep_Kumar.Gedela
	 */
	private void duplicateCheck(RequiredDocumentsDTO requiredDocumentsDTO) throws InvestmentsBusinessException {
		List<RequiredDocuments> requiredDocumentsList = requiredDocumentsRepository
				.findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndStatus(
						requiredDocumentsDTO.getProductId(), requiredDocumentsDTO.getEffectiveStartDate(),
						requiredDocumentsDTO.getStatus());
		if (requiredDocumentsList.isEmpty() || requiredDocumentsList == null) {
			requiredDocumentsList = requiredDocumentsRepository
					.findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateGreaterThanEqualAndStatus(
							requiredDocumentsDTO.getProductId(), requiredDocumentsDTO.getEffectiveStartDate(),
							requiredDocumentsDTO.getEffectiveStartDate(), requiredDocumentsDTO.getStatus());
		}
		if (requiredDocumentsList != null && !requiredDocumentsList.isEmpty()) {
			for (RequiredDocuments requiredDocuments : requiredDocumentsList) {
				if (requiredDocuments != null && null != requiredDocuments.getId()
						&& !requiredDocuments.getId().equals(requiredDocumentsDTO.getId()))
					throw new InvestmentsBusinessException(ApplicationConstants.REQUIRED_DOCUMENTS_ALREADY_EXIST);
			}
		}
	}
}
