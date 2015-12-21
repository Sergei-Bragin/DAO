package model;


public class Payment extends Model {

    private Long idAcc1;
    private Long idAcc2;
    private double sum;

    public Payment() {
        super();
    }

    public Payment(Long id){
        super(id);
    }

    public Long getIdAcc1() {
        return idAcc1;
    }

    public void setIdAcc1(Long idAcc1) {
        this.idAcc1 = idAcc1;
    }

    public Long getIdAcc2() {
        return idAcc2;
    }

    public void setIdAcc2(Long idAcc2) {
        this.idAcc2 = idAcc2;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
