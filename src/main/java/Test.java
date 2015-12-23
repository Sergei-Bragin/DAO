import dao.ClientDao;

import dao.impl.ClientDatabaseDAO;
import model.Client;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        ClientDao clientDatabaseDAO = new ClientDatabaseDAO();
        Client client = clientDatabaseDAO.getById(10);
        System.out.println(client);
    }
}
