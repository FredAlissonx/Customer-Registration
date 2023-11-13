package br.com.fred.dao;

import br.com.fred.dto.CustomerDTO;

import java.util.Collection;

public sealed interface ICustomerDAO permits CustomerMapDAO{
    Boolean register(CustomerDTO customerDTO);
    void remove(Long cpf);
    void edit(CustomerDTO customerDTO);
    CustomerDTO consult(Long cpf);
    Collection<CustomerDTO> searchAll();
}
