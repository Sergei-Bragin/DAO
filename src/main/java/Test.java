import dao.ClientDao;

import dao.impl.ClientDatabaseDAO;
import model.Client;

public class Test {
    public static void main(String[] args) {
        Client client = new Client("Ivan ivanod","ivan1@ya.ru","wsad123");
        ClientDatabaseDAO clientDatabaseDAO = new ClientDatabaseDAO();
        clientDatabaseDAO.add(client);
    }
}
