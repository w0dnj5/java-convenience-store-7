package store.product;

import java.util.Map;
import store.promotion.Promotion;
import store.promotion.Promotions;

public class Product {

    private final String name;
    private final int price;
    private final int quantity;
    private final Promotion promotion;

    public Product(Map<String, String> data, Promotions promotions) {
        name = data.get("name");
        price = Integer.parseInt(data.get("price"));
        quantity = Integer.parseInt(data.get("quantity"));
        promotion = promotions.get(data.get("promotion"));
    }

    public String getName() {
        return name;
    }
}
