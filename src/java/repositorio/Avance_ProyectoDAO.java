package repositorio;

import db.Conexion;
import interfaces.IAvance_ProyectoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Avance_Proyecto;

public class Avance_ProyectoDAO implements IAvance_ProyectoDAO {

    @Override
    public void insertar(Avance_Proyecto avance) throws SQLException {
        String sql = "INSERT INTO avance_proyecto (idProyecto, idAvance, descripcion, foto, fecha) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            int proximoIdAvance = obtenerProximoIdAvance(avance.getIdProyecto(), con);
            
            ps.setInt(1, avance.getIdProyecto());
            ps.setInt(2, proximoIdAvance);
            ps.setString(3, avance.getDescripcion());
            ps.setString(4, avance.getFoto());
            ps.setDate(5, java.sql.Date.valueOf(avance.getFecha()));
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new SQLException("Error al insertar avance de proyecto.", e);
        }
    }
    
    private int obtenerProximoIdAvance(int idProyecto, Connection con) throws SQLException {
        String sql = "SELECT MAX(idAvance) FROM avance_proyecto WHERE idProyecto = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) + 1;
                } else {
                    return 1;
                }
            }
        }
    }

    @Override
    public List<Avance_Proyecto> obtenerTodos() throws SQLException {
        List<Avance_Proyecto> lista = new ArrayList<>();
        String sql = "SELECT * FROM avance_proyecto";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearResultSetAAvance(rs));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener todos los avances.", e);
        }
        return lista;
    }
    
    @Override
    public List<Avance_Proyecto> obtenerPorProyecto(int idProyecto) throws SQLException {
        List<Avance_Proyecto> lista = new ArrayList<>();
        String sql = "SELECT * FROM avance_proyecto WHERE idProyecto = ? ORDER BY fecha DESC, idAvance DESC";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearResultSetAAvance(rs));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener avances por proyecto.", e);
        }
        return lista;
    }

    @Override
    public Avance_Proyecto obtenerPorId(int idProyecto, int idAvance) throws SQLException {
        Avance_Proyecto item = null;
        String sql = "SELECT * FROM avance_proyecto WHERE idProyecto = ? AND idAvance = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            ps.setInt(2, idAvance);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = mapearResultSetAAvance(rs);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener avance por ID.", e);
        }
        return item;
    }

    @Override
    public void actualizar(Avance_Proyecto avance) throws SQLException {
        String sql = "UPDATE avance_proyecto SET descripcion = ?, foto = ?, fecha = ? WHERE idProyecto = ? AND idAvance = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, avance.getDescripcion());
            ps.setString(2, avance.getFoto());
            ps.setDate(3, java.sql.Date.valueOf(avance.getFecha()));
            ps.setInt(4, avance.getIdProyecto());
            ps.setInt(5, avance.getIdAvance());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar avance.", e);
        }
    }

    @Override
    public void eliminar(int idProyecto, int idAvance) throws SQLException {
        String sql = "DELETE FROM avance_proyecto WHERE idProyecto = ? AND idAvance = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            ps.setInt(2, idAvance);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar avance.", e);
        }
    }

    private Avance_Proyecto mapearResultSetAAvance(ResultSet rs) throws SQLException {
        Avance_Proyecto item = new Avance_Proyecto();
        item.setIdProyecto(rs.getInt("idProyecto"));
        item.setIdAvance(rs.getInt("idAvance"));
        item.setDescripcion(rs.getString("descripcion"));
        item.setFoto(rs.getString("foto"));
        item.setFecha(rs.getDate("fecha").toLocalDate());
        return item;
    }
}
