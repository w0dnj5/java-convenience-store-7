package store.product;

import java.time.LocalDate;
import store.promotion.Promotion;

public class Product {

    private final String name;
    private final int price;
    private int quantity;
    private final Promotion promotion;

    public Product(String name, int price, int quantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean hasPromotion() {
        return promotion != null;
    }

    public int getReceiveFreeCount(int count, LocalDate now) {
        if (hasPromotion() && count <= quantity) {
            return promotion.getReceiveFreeCount(count, now);
        }
        return 0;
    }
}
