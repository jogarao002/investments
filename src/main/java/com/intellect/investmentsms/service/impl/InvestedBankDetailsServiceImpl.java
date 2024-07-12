package com.intellect.investmentsms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.InvestedBankDetails;
import com.intellect.investmentsms.domain.InvestmentsWorkFlow;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.InvestedBankDetailsRepository;
import com.intellect.investmentsms.repository.InvestmentsWorkFlowRepository;
import com.intellect.investmentsms.service.InvestedBankDetailsService;
import com.intellect.investmentsms.service.dto.InvestedBankDetailsDTO;
import com.intellect.investmentsms.service.dto.ModuleTypeEnum;
import com.intellect.investmentsms.service.mapper.InvestedBankDetailsMapper;
import com.intellect.investmentsms.util.ApplicationConstants;


/**
 * Service Implementation for managing
 * {@link com.intellect.investmentsms.domain.InvestedBankDetails}.
 */

@Service
@Transactional
public class InvestedBankDetailsServiceImpl implements InvestedBankDetailsService {

	private final InvestedBankDetailsRepository investedBankDetailsRepository;

	private final InvestedBankDetailsMapper investedBankDetailsMapper;

	private final CommonStatusRepository commonStatusRepository;

	private final InvestmentsWorkFlowRepository investmentsWorkFlowRepository;

	public InvestedBankDetailsServiceImpl(InvestedBankDetailsRepository investedBankDetailsRepository,
			InvestedBankDetailsMapper investedBankDetailsMapper, CommonStatusRepository commonStatusRepository,
			InvestmentsWorkFlowRepository investmentsWorkFlowRepository) {
		this.investedBankDetailsRepository = investedBankDetailsRepository;
		this.investedBankDetailsMapper = investedBankDetailsMapper;
		this.commonStatusRepository = commonStatusRepository;
		this.investmentsWorkFlowRepository = investmentsWorkFlowRepository;
	}
	/**
	 *@implNote: save the Invested Bank Details based on "investedBankDetailsDTO"
	 * @param investedBankDetailsDTO .
	 * @return InvestedBankDetailsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	public InvestedBankDetailsDTO save(InvestedBankDetailsDTO investedBankDetailsDTO)
			throws InvestmentsBusinessException {
		Integer previousStatus = null;
		if (investedBankDetailsDTO != null) {
			duplicateBankCheck(investedBankDetailsDTO);
			if (investedBankDetailsDTO.getId() != null) {
				Optional<InvestedBankDetails> investedBankDetails = investedBankDetailsRepository
						.findById(investedBankDetailsDTO.getId());
				InvestedBankDetails investedBankDetail = null;
				if (investedBankDetails.isPresent()) {
					investedBankDetail = investedBankDetails.get();
				}
				if (investedBankDetail != null) {
					if (investedBankDetail.getCreatedBy() != null)
						investedBankDetailsDTO.setCreatedBy(investedBankDetail.getCreatedBy());
					if (investedBankDetail.getCreatedOn() != null)
						investedBankDetailsDTO.setCreatedOn(investedBankDetail.getCreatedOn());
					previousStatus = investedBankDetail.getStatus().intValue();
				}
			}
		}
		if (investedBankDetailsDTO.getStatusName() != null) {
			CommonStatus commonStatus = commonStatusRepository.findByName(investedBankDetailsDTO.getStatusName());
			if (commonStatus != null && commonStatus.getId() != null)
				investedBankDetailsDTO.setStatus(commonStatus.getId().intValue());
		}
		InvestedBankDetails investedBankDetail = investedBankDetailsMapper.toEntity(investedBankDetailsDTO);
		investedBankDetail = investedBankDetailsRepository.save(investedBankDetail);
		InvestedBankDetailsDTO investedBankDetailsDto = investedBankDetailsMapper.toDto(investedBankDetail);
		if (previousStatus == null || (investedBankDetailsDto != null && previousStatus != null
				&& !previousStatus.equals(investedBankDetailsDto.getStatus()))) {
			investmentBankDetailsWorkFlowSave(investedBankDetailsDto);
		}
		return investedBankDetailsDto;
	}
	/**
	 *@implNote: saving work flow for the Invested Bank Details based on investedBankDetailsDto
	 * @param investedBankDetailsDto
	 * @return void
	 * @author LaxmiPrasannaKumar.S
	 */
	private void investmentBankDetailsWorkFlowSave(InvestedBankDetailsDTO investedBankDetailsDto) {
		if (investedBankDetailsDto != null) {
			InvestmentsWorkFlow investmentsWorkFlow = new InvestmentsWorkFlow();
			if (investedBankDetailsDto.getId() != null) {
				investmentsWorkFlow.setModuleId(investedBankDetailsDto.getId());
			}
			if (investedBankDetailsDto.getBranchId() != null) {
				investmentsWorkFlow.setBranchId(investedBankDetailsDto.getBranchId());
			}
			if (investedBankDetailsDto.getId() != null) {
				investmentsWorkFlow.setModuleType(ModuleTypeEnum.INVESTMENT_BANK_DETAILS.getKey());
			}
			if (investedBankDetailsDto.getPacsCode() != null) {
				investmentsWorkFlow.setPacsCode(investedBankDetailsDto.getPacsCode());
			}
			if (investedBankDetailsDto.getPacsId() != null) {
				investmentsWorkFlow.setPacsId(investedBankDetailsDto.getPacsId());
			}
			if (investedBankDetailsDto.getModifiedBy() != null) {
				investmentsWorkFlow.setUpdatedBy(investedBankDetailsDto.getModifiedBy());
			}
			if (investedBankDetailsDto.getModifiedOn() != null) {
				investmentsWorkFlow.setUpdatedOn(investedBankDetailsDto.getModifiedOn());
			}
			if (investedBankDetailsDto.getRemarks() != null) {
				investmentsWorkFlow.setRemarks(investedBankDetailsDto.getRemarks());
			}
			if (investedBankDetailsDto.getStatus() != null) {
				investmentsWorkFlow.setStatus(investedBankDetailsDto.getStatus());
			}
			investmentsWorkFlowRepository.save(investmentsWorkFlow);
		}

	}

	/**
	 *@implNote: duplicate check for the bank based on investedBankDetailsDTO
	 * @param investedBankDetailsDTO .
	 * @return void
	 * @author LaxmiPrasannaKumar.S
	 */
	
	private void duplicateBankCheck(InvestedBankDetailsDTO investedBankDetailsDTO) throws InvestmentsBusinessException {
		List<InvestedBankDetails> investedBankDetailList = investedBankDetailsRepository
				.findByBankName(investedBankDetailsDTO.getBankName());
		if (investedBankDetailList != null && !investedBankDetailList.isEmpty()) {
			for (InvestedBankDetails investedBankDetails : investedBankDetailList) {
				if (investedBankDetails != null && null != investedBankDetails.getBankName() && null != investedBankDetails.getId()
						&& investedBankDetails.getBankName().equalsIgnoreCase(investedBankDetailsDTO.getBankName())
						&& investedBankDetails.getId()!=investedBankDetailsDTO.getId()
						)
					throw new InvestmentsBusinessException(ApplicationConstants.BANK_ALREADY_EXIST);
			}
		}
	}
	/**
	 *@implNote: find all the invested bank details
	 * @param 
	 * @return  List<InvestedBankDetailsDTO> 
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	@Transactional(readOnly = true)
	public List<InvestedBankDetailsDTO> findAll() throws InvestmentsBusinessException {

		List<InvestedBankDetailsDTO> investedBankDetailsDTOList = investedBankDetailsRepository.findAll().stream().map(investedBankDetailsMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
		if (investedBankDetailsDTOList != null && !investedBankDetailsDTOList.isEmpty()) {
			for (InvestedBankDetailsDTO investedBankDetailsDTO : investedBankDetailsDTOList) {
					if (investedBankDetailsDTO.getStatus() != null
							&& investedBankDetailsDTO.getStatus() == ApplicationConstants.ACTIVE) {
						investedBankDetailsDTO.setStatusName(ApplicationConstants.ACTIVE_STATUS);
					} else {
						investedBankDetailsDTO.setStatusName(ApplicationConstants.IN_ACTIVE_STATUS);
					}
			}
		}
		return investedBankDetailsDTOList;
	}
	/**
	 *@implNote: find  the invested bank details by id
	 * @param    id
	 * @return  InvestedBankDetailsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	@Transactional(readOnly = true)
	public InvestedBankDetailsDTO findOne(Long id) throws InvestmentsBusinessException {
		
		InvestedBankDetailsDTO investedBankDetailsDTO = null;
			Optional<InvestedBankDetails> optInvestedBankDetails = investedBankDetailsRepository.findById(id);
			if (optInvestedBankDetails.isPresent())
				investedBankDetailsDTO = investedBankDetailsMapper.toDto(optInvestedBankDetails.get());
			if (investedBankDetailsDTO != null) {
				if (investedBankDetailsDTO.getStatus() != null
						&& investedBankDetailsDTO.getStatus() == ApplicationConstants.ACTIVE) {
					investedBankDetailsDTO.setStatusName(ApplicationConstants.ACTIVE_STATUS);
				} else {
					investedBankDetailsDTO.setStatusName(ApplicationConstants.IN_ACTIVE_STATUS);
				}
			}
			return investedBankDetailsDTO;
	    	
	    }
	/**
	 *@implNote: delete the Invested Bank Details based on "id"
	 * @param id .
	 * @return void
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	public void delete(Long id) throws InvestmentsBusinessException {
    	InvestedBankDetails investedBankDetails = null;
		Optional<InvestedBankDetails> investedBankDetailsOpt = investedBankDetailsRepository.findById(id);
		if (investedBankDetailsOpt.isPresent()) {
			investedBankDetails = investedBankDetailsOpt.get();
			investedBankDetails.setStatus(ApplicationConstants.IN_ACTIVE);
			investedBankDetailsRepository.save(investedBankDetails);
		}
    }
	/**
	 *@implNote: update the Invested Bank Details status based on "investedBankDetailsDTO"
	 * @param investedBankDetailsDTO .
	 * @return InvestedBankDetailsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	public InvestedBankDetailsDTO investedBankDetailsStatus(InvestedBankDetailsDTO investedBankDetailsDTO)
			throws InvestmentsBusinessException {
		if (investedBankDetailsDTO != null) {
			if (investedBankDetailsDTO.getStatusName() != null) {
				CommonStatus approveStatus = commonStatusRepository.findByName(investedBankDetailsDTO.getStatusName());
				if (approveStatus != null)
					investedBankDetailsDTO.setStatus(approveStatus.getId().intValue());
				InvestedBankDetails investedBankDetails = investedBankDetailsMapper.toEntity(investedBankDetailsDTO);
				investedBankDetailsDTO = investedBankDetailsMapper
						.toDto(investedBankDetailsRepository.save(investedBankDetails));
				investmentBankDetailsWorkFlowSave(investedBankDetailsDTO);
			}
			if (null != investedBankDetailsDTO.getStatus()) {
				Optional<CommonStatus> optCommonStatus = commonStatusRepository
						.findById(investedBankDetailsDTO.getStatus().longValue());
				if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
					investedBankDetailsDTO.setStatusName(optCommonStatus.get().getName());
				}
			}
		}
		return investedBankDetailsDTO;
	}

}
