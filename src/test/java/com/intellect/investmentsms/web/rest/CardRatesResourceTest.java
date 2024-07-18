package com.intellect.investmentsms.web.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.CardRatesService;
import com.intellect.investmentsms.service.dto.CardRatesDTO;

@ComponentScan(basePackages = "com.intellect.investmentsms")
@SpringBootTest(classes = {CardRatesResourceTest.class})
@AutoConfigureMockMvc
@ContextConfiguration
public class CardRatesResourceTest {

    @InjectMocks
    private CardRatesResource cardRatesResource;

    @Mock
    private CardRatesService cardRatesService;

    @Autowired
    private MockMvc mockMvc;

    private CardRatesDTO cardRatesDTO;
    
    private List<CardRatesDTO> cardRatesDTOList;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cardRatesResource).build();

        cardRatesDTO = new CardRatesDTO();
        cardRatesDTO.setId(1L);
        cardRatesDTO.setPacsId(123L);
        cardRatesDTO.setTenureType(1);
        cardRatesDTO.setMinTenure(6);
        cardRatesDTO.setMaxTenure(12);
        cardRatesDTO.setGeneralRoi(5.5f);
        cardRatesDTO.setStaffRoi(6.0f);
        cardRatesDTO.setSeniorcitizenRoi(5.0f);
        cardRatesDTO.setPenalRoi(7.5f);
        cardRatesDTO.setEffectiveStartDate(System.currentTimeMillis());
        cardRatesDTO.setEffectiveEndDate(System.currentTimeMillis() + 86400000);
        cardRatesDTO.setStatus(1);
        
        cardRatesDTOList = new ArrayList<>();

	    CardRatesDTO cardRatesDTO1 = new CardRatesDTO();
	    cardRatesDTO1.setId(1L);
	    cardRatesDTO1.setPacsId(123L);
	    cardRatesDTO1.setTenureType(1);
	    cardRatesDTO1.setMinTenure(6);
	    cardRatesDTO1.setMaxTenure(12);
	    cardRatesDTO1.setGeneralRoi(5.5f);
	    cardRatesDTO1.setStaffRoi(6.0f);
	    cardRatesDTO1.setSeniorcitizenRoi(5.0f);
	    cardRatesDTO1.setPenalRoi(7.5f);
	    cardRatesDTO1.setEffectiveStartDate(System.currentTimeMillis());
	    cardRatesDTO1.setEffectiveEndDate(System.currentTimeMillis() + 86400000);
	    cardRatesDTO1.setStatus(1);


	    CardRatesDTO cardRatesDTO2 = new CardRatesDTO();
	    cardRatesDTO2.setId(2L);
	    cardRatesDTO2.setPacsId(124L);
	    cardRatesDTO2.setTenureType(2);
	    cardRatesDTO2.setMinTenure(7);
	    cardRatesDTO2.setMaxTenure(13);
	    cardRatesDTO2.setGeneralRoi(6.0f);
	    cardRatesDTO2.setStaffRoi(6.5f);
	    cardRatesDTO2.setSeniorcitizenRoi(5.5f);
	    cardRatesDTO2.setPenalRoi(8.0f);
	    cardRatesDTO2.setEffectiveStartDate(System.currentTimeMillis());
	    cardRatesDTO2.setEffectiveEndDate(System.currentTimeMillis() + 86400000);
	    cardRatesDTO2.setStatus(1);

	    cardRatesDTOList.add(cardRatesDTO1);
	    cardRatesDTOList.add(cardRatesDTO2);
    }

    @Test
    public void testGetCardRate() throws Exception {
        Long id = 1L;

        when(cardRatesService.findOne(id)).thenReturn(cardRatesDTO);

        this.mockMvc.perform(get("/card_rates/get")
        		.header("id", id.toString())  
                .header("userid", "1")
                .header("authToken", "authToken")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("SUCCESS")))
				.andExpect(jsonPath("$.statusMsg", is("Card Rate(s) Fetch Successfully")))
                .andDo(print());
    }
    
    @Test
    public void testGetCardRate_InvestmentsBusinessException() throws Exception {
        Long id = 1L; 

        when(cardRatesService.findOne(id)).thenThrow(new InvestmentsBusinessException("Error message"));

        this.mockMvc.perform(get("/card_rates/get")
                .header("id", id.toString())
                .header("userid", "1")
                .header("authToken", "authToken")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("ERROR"))) 
				.andExpect(jsonPath("$.statusMsg", is("Card Rate(s) Fetch Failed")))
                .andDo(print());
    }
    
    @Test
    public void testGetCardRate_GeneralException() throws Exception {
        Long id = 1L;

        when(cardRatesService.findOne(id)).thenThrow(new RuntimeException("Error message"));

        this.mockMvc.perform(get("/card_rates/get")
                .header("id", id.toString())
                .header("userid", "1")
                .header("authToken", "authToken")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("ERROR")))
				.andExpect(jsonPath("$.statusMsg", is("Internal server error")))
                .andDo(print());
    }
    
    @Test
    public void testGetAllCardRates() throws Exception {
    	when(cardRatesService.findAll()).thenReturn(cardRatesDTOList);
    	this.mockMvc.perform(get("/card_rates/get_all")
    			.header("userid", "1")
    			.header("authToken", "authToken")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
    			.andExpect(jsonPath("$.status", is("SUCCESS")))
				.andExpect(jsonPath("$.statusMsg", is("Card Rate(s) Fetch Successfully")))
    			.andDo(print());
    }
    
    @Test
    public void testGetAllCardRates_InvestmentsBusinessException() throws Exception {
    	when(cardRatesService.findAll()).thenThrow(new InvestmentsBusinessException("Error message"));
    	this.mockMvc.perform(get("/card_rates/get_all")
    			.header("userid", "1")
    			.header("authToken", "authToken")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
    			.andExpect(jsonPath("$.status", is("ERROR")))
				.andExpect(jsonPath("$.statusMsg", is("Card Rate(s) Fetch Failed")))
    			.andDo(print());
    }
    
    @Test
    public void testGetAllCardRates_GeneralException() throws Exception{
    	when(cardRatesService.findAll()).thenThrow(new RuntimeException("Error message"));
    	this.mockMvc.perform(get("/card_rates/get_all")
    			.header("userid", "1")
    			.header("authToken", "authToken")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
    			.andExpect(jsonPath("$.status", is("ERROR")))
				.andExpect(jsonPath("$.statusMsg", is("Internal server error")))
    			.andDo(print());
    }
    
	@Test
    public void testCreateCardRates() throws Exception {
		CardRatesDTO cardRateDTO = new CardRatesDTO();
		cardRateDTO.setPacsId(123L);
		cardRateDTO.setTenureType(1);
		cardRateDTO.setMinTenure(6);
		cardRateDTO.setMaxTenure(12);
		cardRateDTO.setGeneralRoi(5.5f);
		cardRateDTO.setStaffRoi(6.0f);
		cardRateDTO.setSeniorcitizenRoi(5.0f);
		cardRateDTO.setPenalRoi(7.5f);
		cardRateDTO.setEffectiveStartDate(System.currentTimeMillis());
		cardRateDTO.setEffectiveEndDate(System.currentTimeMillis() + 86400000);
		cardRateDTO.setStatus(1); 
		
		when(cardRatesService.save(any(CardRatesDTO.class))).thenReturn(cardRatesDTO);
    	ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cardRateDTO);
        byte[] jsonBytes = json.getBytes("UTF-8");
    	this.mockMvc.perform(post("/card_rates/add")
    			.header("userId", 1L)
    			.header("authToken", "authToken")
    			.content(jsonBytes)
    			.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status", is("SUCCESS")))
				.andExpect(jsonPath("$.statusMsg", is("Card Rate(s) Created Successfully")))
				.andDo(print());
    }
	
	@Test
	public void testCreateCardRates_InvestmentsBusinessException() throws Exception {
		CardRatesDTO cardRateDTO = new CardRatesDTO();
		cardRateDTO.setPacsId(123L);
		cardRateDTO.setTenureType(1);
		cardRateDTO.setMinTenure(6);
		cardRateDTO.setMaxTenure(12);
		cardRateDTO.setGeneralRoi(5.5f);
		cardRateDTO.setStaffRoi(6.0f);
		cardRateDTO.setSeniorcitizenRoi(5.0f);
		cardRateDTO.setPenalRoi(7.5f);
		cardRateDTO.setEffectiveStartDate(System.currentTimeMillis());
		cardRateDTO.setEffectiveEndDate(System.currentTimeMillis() + 86400000);
		cardRateDTO.setStatus(1); 
		
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cardRateDTO);
        byte[] jsonBytes = json.getBytes("UTF-8");
		
	    when(cardRatesService.save(any(CardRatesDTO.class))).thenThrow(new InvestmentsBusinessException("Error message"));
	    this.mockMvc.perform(post("/card_rates/add")
	           .header("userId", 1L)
	           .header("authToken", "authToken")
	           .content(jsonBytes)
	           .contentType(MediaType.APPLICATION_JSON))
	           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	           .andExpect(jsonPath("$.status", is("ERROR")))
	           .andExpect(jsonPath("$.statusMsg").value("Card Rate(s) Create Failed,Error message"))
	           .andDo(print());

	}
}
