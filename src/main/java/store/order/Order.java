package store.order;

import camp.nextstep.edu.missionutils.DateTimes;
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
        date = DateTimes.now().toLocalDate();
    }

    private void validate(List<Product> products, int count) {
        if (products.stream().mapToInt(Product::getQuantity).sum() < count) {
            throw new IllegalArgumentException();
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
