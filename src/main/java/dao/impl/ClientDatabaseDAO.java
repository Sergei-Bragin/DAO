package dao.impl;


import dao.ClientDao;
import model.Account;
import model.Client;

import java.util.List;

public class ClientDatabaseDAO implements ClientDao {



    public double getClientBalance(Client client, List<Account> accounts) {
        return 0;
    }

    public Client getClientWithMaxBalance(List<Account> accounts) {
        return null;
    }

    public Client getClientWithMinBalance(List<Account> accounts) {
        return null;
    }

    public List<Client> getAll() {
        return null;
    }

    public Client getById(Long id) {
        return null;
    }

    public void add(Client model) {

    }

    public void update(Client model) {

    }

    public void del(Client model) {

    }
}
