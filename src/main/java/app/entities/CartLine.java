package app.entities;

public class CartLine {

    private Toppings toppings;
    private Bottoms bottoms;
    private int amount;

    public CartLine(Toppings toppings, Bottoms bottoms, int amount) {
        this.toppings = toppings;
        this.bottoms = bottoms;
        this.amount = amount;
    }

    public Toppings getToppings() {
        return toppings;
    }

    public Bottoms getBottoms() {
        return bottoms;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return (toppings.getPrice() + bottoms.getPrice()) * amount;
    }
}