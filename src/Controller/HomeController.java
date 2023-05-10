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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController extends Application implements Initializable {
    static Stage stageHome; // Necessário para destruir a tela

    public static Stage getStageHome() {
        return stageHome;
    }

    public static void setStageHome(Stage stageHome) {
        HomeController.stageHome = stageHome;
    }

    @FXML
    private AnchorPane barra_itens;

    @FXML
    private Button btn_cad;

    @FXML
    private Button btn_card;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_res;

    @FXML
    private AnchorPane fundoHome;
    
    @FXML
    void btn_cad_event(ActionEvent event) throws Exception {
        // Chamando a tela de home para aparecer
        CadastroController cadastroScreen = new CadastroController();
        Stage stage = new Stage();
        cadastroScreen.start(stage);
        stageHome.close(); // Removendo tela do form
    }

    @FXML
    void btn_login_event(ActionEvent event) throws Exception {
        // Chamando a tela de home para aparecer
        LoginController loginScreen = new LoginController();
        Stage stage = new Stage();
        loginScreen.start(stage);
        stageHome.close(); // Removendo tela do form
    }
    
    @FXML
    void btn_cardapio_event(ActionEvent event) throws Exception {
        // Chamando a tela de home para aparecer
        CardapioController cardapioScreen = new CardapioController();
        Stage stage = new Stage();
        cardapioScreen.start(stage);
        stageHome.close(); // Removendo tela do form
    }
    
    @FXML 
    void btn_reserva_event(ActionEvent event) throws Exception {
        // Chamando a tela de home para aparecer
        ReservaController reservaScreen = new ReservaController();
        Stage stage = new Stage();
        reservaScreen.start(stage);
        stageHome.close(); // Removendo tela do form
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/home.fxml"));
        Scene scene = new Scene(root);
        setStageHome(stage);
        stage.setScene(scene);
        stage.show();
    }
}