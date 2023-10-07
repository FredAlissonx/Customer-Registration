package br.com.fred.domain;

import java.util.Objects;

public class Costumer {

    private String name;
    private Long cpf;
    private Long phoneNumber;
    private String address;
    private Integer number;
    private String city;
    private String state;
    public Costumer(){
    }

    public Costumer(String name, String cpf, String phoneNumber, String address, String number, String city, String state) {
        this.name = name;
        this.cpf = Long.valueOf(cpf.trim());
        this.phoneNumber = Long.valueOf(phoneNumber.trim());
        this.address = address;
        this.number = Integer.valueOf(number);
        this.city = city;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getTel() {
        return phoneNumber;
    }

    public void setTel(Long tel) {
        this.phoneNumber = tel;
    }

    public String getEnd() {
        return address;
    }

    public void setEnd(String end) {
        this.address = end;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Costumer costumer)) return false;
        return Objects.equals(cpf, costumer.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "br.com.fred.domain.Costumer{" +
                "name='" + name + '\'' +
                ", cpf=" + cpf +
                '}';
    }
}
