package DAO;

import Modelos.Hospede;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class HospedeDAO {
    private Connection connection;

    public HospedeDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Hospede> listar() {
        try {

            List<Hospede> returnList = new ArrayList<>();
            String sql = "SELECT * FROM hospedes";

            try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet resultSet = pstm.getResultSet()) {
                    while (resultSet.next()) {

                        Hospede hospede = resultSetEmHospede(resultSet);
                        returnList.add(hospede);

                    }
                }
            }

            return returnList;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public int criarHospede(Hospede hospede) {

        try {
            String sql = "INSERT INTO hospedes (nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva) VALUES (?, ?, ?, ?, ?, ?)";

            try(PreparedStatement pstm = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, hospede.getNome());
                pstm.setString(2, hospede.getSobrenome());
                pstm.setDate(3, hospede.getData_nascimento());
                pstm.setString(4, hospede.getNacionalidade());
                pstm.setString(5, hospede.getTelefone());
                pstm.setInt(6, hospede.getId_reserva());

                pstm.execute();
                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        hospede.setId(rst.getInt(1));
                    }
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hospede.getId();
    }

    public List<Hospede> buscar(String text) {
        try {

            List<Hospede> returnList = new ArrayList<>();

            String sql = "SELECT * FROM hospedes WHERE sobrenome LIKE ?";

            try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
                pstm.setString(1, '%'+text+ '%');
                pstm.execute();

                try (ResultSet resultSet = pstm.getResultSet()) {
                    while (resultSet.next()) {

                        Hospede hospede = resultSetEmHospede(resultSet);
                        returnList.add(hospede);
                    }
                }
            }
            return returnList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Hospede resultSetEmHospede(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt(1);
        String nome = resultSet.getString(2);
        String sobrenome = resultSet.getString(3);
        Date data_nascimento = resultSet.getDate(4);
        String nacionalidade = resultSet.getString(5);
        String telefone = resultSet.getString(6);
        int id_reserva = resultSet.getInt(7);

        Hospede hospede = new Hospede(id, nome, sobrenome, nacionalidade, telefone, data_nascimento, id_reserva);

        return hospede;
    }

    public void alterar(Hospede hospede) {
        try {
            String sql = "UPDATE hospedes h SET h.nome = ?, h.sobrenome = ?, h.nacionalidade = ?, h.telefone = ?, h.data_nascimento = ?, h.id_reserva = ? WHERE id = ?";

            try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
                pstm.setString(1, hospede.getNome());
                pstm.setString(2, hospede.getSobrenome());
                pstm.setString(3, hospede.getNacionalidade());
                pstm.setString(4, hospede.getTelefone());
                pstm.setDate(5, hospede.getData_nascimento());
                pstm.setInt(6, hospede.getId_reserva());
                pstm.setInt(7,hospede.getId());

                pstm.execute();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(int id) {
        try {
            String sql = "DELETE FROM hospedes WHERE id = ?";

            try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
                pstm.setInt(1,id);

                pstm.execute();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
