package coding.challenge;

import coding.challenge.model.Telephone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtils {

    public static final List<Telephone> getTelephones() {

        List<Telephone> telephoneList = new ArrayList<>();
        Telephone telephone = new Telephone();
        Map<String, Boolean> phoneMap = new HashMap<>();

        phoneMap.put("07515423654", true);
        phoneMap.put("09876234654", false);
        telephone.setCustomerName("Simon Davies");
        telephone.setTelephoneNumber(phoneMap);
        telephoneList.add(telephone);

        Telephone telephone2 = new Telephone();
        Map<String, Boolean> phoneMap2 = new HashMap<>();
        phoneMap2.put("01456 345273", true);
        telephone2.setCustomerName("Michelle Smith");
        telephone2.setTelephoneNumber(phoneMap2);
        telephoneList.add(telephone2);

        Telephone telephone3 = new Telephone();
        Map<String, Boolean> phoneMap3 = new HashMap<>();
        phoneMap3.put("01709 678543", true);
        phoneMap3.put("07709546789", false);
        telephone3.setTelephoneNumber(phoneMap3);
        telephone3.setCustomerName("Curtis Strange");
        telephoneList.add(telephone3);

        Telephone telephone4 = new Telephone();
        Map<String, Boolean> phoneMap4 = new HashMap<>();
        phoneMap4.put("01132 544789", false);
        telephone4.setCustomerName("Rachel Grieves");
        telephone4.setTelephoneNumber(phoneMap4);
        telephoneList.add(telephone4);

        return telephoneList;
    }

}
