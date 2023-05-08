/* ***************************************************************
* Autor............: Breno Bering Silva
* Matricula........: 202110863
* Inicio...........: 06/05/2023
* Ultima alteracao.: 08/05/2023
* Nome.............: Jantar dos Filósofos
* Funcao...........: Resolver problema do Jantar dos Filosofos
*************************************************************** */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import control.*;

public class Principal extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // CRIANDO A TELA PRINCIPAL
    mainController control = new mainController();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/background.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.setWidth(600);
    primaryStage.setHeight(400);
    primaryStage.setResizable(false);
    primaryStage.setTitle("Jantar dos Filósofos");
    primaryStage.getIcons().add(new Image("/assets/fork.png"));
    primaryStage.show();
  }
}