package store.promotion;

import java.time.LocalDate;
import java.util.Map;

public class Promotion {

    private final String name;
    private final int price;
    private final int buy;
    private final int get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(Map<String, String> data) {
        name = data.get("name");
        price = Integer.parseInt(data.get("price"));
        buy = Integer.parseInt(data.get("buy"));
        get = Integer.parseInt(data.get("get"));
        startDate = LocalDate.parse(data.get("start_date"));
        endDate = LocalDate.parse(data.get("end_date"));
    }

    public String getName() {
        return name;
    }
}
