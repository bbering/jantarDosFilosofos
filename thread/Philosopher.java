package thread;

import control.*;
import javafx.application.Platform;

public class Philosopher extends Thread {
    private int id;
    private String name;
    mainController mainController = new mainController();

    public void setController(mainController controller) {
        this.mainController = controller;
    }

    public Philosopher(String name, int id) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            mainController.grabAFork(3);
        } catch (InterruptedException e) {
        }
        try {
            mainController.returnFork(3);
        } catch (InterruptedException e) {
        }
    }
}
