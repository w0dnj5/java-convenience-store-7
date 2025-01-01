package store.view;

import static store.view.OutputMessage.HELLO_MESSAGE;

import store.product.Products;
import store.receipt.Receipt;

public class OutputView {

    public void showAllProducts(Products products) {
        System.out.println(HELLO_MESSAGE.getMessage());
        System.out.println();
        System.out.println(products);
    }

    public void showReceipt(Receipt receipt) {
        System.out.println(receipt);
    }

    public void showErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
