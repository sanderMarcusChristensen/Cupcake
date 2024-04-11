package app.entities;

public class Toppings {
    private int topping_id;
    private String flavor;
    private int price;

    public Toppings(int topping_id, String flavor, int price) {
        this.topping_id = topping_id;
        this.flavor = flavor;
        this.price = price;
    }

    public int getTopping_id() {
        return topping_id;
    }

    public int getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Toppings{" +
                "topping_id=" + topping_id +
                ", flavor='" + flavor + '\'' +
                ", price=" + price +
                '}';
    }
}