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

    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/dao";
    static final String LOGIN = "root";
    static final String PASS = "root";

    public static Connection createConnection(){
        try {
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            log.error("DRIVER problem", e);
            return null;
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,LOGIN,PASS);
            return connection;
        }catch (SQLException e){
            log.error("Connection problem in FcactoryDAO",e);
            return null;
        }catch (NullPointerException e){
            log.error("Connection is NILL in DAO Factory",e);
            return null;
        }
    }

    @Override
    public  ClientDao getClientDAO() {
        return new ClientDatabaseDAO();
    }

    @Override
    public  AccountDao getAccountDAO() {
        return new AccountDatabaseDAO();
    }

    @Override
    public  PaymentDao getPaymentDAO() {
        return new PaymentDatabaseDAO();
    }
}
