package model;


public class Account extends Model {


    private int idUser;
    private double balanc;

    public  Account(){
        super();
    }

    public Account(int id){
        super(id);
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getBalanc() {
        return balanc;
    }

    public void setBalanc(double balanc) {
        this.balanc = balanc;
    }
}
