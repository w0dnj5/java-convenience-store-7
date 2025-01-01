package store.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import store.order.Order;
import store.product.Product;
import store.promotion.Promotion;
import store.receipt.Receipt;
import store.repository.Products;
import store.repository.Promotions;
import store.utils.FileHandler;
import store.utils.FileReader;
import store.utils.InputHandler;
import store.view.InputView;
import store.view.OutputView;

public class Controller {

    private FileHandler fileHandler;
    private InputHandler inputHandler;
    private InputView inputView;
    private OutputView outputView;

    public void run() {
        fileHandler = new FileHandler(new FileReader());
        inputView = new InputView();
        inputHandler = new InputHandler();
        outputView = new OutputView();
        Promotions promotions = new Promotions();
        Products products = new Products();
        promotions.addAll(toPromotions(fileHandler.convert("src/main/resources/promotions.md")));
        products.addAll(toProducts(promotions, fileHandler.convert("src/main/resources/products.md")));

        do {
            outputView.showAllProducts(products);
            List<Order> orders = repeatUntilSuccess(
                    () -> toOrders(products, inputHandler.toOrderData(inputView.requestOrders())));
            orders.forEach(this::progress);
            Receipt receipt = repeatUntilSuccess(
                    () -> new Receipt(orders, inputHandler.toYesOrNo(inputView.requestMembershipApply())));
            outputView.showReceipt(receipt);
            receipt.apply();
        } while (repeatUntilSuccess(() -> inputHandler.toYesOrNo(inputView.requestForAnotherPurchase())));
    }

    private List<Promotion> toPromotions(List<Map<String, String>> data) {
        return data.stream().map(map ->
                new Promotion(
                        map.get("name"),
                        Integer.parseInt(map.get("buy")),
                        Integer.parseInt(map.get("get")),
                        LocalDate.parse(map.get("start_date")),
                        LocalDate.parse(map.get("end_date"))
                )).toList();
    }

    private List<Product> toProducts(Promotions promotions, List<Map<String, String>> data) {
        return data.stream().map(map ->
                new Product(
                        map.get("name"),
                        Integer.parseInt(map.get("price")),
                        Integer.parseInt(map.get("quantity")),
                        promotions.get(map.get("promotion"))
                )).toList();
    }

    private List<Order> toOrders(Products products, List<Map<String, String>> data) {
        try {
            return data.stream().map(map ->
                    new Order(
                            products.findProductsByName(map.get("name")),
                            Integer.parseInt(map.get("count"))
                    )).toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private void progress(Order order) {
        requestMoreBonusApply(order);
        requestNoPromotionApply(order);
    }

    private void requestMoreBonusApply(Order order) {
        int count = order.calculateMoreBonus();
        if (count > 0) {
            boolean result = repeatUntilSuccess(
                    () -> inputHandler.toYesOrNo(inputView.requestMoreBonusApply(order)));
            if (result) {
                order.addMoreBonus();
            }
        }
    }

    private void requestNoPromotionApply(Order order) {
        int count = order.calculateNoPromotionApply();
        if (count > 0) {
            boolean result = repeatUntilSuccess(
                    () -> inputHandler.toYesOrNo(inputView.requestNoPromotionApply(order)));
            if (!result) {
                order.dropNoPromotionApply();
            }
        }
    }

    private <T> T repeatUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {

            }
        }
    }
}
