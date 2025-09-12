package repositorio;

import db.Conexion;
import interfaces.ICancelacion_ProyectoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cancelacion_Proyecto;

public class Cancelacion_ProyectoDAO implements ICancelacion_ProyectoDAO {

    @Override
    public void insertar(Cancelacion_Proyecto cancelacion) {
        String sql = "INSERT INTO cancelacion_proyecto (idProyecto, motivo, fecha) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cancelacion.getIdProyecto());
            ps.setString(2, cancelacion.getMotivo());
            ps.setDate(3, java.sql.Date.valueOf(cancelacion.getFecha()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public List<Cancelacion_Proyecto> obtenerTodos() {
        List<Cancelacion_Proyecto> lista = new ArrayList<>();
        String sql = "SELECT * FROM cancelacion_proyecto";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cancelacion_Proyecto item = new Cancelacion_Proyecto();
                item.setIdProyecto(rs.getInt("idProyecto"));
                item.setMotivo(rs.getString("motivo"));
                item.setFecha(rs.getDate("fecha").toLocalDate());
                lista.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Cancelacion_Proyecto obtenerPorId(int id) {
        Cancelacion_Proyecto item = null;
        String sql = "SELECT * FROM cancelacion_proyecto WHERE idProyecto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = new Cancelacion_Proyecto();
                    item.setIdProyecto(rs.getInt("idProyecto"));
                    item.setMotivo(rs.getString("motivo"));
                    item.setFecha(rs.getDate("fecha").toLocalDate());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void actualizar(Cancelacion_Proyecto cancelacion) {
        String sql = "UPDATE cancelacion_proyecto SET motivo = ?, fecha = ? WHERE idProyecto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cancelacion.getMotivo());
            ps.setDate(2, java.sql.Date.valueOf(cancelacion.getFecha()));
            ps.setInt(3, cancelacion.getIdProyecto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM cancelacion_proyecto WHERE idProyecto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
