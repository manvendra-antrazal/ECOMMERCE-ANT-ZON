package Modal;

import java.sql.Timestamp;

public class Order {

    private int orderId;
    private int buyerId;
    private int productId;
    private int companyId;
    private int quantity;
    private double totalPrice;
    private String transactionId;
    private Timestamp orderDate;

    public Order(int orderId, int buyerId, int productId, int companyId, int quantity,
                 double totalPrice, String transactionId, Timestamp orderDate) {
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.productId = productId;
        this.companyId = companyId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.transactionId = transactionId;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }



    
}
