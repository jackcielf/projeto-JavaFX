package Model;

public class Reserva {
    private int id_rsv;
    private String numMesa;
    private String numPessoas;
    private String horario;
    private int id_usuario_fk;

    public int getId_usuario_fk() {
        return id_usuario_fk;
    }

    public void setId_usuario_fk(int id_usuario_fk) {
        this.id_usuario_fk = id_usuario_fk;
    }

    public int getId_rsv() {
        return id_rsv;
    }

    public void setId_rsv(int id_rsv) {
        this.id_rsv = id_rsv;
    }
    

    public String getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(String numMesa) {
        this.numMesa = numMesa;
    }

    public String getNumPessoas() {
        return numPessoas;
    }

    public void setNumPessoas(String numPessoas) {
        this.numPessoas = numPessoas;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}