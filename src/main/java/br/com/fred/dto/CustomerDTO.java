package br.com.fred.dto;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@Data
public class CustomerDTO {

    private String firstName;
    private String lastName;
    @Setter(value= AccessLevel.NONE) // it won´t generate a setter for cpf
    private Long cpf;
    private Long phoneNumber;
    private String address;
    private String city;
    private String state;

    public CustomerDTO(String firstName,String lastName, String cpf, String phoneNumber, String address, String city, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = Long.valueOf(cpf.trim());
        this.phoneNumber = Long.valueOf(phoneNumber.trim());
        this.address = address;
        this.city = city;
        this.state = state;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO customerDTO)) return false;
        return Objects.equals(cpf, customerDTO.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Name: " + firstName + ", CPF: " + cpf;
    }
}
