package dao.impl;


import dao.ClientDao;
import dao.FactoryDAO;
import model.Account;
import model.Client;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
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

    @Override
    public List<Client> getAll() {
        log.info("Get all client");
        String SQL = "select * from client";
        List<Client> clients = new ArrayList<>();

        try(Connection con = FactoryDatabaseDAO.createConnection()) {
            log.trace("Connect GET ALL");

            try (Statement statement = con != null ? con.createStatement():null){
                log.trace("Create prepared statement");

                ResultSet result = statement.executeQuery(SQL);
                while (result.next()){
                    Client client = new Client();
                    client.setId(result.getInt("id"));
                    client.setName(result.getString("name"));
                    client.setEmail(result.getString("email"));
                    client.setPass(result.getString("pass"));
                    clients.add(client);
                }
            }catch (SQLException e){
                log.error("Statement GET ALL exception",e);
            }catch (NullPointerException e){
                log.error("CON is null",e);
            }

        }catch (SQLException e){
            log.error("Connect exception in GET ALL ", e);
        }
        return clients;
    }

    @Override
    public Client getById(int id) {
        log.info("Get client by ID = " + id);
        String SQL  = "select * from client where id = ?";
        Client client = new Client();

        try (Connection con = FactoryDatabaseDAO.createConnection()){
            log.trace("Connect GET BY ID");

            try (PreparedStatement statement = con != null ? con.prepareStatement(SQL) : null) {
                log.trace("Create prepared statement");

                statement.setInt(1,id);

                ResultSet result = statement.executeQuery();
                result.next();
                client.setId(result.getInt("id"));
                client.setName(result.getString("name"));
                client.setEmail(result.getString("email"));
                client.setPass(result.getString("pass"));

            }catch (SQLException e){
                log.error("Statement GET BY ID exception",e);
            }catch (NullPointerException e){
                log.error("CON is null",e);
            }

        }catch (SQLException e){
            log.error("Connect exception in GET BY ID", e);
        }

        return client;
    }

    @Override
    public void add(Client model) {
        log.info("Create new Client"+model.getName());
        String SQL = "insert into client (name, email, pass) values (?,?,?)";

        try (Connection con = FactoryDatabaseDAO.createConnection()){
            log.trace("Connect ADD COMMAND");

            try (PreparedStatement statement = con != null ? con.prepareStatement(SQL) : null){
                log.trace("Create prepared statement");

                statement.setString(1,model.getName());
                statement.setString(2,model.getEmail());
                statement.setString(3,model.getPass()); //Пока так Но парль надо Хешировать!!!
                statement.executeUpdate();

            }catch (SQLException e){
                log.error("Statement ADD COMMAND exception",e);
            }catch (NullPointerException e){
                log.error("CON is null",e);
            }

        } catch (SQLException e) {
            log.error("Connect exception in ADD COMMAND", e);
        }

    }

    @Override
    public void update(Client model) {
        log.info("UPDATE client"+model.getId());
        String SQL = "update client set name = ?, email = ?, pass = ? where id = ?";

        try (Connection con = FactoryDatabaseDAO.createConnection()){
            log.trace("Connect UPDATE");

            try (PreparedStatement statement = con != null ? con.prepareStatement(SQL) : null){
                log.trace("Create prepared statement");

                statement.setString(1,model.getName());
                statement.setString(2,model.getEmail());
                statement.setString(3,model.getPass());
                statement.setInt(4, model.getId());
                statement.executeUpdate();

            }catch (SQLException e){
                log.error("Statement UPDATE exception",e);
            }catch (NullPointerException e){
                log.error("CON is null",e);
            }

        }catch (SQLException e){
            log.error("UPDATE exception", e);
        }
    }

    @Override
    public void del(Client model) {
        log.info("DELETE Client ");
        String SQL = "delete from client where id = ?";

        try (Connection con = FactoryDatabaseDAO.createConnection()) {
            log.trace("Connect DELETE");

            try(PreparedStatement statement = con != null ? con.prepareStatement(SQL) : null) {
                log.trace("Create prepared statement");

                statement.setInt(1,model.getId());
                statement.executeUpdate();

            }catch (SQLException e){
                log.error("Statement DELETE exception",e);
            }catch (NullPointerException e){
                log.error("CON is null",e);
            }

        }catch (SQLException e){
            log.error("DELETE exception", e);
        }
    }
}
