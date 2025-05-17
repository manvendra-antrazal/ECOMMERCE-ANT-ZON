package Modal;

public class Payment {
_
    private int pay_Id;
    private String pay_Method;
    private boolean Transaction;

    public Payment(int pay_Id, String pay_Method, boolean transaction) {
        this.pay_Id = pay_Id;
        this.pay_Method = pay_Method;
        Transaction = transaction;
    }

    public int getPay_Id() {
        return pay_Id;
    }
    public void setPay_Id(int pay_Id) {
        this.pay_Id = pay_Id;
    }
    public String getPay_Method() {
        return pay_Method;
    }
    public void setPay_Method(String pay_Method) {
        this.pay_Method = pay_Method;
    }

    
    
}
