package br.com.customerregistration.dto;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@Data
public class CustomerDTO {

    private String firstName;
    private String lastName;
    private final String cpf;
    private String phoneNumber;
    private String city;
    private String state;
    private String cep;

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
}
