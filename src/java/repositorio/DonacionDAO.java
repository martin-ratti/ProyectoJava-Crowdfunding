package repositorio;

import db.Conexion;
import interfaces.IDonacionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import modelo.Donacion;

public class DonacionDAO implements IDonacionDAO {

    @Override
    public void insertar(Donacion donacion) {
        String sql = "INSERT INTO donacion (monto, comentario, fecha, idDonante, idProyecto) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setBigDecimal(1, donacion.getMonto());
            ps.setString(2, donacion.getComentario());
            ps.setDate(3, java.sql.Date.valueOf(donacion.getFecha()));
            ps.setInt(4, donacion.getIdDonante());
            ps.setInt(5, donacion.getIdProyecto());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Donacion> obtenerPorIdDonante(int idDonante) {
        List<Donacion> donaciones = new ArrayList<>();
        String sql = "SELECT d.*, p.nombreProyecto FROM donacion d " +
                     "JOIN proyecto p ON d.idProyecto = p.idProyecto " +
                     "WHERE d.idDonante = ? ORDER BY d.fecha DESC";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idDonante);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Donacion donacion = new Donacion();
                    donacion.setIdDonacion(rs.getInt("idDonacion"));
                    donacion.setMonto(rs.getBigDecimal("monto"));
                    donacion.setComentario(rs.getString("comentario"));
                    donacion.setFecha(rs.getDate("fecha").toLocalDate());
                    donacion.setIdDonante(rs.getInt("idDonante"));
                    donacion.setIdProyecto(rs.getInt("idProyecto"));
                    donacion.setNombreProyecto(rs.getString("nombreProyecto")); 
                    donaciones.add(donacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donaciones;
    }

    @Override
    public boolean haDonado(int idUsuario, int idProyecto) {
        String sql = "SELECT COUNT(*) FROM donacion WHERE idDonante = ? AND idProyecto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setInt(2, idProyecto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map<Integer, Boolean> haDonadoEnMultiples(int idUsuario, List<Integer> idsProyectos) {
        Map<Integer, Boolean> resultado = new HashMap<>();
        if (idsProyectos == null || idsProyectos.isEmpty()) {
            return resultado;
        }

        StringJoiner placeholders = new StringJoiner(",");
        for (int i = 0; i < idsProyectos.size(); i++) {
            placeholders.add("?");
        }

        String sql = "SELECT DISTINCT idProyecto FROM donacion WHERE idDonante = ? AND idProyecto IN (" + placeholders.toString() + ")";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idUsuario);
            for (int i = 0; i < idsProyectos.size(); i++) {
                ps.setInt(i + 2, idsProyectos.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.put(rs.getInt("idProyecto"), true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        for (Integer idProyecto : idsProyectos) {
            resultado.putIfAbsent(idProyecto, false);
        }

        return resultado;
    }

    @Override
    public List<Donacion> obtenerTodos() {
        List<Donacion> donaciones = new ArrayList<>();
        String sql = "SELECT * FROM donacion";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Donacion donacion = new Donacion();
                donacion.setIdDonacion(rs.getInt("idDonacion"));
                donacion.setMonto(rs.getBigDecimal("monto"));
                donacion.setComentario(rs.getString("comentario"));
                donacion.setFecha(rs.getDate("fecha").toLocalDate());
                donacion.setIdDonante(rs.getInt("idDonante"));
                donacion.setIdProyecto(rs.getInt("idProyecto"));
                donaciones.add(donacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donaciones;
    }

    @Override
    public Donacion obtenerPorId(int id) {
        Donacion donacion = null;
        String sql = "SELECT * FROM donacion WHERE idDonacion = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    donacion = new Donacion();
                    donacion.setIdDonacion(rs.getInt("idDonacion"));
                    donacion.setMonto(rs.getBigDecimal("monto"));
                    donacion.setComentario(rs.getString("comentario"));
                    donacion.setFecha(rs.getDate("fecha").toLocalDate());
                    donacion.setIdDonante(rs.getInt("idDonante"));
                    donacion.setIdProyecto(rs.getInt("idProyecto"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donacion;
    }

    @Override
    public void actualizar(Donacion donacion) {
        String sql = "UPDATE donacion SET monto = ?, comentario = ?, fecha = ?, idDonante = ?, idProyecto = ? WHERE idDonacion = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBigDecimal(1, donacion.getMonto());
            ps.setString(2, donacion.getComentario());
            ps.setDate(3, java.sql.Date.valueOf(donacion.getFecha()));
            ps.setInt(4, donacion.getIdDonante());
            ps.setInt(5, donacion.getIdProyecto());
            ps.setInt(6, donacion.getIdDonacion());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM donacion WHERE idDonacion = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}

