package com.intellect.investmentsms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.intellect.investmentsms.domain.CardRates;
import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.repository.CardRatesRepository;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.service.dto.CardRatesDTO;
import com.intellect.investmentsms.service.mapper.CardRatesMapper;

@SpringBootTest(classes = { TestCardRatesServiceImpl.class })
public class TestCardRatesServiceImpl {

	@Mock
	private CardRatesRepository cardRatesRepository;

	@Mock
	private CommonStatusRepository commonStatusRepository;

	@Mock
	private CardRatesMapper cardRatesMapper;

	@InjectMocks
	private CardRatesServiceImpl cardRatesServiceImpl;

	private CardRates cardRates;
	private CommonStatus commonStatus;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		// Mock data setup
		cardRates = new CardRates();
		cardRates.setId(1L);
		cardRates.setStatus(1); // Assuming status field is set

		commonStatus = new CommonStatus();
		commonStatus.setId(1L);
		commonStatus.setName("Active");

		// Stubbing repository methods
		when(cardRatesRepository.findById(anyLong())).thenReturn(Optional.of(cardRates));
		when(commonStatusRepository.findById(anyLong())).thenReturn(Optional.of(commonStatus));

		// Stubbing mapper method
		when(cardRatesMapper.toDto(cardRates)).thenReturn(createCardRatesDTO(cardRates, commonStatus));
	}

	@Test
	public void testFindOne() {
		Long id = 1L;
		CardRatesDTO cardRatesDTO = cardRatesServiceImpl.findOne(id);

		// Asserting that cardRatesDTO is not null before accessing its properties
		assertNotNull(cardRatesDTO, "CardRatesDTO should not be null");

		// Asserting specific properties of cardRatesDTO
		assertEquals(cardRates.getId(), cardRatesDTO.getId());
		// Add more assertions for other fields if necessary
	}

	// Helper method to create CardRatesDTO with status name
	private CardRatesDTO createCardRatesDTO(CardRates cardRates, CommonStatus commonStatus) {
		CardRatesDTO cardRatesDTO = new CardRatesDTO();
		cardRatesDTO.setId(cardRates.getId());
		cardRatesDTO.setStatus(cardRates.getStatus()); // Assuming status is mapped
		cardRatesDTO.setStatusName(commonStatus.getName());
		return cardRatesDTO;
	}

}
