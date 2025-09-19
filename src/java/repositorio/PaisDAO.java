package repositorio;

import db.Conexion;
import interfaces.IPaisDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Pais;

public class PaisDAO implements IPaisDAO {

    @Override
    public void insertar(Pais pais) throws SQLException {
        String sql = "INSERT INTO pais (nombrePais) VALUES (?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pais.getNombrePais());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al insertar país.", e);
        }
    }

    @Override
    public List<Pais> obtenerTodos() throws SQLException {
        List<Pais> paises = new ArrayList<>();
        String sql = "SELECT * FROM pais";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Pais pais = new Pais();
                pais.setIdPais(rs.getInt("idPais"));
                pais.setNombrePais(rs.getString("nombrePais"));
                paises.add(pais);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener todos los países.", e);
        }
        return paises;
    }

    @Override
    public Pais obtenerPorId(int id) throws SQLException {
        Pais pais = null;
        String sql = "SELECT * FROM pais WHERE idPais = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pais = new Pais();
                    pais.setIdPais(rs.getInt("idPais"));
                    pais.setNombrePais(rs.getString("nombrePais"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener país por ID.", e);
        }
        return pais;
    }

    @Override
    public void actualizar(Pais pais) throws SQLException {
        String sql = "UPDATE pais SET nombrePais = ? WHERE idPais = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pais.getNombrePais());
            ps.setInt(2, pais.getIdPais());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar país.", e);
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM pais WHERE idPais = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar país.", e);
        }
    }
}
