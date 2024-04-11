package app.entities;

public class Orderline {
    private int orderline_id;
    private int order_id;
    private int total_price;
    private int toppings_id;
    private int bottoms_id;

    public Orderline(int orderline_id, int order_id, int total_price, int toppings_id, int bottoms_id) {
        this.orderline_id = orderline_id;
        this.order_id = order_id;
        this.total_price = total_price;
        this.toppings_id = toppings_id;
        this.bottoms_id = bottoms_id;
    }

    @Override
    public String toString() {
        return "Orderline{" +
                "orderline_id=" + orderline_id +
                ", order_id=" + order_id +
                ", total_price=" + total_price +
                ", toppings_id=" + toppings_id +
                ", bottoms_id=" + bottoms_id +
                '}';
    }
}