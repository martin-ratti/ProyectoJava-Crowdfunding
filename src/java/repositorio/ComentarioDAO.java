package repositorio;

import db.Conexion;
import interfaces.IComentarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import modelo.Comentario;

public class ComentarioDAO implements IComentarioDAO {

    @Override
    public void insertar(Comentario comentario) {
        String sql = "INSERT INTO comentario (descripcion, fecha, idProyecto, idUsuario, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, comentario.getDescripcion());
            ps.setTimestamp(2, Timestamp.valueOf(comentario.getFecha()));
            ps.setInt(3, comentario.getIdProyecto());
            ps.setInt(4, comentario.getIdUsuario());
            ps.setString(5, "Activo"); // siempre entra como activo
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comentario> obtenerPorIdProyecto(int idProyecto) {
        List<Comentario> comentarios = new ArrayList<>();
        String sql = "SELECT c.*, u.nombre AS nombreUsuario FROM comentario c " +
                     "JOIN usuario u ON c.idUsuario = u.idUsuario " +
                     "WHERE c.idProyecto = ? AND c.estado = 'Activo' " +
                     "ORDER BY c.fecha DESC";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comentario comentario = new Comentario();
                    comentario.setIdComentario(rs.getInt("idComentario"));
                    comentario.setDescripcion(rs.getString("descripcion"));
                    comentario.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                    comentario.setIdProyecto(rs.getInt("idProyecto"));
                    comentario.setIdUsuario(rs.getInt("idUsuario"));
                    comentario.setNombreUsuario(rs.getString("nombreUsuario"));
                    comentario.setEstado(rs.getString("estado"));
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }

    public void deshabilitarComentario(int idComentario) {
        String sql = "UPDATE comentario SET estado = 'Ignorado' WHERE idComentario = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idComentario);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comentario> obtenerTodos() { return new ArrayList<>(); }
    @Override
    public Comentario obtenerPorId(int id) { return null; }
    @Override
    public void actualizar(Comentario comentario) {}
    @Override
    public void eliminar(int id) {}
}
