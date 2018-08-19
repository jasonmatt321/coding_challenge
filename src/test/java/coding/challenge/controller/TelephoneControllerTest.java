package coding.challenge.controller;

import coding.challenge.TestUtils;
import coding.challenge.exception.CustomException;
import coding.challenge.model.Telephone;
import coding.challenge.service.TelephoneServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TelephoneControllerTest {

    @InjectMocks
    TelephoneController telephoneController;

    @Mock
    TelephoneServiceImpl telephoneService;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(telephoneController).build();
    }

    @Test
    public void testGetAllTelephoneNumbersReturnsSuccesfulResponseWhenTelephoneNumbersExist() throws Exception {

        when(telephoneService.getAllTelephoneNumbers()).thenReturn(TestUtils.getTelephones());
        mockMvc.perform(get("/getPhoneNumbers"))
                .andExpect(status().is(200));
    }

    @Test
    public void testGetAllTelephoneNumbersReturnsNotFoundResponseWhenTelephoneNumbersDontExist() throws Exception {
        when(telephoneService.getAllTelephoneNumbers()).thenThrow(new CustomException("Number doesn't exist"));
        mockMvc.perform(get("/getPhoneNumbers"))
                .andExpect(status().is(404));
    }

    @Test
    public void testGetPhoneNumberByNameReturnsSuccesfulResponseWhenTelephoneNumbersExist() throws Exception {
        List<Telephone> telephoneList = new ArrayList<>();
        telephoneList.add(TestUtils.getTelephones().get(0));

        when(telephoneService.getPhoneNumberByName("Simon Davies")).thenReturn(telephoneList);

        mockMvc.perform(post("/getPhoneNumberByName")
                .content("Simon Davies"))
                .andExpect(status().is(200));
    }

    @Test
    public void testGetPhoneNumberByNameReturnsNotFoundResponseWhenNameOrNumberDoesntExist() throws Exception {
        when(telephoneService.getPhoneNumberByName("Jack Barnes")).thenThrow(new CustomException("no-one found with that name"));
        mockMvc.perform(post("/getPhoneNumberByName")
                .content("Jack Barnes"))
                .andExpect(status().is(404));
    }

    @Test
    public void testActivatePhoneNumberReturnsSuccessResponseWhenNumberIsActivated() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Telephone telephone = new Telephone();
        Map<String, Boolean> map = new HashMap<>();

        telephone.setTelephoneNumber(map);
        telephone.setCustomerName("Joe Bloggs");
        telephone.setTelephoneNumber(map);

        String jsonContent = mapper.writeValueAsString(telephone);

        when(telephoneService.activateTelephoneNumber(anyObject())).thenReturn(true);
        mockMvc.perform(put("/activatePhoneNumber")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().string("number activated"));
    }

    @Test
    public void testActivatePhoneNumberReturnsSuccessResponseWhenNumberIsAlreadyActivated() throws Exception {

            ObjectMapper mapper = new ObjectMapper();
            Telephone telephone = new Telephone();
            Map<String, Boolean> map = new HashMap<>();

            telephone.setTelephoneNumber(map);
            telephone.setCustomerName("Joe Bloggs");
            telephone.setTelephoneNumber(map);

            String jsonContent = mapper.writeValueAsString(telephone);

            when(telephoneService.activateTelephoneNumber(anyObject())).thenReturn(false);
            mockMvc.perform(put("/activatePhoneNumber")
                    .content(jsonContent)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(200))
                    .andExpect(content().string("number already activated"));
    }

    @Test
    public void testActivatePhoneNumberReturnsNotFoundWhenNumberNotFound() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Telephone telephone = new Telephone();
        Map<String, Boolean> map = new HashMap<>();

        telephone.setTelephoneNumber(map);
        telephone.setCustomerName("Joe Bloggs");
        telephone.setTelephoneNumber(map);

        String jsonContent = mapper.writeValueAsString(telephone);

        when(telephoneService.activateTelephoneNumber(anyObject())).thenThrow(new CustomException("number doesn't exist"));
        mockMvc.perform(put("/activatePhoneNumber")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andExpect(content().string("number doesn't exist"));
    }
}
