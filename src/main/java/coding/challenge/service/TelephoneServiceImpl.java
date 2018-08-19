package coding.challenge.service;

import coding.challenge.exception.CustomException;
import coding.challenge.model.Telephone;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class TelephoneServiceImpl implements TelephoneService {

    private List<Telephone> telephoneList = new ArrayList();

    public List<Telephone> getAllTelephoneNumbers() throws CustomException {
        if (isNull(telephoneList) || telephoneList.isEmpty()) {
            throw new CustomException("No telephone numbers present");
        }
        return telephoneList;
    }

    public List<Telephone> getPhoneNumberByName(String name) throws CustomException {
        List<Telephone> telList = new ArrayList<>();
        for (Telephone tel : telephoneList) {
            if (tel.getCustomerName().equals(name)) {
                telList = telephoneList.stream().filter(t -> t.getCustomerName().equals(name)).collect(Collectors.toList());
            }
        }
        if (telList.isEmpty()) {
            throw new CustomException("No telephone numbers present for this name");
        } else {
            return telList;
        }
    }

    public Boolean activateTelephoneNumber(String telephone) throws CustomException {

        List<Telephone> tel = telephoneList.stream().filter(telephone1 -> telephone1.getTelephoneNumber().containsKey(telephone)).collect(Collectors.toList());
        if (isEmpty(tel)) {
            throw new CustomException("Number doesn't exist");
        }
        Telephone number = tel.get(0);
        Boolean isNumberAlreadyActivated = number.getTelephoneNumber().get(telephone);
        if (!isNumberAlreadyActivated) {
            Map<String, Boolean> map = number.getTelephoneNumber();
            map.put(telephone, true);
            number.setTelephoneNumber(map);
            telephoneList.remove(tel.get(0));
            telephoneList.add(number);
            return true;
        } else {
            throw new CustomException("number already activated");
        }
    }

}
