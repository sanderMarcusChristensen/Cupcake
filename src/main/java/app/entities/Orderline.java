package app.entities;

public class Orderline {
    private int orderline_id;
    private int order_id;
    private int total_price;
    private Toppings toppings_id;
    private Bottoms bottoms_id;

    public Orderline(int orderline_id, int order_id, int total_price, Toppings toppings_id, Bottoms bottoms_id) {
        this.orderline_id = orderline_id;
        this.order_id = order_id;
        this.total_price = total_price;
        this.toppings_id = toppings_id;
        this.bottoms_id = bottoms_id;
    }

    public int getOrderline_id() {
        return orderline_id;
    }

    public void setOrderline_id(int orderline_id) {
        this.orderline_id = orderline_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public Toppings getToppings_id() {
        return toppings_id;
    }

    public void setToppings_id(Toppings toppings_id) {
        this.toppings_id = toppings_id;
    }

    public Bottoms getBottoms_id() {
        return bottoms_id;
    }

    public void setBottoms_id(Bottoms bottoms_id) {
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
