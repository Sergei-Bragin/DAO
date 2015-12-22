package dao.impl;


import dao.ClientDao;
import dao.FactoryDAO;
import model.Account;
import model.Client;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClientDatabaseDAO implements ClientDao {
    private static Logger log = Logger.getLogger(ClientDatabaseDAO.class);


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

    @Override
    public void add(Client model) {
        log.info("Create new Client"+model.getName());
        String SQL = "insert into client (name, email, pass) values (?,?,?);";

        Client client = null;

        try (Connection con = FactoryDatabaseDAO.createConnection()){
            log.trace("Connect addCommand");


            try (PreparedStatement statement = con != null ? con.prepareStatement(SQL) : null){
                log.trace("Create prepared statement");
                statement.setString(1, client.getName());
                statement.setString(2,client.getEmail());
                statement.setString(3,client.getPass());

            }catch (SQLException e){
                log.error("Statement addCommand exception",e);
            }catch (NullPointerException e){
                log.error("Con is null",e);
            }


        } catch (SQLException e) {
            log.error("Connect exception in addCommand", e);
        }

    }

    public void update(Client model) {

    }

    public void del(Client model) {

    }
}
