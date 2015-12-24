package dao.impl;


import dao.ClientDao;
import model.Client;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Фуф, неделю назад даже не знал что такое ДАО и как с этим жить спасибо за задачку! Но еще надо дописывать, пока предпоказ
Да знаю, крепко нехватает абстрактной фабрики (дабы исбежать копирование кода, в других классах), не покрыто юнит тестами
 и по хорошему надо сделать DAOException что бы потом перехватывать всяки неприятные нюансы, в целом и общем нужна тонна
 рефакторинга, пароль в базу нужно хешировать, т.к. речь и дет о деньгах надо продумать вопрос с транзакцией.
 Справедливо будет спросить почему не на hibernate ответ сделал это для то го что бы показать что понимаю прослойку DAO
 (что то в духе примерно понимаю что у хибера под капотом). Отдаю недопилиным иза сжатых сроков (мне Наталья намекнула что
 надо спешить). П.с. буду благодарен за любой фидбек даже если разобьете все мои потуги одним щечком.
 */

public class ClientDatabaseDAO implements ClientDao {
    private static Logger log = Logger.getLogger(ClientDatabaseDAO.class);


    public double getClientBalance(Client client) {
        log.info("Get Client Balance");
        String SQL = "SELECT \n" +
                "client.id, account.iduser, account.balanc\n" +
                "FROM\n" +
                "    client AS client\n" +
                "        INNER JOIN\n" +
                "    account AS account ON client.id = account.iduser\n" +
                "WHERE\n" +
                "    client.id = ?";
        double balance = 0;

        try (Connection con = FactoryDatabaseDAO.createConnection()) {
            log.trace("Connection GET CLIENT BALANCE");

            try (PreparedStatement statement = con != null ? con.prepareStatement(SQL) : null) {
                log.trace("Create prepared statement");

                statement.setInt(1,client.getId());

                ResultSet result = statement.executeQuery();
                result.next();
                balance = result.getDouble("balanc");


            }catch (SQLException e){
                log.error("Statement GET CLIENT BALANCE exception",e);
            }catch (NullPointerException e){
                log.error("CON is null",e);
            }

        }catch (SQLException e){
            log.error("GET CLIENT BALANCE", e);
        }


        return balance;
    }

    public Client getClientWithMaxBalance() {
        log.info("Get client max balance");
        String SQL = "SELECT \n" +
                "    iduser, balanc\n" +
                "FROM\n" +
                "    account\n" +
                "ORDER BY balanc DESC\n" +
                "LIMIT 1";
        Client client = new Client();

        try (Connection con = FactoryDatabaseDAO.createConnection()){
            log.trace("Connect GET MAX BALANCE CLIENT");

            try (PreparedStatement statement = con != null ? con.prepareStatement(SQL) : null) {
                log.trace("Create prepared statement");

                ResultSet result = statement.executeQuery();
                result.next();
                client = getById(result.getInt("iduser"));

            }catch (SQLException e){
                log.error("Statement GET MAX BALANCE CLIENT exception",e);
            }catch (NullPointerException e){
                log.error("CON is null",e);
            }

        }catch (SQLException e){
            log.error("Connect exception in GET MAX BALANCE CLIENT", e);
        }

        return client;
    }

    public Client getClientWithMinBalance() {
        log.info("Get client min balance");
        String SQL = "SELECT \n" +
                "    iduser, balanc\n" +
                "FROM\n" +
                "    account\n" +
                "ORDER BY balanc ASC\n" +
                "LIMIT 1";
        Client client = new Client();

        try (Connection con = FactoryDatabaseDAO.createConnection()){
            log.trace("Connect GET MIN BALANCE CLIENT");

            try (PreparedStatement statement = con != null ? con.prepareStatement(SQL) : null) {
                log.trace("Create prepared statement");

                ResultSet result = statement.executeQuery();
                result.next();
                client = getById(result.getInt(1));

            }catch (SQLException e){
                log.error("Statement GET MAX BALANCE CLIENT exception",e);
            }catch (NullPointerException e){
                log.error("CON is null",e);
            }

        }catch (SQLException e){
            log.error("Connect exception in GET MIN BALANCE CLIENT", e);
        }


        return client;
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
