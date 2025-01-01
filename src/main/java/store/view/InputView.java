package store.view;

import static store.view.InputMessage.REQUEST_FOR_ANOTHER_PURCHASE;
import static store.view.InputMessage.REQUEST_MEMBERSHIP_APPLY;
import static store.view.InputMessage.REQUEST_MORE_BONUS_APPLY;
import static store.view.InputMessage.REQUEST_NO_PROMOTION_APPLY;
import static store.view.InputMessage.REQUEST_ORDERS;

import camp.nextstep.edu.missionutils.Console;
import store.order.Order;

public class InputView {

    public String requestOrders() {
        System.out.println(REQUEST_ORDERS.getMessage());
        return read();
    }

    public String requestMoreBonusApply(Order order) {
        System.out.printf(REQUEST_MORE_BONUS_APPLY.getMessage(), order.getProductName(), order.calculateMoreBonus());
        System.out.println();
        return read();
    }

    public String requestNoPromotionApply(Order order) {
        System.out.printf(REQUEST_NO_PROMOTION_APPLY.getMessage(), order.getProductName(),
                order.calculateNoPromotionApply());
        System.out.println();
        return read();
    }

    public String requestForAnotherPurchase() {
        System.out.println(REQUEST_FOR_ANOTHER_PURCHASE.getMessage());
        return read();
    }

    public String requestMembershipApply() {
        System.out.println(REQUEST_MEMBERSHIP_APPLY.getMessage());
        return read();
    }

    private String read() {
        return Console.readLine();
    }
}
