package dao;

import model.Account;
import model.Client;

import java.util.List;


public interface ClientDao extends ItemDao<Client> {

    double getClientBalance(Client client, List<Account> accounts);
    Client getClientWithMaxBalance(List<Account> accounts);
    Client getClientWithMinBalance(List<Account> accounts);

}
