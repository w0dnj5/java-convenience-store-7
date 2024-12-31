package store.view;

public class OutputView {

    public void showAllProducts(String products) {
        System.out.println(OutputMessage.HELLO_MESSAGE.getMessage());
        System.out.println();
        System.out.println(products);
    }
}
