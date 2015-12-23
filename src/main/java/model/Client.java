package model;


public class Client extends Model{

    private String name;
    private String email;
    private String pass;

    private  Account account;

    public Client() {
        super();
    }

    public Client(Long id){
        super(id);
    }

    public Client(String name, String email, String pass){
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Client{ " +"id "+ getId()+ ", name: " + name + ", email: " + email + ", pass: " + pass + " }";
    }
}
