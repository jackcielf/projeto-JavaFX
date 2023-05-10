package Dao;

import Model.Reserva;
import Model.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao {
    // Variaveis para manusear a conexao (con) e executar no banco (stm)
    private Connection con;
    private Statement stm;
     
    // CONSTRUCTOR: deve ser sempre publico
    public ReservaDao(){
        // if...else
        try {
            con = Conexao.getConnection(); // Chamando a classe de conexao (OBRIGATORIO)
            stm = con.createStatement(); // Executa comandos na base de dados
        } catch(ClassNotFoundException | SQLException e) {} 
    }
    
    // Metodo de INSERÇÃO de usuario: recebe um objeto 
    public boolean inserir(Reserva reserva, Usuario u) throws SQLException {
        ResultSet id = stm.executeQuery("SELECT id_usuario FROM tb_usuario WHERE email='"+u.getEmail()+"'");
        
        Usuario user = new Usuario();
        while (id.next()) {
            user.setId_user(id.getInt("id_usuario"));
        }
        
        String sql_insert = "INSERT INTO tb_reserva(num_mesa, num_pessoas, horario, id_usuario) VALUES ('"+reserva.getNumMesa()+"', '"+reserva.getNumPessoas()+"', '"+reserva.getHorario()+"', "+user.getId_user()+")";
        
        try {
            // Executa o sql no banco
            stm.executeUpdate(sql_insert);
            return true; // Caso returne verdadeiro deu tudo certo na execução do sql no banco
        } catch (SQLException ex) {
            return false; // Caso tenha dado erro será retornado falso
        }
    }
    
    // Metodo de CONSULTA: recebe o email do usuario
    public Reserva consultar(String numMesa) throws SQLException {
        ResultSet dados = stm.executeQuery("SELECT * FROM tb_reserva WHERE num_mesa='"+numMesa+"'");
        Reserva rsv = new Reserva();
        
        // Percorre a tabela com os dados deste usuario e altera os valores dos aributos do usuario
        while (dados.next()){
            rsv.setId_rsv(dados.getInt("id_reserva"));
            rsv.setNumMesa(dados.getString("num_mesa"));
            rsv.setNumPessoas(dados.getString("num_pessoas"));
            rsv.setHorario(dados.getString("horario"));
            rsv.setId_usuario_fk(dados.getInt("id_usuario"));
        } 
        return rsv;
    }
    
    // Metodo de EXCLUSÃO: recebe o email, parametro de identificação do usuario que será apagado
    public boolean excluir(Reserva rsv) throws SQLException{
        try{
            // Executa o sql no banco
            stm.executeUpdate("DELETE FROM tb_reserva WHERE num_mesa='"+rsv.getNumMesa()+"'");
            return true;
        } catch(SQLException ex) {
            return false;
        }
    }
    
    // Metodo de ATUALIZAÇÃO: recebe um objeto
    public boolean atualizar(Reserva rsv) throws SQLException{
        try{
            stm.executeUpdate("UPDATE tb_reserva SET num_pessoas='"+ rsv.getNumPessoas()+"', horario='"+ rsv.getHorario()+"' WHERE num_mesa='"+rsv.getNumMesa()+"'");
            return true;
        }catch (SQLException ex) {
            return false;
        }
    }
    
    // Coloca os usuarios registrados em uma lista
    public List<Reserva> listar_reserva() throws SQLException {
        ResultSet dados_reserva = stm.executeQuery("SELECT * FROM tb_reserva ORDER BY id_reserva ASC");
        List<Reserva> lista = new ArrayList(); // Criando uma lista para colocar os usuarios
        
        // Requisitando os dados da reserva
        while(dados_reserva.next()){ // Percorrendo os dados da tabela reserva
            Reserva rsv = new Reserva();
            rsv.setId_rsv(dados_reserva.getInt("id_reserva"));
            rsv.setNumMesa(dados_reserva.getString("num_mesa"));
            rsv.setNumPessoas(dados_reserva.getString("num_pessoas"));
            rsv.setHorario(dados_reserva.getString("horario"));
            rsv.setId_usuario_fk(dados_reserva.getInt("id_usuario"));
            lista.add(rsv);
        }
        return lista;
    } 
}