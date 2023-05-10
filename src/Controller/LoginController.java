package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends Application implements Initializable {
    // Necessário para destruir a tela
    static Stage stageLogin;

    public static Stage getStageLogin() {
        return stageLogin;
    }

    public static void setStageLogin(Stage stageLogin) {
        LoginController.stageLogin = stageLogin;
    }
    
    @FXML
    private Button btn_logar;

    @FXML
    private TextField input1;

    @FXML
    private TextField input2;

    @FXML
    private Label msg;

    @FXML
    void btn_log_event(ActionEvent event) throws Exception {
        String email = input1.getText();
        String password = input2.getText();
        
        // Verificando se os campos foram preenchidos, depois se o email e senha digitadas então corretos
        if (email.equals("") || password.equals("")) {
            msg.setText("Por favor, preencha todos os campos!");
        } else if (email.equals("yasmin@admin.com") && password.equals("adm")) {
            // Chamando a tela de logado para aparecer
            LogadoController logadoScreen = new LogadoController();
            Stage stage = new Stage();
            logadoScreen.start(stage);
            stageLogin.close(); // Removendo tela do form
        } 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @Override
    // Veio do arquivo principal, que no caso é 'Test_jk'
    // Apresenta na tela
    // Método obrigatório
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
        Scene scene = new Scene(root);
        setStageLogin(stage); // Removendo a tela do form logo após a tela home aparecer
        stage.setScene(scene);
        stage.show();
    }
}