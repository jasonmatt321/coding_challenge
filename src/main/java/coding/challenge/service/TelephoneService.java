package coding.challenge.service;

import coding.challenge.exception.CustomException;
import coding.challenge.model.Telephone;

import java.util.List;

public interface TelephoneService {

    public List<Telephone> getAllTelephoneNumbers() throws CustomException;

    public List<Telephone> getPhoneNumberByName(String name) throws CustomException;

    public Boolean activateTelephoneNumber(String telephone) throws CustomException;
}
