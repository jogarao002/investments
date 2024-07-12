package com.intellect.investmentsms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.InterestPayments;
import com.intellect.investmentsms.domain.Product;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.InterestPaymentsRepository;
import com.intellect.investmentsms.repository.ProductRepository;
import com.intellect.investmentsms.service.InterestPaymentsService;
import com.intellect.investmentsms.service.dto.InterestPaymentsDTO;
import com.intellect.investmentsms.service.mapper.InterestPaymentsMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

/**
 * Service Implementation for managing
 * {@link com.intellect.investmentsms.domain.InterestPayments}.
 */
@Service
@Transactional
public class InterestPaymentsServiceImpl implements InterestPaymentsService {

	private final InterestPaymentsRepository interestPaymentsRepository;

	private final InterestPaymentsMapper interestPaymentsMapper;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CommonStatusRepository commonStatusRepository;

	public InterestPaymentsServiceImpl(InterestPaymentsRepository interestPaymentsRepository,
			InterestPaymentsMapper interestPaymentsMapper) {
		this.interestPaymentsRepository = interestPaymentsRepository;
		this.interestPaymentsMapper = interestPaymentsMapper;
	}

	/**
	 *@implNote: Save the InterestPayments by "interestPaymentsDTO".
	 * @param interestPaymentsDTO. 
	 * @return the DTO Object interestPaymentsDTO.
	 * @author K.Ramu
	 */
	@Override
	public InterestPaymentsDTO save(InterestPaymentsDTO interestPaymentsDTO) throws InvestmentsBusinessException {
		if (interestPaymentsDTO != null) {
			if (interestPaymentsDTO.getId() != null) {
				InterestPayments optInterestPayments = null;
				Optional<InterestPayments> interestPayment = interestPaymentsRepository
						.findById(interestPaymentsDTO.getId());
				if (interestPayment.isPresent()) {
					optInterestPayments = interestPayment.get();
					interestPaymentsDTO.setCreatedOn(optInterestPayments.getCreatedOn());
					interestPaymentsDTO.setCreatedBy(optInterestPayments.getCreatedBy());
				}
			}
		}
		InterestPayments optInterestPayments = interestPaymentsMapper.toEntity(interestPaymentsDTO);
		optInterestPayments = interestPaymentsRepository.save(optInterestPayments);
		return interestPaymentsMapper.toDto(optInterestPayments);
	}

	/**
	 *@implNote: Get All the InterestPayments Details.
	 * @param .
	 * @return the list List<InterestPaymentsDTO>.
	 * @author K.Ramu
	 */
	@Override
	@Transactional(readOnly = true)
	public List<InterestPaymentsDTO> findAll() {
		List<InterestPaymentsDTO> interestPaymentsList = interestPaymentsRepository.findAll().stream()
				.map(interestPaymentsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		if (interestPaymentsList != null && !interestPaymentsList.isEmpty()) {
			for (InterestPaymentsDTO interestPaymentsDTO : interestPaymentsList) {
				if (null != interestPaymentsDTO) {
					if (null != interestPaymentsDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(interestPaymentsDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							interestPaymentsDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if (null != interestPaymentsDTO.getProducts()) {
						Optional<Product> optProduct = productRepository.findById(interestPaymentsDTO.getProducts());
						if (optProduct.isPresent() && null != optProduct.get().getName()) {
							interestPaymentsDTO.setProductName(optProduct.get().getName());
						}
					}

				}
			}
		}
		return interestPaymentsList;
	}

	/**
	 *@implNote: Get the InterestPayments Details by Id.
	 * @param id.
	 * @return the DTO Object interestPaymentsDTO.
	 * @author K.Ramu
	 */
	@Override
	@Transactional(readOnly = true)
	public InterestPaymentsDTO findOne(Long id) {

		InterestPaymentsDTO interestPaymentsDTO = null;
		Optional<InterestPayments> optInterestPayments = interestPaymentsRepository.findById(id);
		if (optInterestPayments.isPresent()) {
			interestPaymentsDTO = interestPaymentsMapper.toDto(optInterestPayments.get());
			if (null != interestPaymentsDTO) {
				if (null != interestPaymentsDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository
							.findById(interestPaymentsDTO.getStatus().longValue());
					if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						interestPaymentsDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
				if (null != interestPaymentsDTO.getProducts()) {
					Optional<Product> optProduct = productRepository.findById(interestPaymentsDTO.getProducts());
					if (optProduct.isPresent() && null != optProduct.get().getName()) {
						interestPaymentsDTO.setProductName(optProduct.get().getName());
					}
				}
			}
		}
		return interestPaymentsDTO;
	}

	/**
	 *@implNote: delete the InterestPayments Details by Id.
	 * @param id.
	 * @return Void.
	 * @author K.Ramu
	 */
	@Override
	public void delete(Long id) {
		interestPaymentsRepository.deleteById(id);
	}

	/**
	 *@implNote: get the InterestPayments Details by products.
	 * @param products.
	 * @return the list List<InterestPaymentsDTO>.
	 * @author K.Ramu
	 */
	@Override
	public List<InterestPaymentsDTO> getInterestPaymentsListByProducts(Long products)
			throws InvestmentsBusinessException {
		List<InterestPaymentsDTO> interestPaymentsDTOList = interestPaymentsMapper
				.toDto(interestPaymentsRepository.findByProducts(products));
		if (interestPaymentsDTOList != null && !interestPaymentsDTOList.isEmpty()) {
			for (InterestPaymentsDTO interestPaymentsDTO : interestPaymentsDTOList) {
				if (interestPaymentsDTO != null) {
					if (interestPaymentsDTO.getProducts() != null) {
						Optional<Product> product = productRepository.findById(interestPaymentsDTO.getProducts());
						if (product.isPresent()) {
							if (null != product.get() && null != product.get().getName())
								interestPaymentsDTO.setProductName(product.get().getName());
						}
					}
					if (null != interestPaymentsDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(interestPaymentsDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							interestPaymentsDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if (null != interestPaymentsDTO.getProducts()) {
						Optional<Product> optProduct = productRepository.findById(interestPaymentsDTO.getProducts());
						if (optProduct.isPresent() && null != optProduct.get().getName()) {
							interestPaymentsDTO.setProductName(optProduct.get().getName());
						}
					}
				}
			}
		}
		return interestPaymentsDTOList;
	}

	/**
	 *@implNote: get the InterestPayments Details by termAccId.
	 * @param termAccId.
	 * @return the list List<InterestPaymentsDTO>.
	 * @author K.Ramu
	 */
	@Override
	public List<InterestPaymentsDTO> getInterestPaymentsListByTermAccId(Long termAccId)
			throws InvestmentsBusinessException {
		List<InterestPaymentsDTO> interestPaymentsDTOList = interestPaymentsMapper
				.toDto(interestPaymentsRepository.findByTermAccId(termAccId));
		if (interestPaymentsDTOList != null && !interestPaymentsDTOList.isEmpty()) {
			for (InterestPaymentsDTO interestPaymentsDTO : interestPaymentsDTOList) {
				if (interestPaymentsDTO != null) {
					if (interestPaymentsDTO.getProducts() != null) {
						Optional<Product> product = productRepository.findById(interestPaymentsDTO.getProducts());
						if (product.isPresent()) {
							if (null != product.get() && null != product.get().getName())
								interestPaymentsDTO.setProductName(product.get().getName());
						}
					}
					if (null != interestPaymentsDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(interestPaymentsDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							interestPaymentsDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if (null != interestPaymentsDTO.getProducts()) {
						Optional<Product> optProduct = productRepository.findById(interestPaymentsDTO.getProducts());
						if (optProduct.isPresent() && null != optProduct.get().getName()) {
							interestPaymentsDTO.setProductName(optProduct.get().getName());
						}
					}
				}
			}
		}
		return interestPaymentsDTOList;
	}

}
