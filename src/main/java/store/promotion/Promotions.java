package store.promotion;

import java.util.HashMap;
import java.util.Map;

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
