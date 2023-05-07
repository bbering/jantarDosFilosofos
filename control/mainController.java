package control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import thread.Philosopher;

public class mainController implements Initializable{

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
  private ImageView fork07;

  @FXML
  private ImageView fork08;

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
  
  private int nPhilosophers = 5;
  int state[] = new int[nPhilosophers];
  Semaphore mutexSemaphore = new Semaphore(1);
  Semaphore arraySemaphore[] = new Semaphore[nPhilosophers];

  public void setEatingImg(int id){
    if(id == 0){
      thinkingEric.setImage(new Image("/assets/eric.png"));
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
    state[id] = 1; // ESTADO DE PENSANDO
    testAFork((id-1)%5); // TESTANDO GARFO DA ESQUERDA
    testAFork((id+1)%5); // TESTANDO GARFO DA DIREITA
    mutexSemaphore.release();
  } // FIM DEVOLVE GARFOS

  public void testAFork(int id) throws InterruptedException {
    if (state[id] == 1 && state[(id - 1) % 5] != 2 && state[(id + 1) % 5] != 2) {
      state[id] = 2;
      arraySemaphore[id].acquire();
    } // FIM DO IF
  } // FIM DO METODO QUE TESTA OS GARFOS

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.philosopher1 = new Philosopher("butters", 0);
    philosopher1.setController(this);
    this.philosopher2 = new Philosopher("kyle", 1);
    philosopher2.setController(this);
    this.philosopher3 = new Philosopher("eric", 2);
    philosopher3.setController(this);
    this.philosopher4 = new Philosopher("stan", 3);
    philosopher4.setController(this);
    this.philosopher5 = new Philosopher("kenny", 4);
    philosopher5.setController(this);
    //philosopher1.run();
    //philosopher2.run();
    //philosopher3.run();
    //philosopher4.run();
    //philosopher5.run();
  }
} 
