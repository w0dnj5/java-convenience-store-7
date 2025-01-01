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

    public int getPrice() {
        return price;
    }

    public boolean hasPromotion() {
        return promotion != null;
    }

    public int calculateMoreBonus(LocalDate date, int count) {
        if (hasPromotion() && count <= quantity) {
            return promotion.calculateMoreBonus(date, count);
        }
        return 0;
    }

    public int calculateNoPromotionApply(LocalDate date, int count) {
        if (hasPromotion() && count > quantity) {
            return promotion.calculateNoPromotionApply(date, count, quantity);
        }
        return 0;
    }

    public int calculateTotalBonus(LocalDate date, int count) {
        if (hasPromotion()) {
            return promotion.calculateTotalBonus(date, Math.min(quantity, count));
        }
        return 0;
    }

    public int apply(int count) {
        if (quantity < count) {
            count = count - quantity;
            quantity = 0;
            return count;
        }
        return quantity -= count;
    }

    @Override
    public String toString() {
        return String.join(" ", "-", String.format("%s %,3d원", name, price), formatQuantity(), formatPromotion());
    }

    public String formatQuantity() {
        if (quantity == 0) {
            return "재고 없음";
        }
        return String.format("%d개", quantity);
    }

    public String formatPromotion() {
        if (hasPromotion()) {
            return promotion.getName();
        }
        return "";
    }
}
