package repositorio;

import java.sql.*;
import java.util.*;
import java.sql.Date;
import modelo.Comentario;
import db.Conexion;

public class ComentarioDAO {

    public void crear(Comentario c) throws SQLException {
        String sql = "INSERT INTO Comentario (titulo, fecha, descripcion, idProyecto, idUsuario) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getTitulo());
            ps.setDate(2, Date.valueOf(c.getFecha()));
            ps.setString(3, c.getDescripcion());
            ps.setInt(4, c.getIdProyecto());
            ps.setInt(5, c.getIdUsuario());
            ps.executeUpdate();
        }
    }

    public Comentario obtenerUno(int idComentario) throws SQLException {
        String sql = "SELECT * FROM Comentario WHERE idComentario = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idComentario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Comentario(
                    rs.getInt("idComentario"),
                    rs.getString("titulo"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getString("descripcion"),
                    rs.getInt("idProyecto"),
                    rs.getInt("idUsuario")
                );
            }
            return null;
        }
    }

    public List<Comentario> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM Comentario";
        List<Comentario> lista = new ArrayList<>();
        try (Connection con = Conexion.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Comentario(
                    rs.getInt("idComentario"),
                    rs.getString("titulo"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getString("descripcion"),
                    rs.getInt("idProyecto"),
                    rs.getInt("idUsuario")
                ));
            }
        }
        return lista;
    }

    public void actualizar(Comentario c) throws SQLException {
        String sql = "UPDATE Comentario SET titulo = ?, fecha = ?, descripcion = ?, idProyecto = ?, idUsuario = ? WHERE idComentario = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getTitulo());
            ps.setDate(2, Date.valueOf(c.getFecha()));
            ps.setString(3, c.getDescripcion());
            ps.setInt(4, c.getIdProyecto());
            ps.setInt(5, c.getIdUsuario());
            ps.setInt(6, c.getIdComentario());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idComentario) throws SQLException {
        String sql = "DELETE FROM Comentario WHERE idComentario = ?";
        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idComentario);
            ps.executeUpdate();
        }
    }
}
