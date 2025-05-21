package Modal;

public class Payment {
_
    private int pay_ID;
    private int order_Now_ID;
    private int user_ID;
    private int company_ID;
    private String payment_Method;
    private boolean transaction_Complete;
    
    public Payment(int pay_ID, int order_Now_ID, int user_ID, int company_ID, String payment_Method,
            boolean transaction_Complete) {
        this.pay_ID = pay_ID;
        this.order_Now_ID = order_Now_ID;
        this.user_ID = user_ID;
        this.company_ID = company_ID;
        this.payment_Method = payment_Method;
        this.transaction_Complete = transaction_Complete;
    }

    public int getPay_ID() {
        return pay_ID;
    }

    public void setPay_ID(int pay_ID) {
        this.pay_ID = pay_ID;
    }

    public int getOrder_Now_ID() {
        return order_Now_ID;
    }

    public void setOrder_Now_ID(int order_Now_ID) {
        this.order_Now_ID = order_Now_ID;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public int getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(int company_ID) {
        this.company_ID = company_ID;
    }

    public String getPayment_Method() {
        return payment_Method;
    }

    public void setPayment_Method(String payment_Method) {
        this.payment_Method = payment_Method;
    }

    public boolean isTransaction_Complete() {
        return transaction_Complete;
    }

    public void setTransaction_Complete(boolean transaction_Complete) {
        this.transaction_Complete = transaction_Complete;
    }

    

    

    
    
}
