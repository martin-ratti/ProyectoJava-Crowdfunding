package repositorio;

import db.Conexion;
import interfaces.IUsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

public class UsuarioDAO implements IUsuarioDAO {

    public Usuario validarUsuario(String email, String password) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = ? AND password = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            // Re-lanzamos la excepción para que el Servlet la maneje
            throw new SQLException("Error al validar el usuario en la base de datos.", e);
        }
        return usuario;
    }

    @Override
    public Usuario insertar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (email, password, nombre, apellido, telefono, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellido());
            ps.setString(5, usuario.getTelefono());
            if (usuario.getFechaNacimiento() != null) {
                ps.setDate(6, java.sql.Date.valueOf(usuario.getFechaNacimiento()));
            } else {
                ps.setDate(6, null);
            }
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setIdUsuario(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Error al insertar el usuario en la base de datos.", e);
        }
        return usuario;
    }

    @Override
    public List<Usuario> obtenerTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener todos los usuarios.", e);
        }
        return usuarios;
    }

    @Override
    public Usuario obtenerPorId(int id) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE idUsuario = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el usuario por ID.", e);
        }
        return usuario;
    }

    @Override
    public void actualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET email = ?, password = ?, nombre = ?, apellido = ?, telefono = ?, fechaNacimiento = ? WHERE idUsuario = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellido());
            ps.setString(5, usuario.getTelefono());
            if (usuario.getFechaNacimiento() != null) {
                ps.setDate(6, java.sql.Date.valueOf(usuario.getFechaNacimiento()));
            } else {
                ps.setDate(6, null);
            }
            ps.setInt(7, usuario.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el usuario.", e);
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE idUsuario = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar el usuario.", e);
        }
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("idUsuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setEmail(rs.getString("email"));
        usuario.setPassword(rs.getString("password"));
        usuario.setTelefono(rs.getString("telefono"));
        if (rs.getDate("fechaNacimiento") != null) {
            usuario.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
        }
        return usuario;
    }
    
    public Usuario obtenerPorEmail(String email) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener usuario por email.", e);
        }
        return usuario;
    }
}
