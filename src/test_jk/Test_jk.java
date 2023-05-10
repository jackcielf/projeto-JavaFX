package test_jk;

import Controller.HomeController;
import static Controller.LoginController.setStageLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test_jk extends Application {
    public static void main(String[] args) throws Exception {
        HomeController tela1 = new HomeController();
        Stage stage = new Stage();
        tela1.start(stage);
    }
    
    @Override
    // Veio do arquivo principal, que no caso é 'Test_jk'
    // Apresenta na tela
    // Método obrigatório
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/home.fxml"));
        Scene scene = new Scene(root);
        setStageLogin(stage); // Removendo a tela do form logo após a tela home aparecer
        stage.setScene(scene);
        stage.show();
    }
}