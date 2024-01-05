package Controllers;

import DAO.HospedeDAO;
import Factory.ConnectionFactory;
import Modelos.Hospede;

import java.sql.Connection;
import java.util.List;

public class HospedeController {

    private HospedeDAO hospedeDAO;

    public HospedeController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.hospedeDAO = new HospedeDAO(connection);
    }

    public List<Hospede> listar() {
        return this.hospedeDAO.listar();
    }

    public int criarHospede(Hospede hospede) {
        return this.hospedeDAO.criarHospede(hospede);
    }

    public List<Hospede> buscar(String text) {
        return this.hospedeDAO.buscar(text);
    }

    public void alterar(Hospede hospede){
        this.hospedeDAO.alterar(hospede);
    }

    public void deletar(int id) {
        this.hospedeDAO.deletar(id);
    }

}
