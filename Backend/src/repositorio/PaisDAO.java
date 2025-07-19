package repositorio;

import db.Conexion;
import modelo.Pais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO {

    public void crear(Pais pais) {
        String sql = "INSERT INTO Pais (nombre) VALUES (?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, pais.getNombre());
            stmt.executeUpdate();
            System.out.println("Pais insertado con éxito.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pais> obtenerTodos() {
        List<Pais> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pais";

        try (Connection con = Conexion.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pais pais = new Pais(rs.getInt("idPais"), rs.getString("nombre"));
                lista.add(pais);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Pais obtenerUno(int id) {
        String sql = "SELECT * FROM Pais WHERE idPais = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Pais(rs.getInt("idPais"), rs.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizar(Pais pais) {
        String sql = "UPDATE Pais SET nombre = ? WHERE idPais = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, pais.getNombre());
            stmt.setInt(2, pais.getIdPais());
            stmt.executeUpdate();
            System.out.println("Pais actualizado con éxito.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM Pais WHERE idPais = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Pais eliminado con éxito.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
