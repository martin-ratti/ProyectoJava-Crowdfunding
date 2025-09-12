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
    public void insertar(Pais pais) {
        String sql = "INSERT INTO pais (nombrePais) VALUES (?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pais.getNombrePais());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pais> obtenerTodos() {
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
            e.printStackTrace();
        }
        return paises;
    }

    @Override
    public Pais obtenerPorId(int id) {
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
            e.printStackTrace();
        }
        return pais;
    }

    @Override
    public void actualizar(Pais pais) {
        String sql = "UPDATE pais SET nombrePais = ? WHERE idPais = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pais.getNombrePais());
            ps.setInt(2, pais.getIdPais());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM pais WHERE idPais = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
