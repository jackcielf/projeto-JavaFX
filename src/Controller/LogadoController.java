package Controller;

import Dao.ReservaDao;
import Dao.UsuarioDao;
import Model.Reserva;
import Model.Usuario;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LogadoController extends Application implements Initializable {

    static Stage stageLogado;

    public static Stage getStageLogado() {
        return stageLogado;
    }

    public static void setStageLogado(Stage stageLogado) {
        LogadoController.stageLogado = stageLogado;
    }
    
    // Botões
    @FXML
    private Button btn_atualizar;

    @FXML
    private Button btn_excluir;

    @FXML
    private Button btn_filtrar;

    @FXML
    private Button btn_sair;
    
    @FXML
    private Button btn_excluirRsv;
    
    // Campos de texto
    @FXML
    private TextField input_email;

    @FXML
    private TextField input_horario;

    @FXML
    private TextField input_nMesa;

    @FXML
    private TextField input_nPessoas;

    @FXML
    private TextField input_nome;

    @FXML
    private TextField input_pesquisar;
    
    @FXML
    private TextField input_pesqRsv;

    @FXML
    private TextField input_senha;

    @FXML
    private TextField input_tel;

    @FXML
    private Label txt_saldacoes;

    // Tabela usuario
    @FXML
    private TableView<Usuario> tb_usuario;

    @FXML
    private TableColumn<Usuario, String> cl_email;

    @FXML
    private TableColumn<Usuario, String> cl_senha;

    @FXML
    private TableColumn<Usuario, String> cl_tel;

    @FXML
    private TableColumn<Usuario, String> cl_id_user;
    
    @FXML
    private TableColumn<?, ?> cl_id_usuario;

    @FXML
    private TableColumn<Usuario, String> cl_nome;

    // Tabela reserva
    @FXML
    private TableView<Reserva> tb_reserva;

    @FXML
    private TableColumn<Reserva, String> cl_horario;

    @FXML
    private TableColumn<Reserva, String> cl_id;

    @FXML
    private TableColumn<Reserva, String> cl_numMesas;

    @FXML
    private TableColumn<Reserva, String> cl_numPessoas;
    
    

    // Inicialiazando as colunas da tabela usuario
    public void inicializar_col_User() {
        cl_id_user.setCellValueFactory(new PropertyValueFactory<>("Id_user"));
        cl_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        cl_senha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        cl_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    // Inicialiazando as colunas da tabela reserva
    public void inicializar_col_Rsv() {
        cl_id.setCellValueFactory(new PropertyValueFactory<>("id_rsv"));
        cl_numMesas.setCellValueFactory(new PropertyValueFactory<>("numMesa"));
        cl_numPessoas.setCellValueFactory(new PropertyValueFactory<>("numPessoas"));
        cl_horario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        cl_id_usuario.setCellValueFactory(new PropertyValueFactory<>("id_usuario_fk"));
    }
    
    List<Usuario> listar_usuario;
    List<Reserva> listar_reserva;

    // Preenchendo a tabela usuario
    public void preencherTabUsuario() throws SQLException {
        UsuarioDao dao = new UsuarioDao();
        listar_usuario = dao.lista_user();
        tb_usuario.setItems(FXCollections.observableArrayList(listar_usuario));
    }

    // Preenchendo a tabela reserva
    public void preencherTabReserva() throws SQLException {
        ReservaDao dao = new ReservaDao();
        listar_reserva = dao.listar_reserva();
        tb_reserva.setItems(FXCollections.observableArrayList(listar_reserva));
    }

    // EVENTOS DO USUARIO
    @FXML
    void btn_atualizarUser_event(ActionEvent event) throws SQLException {
        Usuario u = new Usuario(); // Receber dados do usuario
        
        u.setNome(input_nome.getText());
        u.setEmail(input_email.getText());
        u.setSenha(input_senha.getText());
        u.setTel(input_tel.getText());
        
        UsuarioDao userDao = new UsuarioDao(); // Interage com o arquivo 'UsuarioDao'

        if (userDao.atualizar(u)) {
            JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
            preencherTabUsuario(); // Atualiza a tabela usuarios
        }
    }

    @FXML
    void btn_excluirUser_event(ActionEvent event) throws SQLException {
        UsuarioDao userDao = new UsuarioDao();
        Usuario user = new Usuario();
        
        user.setEmail(input_email.getText());

        if(userDao.excluir(user)){
              if (!input_pesquisar.getText().equals("")) {
                  input_pesquisar.setText("");
              }
              input_nome.setText("");
              input_email.setText("");
              input_senha.setText("");
              input_tel.setText("");
              JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
              preencherTabUsuario();
        }
    }

    @FXML
    void btn_filtrar_event(ActionEvent event) throws SQLException {
        UsuarioDao userDao = new UsuarioDao();
        Usuario u = new Usuario();
        
        String email = input_pesquisar.getText();
        u = userDao.consultar(email);
        
        listar_usuario = userDao.lista_user();
        tb_usuario.setItems(FXCollections.observableArrayList(u)); // Preeenche a tabela com os dados

        input_email.setText(u.getEmail());
        input_nome.setText(u.getNome());
        input_senha.setText(u.getSenha());
        input_tel.setText(u.getTel());
    }
    
    // EVENTOS DA RESERVA
     @FXML
    void btn_atualizarReserva_event(ActionEvent event) throws SQLException {
        Reserva rsv = new Reserva(); // Recebe os dados do reserva
        
        rsv.setNumMesa(input_nMesa.getText());
        rsv.setNumPessoas(input_nPessoas.getText());
        rsv.setHorario(input_horario.getText());
        
        ReservaDao rsvDao = new ReservaDao(); // Interage com o arquivo 'ReservaDao'

        if (rsvDao.atualizar(rsv)) {
            JOptionPane.showMessageDialog(null, "Reserva atualizada com sucesso!");
            preencherTabReserva(); // Atualiza a tabela usuarios
        }
    }
    
     @FXML
    void btn_excluirReserva_event(ActionEvent event) throws SQLException {
        Reserva rsv = new Reserva(); // Recebe os dados da reserva
        ReservaDao rsvDao = new ReservaDao(); // Interage com o arquivo 'ReservaDao'
        
        rsv.setNumMesa(input_nMesa.getText());

        if(rsvDao.excluir(rsv)){
              if (!input_pesqRsv.getText().equals("")) {
                  input_pesqRsv.setText("");
              }
              input_nMesa.setText("");
              input_nPessoas.setText("");
              input_horario.setText("");
              JOptionPane.showMessageDialog(null, "Reserva cancelada com sucesso!");
              preencherTabReserva();
        }
    }
    
    @FXML
    void btn_filtrarReserva_event(ActionEvent event) throws SQLException {
        Reserva rsv = new Reserva(); // Recebe os dados da reserva
        ReservaDao rsvDao = new ReservaDao(); // Interage com o arquivo 'ReservaDao'
        
        String numMesa = input_pesqRsv.getText();
        rsv = rsvDao.consultar(numMesa);
        
        listar_reserva = rsvDao.listar_reserva();
        tb_reserva.setItems(FXCollections.observableArrayList(rsv)); // Preeenche a tabela com os dados

        input_nMesa.setText(rsv.getNumMesa());
        input_nPessoas.setText(rsv.getNumPessoas());
        input_horario.setText(rsv.getHorario());
    }
    
    // Sair da conta atual
    @FXML
    void btn_sair_event(ActionEvent event) throws Exception {
        // Chamando a tela de home para aparecer
        HomeController homeScreen = new HomeController();
        Stage stage = new Stage();
        homeScreen.start(stage);
        stageLogado.close(); // Removendo tela do form
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializar_col_User();
        inicializar_col_Rsv();

        // Verifica se as tabelas foram inicializadas com sucesso
        try {
            preencherTabUsuario();
            preencherTabReserva();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/logado.fxml"));
        Scene scene = new Scene(root);
        setStageLogado(stage);
        stage.setScene(scene);
        stage.show();
    }
}