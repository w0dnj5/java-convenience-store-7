package store.view;

import static store.view.InputMessage.REQUEST_NO_PROMOTION_APPLY;
import static store.view.InputMessage.REQUEST_ORDERS;
import static store.view.InputMessage.REQUEST_RECEIVE_FREE;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String requestOrders() {
        System.out.println(REQUEST_ORDERS.getMessage());
        return read();
    }

    public String requestReceiveFree(String orderName, int count) {
        System.out.printf(REQUEST_RECEIVE_FREE.getMessage(), orderName, count);
        System.out.println();
        return read();
    }

    public String requestNoPromotionApply(String orderName, int count) {
        System.out.printf(REQUEST_NO_PROMOTION_APPLY.getMessage(), orderName, count);
        System.out.println();
        return read();
    }

    private String read() {
        return Console.readLine();
    }
}
