package repositorio;

import db.Conexion;
import interfaces.IComentarioDAO;
import modelo.Comentario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO implements IComentarioDAO {

    @Override
    public void insertar(Comentario comentario) throws SQLException {
        String sql = "INSERT INTO comentario (descripcion, fecha, idProyecto, idUsuario, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, comentario.getDescripcion());
            ps.setTimestamp(2, Timestamp.valueOf(comentario.getFecha()));
            ps.setInt(3, comentario.getIdProyecto());
            ps.setInt(4, comentario.getIdUsuario());
            ps.setString(5, "Activo");

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al insertar comentario.", e);
        }
    }

    @Override
    public List<Comentario> obtenerPorIdProyecto(int idProyecto) throws SQLException {
        List<Comentario> comentarios = new ArrayList<>();
        String sql = "SELECT c.*, u.nombre AS nombreUsuario " +
                     "FROM comentario c " +
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
            throw new SQLException("Error al obtener comentarios por ID de proyecto.", e);
        }
        return comentarios;
    }

    public void deshabilitarComentario(int idComentario) throws SQLException {
        String sql = "UPDATE comentario SET estado = 'Ignorado' WHERE idComentario = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idComentario);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al deshabilitar comentario.", e);
        }
    }

    @Override
    public List<Comentario> obtenerTodos() throws SQLException {
        // Implementación pendiente si es necesaria
        return new ArrayList<>();
    }

    @Override
    public Comentario obtenerPorId(int id) throws SQLException {
        // Implementación pendiente si es necesaria
        return null;
    }

    @Override
    public void actualizar(Comentario comentario) throws SQLException {
        // Implementación pendiente si es necesaria
    }

    @Override
    public void eliminar(int id) throws SQLException {
        // Implementación pendiente si es necesaria
    }
}
