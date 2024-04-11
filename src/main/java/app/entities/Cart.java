package app.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    public List<CartLine> cartLines = new ArrayList<>();

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public int getCount() {
        return cartLines.size();
    }

    public int getTotalPrice() {
        int sum = 0;
        for (CartLine cartLine : cartLines) {
            sum += cartLine.getAmount() * (cartLine.getBottoms().getPrice() + cartLine.getToppings().getPrice());
        }
        return sum;
    }

    public void add(Toppings toppings, Bottoms bottoms, int amount) {
        CartLine cartLine = new CartLine(toppings, bottoms, amount);
        cartLines.add(cartLine);
    }
}