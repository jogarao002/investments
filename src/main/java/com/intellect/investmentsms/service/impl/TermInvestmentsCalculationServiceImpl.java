package com.intellect.investmentsms.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.InterestPayments;
import com.intellect.investmentsms.domain.InvestmentAccountInstallments;
import com.intellect.investmentsms.domain.TermDepositInvestmentAccounts;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CardRatesRepository;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.InterestPaymentsRepository;
import com.intellect.investmentsms.repository.InterestPolicyConfigRepository;
import com.intellect.investmentsms.repository.InvestmentAccountInstallmentsRepository;
import com.intellect.investmentsms.repository.TermDepositInvestmentAccountsRepository;
import com.intellect.investmentsms.service.dto.CardRatesDTO;
import com.intellect.investmentsms.service.dto.CommonStatusEnum;
import com.intellect.investmentsms.service.dto.FrequencyEnum;
import com.intellect.investmentsms.service.dto.InterestApplicableTypeEnum;
import com.intellect.investmentsms.service.dto.InterestPolicyConfigDTO;
import com.intellect.investmentsms.service.dto.InvestmentAccountInstallmentsDTO;
import com.intellect.investmentsms.service.dto.TenureTypeEnum;
import com.intellect.investmentsms.service.dto.TermDepositInvestmentAccountsDTO;
import com.intellect.investmentsms.service.mapper.CardRatesMapper;
import com.intellect.investmentsms.service.mapper.InterestPolicyConfigMapper;
import com.intellect.investmentsms.service.mapper.InvestmentAccountInstallmentsMapper;
import com.intellect.investmentsms.service.mapper.TermDepositInvestmentAccountsMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

@Service
@Transactional
public class TermInvestmentsCalculationServiceImpl {

	@Autowired
	private InterestPolicyConfigMapper interestPolicyConfigMapper;

	@Autowired
	private InterestPolicyConfigRepository interestPolicyConfigRepository;

	@Autowired
	private CardRatesMapper cardRatesMapper;

	@Autowired
	private CardRatesRepository cardRatesRepository;

	@Autowired
	private CommonStatusRepository commonStatusRepository;

	@Autowired
	private TermDepositInvestmentAccountsMapper termDepositInvestmentAccountsMapper;

	@Autowired
	private TermDepositInvestmentAccountsRepository termDepositInvestmentAccountsRepository;

	@Autowired
	private InterestPaymentsRepository interestPaymentsRepository;

	@Autowired
	private InvestmentAccountInstallmentsRepository investmentAccountInstallmentsRepository;

	@Autowired
	private InvestmentAccountInstallmentsMapper investmentAccountInstallmentsMapper;

	public TermDepositInvestmentAccountsDTO getMaturityAmountOnMaturityDate(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO)
			throws InvestmentsBusinessException, IOException {

		if (termDepositInvestmentAccountsDTO != null) {
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			cal.setTimeInMillis(termDepositInvestmentAccountsDTO.getDepositDate());
			Long tenureMonths = 0L;
			Long tenure = 0L;
			Double interestAmount = 0D;
			Double totalInterestAmount = 0D;

			InterestPolicyConfigDTO interestPolicyConfigDTO = getFdCummulativeInterestPolicyConfig(
					termDepositInvestmentAccountsDTO);
			if (termDepositInvestmentAccountsDTO.getTenureInMonths() != null) {
				tenureMonths = termDepositInvestmentAccountsDTO.getTenureInMonths().longValue();
			}
			if (termDepositInvestmentAccountsDTO.getTenureInYears() != null) {
				tenureMonths += (termDepositInvestmentAccountsDTO.getTenureInYears() * 12);
			}
			tenure = tenureMonths / 3;
			Double maturityValue = termDepositInvestmentAccountsDTO.getDepositAmount();
			for (int i = 1; i <= tenure; i++) {
				interestAmount = (maturityValue * interestPolicyConfigDTO.getGeneralRoi() * 3) / 1200;
				totalInterestAmount += interestAmount;
				maturityValue += interestAmount;
			}

			Calendar matureCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			matureCal.setTimeInMillis(termDepositInvestmentAccountsDTO.getDepositDate());
			if (tenureMonths != null) {
				matureCal.add(Calendar.MONTH, tenureMonths.intValue());
				termDepositInvestmentAccountsDTO.setSystemMaturityDate(matureCal.getTimeInMillis());
			}
			termDepositInvestmentAccountsDTO.setSystemMaturityInterest(Math.round(totalInterestAmount * 100.0) / 100.0);
			termDepositInvestmentAccountsDTO.setSystemMaturityAmount(Math.round((maturityValue) * 100.0) / 100.0);
			termDepositInvestmentAccountsDTO.setForeClosureMaturityAmount(null);
			TermDepositInvestmentAccounts termDepositInvestmentAccounts = termDepositInvestmentAccountsMapper
					.toEntity(termDepositInvestmentAccountsDTO);
			termDepositInvestmentAccountsDTO = termDepositInvestmentAccountsMapper
					.toDto(termDepositInvestmentAccountsRepository.save(termDepositInvestmentAccounts));
		}
		return termDepositInvestmentAccountsDTO;
	}

	public TermDepositInvestmentAccountsDTO getMaturityAmountOnForeclosure(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO)
			throws InvestmentsBusinessException, IOException {

		if (termDepositInvestmentAccountsDTO != null) {
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			cal.setTimeInMillis(termDepositInvestmentAccountsDTO.getDepositDate());

			Calendar closingDate = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			termDepositInvestmentAccountsDTO.setClosureDate(closingDate.getTimeInMillis());

			InterestPolicyConfigDTO intestPolicyConfigDTO = getFdCummulativeInterestPolicyConfig(
					termDepositInvestmentAccountsDTO);
			if (intestPolicyConfigDTO != null) {

				if (termDepositInvestmentAccountsDTO.getTenureInMonths() != null
						|| termDepositInvestmentAccountsDTO.getTenureInYears() != null) {
					Long foreCloseTenureDays = 0L;
					Long foreCloseTenureMonths = 0L;
					Long foreCloseTenure = 0L;
					Double foreCloseMonthsInterest = 0D;
					Double foreCloseDaysInterest = 0D;
					Double interestAmount = 0D;

					Integer day = cal.get(Calendar.DAY_OF_MONTH);
					Integer month = cal.get(Calendar.MONTH);
					Integer year = cal.get(Calendar.YEAR);
					LocalDate openDate = LocalDate.of(year, month + 1, day);

					Integer closeDay = closingDate.get(Calendar.DAY_OF_MONTH);
					Integer closeMonth = closingDate.get(Calendar.MONTH);
					Integer closeYear = closingDate.get(Calendar.YEAR);
					LocalDate closeDate = LocalDate.of(closeYear, closeMonth + 1, closeDay);
					Period difference = Period.between(openDate, closeDate);

					foreCloseTenureDays = (long) difference.getDays();
					foreCloseTenureMonths = (long) difference.getMonths();
					foreCloseTenureMonths += (long) (difference.getYears() * 12);
					foreCloseTenure = foreCloseTenureMonths / 3;
					Long remainingmonths = foreCloseTenureMonths % 3;
					Double forCloseMaturityAmount = termDepositInvestmentAccountsDTO.getDepositAmount();
					Double foreClosureInterest = 0D;
					Double foreClosureTotalInterest = 0D;
					for (int i = 1; i <= foreCloseTenure; i++) {
						interestAmount = (forCloseMaturityAmount * intestPolicyConfigDTO.getPenalRoi() * 3) / 1200;
						forCloseMaturityAmount += interestAmount;
						foreClosureInterest += interestAmount;
					}

					if (foreCloseTenureDays != null) {
						foreCloseDaysInterest = (forCloseMaturityAmount * intestPolicyConfigDTO.getPenalRoi()
								* foreCloseTenureDays) / 36500;
					}
					if (remainingmonths != null) {
						foreCloseMonthsInterest = (forCloseMaturityAmount * intestPolicyConfigDTO.getPenalRoi()
								* remainingmonths) / 1200;
					}
					forCloseMaturityAmount += foreCloseDaysInterest + foreCloseMonthsInterest;
					foreClosureTotalInterest = foreClosureInterest + foreCloseDaysInterest + foreCloseMonthsInterest;

					termDepositInvestmentAccountsDTO
							.setForeClosureMaturityAmount(Math.round((forCloseMaturityAmount) * 100.0) / 100.0);
					termDepositInvestmentAccountsDTO.setForeClosureMaturityIntrestAmount(
							Math.round((foreClosureTotalInterest) * 100.0) / 100.0);
					termDepositInvestmentAccountsDTO.setIsForeClosure(ApplicationConstants.TRUE);
					// termDepositInvestmentAccountsDTO = save(termDepositInvestmentAccountsDTO);
				}
			}
		}
		return termDepositInvestmentAccountsDTO;
	}

	public InterestPolicyConfigDTO getFdCummulativeInterestPolicyConfig(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) {
		CardRatesDTO cardRatesDTO = null;
		List<InterestPolicyConfigDTO> intestPolicyConfigDTOList = null;
		InterestPolicyConfigDTO intestPolicyConfigDTO = null;
		if (termDepositInvestmentAccountsDTO != null) {
			Boolean isProductRoi = ApplicationConstants.FALSE;
			if (termDepositInvestmentAccountsDTO.getProductId() != null) {
				intestPolicyConfigDTOList = interestPolicyConfigMapper.toDto(interestPolicyConfigRepository
						.findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndStatus(
								termDepositInvestmentAccountsDTO.getProductId(),
								termDepositInvestmentAccountsDTO.getDepositDate(), ApplicationConstants.ACTIVE));
				if (intestPolicyConfigDTOList == null
						|| (null != intestPolicyConfigDTOList && intestPolicyConfigDTOList.isEmpty()))
					intestPolicyConfigDTOList = interestPolicyConfigMapper.toDto(interestPolicyConfigRepository
							.findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateGreaterThanEqualAndStatus(
									termDepositInvestmentAccountsDTO.getProductId(),
									termDepositInvestmentAccountsDTO.getDepositDate(),
									termDepositInvestmentAccountsDTO.getDepositDate(), ApplicationConstants.ACTIVE));
				if (intestPolicyConfigDTOList != null && !intestPolicyConfigDTOList.isEmpty()) {
					intestPolicyConfigDTO = intestPolicyConfigDTOList.get(0);
					if (termDepositInvestmentAccountsDTO.getIsForeClosure() != null
							&& termDepositInvestmentAccountsDTO.getIsForeClosure()) {
						if (intestPolicyConfigDTO.getForeClosureInterestApplicableAs() != null && intestPolicyConfigDTO
								.getForeClosureInterestApplicableAs() == InterestApplicableTypeEnum.PRODUCT
										.getValue()) {
							isProductRoi = ApplicationConstants.TRUE;
						}
					}
					if (termDepositInvestmentAccountsDTO.getIsAutoRenewal() != null
							&& termDepositInvestmentAccountsDTO.getIsAutoRenewal()) {
						if (intestPolicyConfigDTO.getRenewalInterestApplicableAs() != null && intestPolicyConfigDTO
								.getRenewalInterestApplicableAs() == InterestApplicableTypeEnum.PRODUCT.getValue()) {
							isProductRoi = ApplicationConstants.TRUE;
						}
					}
				}
			}

			if (isProductRoi != null && !isProductRoi) {
				Integer days = 0;
				Integer months = 0;
				Integer years = 0;
				if (termDepositInvestmentAccountsDTO.getTenureInDays() != null) {
					days = termDepositInvestmentAccountsDTO.getTenureInDays();
				}
				if (termDepositInvestmentAccountsDTO.getTenureInMonths() != null) {
					months = termDepositInvestmentAccountsDTO.getTenureInMonths();
				}
				if (termDepositInvestmentAccountsDTO.getTenureInYears() != null) {
					years = termDepositInvestmentAccountsDTO.getTenureInYears();
				}
				Integer tenureType = 0;
				Integer tenureInYears = 0;
				Integer tenureIndays = 0;
				if (years > 0) {
					tenureType = TenureTypeEnum.YEARS.getValue();
					tenureInYears = years;
				} else if (months > 0) {
					Integer inMonths = months / 12;
					if (inMonths >= 1) {
						tenureType = TenureTypeEnum.YEARS.getValue();
						tenureInYears = inMonths;
					} else {
						tenureType = TenureTypeEnum.DAYS.getValue();
						tenureIndays = inMonths * 30;
					}
				} else if (days > 0) {
					tenureType = TenureTypeEnum.DAYS.getValue();
					tenureIndays = days;
				}

				if (tenureType != null && tenureType == TenureTypeEnum.DAYS.getValue()) {
					cardRatesDTO = cardRatesMapper.toDto(cardRatesRepository
							.findByPacsIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateIsNullAndStatus(
									termDepositInvestmentAccountsDTO.getPacsId(),
									termDepositInvestmentAccountsDTO.getDepositDate(), tenureIndays, tenureIndays,
									tenureType, ApplicationConstants.ACTIVE));
					if (cardRatesDTO == null)
						cardRatesDTO = cardRatesMapper.toDto(cardRatesRepository
								.findByPacsIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateGreaterThanEqualAndStatus(
										termDepositInvestmentAccountsDTO.getPacsId(),
										termDepositInvestmentAccountsDTO.getDepositDate(), tenureIndays, tenureIndays,
										tenureType, termDepositInvestmentAccountsDTO.getDepositDate(),
										ApplicationConstants.ACTIVE));
				} else if (tenureType != null && tenureType == TenureTypeEnum.YEARS.getValue()) {
					cardRatesDTO = cardRatesMapper.toDto(cardRatesRepository
							.findByPacsIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateIsNullAndStatus(
									termDepositInvestmentAccountsDTO.getPacsId(),
									termDepositInvestmentAccountsDTO.getDepositDate(), tenureInYears, tenureInYears,
									tenureType, ApplicationConstants.ACTIVE));
					if (cardRatesDTO == null)
						cardRatesDTO = cardRatesMapper.toDto(cardRatesRepository
								.findByPacsIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateGreaterThanEqualAndStatus(
										termDepositInvestmentAccountsDTO.getPacsId(),
										termDepositInvestmentAccountsDTO.getDepositDate(), tenureInYears, tenureInYears,
										tenureType, termDepositInvestmentAccountsDTO.getDepositDate(),
										ApplicationConstants.ACTIVE));
				}
			}
			if (cardRatesDTO != null && intestPolicyConfigDTO != null) {
				intestPolicyConfigDTO.setGeneralRoi(cardRatesDTO.getGeneralRoi());
				intestPolicyConfigDTO.setPenalRoi(cardRatesDTO.getPenalRoi());
			}
		}
		return intestPolicyConfigDTO;
	}

	public TermDepositInvestmentAccountsDTO saveFdNonCummulativeInterestPaymentsOnAccountApproval(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO, Boolean isApproved)
			throws InvestmentsBusinessException {
		if (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency() == null
				|| (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency() != null
						&& termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency()
								.equals(FrequencyEnum.ON_MATURITY.getValue().longValue()))) {
			termDepositInvestmentAccountsDTO = saveInterestPaymentWithoutPostingFrequency(
					termDepositInvestmentAccountsDTO, isApproved);
		}
		TermDepositInvestmentAccounts termDepositInvestmentAccounts = termDepositInvestmentAccountsMapper
				.toEntity(termDepositInvestmentAccountsDTO);
		termDepositInvestmentAccountsDTO = termDepositInvestmentAccountsMapper
				.toDto(termDepositInvestmentAccountsRepository.save(termDepositInvestmentAccounts));

		if (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency() != null
				&& termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency()
						.equals(FrequencyEnum.ON_MATURITY.getValue().longValue())) {
			termDepositInvestmentAccountsDTO = saveInterestPaymentWithPostingFrequency(termDepositInvestmentAccountsDTO,
					isApproved);
		}
		return termDepositInvestmentAccountsDTO;
	}

	private TermDepositInvestmentAccountsDTO saveInterestPaymentWithoutPostingFrequency(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO, Boolean isApproved) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		if (null != termDepositInvestmentAccountsDTO.getDepositDate())
			cal.setTimeInMillis(termDepositInvestmentAccountsDTO.getDepositDate());
		Double fdROI = 0D;
		Long tenureDays = 0L;
		Long tenureMonths = 0L;
		Long tenureYears = 0L;
		Double daysInterest = 0D;
		Double monthsInterest = 0D;
		Double yearsInterest = 0D;

		fdROI = termDepositInvestmentAccountsDTO.getRoi().doubleValue();
		tenureDays = termDepositInvestmentAccountsDTO.getTenureInDays().longValue();
		tenureMonths = termDepositInvestmentAccountsDTO.getTenureInMonths().longValue();
		tenureYears = termDepositInvestmentAccountsDTO.getTenureInYears().longValue();

		Double systemGeneratedInterest = 0D;
		if (termDepositInvestmentAccountsDTO.getTenureInDays() != null) {
			daysInterest = (termDepositInvestmentAccountsDTO.getDepositAmount() * fdROI
					* termDepositInvestmentAccountsDTO.getTenureInDays()) / 36500;
		}
		if (termDepositInvestmentAccountsDTO.getTenureInMonths() != null) {
			monthsInterest = (termDepositInvestmentAccountsDTO.getDepositAmount() * fdROI
					* termDepositInvestmentAccountsDTO.getTenureInMonths()) / 1200;
		}
		if (termDepositInvestmentAccountsDTO.getTenureInYears() != null) {
			yearsInterest = (termDepositInvestmentAccountsDTO.getDepositAmount() * fdROI
					* termDepositInvestmentAccountsDTO.getTenureInYears()) / 100;
		}
		systemGeneratedInterest = daysInterest + monthsInterest + yearsInterest;
		termDepositInvestmentAccountsDTO.setMaturityInterest(Math.round((systemGeneratedInterest) * 100.0) / 100.0);
		if (termDepositInvestmentAccountsDTO.getDepositAmount() == null)
			termDepositInvestmentAccountsDTO.setDepositAmount(0.0);
		Double totalMaturityAmount = systemGeneratedInterest + termDepositInvestmentAccountsDTO.getDepositAmount();
		termDepositInvestmentAccountsDTO.setMaturityAmount(Math.round((totalMaturityAmount) * 100.0) / 100.0);
		Calendar matureCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		if (tenureYears != null)
			matureCal.add(Calendar.YEAR, tenureYears.intValue());
		if (tenureMonths != null)
			matureCal.add(Calendar.MONTH, tenureMonths.intValue());
		if (tenureDays != null)
			matureCal.add(Calendar.DAY_OF_MONTH, tenureDays.intValue());
		if (tenureYears != null || tenureMonths != null || tenureDays != null)
			termDepositInvestmentAccountsDTO.setMaturityDate(matureCal.getTimeInMillis());

		// Set ForeClosure Amounts as null if we modify any data
//		termDepositInvestmentAccountsDTO.setExcessPaidMaturityInterest(null);
		termDepositInvestmentAccountsDTO.setForeClosureMaturityIntrestAmount(null);
		termDepositInvestmentAccountsDTO.setForeClosureMaturityAmount(null);

		if (isApproved) {
			TermDepositInvestmentAccounts termDepositInvestmentAccounts = termDepositInvestmentAccountsMapper
					.toEntity(termDepositInvestmentAccountsDTO);
			termDepositInvestmentAccountsDTO = termDepositInvestmentAccountsMapper
					.toDto(termDepositInvestmentAccountsRepository.save(termDepositInvestmentAccounts));
		}
		return termDepositInvestmentAccountsDTO;
	}

	private TermDepositInvestmentAccountsDTO saveInterestPaymentWithPostingFrequency(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO, Boolean isApproved) {
		if (null != termDepositInvestmentAccountsDTO && null != termDepositInvestmentAccountsDTO.getId()) {

			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			if (null != termDepositInvestmentAccountsDTO.getDepositDate())
				cal.setTimeInMillis(termDepositInvestmentAccountsDTO.getDepositDate());
			Double fdROI = 0D;
			Long tenureDays = 0L;
			Long tenureMonths = 0L;
			Long tenureYears = 0L;
			Double daysInterest = 0D;
			Double monthsInterest = 0D;
			Double yearsInterest = 0D;

			fdROI = termDepositInvestmentAccountsDTO.getRoi().doubleValue();
			tenureDays = termDepositInvestmentAccountsDTO.getTenureInDays().longValue();
			tenureMonths = termDepositInvestmentAccountsDTO.getTenureInMonths().longValue();
			tenureYears = termDepositInvestmentAccountsDTO.getTenureInYears().longValue();

			Double systemGeneratedInterest = 0D;
			if (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency()
					.equals(FrequencyEnum.MONTHLY.getValue().longValue())) {
				if (termDepositInvestmentAccountsDTO.getDepositDate() != null) {
					Integer tenure = 0;
					if (tenureYears != null) {
						tenure = (int) (tenureYears * 12);
					}
					if (tenureMonths != null) {
						tenure += tenureMonths.intValue();
					}
					for (int i = 1; i <= tenure; i++) {
						cal.add(Calendar.MONTH, 1);
						Double systemInterestPostingAmount = 0D;
						InterestPayments interestpaymentsData = interestPaymentsRepository
								.findByTermAccIdAndInterestPostingDate(termDepositInvestmentAccountsDTO.getId(),
										cal.getTimeInMillis());
						if (interestpaymentsData == null)
							interestpaymentsData = new InterestPayments();
						if (termDepositInvestmentAccountsDTO.getDepositAmount() != null && fdROI != null) {
							systemInterestPostingAmount = (termDepositInvestmentAccountsDTO.getDepositAmount() * fdROI)
									/ (1200 + fdROI);
						}
						interestpaymentsData.setInterestPostingDate(cal.getTimeInMillis());
						interestpaymentsData.setTermAccId(termDepositInvestmentAccountsDTO.getId());
						interestpaymentsData
								.setInterestAmount(Math.round((systemInterestPostingAmount) * 100.0) / 100.0);
						systemGeneratedInterest += systemInterestPostingAmount;
//						if (null != termDepositInvestmentAccountsDTO.getAccountNumber())
//							interestpaymentsData.setAccountNumber(termDepositInvestmentAccountsDTO.getAccountNumber());
						if (null != termDepositInvestmentAccountsDTO.getAdmissionNumber())
							interestpaymentsData
									.setAdmissionNumber(termDepositInvestmentAccountsDTO.getAdmissionNumber());
						if (isApproved)
							interestPaymentsRepository.save(interestpaymentsData);
					}
				}
			} else if (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency()
					.equals(FrequencyEnum.QUARTERLY.getValue().longValue())) {
				Integer tenure = 0;
				if (tenureYears != null) {
					tenure = (int) (tenureYears * 4);
				}
				if (tenureMonths != null) {
					tenure += tenureMonths.intValue() / 3;
				}
				for (int i = 1; i <= tenure; i++) {
					cal.add(Calendar.MONTH, 3);
					Double systemInterestPostingAmount = 0D;
					InterestPayments interestpaymentsData = interestPaymentsRepository
							.findByTermAccIdAndInterestPostingDate(termDepositInvestmentAccountsDTO.getId(),
									cal.getTimeInMillis());
					if (interestpaymentsData == null)
						interestpaymentsData = new InterestPayments();

					if (termDepositInvestmentAccountsDTO.getDepositAmount() != null && fdROI != null) {
						systemInterestPostingAmount = (termDepositInvestmentAccountsDTO.getDepositAmount() * fdROI)
								/ (400);
					}
					interestpaymentsData.setInterestPostingDate(cal.getTimeInMillis());
					interestpaymentsData.setTermAccId(termDepositInvestmentAccountsDTO.getId());
					interestpaymentsData.setInterestAmount(Math.round((systemInterestPostingAmount) * 100.0) / 100.0);
					systemGeneratedInterest += systemInterestPostingAmount;
//					if (null != termDepositInvestmentAccountsDTO.getAccountNumber())
//						interestpaymentsData.setAccountNumber(termDepositInvestmentAccountsDTO.getAccountNumber());
					if (null != termDepositInvestmentAccountsDTO.getAdmissionNumber())
						interestpaymentsData.setAdmissionNumber(termDepositInvestmentAccountsDTO.getAdmissionNumber());
					if (isApproved)
						interestPaymentsRepository.save(interestpaymentsData);
				}
			} else if (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency()
					.equals(FrequencyEnum.HALF_YEARLY.getValue().longValue())) {
				Integer tenure = 0;
				if (tenureYears != null) {
					tenure = (int) (tenureYears * 2);
				}
				if (tenureMonths != null) {
					tenure += tenureMonths.intValue() / 6;
				}
				for (int i = 1; i <= tenure; i++) {
					cal.add(Calendar.MONTH, 6);
					Double systemInterestPostingAmount = 0D;
					InterestPayments interestpaymentsData = interestPaymentsRepository
							.findByTermAccIdAndInterestPostingDate(termDepositInvestmentAccountsDTO.getId(),
									cal.getTimeInMillis());
					if (interestpaymentsData == null)
						interestpaymentsData = new InterestPayments();
					if (termDepositInvestmentAccountsDTO.getDepositAmount() != null && fdROI != null) {
						systemInterestPostingAmount = (termDepositInvestmentAccountsDTO.getDepositAmount() * fdROI)
								/ (200);
					}
					interestpaymentsData.setInterestPostingDate(cal.getTimeInMillis());
					interestpaymentsData.setTermAccId(termDepositInvestmentAccountsDTO.getId());
					interestpaymentsData.setInterestAmount(Math.round((systemInterestPostingAmount) * 100.0) / 100.0);
					systemGeneratedInterest += systemInterestPostingAmount;
//					if (null != termDepositInvestmentAccountsDTO.getAccountNumber())
//						interestpaymentsData.setAccountNumber(termDepositInvestmentAccountsDTO.getAccountNumber());
					if (null != termDepositInvestmentAccountsDTO.getAdmissionNumber())
						interestpaymentsData.setAdmissionNumber(termDepositInvestmentAccountsDTO.getAdmissionNumber());
					if (isApproved)
						interestPaymentsRepository.save(interestpaymentsData);
				}
			} else if (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency()
					.equals(FrequencyEnum.YEARLY.getValue().longValue())) {
				Integer tenure = 0;
				if (tenureYears != null) {
					tenure = tenureYears.intValue();
				}
				if (tenureMonths != null) {
					tenure += tenureMonths.intValue() / 12;
				}
				for (int i = 1; i <= tenure; i++) {
					cal.add(Calendar.YEAR, 1);
					Double systemInterestPostingAmount = 0D;
					InterestPayments interestpaymentsData = interestPaymentsRepository
							.findByTermAccIdAndInterestPostingDate(termDepositInvestmentAccountsDTO.getId(),
									cal.getTimeInMillis());
					if (interestpaymentsData == null)
						interestpaymentsData = new InterestPayments();
					if (termDepositInvestmentAccountsDTO.getDepositAmount() != null && fdROI != null) {
						systemInterestPostingAmount = (termDepositInvestmentAccountsDTO.getDepositAmount() * fdROI)
								/ (100);
					}
					interestpaymentsData.setInterestPostingDate(cal.getTimeInMillis());
					interestpaymentsData.setTermAccId(termDepositInvestmentAccountsDTO.getId());
					interestpaymentsData.setInterestAmount(Math.round((systemInterestPostingAmount) * 100.0) / 100.0);
					systemGeneratedInterest += systemInterestPostingAmount;
//					if (null != termDepositInvestmentAccountsDTO.getAccountNumber())
//						interestpaymentsData.setAccountNumber(termDepositInvestmentAccountsDTO.getAccountNumber());
					if (null != termDepositInvestmentAccountsDTO.getAdmissionNumber())
						interestpaymentsData.setAdmissionNumber(termDepositInvestmentAccountsDTO.getAdmissionNumber());
					if (isApproved)
						interestPaymentsRepository.save(interestpaymentsData);
				}
			}

			Double remainingDaysInterest = 0D;
			Double remainingInterest = 0D;
			if (tenureDays != null) {
				remainingDaysInterest = (termDepositInvestmentAccountsDTO.getDepositAmount() * fdROI * tenureDays)
						/ 36500;
				remainingInterest = remainingDaysInterest + remainingInterest;
			}
			Integer remainingMonths = (int) (tenureMonths % 3);
			Double remainingMonthsInterest = 0D;
			if (remainingMonths != null) {
				remainingMonthsInterest = (termDepositInvestmentAccountsDTO.getDepositAmount() * fdROI
						* remainingMonths) / 1200;
				remainingInterest = remainingInterest + remainingMonthsInterest;
			}
			if (remainingInterest > 0) {
				InterestPayments interestpaymentsData = interestPaymentsRepository
						.findByTermAccIdAndInterestPostingDate(termDepositInvestmentAccountsDTO.getId(),
								cal.getTimeInMillis());
				if (interestpaymentsData == null)
					interestpaymentsData = new InterestPayments();
				interestpaymentsData.setInterestPostingDate(cal.getTimeInMillis());
				interestpaymentsData.setTermAccId(termDepositInvestmentAccountsDTO.getId());
				interestpaymentsData.setInterestAmount(Math.round((remainingInterest) * 100.0) / 100.0);
//				if (null != termDepositInvestmentAccountsDTO.getAccountNumber())
//					interestpaymentsData.setAccountNumber(termDepositInvestmentAccountsDTO.getAccountNumber());
				if (null != termDepositInvestmentAccountsDTO.getAdmissionNumber())
					interestpaymentsData.setAdmissionNumber(termDepositInvestmentAccountsDTO.getAdmissionNumber());
				if (isApproved)
					interestPaymentsRepository.save(interestpaymentsData);
			}

			Double totalMaturityAmount = systemGeneratedInterest + termDepositInvestmentAccountsDTO.getDepositAmount();
			termDepositInvestmentAccountsDTO.setMaturityInterest(Math.round((systemGeneratedInterest) * 100.0) / 100.0);
			termDepositInvestmentAccountsDTO.setMaturityAmount(Math.round((totalMaturityAmount) * 100.0) / 100.0);

			// Set ForeClosure Amounts as null if we modify any data

//			termDepositInvestmentAccountsDTO.setExcessPaidMaturityInterest(null);
			termDepositInvestmentAccountsDTO.setForeClosureMaturityIntrestAmount(null);
			termDepositInvestmentAccountsDTO.setForeClosureMaturityAmount(null);

			if (isApproved) {
				TermDepositInvestmentAccounts termDepositInvestmentAccounts = termDepositInvestmentAccountsMapper
						.toEntity(termDepositInvestmentAccountsDTO);
				termDepositInvestmentAccountsDTO = termDepositInvestmentAccountsMapper
						.toDto(termDepositInvestmentAccountsRepository.save(termDepositInvestmentAccounts));
			}

		}
		return termDepositInvestmentAccountsDTO;
	}

	public TermDepositInvestmentAccountsDTO saveForeClosureDetails(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO, Boolean isForeClosure) {
		if (null != termDepositInvestmentAccountsDTO && null != termDepositInvestmentAccountsDTO.getId()) {
			InterestPolicyConfigDTO interestPolicyConfigDTO = getFdNonCummulativeInterestPolicyConfig(
					termDepositInvestmentAccountsDTO);
			if (interestPolicyConfigDTO != null) {

				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
				Float foreCloseFdROI = 0F;
				Long foreCloseTenureDays = 0L;
				Long foreCloseTenureMonths = 0L;
				Long foreCloseTenureYears = 0L;
				Double foreCloseDaysInterest = 0D;
				Double foreCloseMonthsInterest = 0D;
				Double foreCloseYearsInterest = 0D;

				if (interestPolicyConfigDTO.getPenalRoi() != null)
					termDepositInvestmentAccountsDTO.setForeClosureRoi(interestPolicyConfigDTO.getPenalRoi());

				CommonStatus commonStatus = null;
				if (termDepositInvestmentAccountsDTO.getStatusName() != null) {
					commonStatus = commonStatusRepository.findByName(termDepositInvestmentAccountsDTO.getStatusName());
					if (commonStatus != null)
						termDepositInvestmentAccountsDTO.setStatus(commonStatus.getId().intValue());
				}

				if (termDepositInvestmentAccountsDTO.getRoi() != null
						&& termDepositInvestmentAccountsDTO.getForeClosureRoi() != null)
					foreCloseFdROI = termDepositInvestmentAccountsDTO.getRoi()
							- termDepositInvestmentAccountsDTO.getForeClosureRoi();
				else if (termDepositInvestmentAccountsDTO.getRoi() != null)
					foreCloseFdROI = termDepositInvestmentAccountsDTO.getRoi();

				Calendar closingDate = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
				closingDate.setTimeInMillis(termDepositInvestmentAccountsDTO.getClosureDate());
				cal.setTimeInMillis(termDepositInvestmentAccountsDTO.getDepositDate());
				if (termDepositInvestmentAccountsDTO.getTenureInMonths() != null
						|| termDepositInvestmentAccountsDTO.getTenureInYears() != null) {
					Integer day = cal.get(Calendar.DAY_OF_MONTH);
					Integer month = cal.get(Calendar.MONTH);
					Integer year = cal.get(Calendar.YEAR);
					LocalDate openDate = LocalDate.of(year, month + 1, day);

					Integer closeDay = closingDate.get(Calendar.DAY_OF_MONTH);
					Integer closeMonth = closingDate.get(Calendar.MONTH);
					Integer closeYear = closingDate.get(Calendar.YEAR);
					LocalDate closeDate = LocalDate.of(closeYear, closeMonth + 1, closeDay);
					Period difference = Period.between(openDate, closeDate);

					foreCloseTenureDays = (long) difference.getDays();
					foreCloseTenureMonths = (long) difference.getMonths();
					foreCloseTenureYears = (long) difference.getYears();
				} else {
					Long diff = closingDate.getTimeInMillis() - cal.getTimeInMillis();
					foreCloseTenureDays = Math.abs(diff / (24 * 60 * 60 * 1000));
				}

				if (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency() == null
						|| (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency() != null
								&& termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency()
										.equals(FrequencyEnum.ON_MATURITY.getValue().longValue()))) {
					Double foreCloseSystemGeneratedInterest = 0D;
					if (foreCloseTenureDays != null) {
						foreCloseDaysInterest = (termDepositInvestmentAccountsDTO.getDepositAmount() * foreCloseFdROI
								* foreCloseTenureDays) / 36500;
					}
					if (foreCloseTenureMonths != null) {
						foreCloseMonthsInterest = (termDepositInvestmentAccountsDTO.getDepositAmount() * foreCloseFdROI
								* foreCloseTenureMonths) / 1200;
					}
					if (foreCloseTenureYears != null) {
						foreCloseYearsInterest = (termDepositInvestmentAccountsDTO.getDepositAmount() * foreCloseFdROI
								* foreCloseTenureYears) / 100;
					}
					foreCloseSystemGeneratedInterest = foreCloseDaysInterest + foreCloseMonthsInterest
							+ foreCloseYearsInterest;
					termDepositInvestmentAccountsDTO.setForeClosureMaturityIntrestAmount(
							Math.round((foreCloseSystemGeneratedInterest) * 100.0) / 100.0);
					Double forCloseTotalMaturityAmount = foreCloseSystemGeneratedInterest
							+ termDepositInvestmentAccountsDTO.getDepositAmount();
					termDepositInvestmentAccountsDTO
							.setForeClosureMaturityAmount(Math.round((forCloseTotalMaturityAmount) * 100.0) / 100.0);

					if (isForeClosure) {
						TermDepositInvestmentAccounts termDepositInvestmentAccounts = termDepositInvestmentAccountsMapper
								.toEntity(termDepositInvestmentAccountsDTO);
						termDepositInvestmentAccountsDTO = termDepositInvestmentAccountsMapper
								.toDto(termDepositInvestmentAccountsRepository.save(termDepositInvestmentAccounts));
					}

				} else if (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency() != null
						&& !termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency()
								.equals(FrequencyEnum.ON_MATURITY.getValue().longValue())) {
					Double foreCloseSystemGeneratedInterest = 0D;
					Double paidSystemGeneratedInterest = 0D;
					if (foreCloseTenureDays != null) {
						foreCloseDaysInterest = (termDepositInvestmentAccountsDTO.getDepositAmount() * foreCloseFdROI
								* foreCloseTenureDays) / 36500;
					}

					if (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency()
							.equals(FrequencyEnum.MONTHLY.getValue().longValue())) {
						if (termDepositInvestmentAccountsDTO.getDepositDate() != null) {
							Integer tenure = 0;
							if (foreCloseTenureYears != null) {
								tenure = (int) (foreCloseTenureYears * 12);
							}
							if (foreCloseTenureMonths != null) {
								tenure += foreCloseTenureMonths.intValue();
							}
							for (int i = 1; i <= tenure; i++) {
								cal.add(Calendar.MONTH, 1);
								Double systemInterestPostingAmount = 0D;
								InterestPayments interestpaymentsData = interestPaymentsRepository
										.findByTermAccIdAndInterestPostingDate(termDepositInvestmentAccountsDTO.getId(),
												cal.getTimeInMillis());
								if (interestpaymentsData == null)
									interestpaymentsData = new InterestPayments();
								if (termDepositInvestmentAccountsDTO.getDepositAmount() != null
										&& foreCloseFdROI != null) {
									systemInterestPostingAmount = (termDepositInvestmentAccountsDTO.getDepositAmount()
											* foreCloseFdROI) / (1200 + foreCloseFdROI);
								}
//							systemInterestPostingAmount += foreCloseDaysInterest;
								interestpaymentsData.setForeClosureInterestAmount(
										Math.round((systemInterestPostingAmount) * 100.0) / 100.0);
								foreCloseSystemGeneratedInterest += systemInterestPostingAmount;
								interestpaymentsData.setInterestPostingDate(cal.getTimeInMillis());
								interestpaymentsData.setTermAccId(termDepositInvestmentAccountsDTO.getId());
								if (null != interestpaymentsData.getInterestAmount())
									paidSystemGeneratedInterest += interestpaymentsData.getInterestAmount();
								if (isForeClosure)
									interestPaymentsRepository.save(interestpaymentsData);
							}
						}
					} else if (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency()
							.equals(FrequencyEnum.QUARTERLY.getValue().longValue())) {
						Integer tenure = 0;
						Integer remainingMonths = (int) (foreCloseTenureMonths % 3);
						if (remainingMonths != null) {
							foreCloseMonthsInterest = (termDepositInvestmentAccountsDTO.getDepositAmount()
									* foreCloseFdROI * remainingMonths) / 1200;
						}
						if (foreCloseTenureYears != null) {
							tenure = (int) (foreCloseTenureYears * 4);
						}
						if (foreCloseTenureMonths != null) {
							tenure += foreCloseTenureMonths.intValue() / 3;
						}
						for (int i = 1; i <= tenure; i++) {
							cal.add(Calendar.MONTH, 3);
							Double systemInterestPostingAmount = 0D;
							InterestPayments interestpaymentsData = interestPaymentsRepository
									.findByTermAccIdAndInterestPostingDate(termDepositInvestmentAccountsDTO.getId(),
											cal.getTimeInMillis());
							if (interestpaymentsData == null)
								interestpaymentsData = new InterestPayments();

							if (termDepositInvestmentAccountsDTO.getDepositAmount() != null && foreCloseFdROI != null) {
								systemInterestPostingAmount = (termDepositInvestmentAccountsDTO.getDepositAmount()
										* foreCloseFdROI) / (400);
							}
							interestpaymentsData.setForeClosureInterestAmount(
									Math.round((systemInterestPostingAmount) * 100.0) / 100.0);
							foreCloseSystemGeneratedInterest += systemInterestPostingAmount;
							interestpaymentsData.setInterestPostingDate(cal.getTimeInMillis());
							interestpaymentsData.setTermAccId(termDepositInvestmentAccountsDTO.getId());
							if (null != interestpaymentsData.getInterestAmount())
								paidSystemGeneratedInterest += interestpaymentsData.getInterestAmount();
							if (isForeClosure)
								interestPaymentsRepository.save(interestpaymentsData);
						}
					} else if (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency()
							.equals(FrequencyEnum.HALF_YEARLY.getValue().longValue())) {
						Integer tenure = 0;
						Integer remainingMonths = (int) (foreCloseTenureMonths % 6);
						if (remainingMonths != null) {
							foreCloseMonthsInterest = (termDepositInvestmentAccountsDTO.getDepositAmount()
									* foreCloseFdROI * remainingMonths) / 1200;
						}
						if (foreCloseTenureYears != null) {
							tenure = (int) (foreCloseTenureYears * 2);
						}
						if (foreCloseTenureMonths != null) {
							tenure += foreCloseTenureMonths.intValue() / 6;
						}
						for (int i = 1; i <= tenure; i++) {
							cal.add(Calendar.MONTH, 6);
							Double systemInterestPostingAmount = 0D;
							InterestPayments interestpaymentsData = interestPaymentsRepository
									.findByTermAccIdAndInterestPostingDate(termDepositInvestmentAccountsDTO.getId(),
											cal.getTimeInMillis());
							if (interestpaymentsData == null)
								interestpaymentsData = new InterestPayments();
							interestpaymentsData.setInterestPostingDate(cal.getTimeInMillis());
							interestpaymentsData.setTermAccId(termDepositInvestmentAccountsDTO.getId());
							if (termDepositInvestmentAccountsDTO.getDepositAmount() != null && foreCloseFdROI != null) {
								systemInterestPostingAmount = (termDepositInvestmentAccountsDTO.getDepositAmount()
										* foreCloseFdROI) / (200);
							}
							interestpaymentsData.setForeClosureInterestAmount(
									Math.round((systemInterestPostingAmount) * 100.0) / 100.0);
							foreCloseSystemGeneratedInterest += systemInterestPostingAmount;
							if (null != interestpaymentsData.getInterestAmount())
								paidSystemGeneratedInterest += interestpaymentsData.getInterestAmount();
							if (isForeClosure)
								interestPaymentsRepository.save(interestpaymentsData);
						}
					} else if (termDepositInvestmentAccountsDTO.getInterestOrInstallmentFrequency()
							.equals(FrequencyEnum.YEARLY.getValue().longValue())) {
						Integer tenure = 0;
						Integer remainingMonths = (int) (foreCloseTenureMonths % 12);
						if (remainingMonths != null) {
							foreCloseMonthsInterest = (termDepositInvestmentAccountsDTO.getDepositAmount()
									* foreCloseFdROI * remainingMonths) / 1200;
						}
						if (foreCloseTenureYears != null) {
							tenure = foreCloseTenureYears.intValue();
						}
						if (foreCloseTenureMonths != null) {
							tenure += foreCloseTenureMonths.intValue() / 12;
						}
						for (int i = 1; i <= tenure; i++) {
							cal.add(Calendar.YEAR, 1);
							Double systemInterestPostingAmount = 0D;
							InterestPayments interestpaymentsData = interestPaymentsRepository
									.findByTermAccIdAndInterestPostingDate(termDepositInvestmentAccountsDTO.getId(),
											cal.getTimeInMillis());
							if (interestpaymentsData == null)
								interestpaymentsData = new InterestPayments();
							interestpaymentsData.setInterestPostingDate(cal.getTimeInMillis());
							interestpaymentsData.setTermAccId(termDepositInvestmentAccountsDTO.getId());
							if (termDepositInvestmentAccountsDTO.getDepositAmount() != null && foreCloseFdROI != null) {
								systemInterestPostingAmount = (termDepositInvestmentAccountsDTO.getDepositAmount()
										* foreCloseFdROI) / (100);
							}
							interestpaymentsData.setForeClosureInterestAmount(
									Math.round((systemInterestPostingAmount) * 100.0) / 100.0);
							foreCloseSystemGeneratedInterest += systemInterestPostingAmount;
							if (null != interestpaymentsData.getInterestAmount())
								paidSystemGeneratedInterest += interestpaymentsData.getInterestAmount();
							if (isForeClosure)
								interestPaymentsRepository.save(interestpaymentsData);
						}
					}
					foreCloseSystemGeneratedInterest += foreCloseDaysInterest + foreCloseMonthsInterest;
					termDepositInvestmentAccountsDTO.setForeClosureMaturityIntrestAmount(
							Math.round((foreCloseSystemGeneratedInterest) * 100.0) / 100.0);
					Double excessPaidInterest = 0D;
					if (paidSystemGeneratedInterest != null && paidSystemGeneratedInterest != 0)
						excessPaidInterest = paidSystemGeneratedInterest - foreCloseSystemGeneratedInterest;
					Double foreCloseTotalMaturityAmount = 0D;
					if (excessPaidInterest != 0D) {
						foreCloseTotalMaturityAmount = termDepositInvestmentAccountsDTO.getDepositAmount()
								- excessPaidInterest;
					} else {
						foreCloseTotalMaturityAmount = termDepositInvestmentAccountsDTO.getDepositAmount()
								+ foreCloseSystemGeneratedInterest - excessPaidInterest;
					}

//				termDepositInvestmentAccountsDTO.setExcessPaidMaturityInterest(excessPaidInterest);
					termDepositInvestmentAccountsDTO
							.setForeClosureMaturityAmount(Math.round((foreCloseTotalMaturityAmount) * 100.0) / 100.0);
					if (isForeClosure) {
						TermDepositInvestmentAccounts termDepositInvestmentAccounts = termDepositInvestmentAccountsMapper
								.toEntity(termDepositInvestmentAccountsDTO);
						termDepositInvestmentAccountsDTO = termDepositInvestmentAccountsMapper
								.toDto(termDepositInvestmentAccountsRepository.save(termDepositInvestmentAccounts));
					}
				}
			}
		}
		return termDepositInvestmentAccountsDTO;
	}

	public InterestPolicyConfigDTO getFdNonCummulativeInterestPolicyConfig(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) {
		InterestPolicyConfigDTO interestPolicyConfigDTO = null;
		if (termDepositInvestmentAccountsDTO != null) {
			Integer days = 0;
			Integer months = 0;
			Integer years = 0;
			if (termDepositInvestmentAccountsDTO.getTenureInDays() != null) {
				days = termDepositInvestmentAccountsDTO.getTenureInDays();
			}
			if (termDepositInvestmentAccountsDTO.getTenureInMonths() != null) {
				months = termDepositInvestmentAccountsDTO.getTenureInMonths();
			}
			if (termDepositInvestmentAccountsDTO.getTenureInYears() != null) {
				years = termDepositInvestmentAccountsDTO.getTenureInYears();
			}
			Integer tenureType = 0;
			Integer tenureInYears = 0;
			Integer tenureIndays = 0;
			if (years > 0) {
				tenureType = TenureTypeEnum.YEARS.getValue();
				tenureInYears = years;
			} else if (months > 0) {
				Integer inMonths = months / 12;
				if (inMonths >= 1) {
					tenureType = TenureTypeEnum.YEARS.getValue();
					tenureInYears = inMonths;
				} else {
					tenureType = TenureTypeEnum.DAYS.getValue();
					tenureIndays = inMonths * 30;
				}
			} else if (days > 0) {
				tenureType = TenureTypeEnum.DAYS.getValue();
				tenureIndays = days;
			}

			if (tenureType != null && tenureType == TenureTypeEnum.DAYS.getValue()) {
				interestPolicyConfigDTO = interestPolicyConfigMapper.toDto(interestPolicyConfigRepository
						.findByProductIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateIsNullAndStatus(
								termDepositInvestmentAccountsDTO.getProductId(),
								termDepositInvestmentAccountsDTO.getDepositDate(), tenureIndays, tenureIndays,
								tenureType, ApplicationConstants.ACTIVE));
				if (interestPolicyConfigDTO == null)
					interestPolicyConfigDTO = interestPolicyConfigMapper.toDto(interestPolicyConfigRepository
							.findByProductIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateGreaterThanEqualAndStatus(
									termDepositInvestmentAccountsDTO.getProductId(),
									termDepositInvestmentAccountsDTO.getDepositDate(), tenureIndays, tenureIndays,
									tenureType, termDepositInvestmentAccountsDTO.getDepositDate(),
									ApplicationConstants.ACTIVE));
			} else if (tenureType != null && tenureType == TenureTypeEnum.YEARS.getValue()) {
				interestPolicyConfigDTO = interestPolicyConfigMapper.toDto(interestPolicyConfigRepository
						.findByProductIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateIsNullAndStatus(
								termDepositInvestmentAccountsDTO.getProductId(),
								termDepositInvestmentAccountsDTO.getDepositDate(), tenureInYears, tenureInYears,
								tenureType, ApplicationConstants.ACTIVE));
				if (interestPolicyConfigDTO == null)
					interestPolicyConfigDTO = interestPolicyConfigMapper.toDto(interestPolicyConfigRepository
							.findByProductIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateGreaterThanEqualAndStatus(
									termDepositInvestmentAccountsDTO.getProductId(),
									termDepositInvestmentAccountsDTO.getDepositDate(), tenureInYears, tenureInYears,
									tenureType, termDepositInvestmentAccountsDTO.getDepositDate(),
									ApplicationConstants.ACTIVE));
			}
		}
		return interestPolicyConfigDTO;
	}

	public TermDepositInvestmentAccountsDTO getRDMaturityAmountOnMaturityDate(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) throws InvestmentsBusinessException {
		if (termDepositInvestmentAccountsDTO != null && null != termDepositInvestmentAccountsDTO.getId()) {
			InterestPolicyConfigDTO interestPolicyConfigDTO = getRdInterestPolicyConfig(
					termDepositInvestmentAccountsDTO);
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			if (null != termDepositInvestmentAccountsDTO.getDepositDate())
				cal.setTimeInMillis(termDepositInvestmentAccountsDTO.getDepositDate());

			Float rdROI = 0F;
			Integer tenureMonths = 0;

			if (termDepositInvestmentAccountsDTO.getTenureInMonths() != null) {
				tenureMonths = termDepositInvestmentAccountsDTO.getTenureInMonths();
			}
			if (termDepositInvestmentAccountsDTO.getTenureInYears() != null) {
				tenureMonths += (termDepositInvestmentAccountsDTO.getTenureInYears() * 12);
			}
			Double products = 0D;
			Double installment = 0D;
			if (null != termDepositInvestmentAccountsDTO.getInstallmentAmount())
				installment = termDepositInvestmentAccountsDTO.getInstallmentAmount();
			for (int i = 1; i <= tenureMonths; i++) {
				products = products + (installment * i);
			}
			Double interest = (products * interestPolicyConfigDTO.getGeneralRoi()) / 1200;
			Double depositAmount = (installment * tenureMonths);
			Double maturityValue = depositAmount + interest;

			Calendar matureCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			matureCal.setTimeInMillis(termDepositInvestmentAccountsDTO.getDepositDate());
			if (tenureMonths != null) {
				matureCal.add(Calendar.MONTH, tenureMonths.intValue());
				termDepositInvestmentAccountsDTO.setMaturityDate(matureCal.getTimeInMillis());
			}
			termDepositInvestmentAccountsDTO.setMaturityAmount(Math.round((maturityValue) * 100.0) / 100.0);
			termDepositInvestmentAccountsDTO.setMaturityInterest(Math.round((interest) * 100.0) / 100.0);
			termDepositInvestmentAccountsDTO.setDepositAmount(depositAmount);
			termDepositInvestmentAccountsDTO.setForeClosureMaturityAmount(null);

			List<InvestmentAccountInstallmentsDTO> termAccountInstallmentsDTOList = new ArrayList<InvestmentAccountInstallmentsDTO>();
			// For first Installment
			Long firstInstallmentStartDate = cal.getTimeInMillis();
			InvestmentAccountInstallments firstTermAccountInstallments = investmentAccountInstallmentsRepository
					.findByTermAccIdAndInstallmentDate(termDepositInvestmentAccountsDTO.getId(), cal.getTimeInMillis());

			if (firstTermAccountInstallments == null)
				firstTermAccountInstallments = new InvestmentAccountInstallments();
			firstTermAccountInstallments.setInstallmentDate(firstInstallmentStartDate);
			firstTermAccountInstallments.setTermAccId(termDepositInvestmentAccountsDTO.getId());
			firstTermAccountInstallments.setInstallmentNumber(1);
			if (null != termDepositInvestmentAccountsDTO.getInstallmentAmount())
				firstTermAccountInstallments.setInstallmentAmount(
						Math.round((termDepositInvestmentAccountsDTO.getInstallmentAmount()) * 100.0) / 100.0);

			termAccountInstallmentsDTOList.add(investmentAccountInstallmentsMapper.toDto(firstTermAccountInstallments));
			// For next Installments
			for (int i = 2; i <= tenureMonths; i++) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
				cal.add(Calendar.DAY_OF_MONTH, -1);
				cal.add(Calendar.MONTH, 1);
				InvestmentAccountInstallments termAccountInstallments = investmentAccountInstallmentsRepository
						.findByTermAccIdAndInstallmentDate(termDepositInvestmentAccountsDTO.getId(),
								cal.getTimeInMillis());
				if (termAccountInstallments == null)
					termAccountInstallments = new InvestmentAccountInstallments();
				termAccountInstallments.setInstallmentDate(cal.getTimeInMillis());
				termAccountInstallments.setTermAccId(termDepositInvestmentAccountsDTO.getId());
				termAccountInstallments.setInstallmentNumber(i);
				if (null != termDepositInvestmentAccountsDTO.getInstallmentAmount())
					termAccountInstallments.setInstallmentAmount(
							Math.round((termDepositInvestmentAccountsDTO.getInstallmentAmount()) * 100.0) / 100.0);
				termAccountInstallmentsDTOList.add(investmentAccountInstallmentsMapper.toDto(termAccountInstallments));
			}
			termDepositInvestmentAccountsDTO.setInvestmentAccountInstallmentsDTOList(termAccountInstallmentsDTOList);
		}
		return termDepositInvestmentAccountsDTO;
	}

	public TermDepositInvestmentAccountsDTO getRDMaturityAmountOnForeclosure(TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO)
			throws InvestmentsBusinessException {
		if (null != termDepositInvestmentAccountsDTO && null != termDepositInvestmentAccountsDTO.getId()) {
			InterestPolicyConfigDTO interestPolicyConfigDTO = getRdInterestPolicyConfig(termDepositInvestmentAccountsDTO);
			if (interestPolicyConfigDTO != null) {
				if (interestPolicyConfigDTO.getPenalRoi() != null)
					termDepositInvestmentAccountsDTO.setForeClosureRoi(interestPolicyConfigDTO.getPenalRoi());
				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
				if (null != termDepositInvestmentAccountsDTO.getDepositDate())
					cal.setTimeInMillis(termDepositInvestmentAccountsDTO.getDepositDate());

				Calendar closingDate = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
				termDepositInvestmentAccountsDTO.setClosureDate(closingDate.getTimeInMillis());

				if (termDepositInvestmentAccountsDTO.getTenureInMonths() != null || termDepositInvestmentAccountsDTO.getTenureInYears() != null) {
					Long foreCloseTenureDays = 0L;
					Long foreCloseTenureMonths = 0L;

					Integer day = cal.get(Calendar.DAY_OF_MONTH);
					Integer month = cal.get(Calendar.MONTH);
					Integer year = cal.get(Calendar.YEAR);
					LocalDate openDate = LocalDate.of(year, month + 1, day);

					Integer closeDay = closingDate.get(Calendar.DAY_OF_MONTH);
					Integer closeMonth = closingDate.get(Calendar.MONTH);
					Integer closeYear = closingDate.get(Calendar.YEAR);
					LocalDate closeDate = LocalDate.of(closeYear, closeMonth + 1, closeDay);
					Period difference = Period.between(openDate, closeDate);

					foreCloseTenureDays = (long) difference.getDays();
					foreCloseTenureMonths = (long) difference.getMonths();
					foreCloseTenureMonths += (long) (difference.getYears() * 12);
					Double foreCloseProducts = 0D;
					Double foreCloseInstallment = termDepositInvestmentAccountsDTO.getInstallmentAmount();
					
					CommonStatus commonStatus = commonStatusRepository.findByName(CommonStatusEnum.PAID.getName());
					List<InvestmentAccountInstallments> termAccInstallmentsList = null;
					if(null != termDepositInvestmentAccountsDTO.getId() && null != commonStatus && null != commonStatus.getId())
						termAccInstallmentsList = investmentAccountInstallmentsRepository.findByTermAccIdAndStatus(termDepositInvestmentAccountsDTO.getId(), commonStatus.getId());
					if(null != termAccInstallmentsList && !termAccInstallmentsList.isEmpty()) {
						for (int i = 0; i < termAccInstallmentsList.size(); i++) {
							foreCloseProducts = foreCloseProducts
									+ (termDepositInvestmentAccountsDTO.getInstallmentAmount() * (foreCloseTenureMonths - i));
						}
						Double foreCloseInterest = (foreCloseProducts * interestPolicyConfigDTO.getPenalRoi()) / 1200;
						Double foreClosureDaysInterest = (termAccInstallmentsList.size()
								* termDepositInvestmentAccountsDTO.getInstallmentAmount() * interestPolicyConfigDTO.getPenalRoi() * foreCloseTenureDays) / 36500;
						Double foreCloseMaturityValue = (foreCloseInstallment * foreCloseTenureMonths) + foreCloseInterest
								+ foreClosureDaysInterest;
						termDepositInvestmentAccountsDTO.setForeClosureMaturityAmount(Math.round((foreCloseMaturityValue) * 100.0) / 100.0);
						termDepositInvestmentAccountsDTO.setForeClosureMaturityIntrestAmount(Math.round((foreCloseInterest) * 100.0) / 100.0);
						termDepositInvestmentAccountsDTO.setIsForeClosure(ApplicationConstants.TRUE);
					}
				}
			}
		}
		return termDepositInvestmentAccountsDTO;
	}
	
	public InterestPolicyConfigDTO getRdInterestPolicyConfig(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) {
		CardRatesDTO cardRatesDTO = null;
		List<InterestPolicyConfigDTO> rdInterestPolicyConfigDTOList = null;
		InterestPolicyConfigDTO interestPolicyConfigDTO = null;
		if (termDepositInvestmentAccountsDTO != null) {
			Boolean isProductRoi = false;
			if (termDepositInvestmentAccountsDTO.getProductId() != null) {
				rdInterestPolicyConfigDTOList = interestPolicyConfigMapper.toDto(interestPolicyConfigRepository
						.findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndStatus(
								termDepositInvestmentAccountsDTO.getProductId(),
								termDepositInvestmentAccountsDTO.getDepositDate(), ApplicationConstants.ACTIVE));
				if (rdInterestPolicyConfigDTOList == null
						|| (rdInterestPolicyConfigDTOList != null && rdInterestPolicyConfigDTOList.isEmpty()))
					rdInterestPolicyConfigDTOList = interestPolicyConfigMapper.toDto(interestPolicyConfigRepository
							.findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateGreaterThanEqualAndStatus(
									termDepositInvestmentAccountsDTO.getProductId(),
									termDepositInvestmentAccountsDTO.getDepositDate(),
									termDepositInvestmentAccountsDTO.getDepositDate(), ApplicationConstants.ACTIVE));
				if (rdInterestPolicyConfigDTOList != null && !rdInterestPolicyConfigDTOList.isEmpty()) {
					interestPolicyConfigDTO = rdInterestPolicyConfigDTOList.get(0);
					if (termDepositInvestmentAccountsDTO.getIsForeClosure() != null
							&& termDepositInvestmentAccountsDTO.getIsForeClosure()) {
						if (interestPolicyConfigDTO.getForeClosureInterestApplicableAs() != null
								&& interestPolicyConfigDTO
										.getForeClosureInterestApplicableAs() == InterestApplicableTypeEnum.PRODUCT
												.getValue()) {
							isProductRoi = ApplicationConstants.TRUE;
						}
					}
					if (termDepositInvestmentAccountsDTO.getIsAutoRenewal() != null
							&& termDepositInvestmentAccountsDTO.getIsAutoRenewal()) {
						if (interestPolicyConfigDTO.getRenewalInterestApplicableAs() != null && interestPolicyConfigDTO
								.getRenewalInterestApplicableAs() == InterestApplicableTypeEnum.PRODUCT.getValue()) {
							isProductRoi = ApplicationConstants.TRUE;
						}
					}
				}
			}
			if (isProductRoi != null && !isProductRoi) {
				Integer days = 0;
				Integer months = 0;
				Integer years = 0;
				if (termDepositInvestmentAccountsDTO.getTenureInDays() != null) {
					days = termDepositInvestmentAccountsDTO.getTenureInDays();
				}
				if (termDepositInvestmentAccountsDTO.getTenureInMonths() != null) {
					months = termDepositInvestmentAccountsDTO.getTenureInMonths();
				}
				if (termDepositInvestmentAccountsDTO.getTenureInYears() != null) {
					years = termDepositInvestmentAccountsDTO.getTenureInYears();
				}
				Integer tenureType = 0;
				Integer tenureInYears = 0;
				Integer tenureIndays = 0;
				if (years > 0) {
					tenureType = TenureTypeEnum.YEARS.getValue();
					tenureInYears = years;
				} else if (months > 0) {
					Integer inMonths = months / 12;
					if (inMonths >= 1) {
						tenureType = TenureTypeEnum.YEARS.getValue();
						tenureInYears = inMonths;
					} else {
						tenureType = TenureTypeEnum.DAYS.getValue();
						tenureIndays = inMonths * 30;
					}
				} else if (days > 0) {
					tenureType = TenureTypeEnum.DAYS.getValue();
					tenureIndays = days;
				}

				if (tenureType != null && tenureType == TenureTypeEnum.DAYS.getValue()) {
					cardRatesDTO = cardRatesMapper.toDto(cardRatesRepository
							.findByPacsIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateIsNullAndStatus(
									termDepositInvestmentAccountsDTO.getPacsId(),
									termDepositInvestmentAccountsDTO.getDepositDate(), tenureIndays, tenureIndays,
									tenureType, ApplicationConstants.ACTIVE));
					if (cardRatesDTO == null)
						cardRatesDTO = cardRatesMapper.toDto(cardRatesRepository
								.findByPacsIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateGreaterThanEqualAndStatus(
										termDepositInvestmentAccountsDTO.getPacsId(),
										termDepositInvestmentAccountsDTO.getDepositDate(), tenureIndays, tenureIndays,
										tenureType, termDepositInvestmentAccountsDTO.getDepositDate(),
										ApplicationConstants.ACTIVE));
				} else if (tenureType != null && tenureType == TenureTypeEnum.YEARS.getValue()) {
					cardRatesDTO = cardRatesMapper.toDto(cardRatesRepository
							.findByPacsIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateIsNullAndStatus(
									termDepositInvestmentAccountsDTO.getPacsId(),
									termDepositInvestmentAccountsDTO.getDepositDate(), tenureInYears, tenureInYears,
									tenureType, ApplicationConstants.ACTIVE));
					if (cardRatesDTO == null)
						cardRatesDTO = cardRatesMapper.toDto(cardRatesRepository
								.findByPacsIdAndEffectiveStartDateLessThanEqualAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndTenureTypeAndEffectiveEndDateGreaterThanEqualAndStatus(
										termDepositInvestmentAccountsDTO.getPacsId(),
										termDepositInvestmentAccountsDTO.getDepositDate(), tenureInYears, tenureInYears,
										tenureType, termDepositInvestmentAccountsDTO.getDepositDate(),
										ApplicationConstants.ACTIVE));
				}
			}
			if (cardRatesDTO != null && interestPolicyConfigDTO != null) {
				interestPolicyConfigDTO.setGeneralRoi(cardRatesDTO.getGeneralRoi());
				interestPolicyConfigDTO.setPenalRoi(cardRatesDTO.getPenalRoi());
			}
		}
		return interestPolicyConfigDTO;
	}

}
