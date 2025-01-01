package store.product;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Products {

    private final Map<String, List<Product>> storage = new HashMap<>();

    public void addAll(List<Product> products) {
        products.forEach(this::add);
    }

    private void add(Product product) {
        String productName = product.getName();
        List<Product> list = storage.getOrDefault(productName, new LinkedList<>());
        list.add(product);
        storage.put(productName, list);
    }

    public List<Product> findProductsByName(String productName) {
        return storage.get(productName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Product> products = new LinkedList<>();
        storage.values().forEach(products::addAll);
        products.forEach(p -> sb.append(p.toString()).append("\n"));
        return sb.toString();
    }
}
