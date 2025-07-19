package repositorio;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import modelo.Avance_Proyecto;
import db.Conexion;

public class Avance_ProyectoDAO {
    public void crear(Avance_Proyecto a) throws SQLException {
        String sql = "INSERT INTO Avance_Proyecto (idProyecto, idAvance, descripcion, foto, fecha) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getIdProyecto());
            ps.setInt(2, a.getIdAvance());
            ps.setString(3, a.getDescripcion());
            ps.setString(4, a.getFoto());
            ps.setDate(5, Date.valueOf(a.getFecha()));
            ps.executeUpdate();
        }
    }

    public Avance_Proyecto obtenerUno(int idProyecto, int idAvance) throws SQLException {
        String sql = "SELECT * FROM Avance_Proyecto WHERE idProyecto = ? AND idAvance = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            ps.setInt(2, idAvance);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Avance_Proyecto(
                    rs.getInt("idProyecto"),
                    rs.getInt("idAvance"),
                    rs.getString("descripcion"),
                    rs.getString("foto"),
                    rs.getDate("fecha").toLocalDate()
                );
            }
            return null;
        }
    }

    public List<Avance_Proyecto> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM Avance_Proyecto";
        List<Avance_Proyecto> lista = new ArrayList<>();
        try (Connection con = Conexion.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Avance_Proyecto(
                    rs.getInt("idProyecto"),
                    rs.getInt("idAvance"),
                    rs.getString("descripcion"),
                    rs.getString("foto"),
                    rs.getDate("fecha").toLocalDate()
                ));
            }
        }
        return lista;
    }

    public void actualizar(Avance_Proyecto a) throws SQLException {
        String sql = "UPDATE Avance_Proyecto SET descripcion = ?, foto = ?, fecha = ? WHERE idProyecto = ? AND idAvance = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getDescripcion());
            ps.setString(2, a.getFoto());
            ps.setDate(3, Date.valueOf(a.getFecha()));
            ps.setInt(4, a.getIdProyecto());
            ps.setInt(5, a.getIdAvance());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idProyecto, int idAvance) throws SQLException {
        String sql = "DELETE FROM Avance_Proyecto WHERE idProyecto = ? AND idAvance = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            ps.setInt(2, idAvance);
            ps.executeUpdate();
        }
    }
}