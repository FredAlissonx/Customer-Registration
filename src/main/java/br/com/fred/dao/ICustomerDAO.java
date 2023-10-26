package br.com.fred.dao;

import br.com.fred.domain.Customer;

import java.util.Collection;

public interface ICustomerDAO {
    Boolean register(Customer customer);
    void remove(Long cpf);
    void edit(Customer customer);
    Customer consult(Long cpf);
    Collection<Customer> searchAll();
}
