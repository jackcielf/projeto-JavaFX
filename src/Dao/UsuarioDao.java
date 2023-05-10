package Dao;

import Model.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    // Variaveis para manusear a conexao (con) e executar no banco (stm)
    private Connection con;
    private Statement stm;
     
    // CONSTRUCTOR: deve ser sempre publico
    public UsuarioDao() {
        // if...else
        try {
            con = Conexao.getConnection(); // Chamando a classe de conexao (OBRIGATORIO)
            stm = con.createStatement(); // Executa comandos na base de dados
        } catch(ClassNotFoundException | SQLException e){} 
    }
    
    // Metodo de INSERÇÃO de usuario: recebe um objeto 
    public boolean inserir(Usuario user){
        String sql_insert="INSERT INTO tb_usuario(nome,email,senha,telefone)VALUES('"+user.getNome()+"','"+user.getEmail()+"', '"+user.getSenha()+"', '"+user.getTel()+"')";
        try {
            stm.executeUpdate(sql_insert); // Executa o sql no banco
            return true; // Caso returne verdadeiro deu tudo certo na execução do sql no banco
        } catch (SQLException ex) {
            return false; // Caso tenha dado erro será retornado falso
        }
    }
    
    // Metodo de CONSULTA: recebe o email do usuario
    public Usuario consultar(String email) throws SQLException{
        ResultSet dados = stm.executeQuery("SELECT id_usuario, nome, email, senha, telefone FROM tb_usuario WHERE email='"+email+"'");
        
        Usuario user = new Usuario();
        
        // Percorre a tabela com os dados deste usuario e altera os valores dos aributos do usuario
        while (dados.next()){
            user.setId_user(dados.getInt("id_usuario"));
            user.setNome(dados.getString("nome"));
            user.setEmail(dados.getString("email"));
            user.setSenha(dados.getString("senha"));
            user.setTel(dados.getString("telefone"));
        }
        return user;
    }
    
    // Metodo de EXCLUSÃO: recebe o email, parametro de identificação do usuario que será apagado
    public boolean excluir(Usuario user) throws SQLException{
        try {
            // Executa o sql no banco
            stm.executeUpdate("DELETE FROM tb_usuario WHERE email='"+user.getEmail()+"'");
            return true;
        } catch(SQLException ex) {
            return false;
        }
    }
    
    // Metodo de ATUALIZAÇÃO: recebe um objeto
    public boolean atualizar(Usuario u) throws SQLException {
        try {
            stm.executeUpdate("UPDATE tb_usuario SET nome='" + u.getNome()+"', senha='"+ u.getSenha()+"', telefone='"+ u.getTel()+"' WHERE email='"+ u.getEmail()+"'");
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    // Coloca os usuarios registrados em uma lista
    public List<Usuario> lista_user() throws SQLException {
        ResultSet dados = stm.executeQuery("SELECT id_usuario, nome, email, senha, telefone FROM tb_usuario ORDER BY id_usuario ASC");
        List<Usuario> lista = new ArrayList(); // Criando uma lista para colocar os usuarios
        
        // Requisitando os dados do usuario
        while(dados.next()) { // Percorrendo os dados de usuarios
            Usuario c = new Usuario();
            c.setId_user(dados.getInt("id_usuario"));
            c.setNome(dados.getString("nome"));
            c.setEmail(dados.getString("email"));
            c.setSenha(dados.getString("senha"));
            c.setTel(dados.getString("telefone"));
            lista.add(c);
        }
        return lista;
    } 
}