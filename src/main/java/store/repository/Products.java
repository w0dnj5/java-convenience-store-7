package store.repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import store.product.Product;

public class Products {

    private static final Map<String, List<Product>> products = new HashMap<>();

    public void add(Product product) {
        String productName = product.getName();
        List<Product> list = products.getOrDefault(productName, new LinkedList<>());
        list.add(product);
        products.put(productName, list);
    }

    public List<Product> findProductsByName(String productName) {
        return products.get(productName);
    }
}
