package br.com.customerregistration.dao;

import br.com.customerregistration.dto.CustomerDTO;
import br.com.customerregistration.enums.EEditOption;
import br.com.customerregistration.services.EditCustomerService;
import br.com.customerregistration.ui.NotificationUI;
import br.com.customerregistration.ui.InputDialogUI;
import br.com.customerregistration.ui.MessageDisplayUI;

import java.util.*;
public non-sealed class CustomerMapDAO implements ICustomerDAO {
    private final Map<String, CustomerDTO> CUSTOMER_BY_CPF;
    public CustomerMapDAO(){
        this.CUSTOMER_BY_CPF = new HashMap<>();
    }
    @Override
    public Boolean register(CustomerDTO customerDTO) {
        String customerCPF = customerDTO.getCpf();

        if (CUSTOMER_BY_CPF.containsKey(customerCPF)) return false;

        CUSTOMER_BY_CPF.put(customerCPF, customerDTO);
        return true;
    }

    @Override
    public void remove(String cpf) {
        CUSTOMER_BY_CPF.remove(cpf);
    }
    @Override
    public void edit(EEditOption editOption, CustomerDTO customer) {
        try {
            switch (editOption) {
                case FIRST_NAME -> customer.setFirstName(EditCustomerService.firstNameEdit(customer.getFirstName()));
                case LAST_NAME -> customer.setLastName(EditCustomerService.lastNameEdit(customer.getLastName()));
                case PHONE_NUMBER -> customer.setPhoneNumber(EditCustomerService.phoneNumberEdit(customer.getPhoneNumber()));
                case ADDRESS -> {
                    Optional<String[]> addressEditResult = EditCustomerService.addressEdit(customer);
                    if (addressEditResult.isPresent()) {
                        String[] addressParts = addressEditResult.get();
                        customer.setCity(addressParts[0]);
                        customer.setState(addressParts[1]);
                    }
                }
                case CEP -> customer.setCep(EditCustomerService.cepEdit(customer.getCep()));
            }
        } catch (IllegalArgumentException exception) {
            NotificationUI.displayErrorMessage(exception.getMessage(), "Error");
        }
    }

    @Override
    public CustomerDTO consult(String cpf) {
        return CUSTOMER_BY_CPF.get(cpf);
    }

    @Override
    public Collection<CustomerDTO> getAllCustomers() {
        return CUSTOMER_BY_CPF.values();
    }

    @Override
    public Map<String, CustomerDTO> getAllByCpf() {
        return new HashMap<>(CUSTOMER_BY_CPF);
    }

    @Override
    public void removeAllCustomers() {
        CUSTOMER_BY_CPF.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerMapDAO that)) return false;
        return Objects.equals(CUSTOMER_BY_CPF, that.CUSTOMER_BY_CPF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CUSTOMER_BY_CPF);
    }
}
