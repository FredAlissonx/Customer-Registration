package br.com.fred.dao;

import br.com.fred.domain.Costumer;

import java.util.Collection;

public interface ICustomerDAO {
    public Boolean register(Costumer costumer);
    public void remove(Long cpf);
    public void edit(Costumer costumer);
    public Costumer consult(Long cpf);
    public Collection<Costumer> searchAll();
}
