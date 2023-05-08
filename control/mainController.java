package control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import thread.Philosopher;

public class mainController implements Initializable {

  // DECLARACAO DOS RECURSOS DO FXML

  // SLIDERS

  @FXML
  private Slider buttersESlider;

  @FXML
  private Slider buttersTSlider;

  @FXML
  private Slider ericESlider;

  @FXML
  private Slider ericTSlider;

  @FXML
  private Slider kennyESlider;

  @FXML
  private Slider kennyTSlider;

  @FXML
  private Slider kyleESlider;

  @FXML
  private Slider kyleTSlider;

  @FXML
  private Slider stanESlider;

  @FXML
  private Slider stanTSlider;

  // IMAGEVIEW

  @FXML
  private ImageView fork01;

  @FXML
  private ImageView fork02;

  @FXML
  private ImageView fork03;

  @FXML
  private ImageView fork04;

  @FXML
  private ImageView fork05;

  @FXML
  private ImageView plate01;

  @FXML
  private ImageView plate02;

  @FXML
  private ImageView plate03;

  @FXML
  private ImageView plate04;

  @FXML
  private ImageView plate05;

  @FXML
  private ImageView thinkingButters;

  @FXML
  private ImageView thinkingEric;

  @FXML
  private ImageView thinkingKenny;

  @FXML
  private ImageView thinkingKyle;

  @FXML
  private ImageView thinkingStan;

  // THREADS UTILIZADA NO CODIGO

  private Philosopher philosopher1;

  private Philosopher philosopher2;

  private Philosopher philosopher3;

  private Philosopher philosopher4;

  private Philosopher philosopher5;

  // ARRAYS E SEMAFOROS

  private int nPhilosophers = 5;
  int state[] = new int[nPhilosophers];
  Semaphore mutexSemaphore = new Semaphore(1);
  Semaphore arraySemaphore[] = new Semaphore[nPhilosophers];

  // VARIAVEIS DE VELOCIDADE
  private int TSpeed = 1000;
  private int ESpeed = 1000;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setState();
    // Instanciando as threads dos filosofos
    this.philosopher1 = new Philosopher(0);
    this.philosopher2 = new Philosopher(1);
    this.philosopher3 = new Philosopher(2);
    this.philosopher4 = new Philosopher(3);
    this.philosopher5 = new Philosopher(4);

    // Setando os controllers dos filosofos
    philosopher1.setController(this);
    philosopher2.setController(this);
    philosopher3.setController(this);
    philosopher4.setController(this);
    philosopher5.setController(this);

    // Startando as threads
    philosopher1.start();
    philosopher2.start();
    philosopher3.start();
    philosopher4.start();
    philosopher5.start();
  }

  // Metodos de setar as imagens

  public void setThinkingImg(int id) {
    Platform.runLater(() -> {
      if (id == 0) {
        thinkingButters.setImage(new Image("/assets/thinkingButters.png"));
        fork01.setVisible(true);
        fork02.setVisible(true);
        plate01.setVisible(false);
      }
      if (id == 1) {
        thinkingKyle.setImage(new Image("/assets/thinkingKyle.png"));
        fork02.setVisible(true);
        fork03.setVisible(true);
        plate02.setVisible(false);
      }
      if (id == 2) {
        thinkingEric.setImage(new Image("/assets/thinkingEric.png"));
        fork03.setVisible(true);
        fork04.setVisible(true);
        plate03.setVisible(false);
      }
      if (id == 3) {
        thinkingStan.setImage(new Image("/assets/thinkingStan.png"));
        fork04.setVisible(true);
        fork05.setVisible(true);
        plate04.setVisible(false);
      }
      if (id == 4) {
        thinkingKenny.setImage(new Image("/assets/thinkingKenny.png"));
        fork05.setVisible(true);
        fork01.setVisible(true);
        plate05.setVisible(false);
      }
    });
  }

  public void setEatingImg(int id) {
    Platform.runLater(() -> {
      if (id == 0) {
        thinkingButters.setImage(new Image("/assets/eatingButters.png"));
        fork01.setVisible(false);
        fork02.setVisible(false);
        plate01.setVisible(true);
      }
      if (id == 1) {
        thinkingKyle.setImage(new Image("/assets/eatingKyle.png"));
        fork02.setVisible(false);
        fork03.setVisible(false);
        plate02.setVisible(true);
      }
      if (id == 2) {
        thinkingEric.setImage(new Image("/assets/eatingEric.png"));
        fork03.setVisible(false);
        fork04.setVisible(false);
        plate03.setVisible(true);
      }
      if (id == 3) {
        thinkingStan.setImage(new Image("/assets/eatingStan.png"));
        fork04.setVisible(false);
        fork05.setVisible(false);
        plate04.setVisible(true);
      }
      if (id == 4) {
        thinkingKenny.setImage(new Image("/assets/eatingKenny.png"));
        fork05.setVisible(false);
        fork01.setVisible(false);
        plate05.setVisible(true);
      }
    });
  }

  // Metodo que inicia os estados com 0

  public void setState() {
    for (int i = 0; i <= 4; i++) {
      state[i] = 0;
      arraySemaphore[i] = new Semaphore(0);
    }
  }

  public void grabAFork(int id) throws InterruptedException {
    mutexSemaphore.acquire();
    state[id] = 1; // ESTADO DE FOME
    testAFork(id);
    mutexSemaphore.release();
    arraySemaphore[id].acquire();
  } // FIM DO METODO QUE PEGA OS GARFOS

  public void returnFork(int id) throws InterruptedException {
    mutexSemaphore.acquire();
    state[id] = 0; // ESTADO DE PENSANDO
    setThinkingImg(id);
    testAFork(((id - 1) + 5) % 5); // TESTANDO GARFO DA ESQUERDA
    testAFork((id + 1) % 5); // TESTANDO GARFO DA DIREITA
    System.out.println("O FILOSOFO " + id + " DEVOLVEU OS GARFOS");
    mutexSemaphore.release();
  } // FIM DEVOLVE GARFOS

  public void testAFork(int id) throws InterruptedException {
    if (state[id] == 1 && state[((id - 1) + 5) % 5] != 2 && state[(id + 1) % 5] != 2) {
      state[id] = 2;
      arraySemaphore[id].release();
    } // FIM DO IF
  } // FIM DO METODO QUE TESTA OS GARFOS

  // METODO QUE SETA O ESTADO DE PENSANDO
  public void setState(int id) {
    state[id] = 0;
  }

  // METODOS QUE SETAM AS VELOCIDADES DE PENSAR E COMER UTILIZANDO OS SLIDERS

  public void setTValue(int id) {
    if (id == 0) {
      this.TSpeed = (int) buttersTSlider.getValue() * 1000;
    }
    if (id == 1) {
      this.TSpeed = (int) kyleTSlider.getValue() * 1000;
    }
    if (id == 2) {
      this.TSpeed = (int) ericTSlider.getValue() * 1000;
    }
    if (id == 3) {
      this.TSpeed = (int) stanTSlider.getValue() * 1000;
    }
    if (id == 4) {
      this.TSpeed = (int) kennyTSlider.getValue() * 1000;
    }
  }

  public void setEValue(int id) {
    if (id == 0) {
      this.ESpeed = (int) buttersESlider.getValue() * 1000;
    }
    if (id == 1) {
      this.ESpeed = (int) kyleESlider.getValue() * 1000;
    }
    if (id == 2) {
      this.ESpeed = (int) ericESlider.getValue() * 1000;
    }
    if (id == 3) {
      this.ESpeed = (int) stanESlider.getValue() * 1000;
    }
    if (id == 4) {
      this.ESpeed = (int) kennyESlider.getValue() * 1000;
    }
  }

  public int getTSpeed() {
      return TSpeed;
  }

  public int getESpeed() {
      return ESpeed;
  }
}