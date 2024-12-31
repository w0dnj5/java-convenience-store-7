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

    public int getReceiveFreeCount(LocalDate date, int count) {
        if (hasPromotion() && count <= quantity) {
            return promotion.getReceiveFreeCount(date, count);
        }
        return 0;
    }

    public int getNoPromotionApplyCount(LocalDate date, int count) {
        if (hasPromotion() && count > quantity) {
            return promotion.getNoPromotionApplyCount(date, count, quantity);
        }
        return 0;
    }
}
