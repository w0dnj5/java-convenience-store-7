package store.repository;

import java.util.HashMap;
import java.util.Map;
import store.promotion.Promotion;

public class Promotions {

    private static final Map<String, Promotion> promotions = new HashMap<>();

    public void add(Promotion promotion) {
        String promotionName = promotion.getName();
        promotions.put(promotionName, promotion);
    }

    public Promotion get(String promotionName) {
        return promotions.getOrDefault(promotionName, null);
    }
}
