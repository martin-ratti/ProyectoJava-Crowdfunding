package repositorio;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import modelo.Cancelacion_Proyecto;
import db.Conexion;

public class Cancelacion_ProyectoDAO {

    public void crear(Cancelacion_Proyecto c) throws SQLException {
        String sql = "INSERT INTO Cancelacion_Proyecto (idProyecto, motivo, fecha) VALUES (?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getIdProyecto());
            ps.setString(2, c.getMotivo());
            ps.setDate(3, Date.valueOf(c.getFecha()));
            ps.executeUpdate();
        }
    }

    public Cancelacion_Proyecto obtenerUno(int idProyecto) throws SQLException {
        String sql = "SELECT * FROM Cancelacion_Proyecto WHERE idProyecto = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cancelacion_Proyecto(
                    rs.getInt("idProyecto"),
                    rs.getString("motivo"),
                    rs.getDate("fecha").toLocalDate()
                );
            }
            return null;
        }
    }

    public List<Cancelacion_Proyecto> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM Cancelacion_Proyecto";
        List<Cancelacion_Proyecto> lista = new ArrayList<>();
        try (Connection con = Conexion.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Cancelacion_Proyecto(
                    rs.getInt("idProyecto"),
                    rs.getString("motivo"),
                    rs.getDate("fecha").toLocalDate()
                ));
            }
        }
        return lista;
    }

    public void actualizar(Cancelacion_Proyecto c) throws SQLException {
        String sql = "UPDATE Cancelacion_Proyecto SET motivo = ?, fecha = ? WHERE idProyecto = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getMotivo());
            ps.setDate(2, Date.valueOf(c.getFecha()));
            ps.setInt(3, c.getIdProyecto());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idProyecto) throws SQLException {
        String sql = "DELETE FROM Cancelacion_Proyecto WHERE idProyecto = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            ps.executeUpdate();
        }
    }
}
