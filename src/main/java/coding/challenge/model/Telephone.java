package coding.challenge.model;

import java.util.Map;
import java.util.Objects;

public class Telephone {

    private String customerName;

    private Map<String, Boolean> telephoneNumber;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Map<String, Boolean> getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Map<String, Boolean> telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Telephone)) return false;
        Telephone telephone = (Telephone) o;
        return Objects.equals(customerName, telephone.customerName) &&
                Objects.equals(telephoneNumber, telephone.telephoneNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(customerName, telephoneNumber);
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "customerName='" + customerName + '\'' +
                ", telephoneNumber=" + telephoneNumber +
                '}';
    }
}
