package repositorio;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import modelo.Donacion;
import db.Conexion;

public class DonacionDAO {
    public void crear(Donacion d) throws SQLException {
        String sql = "INSERT INTO Donacion (monto, comentario, fecha, idDonante, idProyecto) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, d.getMonto());
            ps.setString(2, d.getComentario());
            ps.setDate(3, Date.valueOf(d.getFecha()));
            ps.setInt(4, d.getIdDonante());
            ps.setInt(5, d.getIdProyecto());
            ps.executeUpdate();
        }
    }

    public Donacion obtenerUno(int idDonacion) throws SQLException {
        String sql = "SELECT * FROM Donacion WHERE idDonacion = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idDonacion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Donacion(
                    rs.getInt("idDonacion"),
                    rs.getDouble("monto"),
                    rs.getString("comentario"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getInt("idDonante"),
                    rs.getInt("idProyecto")
                );
            }
            return null;
        }
    }

    public List<Donacion> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM Donacion";
        List<Donacion> lista = new ArrayList<>();
        try (Connection con = Conexion.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Donacion(
                    rs.getInt("idDonacion"),
                    rs.getDouble("monto"),
                    rs.getString("comentario"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getInt("idDonante"),
                    rs.getInt("idProyecto")
                ));
            }
        }
        return lista;
    }

    public void actualizar(Donacion d) throws SQLException {
        String sql = "UPDATE Donacion SET monto = ?, comentario = ?, fecha = ?, idDonante = ?, idProyecto = ? WHERE idDonacion = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, d.getMonto());
            ps.setString(2, d.getComentario());
            ps.setDate(3, Date.valueOf(d.getFecha()));
            ps.setInt(4, d.getIdDonante());
            ps.setInt(5, d.getIdProyecto());
            ps.setInt(6, d.getIdDonacion());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idDonacion) throws SQLException {
        String sql = "DELETE FROM Donacion WHERE idDonacion = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idDonacion);
            ps.executeUpdate();
        }
    }
}
