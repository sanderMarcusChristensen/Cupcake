package app.entities;

public class Bottoms {
    private int bottom_id;
    private String flavor;
    private int price;

    public Bottoms(int bottom_id, String flavor, int price) {
        this.bottom_id = bottom_id;
        this.flavor = flavor;
        this.price = price;
    }

    public int getBottom_id() {
        return bottom_id;
    }

    public String getFlavor() {
        return flavor;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Bottoms{" +
                "bottom_id=" + bottom_id +
                ", flavor='" + flavor + '\'' +
                ", price=" + price +
                '}';
    }
}