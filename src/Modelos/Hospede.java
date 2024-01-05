package Modelos;

import java.sql.Date;

public class Hospede {

    private int id;
    private String nome;
    private String sobrenome;
    private String nacionalidade;
    private String telefone;
    private Date data_nascimento;
    private int id_reserva;

    public Hospede(String nome, String sobrenome, String nacionalidade, String telefone, Date data_nascimento, int id_reserva) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.data_nascimento = data_nascimento;
        this.id_reserva = id_reserva;
    }

    public Hospede(int id, String nome, String sobrenome, String nacionalidade, String telefone, Date data_nascimento, int id_reserva) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.data_nascimento = data_nascimento;
        this.id_reserva = id_reserva;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public String getData_nascimentoString() {
        return this.data_nascimento.toString();
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId(int id) {
        this.id = id;
    }
}
