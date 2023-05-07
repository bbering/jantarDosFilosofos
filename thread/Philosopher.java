package thread;

import control.*;

public class Philosopher extends Thread {
    private int id;
    mainController mainController = new mainController();

    public void setController(mainController controller) {
        this.mainController = controller;
    }

    public Philosopher(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            thinking();
        } catch (InterruptedException e) {
        }
        try {
            mainController.grabAFork(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            mainController.returnFork(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void thinking() throws InterruptedException{
        sleep(1000);
    }
}
