package br.com.fred.dao;

import br.com.fred.domain.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomerMapDAO implements ICustomerDAO {
    private Map<Long, Customer> map; // key == cpf
    public CustomerMapDAO(){
        this.map = new HashMap<>();
    }
    @Override
    public Boolean register(Customer customer) {
        // if it already has the key(cpf) of client
        if (map.containsKey(customer.getCpf())) return false;

        this.map.put(customer.getCpf(), customer);
        return true;
    }

    @Override
    public void remove(Long cpf) {
        Customer registeredCustomer = map.get(cpf);

        if (registeredCustomer != null) map.remove(registeredCustomer.getCpf(), registeredCustomer);
    }

    @Override
    public void edit(Customer customer) {
        Customer registeredCustomer = map.get(customer.getCpf());

        if (registeredCustomer != null){
            registeredCustomer.setName(customer.getName());
            registeredCustomer.setPhoneNumber(customer.getPhoneNumber());
            registeredCustomer.setNumber(customer.getNumber());
            registeredCustomer.setAddress(customer.getAddress());
            registeredCustomer.setCity(customer.getCity());
            registeredCustomer.setState(customer.getState());
        }
    }

    @Override
    public Customer consult(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Customer> searchAll() {
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
