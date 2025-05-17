package Modal;

public class Order {

    private int order_Id;
    private double additonal_discount;
    private double total_amount;

    public Order(double additonal_discount, int order_Id, double total_amount) {
        this.additonal_discount = additonal_discount;
        this.order_Id = order_Id;
        this.total_amount = total_amount;
    }

    public int getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(int order_Id) {
        this.order_Id = order_Id;
    }

    public double getAdditonal_discount() {
        return additonal_discount;
    }

    public void setAdditonal_discount(double additonal_discount) {
        this.additonal_discount = additonal_discount;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }
}
