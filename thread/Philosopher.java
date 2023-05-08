package thread;

import control.*;
import javafx.application.Platform;

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
  public void run() { // METODO QUE É INICIADO AO STARTAR A THREAD
    while (true) {
      try {
        thinking();
        mainController.grabAFork(id);
        eating();
        mainController.returnFork(id);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  // METODO QUE FAZ O PERSONAGEM "PENSAR"

  public void thinking() throws InterruptedException {
    Platform.runLater(() -> mainController.setState(id));
    System.out.println(mainController.getTSpeed());
    sleep(mainController.getTSpeed());
  }
 
  // METODO ONDE O PERSONAGEM COME
  
  public void eating() throws InterruptedException {
    mainController.setEatingImg(id);
    System.out.println(mainController.getESpeed());
    sleep(mainController.getESpeed());
  }
}