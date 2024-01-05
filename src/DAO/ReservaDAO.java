package DAO;

import Modelos.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Reserva> listar() {
        try {

            List<Reserva> returnList = new ArrayList<>();
            String sql = "SELECT * FROM reservas";

            try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet resultSet = pstm.getResultSet()) {
                    while (resultSet.next()) {
                        Reserva reserva = resultSetEmReserva(resultSet);
                        returnList.add(reserva);
                    }
                }
            }

            return returnList;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }


    public List<Reserva> buscar(int id) {
        try {
            List<Reserva> returnList = new ArrayList<>();
            String sql = "SELECT * FROM reservas WHERE id LIKE ?";

            try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
                pstm.setInt(1, id);

                pstm.execute();

                try (ResultSet resultSet = pstm.getResultSet()) {
                    while (resultSet.next()) {
                        Reserva reserva = resultSetEmReserva(resultSet);
                        returnList.add(reserva);
                    }
                }
            }
            return returnList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Reserva resultSetEmReserva(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Date data_entrada = resultSet.getDate(2);
        Date data_saida = resultSet.getDate(3);
        Double valor = resultSet.getDouble(4);
        String forma_pagamento = resultSet.getString(5);

        Reserva reserva = new Reserva(id, data_entrada, data_saida, valor, forma_pagamento);

        return reserva;
    }

    public void alterar(Reserva reserva) {
        try {
            String sql = "UPDATE reservas r SET r.data_entrada = ?, r.data_saida = ?, r.valor = ?, r.forma_pagamento = ? WHERE id = ?";

            try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
                pstm.setDate(1, reserva.getData_entrada());
                pstm.setDate(2, reserva.getData_saida());
                pstm.setDouble(3, reserva.getValor());
                pstm.setString(4, reserva.getForma_pagamento());
                pstm.setInt(5, reserva.getId());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM reservas WHERE id = ?";

        try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
            pstm.setInt(1,id);

            pstm.execute();
        }
    }

    public int criarReserva(Reserva reserva) {
        try {
            String sql = "INSERT INTO reservas (data_entrada, data_saida, valor, forma_pagamento) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstm.setDate(1, reserva.getData_entrada());
                pstm.setDate(2, reserva.getData_saida());
                pstm.setDouble(3, reserva.getValor());
                pstm.setString(4,reserva.getForma_pagamento());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        reserva.setId(rst.getInt(1));
                    }
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reserva.getId();
    }
}
