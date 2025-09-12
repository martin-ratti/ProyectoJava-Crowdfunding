package repositorio;

import db.Conexion;
import interfaces.IArrepentimiento_DonacionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Arrepentimiento_Donacion;

public class Arrepentimiento_DonacionDAO implements IArrepentimiento_DonacionDAO {

    @Override
    public void insertar(Arrepentimiento_Donacion arrepentimiento) {
        String sql = "INSERT INTO arrepentimiento_donacion (idDonacion, motivo, fechaCancelacion) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, arrepentimiento.getIdDonacion());
            ps.setString(2, arrepentimiento.getMotivo());
            ps.setDate(3, java.sql.Date.valueOf(arrepentimiento.getFechaCancelacion()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Arrepentimiento_Donacion> obtenerTodos() {
        List<Arrepentimiento_Donacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM arrepentimiento_donacion";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Arrepentimiento_Donacion item = new Arrepentimiento_Donacion();
                item.setIdDonacion(rs.getInt("idDonacion"));
                item.setMotivo(rs.getString("motivo"));
                item.setFechaCancelacion(rs.getDate("fechaCancelacion").toLocalDate());
                lista.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Arrepentimiento_Donacion obtenerPorId(int id) {
        Arrepentimiento_Donacion item = null;
        String sql = "SELECT * FROM arrepentimiento_donacion WHERE idDonacion = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = new Arrepentimiento_Donacion();
                    item.setIdDonacion(rs.getInt("idDonacion"));
                    item.setMotivo(rs.getString("motivo"));
                    item.setFechaCancelacion(rs.getDate("fechaCancelacion").toLocalDate());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void actualizar(Arrepentimiento_Donacion arrepentimiento) {
        String sql = "UPDATE arrepentimiento_donacion SET motivo = ?, fechaCancelacion = ? WHERE idDonacion = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, arrepentimiento.getMotivo());
            ps.setDate(2, java.sql.Date.valueOf(arrepentimiento.getFechaCancelacion()));
            ps.setInt(3, arrepentimiento.getIdDonacion());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM arrepentimiento_donacion WHERE idDonacion = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
