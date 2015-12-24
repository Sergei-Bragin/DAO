import dao.ClientDao;

import dao.impl.ClientDatabaseDAO;
import model.Client;
import sun.misc.Cleaner;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        ClientDao clientDatabaseDAO = new ClientDatabaseDAO();
        Client client = new Client(3, "Vika Flex", "vikaflex12@gmail.com", "flexvik");
        clientDatabaseDAO.update(client);


    }
}
