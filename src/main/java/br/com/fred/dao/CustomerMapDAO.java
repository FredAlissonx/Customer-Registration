package br.com.fred.dao;

import br.com.fred.dto.CustomerDTO;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public non-sealed class CustomerMapDAO implements ICustomerDAO {
    private Map<Long, CustomerDTO> map; // key == cpf
    public CustomerMapDAO(){
        this.map = new HashMap<>();
    }
    @Override
    public Boolean register(CustomerDTO customerDTO) {
        // if it already has the key(cpf) of client
        if (map.containsKey(customerDTO.getCpf())) return false;

        this.map.put(customerDTO.getCpf(), customerDTO);
        return true;
    }

    @Override
    public void remove(Long cpf) {
        CustomerDTO registeredCustomerDTO = map.get(cpf);

        if (registeredCustomerDTO != null)
            map.remove(registeredCustomerDTO.getCpf(), registeredCustomerDTO);
    }

    @Override
    public void edit(CustomerDTO customerDTO) {
        CustomerDTO registeredCustomerDTO = map.get(customerDTO.getCpf());

        if (registeredCustomerDTO != null){
            registeredCustomerDTO.setFirstName(customerDTO.getFirstName());
            registeredCustomerDTO.setLastName(customerDTO.getLastName());
            registeredCustomerDTO.setPhoneNumber(customerDTO.getPhoneNumber());
            registeredCustomerDTO.setAddress(customerDTO.getAddress());
            registeredCustomerDTO.setCity(customerDTO.getCity());
            registeredCustomerDTO.setState(customerDTO.getState());
        }
    }

    @Override
    public CustomerDTO consult(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<CustomerDTO> searchAll() {
        return this.map.values();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerMapDAO that)) return false;
        return Objects.equals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
}
