package Modelos;

import java.sql.Date;

public class Reserva {
    private int id;
    private Date data_entrada;
    private Date data_saida;
    private Double valor;
    private String forma_pagamento;

    public Reserva(Date data_entrada, Date data_saida, Double valor, String forma_pagamento) {
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.valor = valor;
        this.forma_pagamento = forma_pagamento;
    }

    public Reserva(int id, Date data_entrada, Date data_saida, Double valor, String forma_pagamento) {
        this.id = id;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.valor = valor;
        this.forma_pagamento = forma_pagamento;
    }

    public int getId() {
        return id;
    }

    public Date getData_entrada() {
        return this.data_entrada;
    }

    public String getData_entradaString() {
        return this.data_entrada.toString();
    }

    public Date getData_saida() {
        return this.data_saida;
    }

    public String getData_saidaString() {
        return this.data_saida.toString();
    }

    public Double getValor() {
        return valor;
    }

    public String getValorString() {
        return this.valor.toString();
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.data_entrada + " " + this.data_saida + " " + this.valor;
    }
}
