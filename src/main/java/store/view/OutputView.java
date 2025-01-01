package store.view;

import store.receipt.Receipt;
import store.repository.Products;

public class OutputView {

    public void showAllProducts(Products products) {
        System.out.println(OutputMessage.HELLO_MESSAGE.getMessage());
        System.out.println();
        System.out.println(products);
    }

    public void showReceipt(Receipt receipt) {
        System.out.println(receipt);
    }
}
