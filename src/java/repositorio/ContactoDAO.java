package repositorio;

import db.Conexion;
import interfaces.IContactoDAO;
import modelo.Contacto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactoDAO implements IContactoDAO {

    @Override
    public void insertar(Contacto contacto) {
        String sql = "INSERT INTO contacto (nombre, email, asunto, mensaje, fecha) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getEmail());
            ps.setString(3, contacto.getAsunto());
            ps.setString(4, contacto.getMensaje());
            ps.setTimestamp(5, Timestamp.valueOf(contacto.getFecha()));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contacto> obtenerTodos() {
        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT * FROM contacto WHERE visto = FALSE ORDER BY fecha DESC";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Contacto c = new Contacto();
                c.setIdContacto(rs.getInt("idContacto"));
                c.setNombre(rs.getString("nombre"));
                c.setEmail(rs.getString("email"));
                c.setAsunto(rs.getString("asunto"));
                c.setMensaje(rs.getString("mensaje"));
                c.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    @Override
    public Contacto obtenerPorId(int id) {
        Contacto c = null;
        String sql = "SELECT * FROM contacto WHERE idContacto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Contacto();
                    c.setIdContacto(rs.getInt("idContacto"));
                    c.setNombre(rs.getString("nombre"));
                    c.setEmail(rs.getString("email"));
                    c.setAsunto(rs.getString("asunto"));
                    c.setMensaje(rs.getString("mensaje"));
                    c.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM contacto WHERE idContacto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void marcarComoVisto(int id) {
        String sql = "UPDATE contacto SET visto = TRUE WHERE idContacto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
