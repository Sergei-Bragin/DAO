package model;


public class Account extends Model {


    private Long idUser;
    private double balanc;

    public  Account(){
        super();
    }

    public Account(Long id){
        super(id);
    }

    public Long getIdUser() {
        return idUser;
    }


    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public double getBalanc() {
        return balanc;
    }

    public void setBalanc(double balanc) {
        this.balanc = balanc;
    }
}
