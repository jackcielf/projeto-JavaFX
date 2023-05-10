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
import javafx.stage.Stage;


public class CardapioController extends Application implements Initializable {
    static Stage stageCardapio;

    public static Stage getStageCardapio() {
        return stageCardapio;
    }

    public static void setStageCardapio(Stage stageCardapio) {
        CardapioController.stageCardapio = stageCardapio;
    }
    
    @FXML
    private Button cdp_bownie_8;

    @FXML
    private Label cdp_bruschetta;

    @FXML
    private Label cdp_bruschetta1;

    @FXML
    private Button cdp_bruschetta_1;

    @FXML
    private Button cdp_ceviche_4;

    @FXML
    private Button cdp_pave_7;

    @FXML
    private Button cdp_risoto_6;

    @FXML
    private Label cdp_salada;

    @FXML
    private Label cdp_salada1;

    @FXML
    private Label cdp_salada11;

    @FXML
    private Button cdp_saladaCabrese_5;

    @FXML
    private Button cdp_saladaFruta_3;

    @FXML
    private Label cdp_torresmo;

    @FXML
    private Label cdp_torresmo1;

    @FXML
    private Label cdp_torresmo11;

    @FXML
    private Button cdp_torresmo_2;

    @FXML
    private Label title_cardapio;

    @FXML
    private Label title_cardapio1;

    @FXML
    private Label title_cardapio11;

    @FXML
    void btn_add_bruschetta_1_event(ActionEvent event) {
        
    }

    @FXML
    void btn_add_saladaFruita_3_event(ActionEvent event) {

    }

    @FXML
    void btn_add_torresmo_2_event(ActionEvent event) {

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    // Veio do arquivo principal, que no caso é 'Test_jk'
    // Apresenta na tela
    // Método obrigatório
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/cardapio.fxml"));
        
        Scene scene = new Scene(root);
        setStageCardapio(stage); // Removendo a tela do form logo após a tela home aparecer
        stage.setScene(scene);
        stage.show();
    }
    
}
