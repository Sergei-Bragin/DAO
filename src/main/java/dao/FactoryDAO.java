package dao;

import dao.impl.FactoryDatabaseDAO;

public abstract class FactoryDAO  {

    public abstract ClientDao getClientDAO();
    public abstract AccountDao getAccountDAO();
    public abstract PaymentDao getPaymentDAO();

    public static FactoryDAO getFactoryDAO(){
        return new FactoryDatabaseDAO();
    }
}
