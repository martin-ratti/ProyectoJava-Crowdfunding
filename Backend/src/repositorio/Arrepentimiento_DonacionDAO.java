package repositorio;

import java.sql.*;
import java.util.*;
import modelo.Arrepentimiento_Donacion;
import db.Conexion;

public class Arrepentimiento_DonacionDAO {
    public void crear(Arrepentimiento_Donacion a) throws SQLException {
        String sql = "INSERT INTO Arrepentimiento_Donacion (idDonacion, motivo, fechaCancelacion) VALUES (?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getIdDonacion());
            ps.setString(2, a.getMotivo());
            ps.setDate(3, java.sql.Date.valueOf(a.getFechaCancelacion()));
            ps.executeUpdate();
        }
    }

    public Arrepentimiento_Donacion obtenerUno(int idDonacion) throws SQLException {
        String sql = "SELECT * FROM Arrepentimiento_Donacion WHERE idDonacion = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idDonacion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Arrepentimiento_Donacion(
                    rs.getInt("idDonacion"),
                    rs.getString("motivo"),
                    rs.getDate("fechaCancelacion").toLocalDate()
                );
            }
            return null;
        }
    }

    public List<Arrepentimiento_Donacion> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM Arrepentimiento_Donacion";
        List<Arrepentimiento_Donacion> lista = new ArrayList<>();
        try (Connection con = Conexion.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Arrepentimiento_Donacion(
                    rs.getInt("idDonacion"),
                    rs.getString("motivo"),
                    rs.getDate("fechaCancelacion").toLocalDate()
                ));
            }
        }
        return lista;
    }

    public void actualizar(Arrepentimiento_Donacion a) throws SQLException {
        String sql = "UPDATE Arrepentimiento_Donacion SET motivo = ?, fechaCancelacion = ? WHERE idDonacion = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getMotivo());
            ps.setDate(2, java.sql.Date.valueOf(a.getFechaCancelacion()));
            ps.setInt(3, a.getIdDonacion());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idDonacion) throws SQLException {
        String sql = "DELETE FROM Arrepentimiento_Donacion WHERE idDonacion = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idDonacion);
            ps.executeUpdate();
        }
    }
}

