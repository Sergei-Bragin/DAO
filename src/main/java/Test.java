import dao.ClientDao;

import dao.impl.ClientDatabaseDAO;
import model.Client;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        ClientDao clientDatabaseDAO = new ClientDatabaseDAO();
        List<Client> clients = clientDatabaseDAO.getAll();
        for(Client client: clients){
            System.out.println(client);
        }
    }
}
