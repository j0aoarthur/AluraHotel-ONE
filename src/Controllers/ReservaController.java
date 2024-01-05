package Controllers;

import DAO.ReservaDAO;
import Factory.ConnectionFactory;
import Modelos.Reserva;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.reservaDAO = new ReservaDAO(connection);
    }

    public List<Reserva> listar() {
        return this.reservaDAO.listar();
    }

    public List<Reserva> buscar(int id) {
        return this.reservaDAO.buscar(id);
    }

    public void alterar(Reserva reserva) {
        this.reservaDAO.alterar(reserva);
    }

    public void deletar(int id) throws SQLException {
        this.reservaDAO.deletar(id);
    }

    public int criarReserva(Reserva reserva) {
        return this.reservaDAO.criarReserva(reserva);
    }
}
