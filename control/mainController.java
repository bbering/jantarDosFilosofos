package control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import thread.Philosopher;

public class mainController implements Initializable {

  // DECLARACAO DOS RECURSOS DO FXML

  // SLIDERS

  @FXML
  public Slider buttersESlider;

  @FXML
  public Slider buttersTSlider;

  @FXML
  public Slider ericESlider;

  @FXML
  public Slider ericTSlider;

  @FXML
  public Slider kennyESlider;

  @FXML
  public Slider kennyTSlider;

  @FXML
  public Slider kyleESlider;

  @FXML
  public Slider kyleTSlider;

  @FXML
  public Slider stanESlider;

  @FXML
  public Slider stanTSlider;

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

  // BOTAO DE PAUSE COM VARIAVEL DE TRAVA

  @FXML
  private Button pauseButton01;
  private int isLocked01;
  @FXML
  private Button pauseButton02;
  private int isLocked02;
  @FXML
  private Button pauseButton03;
  private int isLocked03;
  @FXML
  private Button pauseButton04;
  private int isLocked04;
  @FXML
  private Button pauseButton05;
  private int isLocked05;

  // BOTAO DE RESET

  @FXML
  private Button resetButton;

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
    this.philosopher3 = new Philosopher(2);
    this.philosopher4 = new Philosopher(3);
    this.philosopher5 = new Philosopher(4);

    // Setando os controllers dos filosofos
    philosopher1.setController(this);
    philosopher2.setController(this);
    philosopher3.setController(this);
    philosopher4.setController(this);
    philosopher5.setController(this);

    // Botoes de pause

    pauseButton01.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (isLocked01 == 0) {
          philosopher1.suspend();
          isLocked01 = 1;
        } else {
          philosopher1.resume();
        }
      }
    });

    pauseButton02.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (isLocked02 == 0) {
          philosopher2.suspend();
          isLocked02 = 1;
        } else {
          philosopher2.resume();
        }
      }
    });

    pauseButton03.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (isLocked03 == 0) {
          philosopher3.suspend();
          isLocked03 = 1;
        } else {
          philosopher3.resume();
        }
      }
    });

    pauseButton04.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (isLocked04 == 0) {
          philosopher4.suspend();
          isLocked04 = 1;
        } else {
          philosopher4.resume();
        }
      }
    });

    pauseButton05.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (isLocked05 == 0) {
          philosopher5.suspend();
          isLocked05 = 1;
        } else {
          philosopher5.resume();
        }
      }
    });

    // BOTAO DE RESTART

    resetButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        philosopher1.stop();
        philosopher2.stop();
        philosopher3.stop();
        philosopher4.stop();
        philosopher5.stop();
        createNewThread();
      }
    });

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

  // Metodo que seta os estados com 0

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
    mutexSemaphore.release();
  } // FIM DEVOLVE GARFOS

  public void testAFork(int id) throws InterruptedException {
    if (state[id] == 1 && state[((id - 1) + 5) % 5] != 2 && state[(id + 1) % 5] != 2) {
      state[id] = 2;
      arraySemaphore[id].release();
    } // FIM DO IF
  } // FIM DO METODO QUE TESTA OS GARFOS

  public void createNewThread() {

    // Setando os estados para 0 novamente

    setState();

    // Zerando as travas do pause

    isLocked01 = 0;
    isLocked02 = 0;
    isLocked03 = 0;
    isLocked04 = 0;
    isLocked05 = 0;

    // Resetando as imagens necessarias

    thinkingButters.setImage(new Image("/assets/thinkingButters.png"));
    thinkingKyle.setImage(new Image("/assets/thinkingKyle.png"));
    thinkingEric.setImage(new Image("/assets/thinkingEric.png"));
    thinkingStan.setImage(new Image("/assets/thinkingStan.png"));
    thinkingKenny.setImage(new Image("/assets/thinkingKenny.png"));
    plate01.setVisible(false);
    plate02.setVisible(false);
    plate03.setVisible(false);
    plate04.setVisible(false);
    plate05.setVisible(false);
    fork01.setVisible(true);
    fork02.setVisible(true);
    fork03.setVisible(true);
    fork04.setVisible(true);
    fork05.setVisible(true);

    // Setando os valores dos sliders como valores iniciais novamente

    buttersTSlider.setValue(1);
    kyleTSlider.setValue(1);
    ericTSlider.setValue(1);
    stanTSlider.setValue(1);
    kennyTSlider.setValue(1);

    buttersESlider.setValue(1);
    kyleESlider.setValue(1);
    ericESlider.setValue(1);
    stanESlider.setValue(1);
    kennyESlider.setValue(1);

    // Criando novas threads para serem executadas

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

    // Iniciando novamente as threads

    philosopher1.start();
    philosopher2.start();
    philosopher3.start();
    philosopher4.start();
    philosopher5.start();
  }
}