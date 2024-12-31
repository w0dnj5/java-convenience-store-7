package store.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.promotion.Promotion;

public class Promotions {

    private static final Map<String, Promotion> storage = new HashMap<>();

    public void addAll(List<Promotion> promotions) {
        promotions.forEach(promotion ->
                storage.put(promotion.getName(), promotion)
        );
    }

    public Promotion get(String promotionName) {
        return storage.getOrDefault(promotionName, null);
    }
}
