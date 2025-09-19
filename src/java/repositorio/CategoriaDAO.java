package repositorio;

import db.Conexion;
import interfaces.ICategoriaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;

public class CategoriaDAO implements ICategoriaDAO {

    @Override
    public void insertar(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categoria (nombreCategoria) VALUES (?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombreCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al insertar categoría.", e);
        }
    }

    @Override
    public List<Categoria> obtenerTodos() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombreCategoria(rs.getString("nombreCategoria"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener todas las categorías.", e);
        }
        return categorias;
    }

    @Override
    public Categoria obtenerPorId(int id) throws SQLException {
        Categoria categoria = null;
        String sql = "SELECT * FROM categoria WHERE idCategoria = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoria = new Categoria();
                    categoria.setIdCategoria(rs.getInt("idCategoria"));
                    categoria.setNombreCategoria(rs.getString("nombreCategoria"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener categoría por ID.", e);
        }
        return categoria;
    }

    @Override
    public void actualizar(Categoria categoria) throws SQLException {
        String sql = "UPDATE categoria SET nombreCategoria = ? WHERE idCategoria = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombreCategoria());
            ps.setInt(2, categoria.getIdCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar categoría.", e);
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM categoria WHERE idCategoria = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar categoría.", e);
        }
    }
}
