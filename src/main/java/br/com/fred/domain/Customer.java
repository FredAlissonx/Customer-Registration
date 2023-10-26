package br.com.fred.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class Customer {

    private String name;
    private Long cpf;
    private Long phoneNumber;
    private String address;
    private Integer number;
    private String city;
    private String state;
    public Customer(String name, String cpf, String phoneNumber, String address, String number, String city, String state) {
        this.name = name;
        this.cpf = Long.valueOf(cpf.trim());
        this.phoneNumber = Long.valueOf(phoneNumber.trim());
        this.address = address;
        this.number = Integer.valueOf(number);
        this.city = city;
        this.state = state;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(cpf, customer.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "br.com.fred.domain.Customer{" +
                "name='" + name + '\'' +
                ", cpf=" + cpf +
                '}';
    }
}
