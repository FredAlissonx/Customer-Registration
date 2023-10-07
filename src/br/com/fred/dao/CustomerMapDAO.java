package br.com.fred.dao;

import br.com.fred.domain.Costumer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomerMapDAO implements ICustomerDAO {
    private Map<Long, Costumer> map; // key == cpf
    public CustomerMapDAO(){
        this.map = new HashMap<>();
    }
    @Override
    public Boolean register(Costumer costumer) {
        // if it already has the key(cpf) of client
        if (map.containsKey(costumer.getCpf())) return false;

        this.map.put(costumer.getCpf(), costumer);
        return true;
    }

    @Override
    public void remove(Long cpf) {
        Costumer registeredCostumer = map.get(cpf);

        if (registeredCostumer != null) map.remove(registeredCostumer.getCpf(), registeredCostumer);
    }

    @Override
    public void edit(Costumer costumer) {
        Costumer registeredCostumer = map.get(costumer.getCpf());

        if (registeredCostumer != null){
            registeredCostumer.setName(costumer.getName());
            registeredCostumer.setTel(costumer.getTel());
            registeredCostumer.setNumber(costumer.getNumber());
            registeredCostumer.setEnd(costumer.getEnd());
            registeredCostumer.setCity(costumer.getCity());
            registeredCostumer.setState(costumer.getState());
        }
    }

    @Override
    public Costumer consult(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Costumer> searchAll() {
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
