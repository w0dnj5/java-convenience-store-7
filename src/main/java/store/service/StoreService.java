package store.service;

import java.time.LocalDate;
import java.util.List;
import store.product.Product;
import store.repository.Products;
import store.repository.Promotions;

public class StoreService {

    private final Products products;
    private final Promotions promotions;

    public StoreService(Products products, Promotions promotions) {
        this.products = products;
        this.promotions = promotions;
    }

    public void add(Product product) {
        products.add(product);
    }

    public int getReceiveFreeCount(String productName, int count, LocalDate now) {
        List<Product> find = products.findProductsByName(productName);
        validate(find, count);
        return find.stream().mapToInt(product -> product.getReceiveFreeCount(count, now)).sum();
    }

    public void validate(List<Product> find, int count) {
        if (find.stream().mapToInt(Product::getQuantity).sum() < count) {
            throw new IllegalArgumentException();
        }
    }
}
