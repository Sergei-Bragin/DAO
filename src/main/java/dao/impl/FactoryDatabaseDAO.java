package dao.impl;


import dao.AccountDao;
import dao.ClientDao;
import dao.FactoryDAO;
import dao.PaymentDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryDatabaseDAO extends FactoryDAO{

    private static final Logger log = Logger.getLogger(FactoryDatabaseDAO.class);

    public final String USER = "root";
    public final String PASSWORD = "root";
    public final String URL = "jdbc:mysql://localhost:3306/dao";
    public final String DRIVER = "com.mysql.jdbc.Driver";

    public static Connection createConnection(){
        Connection connection;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            log.info("Connect to Database");
            return connection;
        }catch (ClassNotFoundException e){
            log.error("Class not found exception in createConnect", e);
            return null;
        }catch (SQLException e){
            log.error("SQLException in createConnect", e);
            return null;
        }catch (Exception e){
            log.error("Exception in createConnect", e);
            return null;
        }
    }

    @Override
    public ClientDao getClientDAO() {
        return new ClientDatabaseDAO();
    }

    @Override
    public AccountDao getAccountDAO() {
        return new AccountDatabaseDAO();
    }

    @Override
    public PaymentDao getPaymentDAO() {
        return new PaymentDatabaseDAO();
    }
}
