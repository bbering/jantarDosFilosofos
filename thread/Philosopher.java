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
    if (id == 0) {
      sleep((int) (1000 / mainController.buttersTSlider.getValue()));
    }
    if (id == 1) {
      sleep((int) (1000 / mainController.kyleTSlider.getValue()));
    }
    if (id == 2) {
      sleep(((int) (1000 / mainController.ericTSlider.getValue())));
    }
    if (id == 3) {
      sleep(((int) (1000 / mainController.stanTSlider.getValue())));
    }
    if (id == 4) {
      sleep(((int) (1000 / mainController.kennyTSlider.getValue())));
    }
  }

  // METODO ONDE O PERSONAGEM COME

  public void eating() throws InterruptedException {
    mainController.setEatingImg(id);
    if (id == 0) {
      sleep(((int) (1000 / mainController.buttersESlider.getValue())));
    }
    if (id == 1) {
      sleep(((int) (1000 / mainController.kyleESlider.getValue())));
    }
    if (id == 2) {
      sleep(((int) (1000 / mainController.ericESlider.getValue())));
    }
    if (id == 3) {
      sleep(((int) (1000 / mainController.stanESlider.getValue())));
    }
    if (id == 4) {
      sleep(((int) (1000 / mainController.kennyESlider.getValue())));
    }
  }
}