package control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import thread.Philosopher;

public class mainController implements Initializable {

  // DECLARACAO DOS RECURSOS DO FXML

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
  private ImageView fork06;

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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setState();
    // Instanciando as threads dos filosofos
    this.philosopher1 = new Philosopher(0);
    this.philosopher2 = new Philosopher(1);
    this.philosopher3 = new Philosopher(3);
    this.philosopher4 = new Philosopher(4);
    this.philosopher5 = new Philosopher(5);

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

  public void setEatingImg(int id) {
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
      fork06.setVisible(false);
      plate05.setVisible(true);
    }
  }

  public void setState() {
    for (int i = 0; i <= 4; i++) {
      state[i] = 0;
      arraySemaphore[i] = new Semaphore(0);
    }
  }

  public void grabAFork(int id) throws InterruptedException {
    mutexSemaphore.acquire();
    state[id] = 1; // ESTADO DE FOME
    System.err.println("SETOU FOME");
    testAFork(id);
    System.out.println("O FILOSOFO: " + id + " ESTA COMENDO");
    setEatingImg(id);
    mutexSemaphore.release();
    arraySemaphore[id].acquire();
  } // FIM DO METODO QUE PEGA OS GARFOS

  public void returnFork(int id) throws InterruptedException {
    mutexSemaphore.acquire();
    state[id] = 1; // ESTADO DE PENSANDO
    testAFork((id - 1) % 5); // TESTANDO GARFO DA ESQUERDA
    testAFork((id + 1) % 5); // TESTANDO GARFO DA DIREITA
    mutexSemaphore.release();
    System.out.println("DEVOLVEU GARFO");
  } // FIM DEVOLVE GARFOS

  public void testAFork(int id) throws InterruptedException {
    if (state[id] == 1 && state[(id - 1) % 5] != 2 && state[(id + 1) % 5] != 2) {
      state[id] = 2;
      arraySemaphore[id].release();
    } // FIM DO IF
  } // FIM DO METODO QUE TESTA OS GARFOS
}
