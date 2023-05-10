package Controller;

import Dao.UsuarioDao;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

// S.A

public class CadastroController extends Application implements Initializable {
    static Stage stageCadastro; // Necessário para destruir a tela

    public static Stage getStageCadastro() {
        return stageCadastro;
    }

    public static void setStageCadastro(Stage stageCadastro) {
        CadastroController.stageCadastro = stageCadastro;
    }
    
    @FXML
    private Button btn_cadastrar;

    @FXML
    private TextField input_email;

    @FXML
    private TextField input_nome;

    @FXML
    private TextField input_senha;

    @FXML
    private TextField input_tel;

    @FXML
    private Label msg;

    @FXML
    void btn_cadastrar_event(ActionEvent event) throws Exception {
        // Pegando os valores dos inputs
        String nome = input_nome.getText();
        String email = input_email.getText();
        String senha = input_senha.getText();
        String tel = input_tel.getText();
        
        Usuario user = new Usuario(); // Chamando o objeto usuario
        UsuarioDao userDao = new UsuarioDao();
        
        // Colocando os dados do usuario no objeto acima
        user.setNome(nome);
        user.setEmail(email);
        user.setSenha(senha);
        user.setTel(tel);
        
        // Verificando se os campos foram preenchidos
        if (nome.equals("") || email.equals("") || senha.equals("") || tel.equals("")) {
            msg.setText("Preencha todos os campos!");
        } else {
            userDao.inserir(user); // Chama a função de inserção e passa um objeto com os dados do usuario
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            
            // Chamando a tela de home para aparecer
            HomeController homeScreen = new HomeController();
            Stage stage = new Stage();
            homeScreen.start(stage);
            stageCadastro.close(); // Removendo tela do form
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
        Parent root = FXMLLoader.load(getClass().getResource("/View/cadastro.fxml"));
        
        Scene scene = new Scene(root);
        setStageCadastro(stage); // Removendo a tela do form logo após a tela home aparecer
        stage.setScene(scene);
        stage.show();
    }
}