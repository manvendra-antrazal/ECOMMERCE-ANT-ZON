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
    private String productName;
    private String categoryName;

    public Order() {
    }

    public Order(int orderId,
                 int buyerId,
                 int productId,
                 int companyId,
                 int quantity,
                 double totalPrice,
                 String transactionId,
                 Timestamp orderDate,
                 String productName,
                 String categoryName) {
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.productId = productId;
        this.companyId = companyId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.transactionId = transactionId;
        this.orderDate = orderDate;
        this.productName = productName;
        this.categoryName = categoryName;
    }

    public Order(int buyerId,
                 int productId,
                 int companyId,
                 int quantity,
                 double totalPrice,
                 String transactionId,
                 String productName) {
        this(0, buyerId, productId, companyId, quantity, totalPrice, transactionId, null, productName, null);
    }

    // Getters and Setters

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
        if (quantity < 0) {
            throw new IllegalArgumentException("Order quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        if (totalPrice < 0) {
            throw new IllegalArgumentException("Total price cannot be negative");
        }
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getDerivedUnitPrice() {
        return quantity > 0 ? totalPrice / quantity : 0.0;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", buyerId=" + buyerId +
                ", productId=" + productId +
                ", companyId=" + companyId +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", transactionId='" + transactionId + '\'' +
                ", orderDate=" + orderDate +
                ", productName='" + productName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
