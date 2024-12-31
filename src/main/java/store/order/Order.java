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

    public int getReceiveFreeCount() {
        return products.stream()
                .mapToInt(product -> product.getReceiveFreeCount(date, count))
                .sum();
    }

    public int getNoPromotionApplyCount() {
        return products.stream()
                .mapToInt(product -> product.getNoPromotionApplyCount(date, count))
                .sum();
    }
}
