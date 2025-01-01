package store.receipt;

import java.util.List;
import store.order.Order;

public class Receipt {

    private final List<Order> orders;
    private final boolean memberShip;

    public Receipt(List<Order> orders, boolean memberShip) {
        this.orders = orders;
        this.memberShip = memberShip;
    }

    public int getTotalCount() {
        return orders.stream().mapToInt(Order::getCount).sum();
    }

    public int getTotalPrice() {
        return orders.stream().mapToInt(Order::getTotalPrice).sum();
    }

    public int getTotalDiscount() {
        return orders.stream().mapToInt(Order::calculateDiscount).sum();
    }

    public int getMembershipDiscount() {
        if (!memberShip) {
            return 0;
        }
        return Math.max(getTotalPrice() / 100 * 30 * -1, -8000);
    }

    public void apply() {
        orders.forEach(Order::apply);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("==============W 편의점================")
                .append("\n")
                .append("상품명\t\t수량\t\t금액")
                .append("\n");
        orders.forEach(order -> sb.append(order.toString()).append("\n"));
        sb.append("=============증\t정===============")
                .append("\n");
        orders.forEach(order -> sb.append(order.formatBonus()).append("\n"));

        sb.append("====================================")
                .append("\n")
                .append(formatTotalPurchase())
                .append("\n")
                .append(formatTotalDiscount())
                .append("\n")
                .append(formatMembership())
                .append("\n")
                .append(formatResult());
        return sb.toString();
    }

    private String formatTotalPurchase() {
        return "총구매액" + "\t\t" + getTotalCount() + "\t\t" + String.format("%,3d", getTotalPrice());
    }

    private String formatTotalDiscount() {
        return "행사할인" + "\t\t\t\t" + String.format("%,3d", getTotalDiscount());
    }

    private String formatResult() {
        return "내실돈" + "\t\t\t\t" + String.format("%,3d",
                getTotalPrice() + getTotalDiscount() + getMembershipDiscount());
    }

    private String formatMembership() {
        return "멤버십할인" + "\t\t\t\t" + String.format("%,3d", getMembershipDiscount());
    }
}
