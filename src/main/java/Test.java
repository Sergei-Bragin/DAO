import dao.ClientDao;

import dao.impl.ClientDatabaseDAO;
import model.Client;
import sun.misc.Cleaner;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        ClientDao clientDatabaseDAO = new ClientDatabaseDAO();
        Client client = clientDatabaseDAO.getClientWithMinBalance();
        System.out.println(client);


    }
}
