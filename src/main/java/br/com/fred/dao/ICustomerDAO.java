package br.com.fred.dao;

import br.com.fred.dao.CustomerMapDAO;
import br.com.fred.dto.CustomerDTO;

import java.util.Collection;
import java.util.Map;

public sealed interface ICustomerDAO permits CustomerMapDAO {
    Boolean register(CustomerDTO customerDTO);
    void remove(String cpf);
    void edit(CustomerDTO customerDTO);
    CustomerDTO consult(String cpf);
    Collection<CustomerDTO> searchAll();
    Map<String, CustomerDTO> searchAllByCpf();
}
