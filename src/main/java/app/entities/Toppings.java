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

    public String getFlavor() {
        return flavor;
    }

    public void setTopping_id(int topping_id) {
        this.topping_id = topping_id;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public void setPrice(int price) {
        this.price = price;
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