package coding.challenge.service;

import coding.challenge.TestUtils;
import coding.challenge.exception.CustomException;
import coding.challenge.model.Telephone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class TelephoneServiceTest {

    TelephoneServiceImpl telephoneService = new TelephoneServiceImpl();

    @Before
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        Field field = TelephoneServiceImpl.class.getDeclaredField("telephoneList");
        field.setAccessible(true);
        field.set(telephoneService, TestUtils.getTelephones());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetAllTelephoneNumbersThrowsExceptionWhenMapIsEmpty() throws Exception {
        thrown.expect(CustomException.class);
        thrown.expectMessage("No telephone numbers present");
        Field field = TelephoneServiceImpl.class.getDeclaredField("telephoneList");
        field.setAccessible(true);
        field.set(telephoneService,null);
        telephoneService.getAllTelephoneNumbers();
    }

    @Test
    public void testGetAllPhoneNumbersReturnsAllPhoneNumbers() throws Exception {
        List<Telephone> result = telephoneService.getAllTelephoneNumbers();
        Assert.assertThat(result,is(equalTo(TestUtils.getTelephones())));
    }

    @Test
    public void testGetPhoneNumberByNameThrowsExceptionWhenNameIsntPresentInMap() throws CustomException {
        thrown.expect(CustomException.class);
        thrown.expectMessage("No telephone numbers present for this name");
        telephoneService.getPhoneNumberByName("Robert Smith");
    }

    @Test
    public void testGetPhoneNumberByNameReturnsCorrectPhoneNumbers() throws CustomException {
        Telephone telephone = new Telephone();
        Map telMap= new HashMap<>();
        telMap.put("07515423654", true);
        telMap.put("09876234654", false);
        telephone.setCustomerName("Simon Davies");
        telephone.setTelephoneNumber(telMap);
        Assert.assertThat(telephoneService.getPhoneNumberByName("Simon Davies").toString(), is(equalTo("[" + telephone.toString() + "]")));
    }

    @Test
    public void testActivateTelNumber() throws CustomException {
        Assert.assertThat(telephoneService.activateTelephoneNumber("01132 544789"), is(true));
    }

    @Test
    public void testActivateNumberWhenAlreadyActivatedThrowsException() throws CustomException {
        thrown.expect(CustomException.class);
        thrown.expectMessage("number already activated");
        telephoneService.activateTelephoneNumber("01709 678543");
    }

    @Test
    public void testActivateNumberWithNumberThatDoesntExist() throws CustomException {
        thrown.expect(CustomException.class);
        thrown.expectMessage("Number doesn't exist");
        telephoneService.activateTelephoneNumber("999");
    }

}
