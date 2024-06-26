package app.entities;

public class Order {
    private int order_id;
    private int total_price;
    private int orderline_amount;
    private int user_id;
    private String user_name;

    public Order(int order_id, int total_price, int orderline_amount, int user_id) {
        this.order_id = order_id;
        this.total_price = total_price;
        this.orderline_amount = orderline_amount;
        this.user_id = user_id;
    }

    public Order(int order_id, int total_price, int orderline_amount, int user_id, String user_name) {
        this.order_id = order_id;
        this.total_price = total_price;
        this.orderline_amount = orderline_amount;
        this.user_id = user_id;
        this.user_name = user_name;
    }

    public int getUser_id() {
        return user_id;
    }


    public int getTotal_price() {
        return total_price;
    }


    public int getOrderline_amount() {
        return orderline_amount;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void setOrderline_amount(int orderline_amount) {
        this.orderline_amount = orderline_amount;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", total_price=" + total_price +
                ", orderline_amount=" + orderline_amount +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}