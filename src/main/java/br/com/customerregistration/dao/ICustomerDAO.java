package br.com.customerregistration.dao;

import br.com.customerregistration.dto.CustomerDTO;
import br.com.customerregistration.enums.EEditOption;

import java.util.Collection;
import java.util.Map;

public sealed interface ICustomerDAO permits CustomerMapDAO {
    Boolean register(CustomerDTO customerDTO);
    void remove(String cpf);
    void edit(EEditOption editOption, CustomerDTO customer);
    CustomerDTO consult(String cpf);
    Collection<CustomerDTO> getAllCustomers();
    Map<String, CustomerDTO> getAllByCpf();
    void removeAllCustomers();
}
