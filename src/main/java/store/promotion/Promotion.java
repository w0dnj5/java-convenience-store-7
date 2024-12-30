package store.promotion;

import java.time.LocalDate;

public class Promotion {

    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public int getReceiveFreeCount(int count, LocalDate now) {
        if (isInPeriod(now) && (count + get) % (buy + get) == 0) {
            return get;
        }
        return 0;
    }

    private boolean isInPeriod(LocalDate now) {
        return !(now.isBefore(startDate) || now.isAfter(endDate));
    }
}
