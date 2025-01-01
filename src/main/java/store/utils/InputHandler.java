package store.utils;

import static store.error.ErrorMessage.WRONG_INPUT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InputHandler {

    public List<Map<String, String>> toOrderData(String input) {
        List<Map<String, String>> list = new LinkedList<>();
        String[] orders = input.split(",");
        Arrays.stream(orders).forEach(order -> list.add(toMap(order)));
        return list;
    }

    private Map<String, String> toMap(String order) {
        Map<String, String> map = new HashMap<>();
        List<String> data = Arrays.stream(order.replace("[", "").replace("]", "").split("-")).toList();
        map.put("name", data.getFirst());
        map.put("count", data.getLast());
        return map;
    }

    public boolean toYesOrNo(String input) {
        if (isYes(input)) {
            return true;
        }
        if (isNo(input)) {
            return false;
        }
        throw new IllegalArgumentException(WRONG_INPUT.getMessage());
    }

    private boolean isYes(String input) {
        return input.equalsIgnoreCase("Y");
    }

    private boolean isNo(String input) {
        return input.equalsIgnoreCase("N");
    }
}
