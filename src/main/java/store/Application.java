package store;

import store.controller.Controller;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
    }
}
