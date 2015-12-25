package dao;

import model.Account;
import model.Client;

import java.util.List;


public interface ClientDao extends ItemDao<Client> {

    double getClientBalance(Client client);
    Client getClientWithMaxBalance();
    Client getClientWithMinBalance();

}
