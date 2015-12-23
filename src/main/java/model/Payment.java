package model;


public class Payment extends Model {


    private int idAcc1;
    private int idAcc2;
    private double sum;

    public Payment() {
        super();
    }

    public Payment(int id){
        super(id);
    }

    public int getIdAcc1() {
        return idAcc1;
    }

    public void setIdAcc1(int idAcc1) {
        this.idAcc1 = idAcc1;
    }

    public int getIdAcc2() {
        return idAcc2;
    }

    public void setIdAcc2(int idAcc2) {
        this.idAcc2 = idAcc2;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
