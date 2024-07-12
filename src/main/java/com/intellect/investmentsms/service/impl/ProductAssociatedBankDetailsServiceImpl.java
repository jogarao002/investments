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
import com.intellect.investmentsms.domain.ProductAssociatedBankDetails;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.ProductAssociatedBankDetailsRepository;
import com.intellect.investmentsms.repository.ProductRepository;
import com.intellect.investmentsms.service.ProductAssociatedBankDetailsService;
import com.intellect.investmentsms.service.dto.ProductAssociatedBankDetailsDTO;
import com.intellect.investmentsms.service.mapper.ProductAssociatedBankDetailsMapper;


@Service
@Transactional
public class ProductAssociatedBankDetailsServiceImpl implements ProductAssociatedBankDetailsService {

	@Autowired
	private ProductRepository productRepository;

	private final ProductAssociatedBankDetailsRepository productAssociatedBankDetailsRepository;

	private final ProductAssociatedBankDetailsMapper productAssociatedBankDetailsMapper;

	@Autowired
	private CommonStatusRepository commonStatusRepository;

	public ProductAssociatedBankDetailsServiceImpl(
			ProductAssociatedBankDetailsRepository productAssociatedBankDetailsRepository,
			ProductAssociatedBankDetailsMapper productAssociatedBankDetailsMapper) {
		this.productAssociatedBankDetailsRepository = productAssociatedBankDetailsRepository;
		this.productAssociatedBankDetailsMapper = productAssociatedBankDetailsMapper;
	}

	/**
	 *@implNote: Save the ProductAssociatedBankDetails by "productAssociatedBankDetailsDTO".
	 * @param productAssociatedBankDetailsDTO. 
	 * @return the DTO Object ProductAssociatedBankDetailsDTO.
	 * @author Dilip Kumar.G
	 */
	
	@Override
	public ProductAssociatedBankDetailsDTO save(ProductAssociatedBankDetailsDTO productAssociatedBankDetailsDTO) {
		if (productAssociatedBankDetailsDTO != null) {
			if (productAssociatedBankDetailsDTO.getId() != null) {
				ProductAssociatedBankDetails productAssociatedBankDetails = null;
				Optional<ProductAssociatedBankDetails> productAssociatedBankDetailsOptional = productAssociatedBankDetailsRepository
						.findById(productAssociatedBankDetailsDTO.getId());
				if (productAssociatedBankDetailsOptional.isPresent()) {
					productAssociatedBankDetails = productAssociatedBankDetailsOptional.get();
					productAssociatedBankDetailsDTO.setCreatedOn(productAssociatedBankDetails.getCreatedOn());
					productAssociatedBankDetailsDTO.setCreatedBy(productAssociatedBankDetails.getCreatedBy());
				}
			}
		}
		ProductAssociatedBankDetails productAssociatedBankDetail = productAssociatedBankDetailsMapper
				.toEntity(productAssociatedBankDetailsDTO);
		productAssociatedBankDetail = productAssociatedBankDetailsRepository.save(productAssociatedBankDetail);
		return productAssociatedBankDetailsMapper.toDto(productAssociatedBankDetail);
	}

	/**
	 *@implNote: Update the ProductAssociatedBankDetails by "productAssociatedBankDetailsDTO".
	 * @param productAssociatedBankDetailsDTO. 
	 * @return the DTO Object ProductAssociatedBankDetailsDTO.
	 * @author Dilip Kumar.G
	 */
	
	@Override
	public ProductAssociatedBankDetailsDTO update(ProductAssociatedBankDetailsDTO productAssociatedBankDetailsDTO) {
		ProductAssociatedBankDetails productAssociatedBankDetails = productAssociatedBankDetailsMapper
				.toEntity(productAssociatedBankDetailsDTO);
		productAssociatedBankDetails = productAssociatedBankDetailsRepository.save(productAssociatedBankDetails);
		return productAssociatedBankDetailsMapper.toDto(productAssociatedBankDetails);
	}

	/**
	 *@implNote: Get All the ProductAssociatedBankDetails.
	 * @param . 
	 * @return the List List<ProductAssociatedBankDetailsDTO>.
	 * @author Dilip Kumar.G
	 */
	
	@Override
	@Transactional(readOnly = true)
	public List<ProductAssociatedBankDetailsDTO> findAll() {
		List<ProductAssociatedBankDetailsDTO> productAssociatedBankDetailsDTOList = productAssociatedBankDetailsRepository
				.findAll().stream().map(productAssociatedBankDetailsMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
		if (productAssociatedBankDetailsDTOList != null && !productAssociatedBankDetailsDTOList.isEmpty()) {
			for (ProductAssociatedBankDetailsDTO productAssociatedBankDetailsDTO : productAssociatedBankDetailsDTOList) {
				if (null != productAssociatedBankDetailsDTO) {
					if (null != productAssociatedBankDetailsDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(productAssociatedBankDetailsDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							productAssociatedBankDetailsDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if (null != productAssociatedBankDetailsDTO.getProductId()) {
						Optional<Product> optProduct = productRepository
								.findById(productAssociatedBankDetailsDTO.getProductId());
						if (optProduct.isPresent() && null != optProduct.get().getName()) {
							productAssociatedBankDetailsDTO.setProductName(optProduct.get().getName());
						}
					}

				}
			}
		}
		return productAssociatedBankDetailsDTOList;
	}

	/**
	 *@implNote: Get the ProductAssociatedBankDetails by "id".
	 * @param id . 
	 * @return the Object ProductAssociatedBankDetailsDTO.
	 * @author Dilip Kumar.G
	 */

	@Override
	@Transactional(readOnly = true)
	public ProductAssociatedBankDetailsDTO findOne(Long id) {

		ProductAssociatedBankDetailsDTO productAssociatedBankDetailsDTO = null;
		Optional<ProductAssociatedBankDetails> optProductAssociatedBankDetails = productAssociatedBankDetailsRepository
				.findById(id);
		if (optProductAssociatedBankDetails.isPresent()) {
			productAssociatedBankDetailsDTO = productAssociatedBankDetailsMapper
					.toDto(optProductAssociatedBankDetails.get());

			if (null != productAssociatedBankDetailsDTO) {
				if (null != productAssociatedBankDetailsDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository
							.findById(productAssociatedBankDetailsDTO.getStatus().longValue());
					if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						productAssociatedBankDetailsDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
				if (null != productAssociatedBankDetailsDTO.getProductId()) {
					Optional<Product> optProduct = productRepository
							.findById(productAssociatedBankDetailsDTO.getProductId());
					if (optProduct.isPresent() && null != optProduct.get().getName()) {
						productAssociatedBankDetailsDTO.setProductName(optProduct.get().getName());
					}
				}
			}
		}
		return productAssociatedBankDetailsDTO;

	}

	/**
	 *@implNote: Delete the ProductAssociatedBankDetails by "id".
	 * @param id . 
	 * @return void.
	 * @author Dilip Kumar.G
	 */

	@Override
	public void delete(Long id) {
		productAssociatedBankDetailsRepository.deleteById(id);
	}

	/**
	 *@implNote: Get the ProductAssociatedBankDetails by "productid".
	 * @param productid . 
	 * @return the List List<ProductAssociatedBankDetailsDTO>.
	 * @author Dilip Kumar.G
	 */
	
	@Override
	public List<ProductAssociatedBankDetailsDTO> getProductAssociatedBankDetailsByProductId(Long productid)
			throws InvestmentsBusinessException {
		List<ProductAssociatedBankDetailsDTO> productAssociatedBankDetailsDTOList = null;
		if (productid != null) {
			productAssociatedBankDetailsDTOList = productAssociatedBankDetailsMapper
					.toDto(productAssociatedBankDetailsRepository.findByProductId(productid));
			for (ProductAssociatedBankDetailsDTO productAssociatedBankDetailsDTO : productAssociatedBankDetailsDTOList) {
				if (null != productAssociatedBankDetailsDTO) {
					if (null != productAssociatedBankDetailsDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(productAssociatedBankDetailsDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							productAssociatedBankDetailsDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if (null != productAssociatedBankDetailsDTO.getProductId()) {
						Optional<Product> optProduct = productRepository
								.findById(productAssociatedBankDetailsDTO.getProductId());
						if (optProduct.isPresent() && null != optProduct.get().getName()) {
							productAssociatedBankDetailsDTO.setProductName(optProduct.get().getName());
						}
					}

				}
			}
		}
		return productAssociatedBankDetailsDTOList;
	}
}
