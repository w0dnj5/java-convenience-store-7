package store.order;

import static store.error.ErrorMessage.NO_EXIST_PRODUCT;
import static store.error.ErrorMessage.OUT_OF_STOCK;

import java.time.LocalDate;
import java.util.List;
import store.product.Product;

public class Order {

    private final List<Product> products;
    private int count;
    private final LocalDate date;

    public Order(List<Product> products, int count) {
        validate(products, count);
        this.products = products;
        this.count = count;
        date = LocalDate.parse("2024-12-11"); // DateTimes.now().toLocalDate();
        // 이벤트 반영을 위한 시간 조정
    }

    private void validate(List<Product> products, int count) {
        if (products.isEmpty()) {
            throw new IllegalArgumentException(NO_EXIST_PRODUCT.getMessage());
        }
        if (products.stream().mapToInt(Product::getQuantity).sum() < count) {
            throw new IllegalArgumentException(OUT_OF_STOCK.getMessage());
        }
    }

    public int calculateMoreBonus() {
        return products.stream()
                .mapToInt(product -> product.calculateMoreBonus(date, count))
                .sum();
    }

    public int calculateNoPromotionApply() {
        return products.stream()
                .mapToInt(product -> product.calculateNoPromotionApply(date, count))
                .sum();
    }

    public String getProductName() {
        return products.getFirst().getName();
    }

    public int getCount() {
        return count;
    }

    public int getTotalPrice() {
        Product product = products.getFirst();
        return count * product.getPrice();
    }

    public void addMoreBonus() {
        Product product = getFirst();
        if (product.hasPromotion()) {
            count += product.calculateMoreBonus(date, count);
        }
    }

    public void dropNoPromotionApply() {
        Product product = getFirst();
        if (product.hasPromotion()) {
            count -= product.calculateNoPromotionApply(date, count);
        }
    }

    public int calculateTotalBonus() {
        Product product = getFirst();
        if (product.hasPromotion()) {
            return product.calculateTotalBonus(date, count);
        }
        return 0;
    }

    public int calculateDiscount() {
        Product product = getFirst();
        return calculateTotalBonus() * product.getPrice() * -1;
    }

    public void apply() {
        for (Product p : products) {
            if (count != 0) {
                count = p.apply(count);
            }
        }
    }

    private Product getFirst() {
        return products.getFirst();
    }

    @Override
    public String toString() {
        return getProductName() + "\t\t" + count + "\t\t" + String.format("%,3d", getTotalPrice());
    }

    public String formatBonus() {
        return getProductName() + "\t\t" + calculateTotalBonus() + "\t\t" + String.format("%,3d", calculateDiscount());
    }
}
