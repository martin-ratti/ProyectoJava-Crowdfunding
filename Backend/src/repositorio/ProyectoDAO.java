package repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Proyecto;
import db.Conexion;

public class ProyectoDAO {

    public void crear(Proyecto proyecto) throws SQLException {
        String sql = "INSERT INTO Proyecto (nombreProyecto, descripcion, montoMeta, fechaIni, fechaFin, foto, estado, montoRecaudado, idPais, idCreador, idCategoria) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, proyecto.getNombreProyecto());
            ps.setString(2, proyecto.getDescripcion());
            ps.setBigDecimal(3, proyecto.getMontoMeta());
            ps.setDate(4, proyecto.getFechaIni());
            ps.setDate(5, proyecto.getFechaFin());
            ps.setString(6, proyecto.getFoto());
            ps.setString(7, proyecto.getEstado());
            ps.setBigDecimal(8, proyecto.getMontoRecaudado());
            ps.setInt(9, proyecto.getIdPais());
            ps.setInt(10, proyecto.getIdCreador());
            ps.setInt(11, proyecto.getIdCategoria());
            ps.executeUpdate();
        }
    }

    public Proyecto obtenerUno(int idProyecto) throws SQLException {
        String sql = "SELECT * FROM Proyecto WHERE idProyecto = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Proyecto(
                    rs.getInt("idProyecto"),
                    rs.getString("nombreProyecto"),
                    rs.getString("descripcion"),
                    rs.getBigDecimal("montoMeta"),
                    rs.getDate("fechaIni"),
                    rs.getDate("fechaFin"),
                    rs.getString("foto"),
                    rs.getString("estado"),
                    rs.getBigDecimal("montoRecaudado"),
                    rs.getInt("idPais"),
                    rs.getInt("idCreador"),
                    rs.getInt("idCategoria")
                );
            }
        }
        return null;
    }

    public List<Proyecto> obtenerTodos() throws SQLException {
        List<Proyecto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Proyecto";
        try (Connection con = Conexion.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Proyecto(
                    rs.getInt("idProyecto"),
                    rs.getString("nombreProyecto"),
                    rs.getString("descripcion"),
                    rs.getBigDecimal("montoMeta"),
                    rs.getDate("fechaIni"),
                    rs.getDate("fechaFin"),
                    rs.getString("foto"),
                    rs.getString("estado"),
                    rs.getBigDecimal("montoRecaudado"),
                    rs.getInt("idPais"),
                    rs.getInt("idCreador"),
                    rs.getInt("idCategoria")
                ));
            }
        }
        return lista;
    }

    public void actualizar(Proyecto proyecto) throws SQLException {
        String sql = "UPDATE Proyecto SET nombreProyecto = ?, descripcion = ?, montoMeta = ?, fechaIni = ?, fechaFin = ?, foto = ?, estado = ?, montoRecaudado = ?, idPais = ?, idCreador = ?, idCategoria = ? WHERE idProyecto = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, proyecto.getNombreProyecto());
            ps.setString(2, proyecto.getDescripcion());
            ps.setBigDecimal(3, proyecto.getMontoMeta());
            ps.setDate(4, proyecto.getFechaIni());
            ps.setDate(5, proyecto.getFechaFin());
            ps.setString(6, proyecto.getFoto());
            ps.setString(7, proyecto.getEstado());
            ps.setBigDecimal(8, proyecto.getMontoRecaudado());
            ps.setInt(9, proyecto.getIdPais());
            ps.setInt(10, proyecto.getIdCreador());
            ps.setInt(11, proyecto.getIdCategoria());
            ps.setInt(12, proyecto.getIdProyecto());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idProyecto) throws SQLException {
        String sql = "DELETE FROM Proyecto WHERE idProyecto = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            ps.executeUpdate();
        }
    }
}
