package Controller;

import static Controller.HomeController.stageHome;
import Dao.ReservaDao;
import Model.Reserva;
import Model.Usuario;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ReservaController extends Application implements Initializable {
    static Stage stageReserva;

    public static Stage getStageReserva() {
        return stageReserva;
    }

    public static void setStageReserva(Stage stageReserva) {
        ReservaController.stageReserva = stageReserva;
    }
    
    @FXML
    private TextField input_emailRsv;
    
    @FXML
    private Button btn_reservar;

    @FXML
    private TextField input_horario;

    @FXML
    private TextField input_numMesa;

    @FXML
    private TextField input_numPessoas;

    @FXML
    private ImageView logo_reserva;

    @FXML
    private Label msg;

    @FXML
    void btn_reserva_event(ActionEvent event) throws Exception {
        // Pegando o digitado nos campos
        String emailRsv = input_emailRsv.getText();
        String horario = input_horario.getText();
        String numMesa = input_numMesa.getText();
        String numPessoas = input_numPessoas.getText();
        
        Reserva rsv = new Reserva();
        ReservaDao rsvDao = new ReservaDao();
        Usuario u = new Usuario();
        u.setEmail(emailRsv);
        
        rsv.setHorario(horario);
        rsv.setNumMesa(numMesa);
        rsv.setNumPessoas(numPessoas);
        
        // Verificando se os campos foram preenchidos
        if (horario.equals("") || numMesa.equals("") || numPessoas.equals("")) {
            msg.setText("Preencha todos os campos!");
        } else {
            rsvDao.inserir(rsv, u); // Chama a função de inserção e passa um objeto com os dados do usuario
            JOptionPane.showMessageDialog(null, "Reserva feita com sucesso!");

            // Chamando a tela de home para aparecer
            HomeController homeScreen = new HomeController();
            Stage stage = new Stage();
            homeScreen.start(stage);
            stageReserva.close(); // Removendo tela do form
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
        Parent root = FXMLLoader.load(getClass().getResource("/View/reserva.fxml"));
        Scene scene = new Scene(root);
        setStageReserva(stage); // Removendo a tela do form logo após a tela home aparecer
        stage.setScene(scene);
        stage.show();
    }
}
