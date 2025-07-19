package repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;
import db.Conexion;

public class CategoriaDAO {

    public void crear(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO Categoria(nombreCategoria) VALUES (?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombreCategoria());
            ps.executeUpdate();
        }
    }

    public Categoria obtenerUno(int idCategoria) throws SQLException {
        String sql = "SELECT * FROM Categoria WHERE idCategoria = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Categoria(rs.getInt("idCategoria"), rs.getString("nombreCategoria"));
            }
        }
        return null;
    }

    public List<Categoria> obtenerTodos() throws SQLException {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM Categoria";
        try (Connection con = Conexion.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Categoria(rs.getInt("idCategoria"), rs.getString("nombreCategoria")));
            }
        }
        return lista;
    }

    public void actualizar(Categoria categoria) throws SQLException {
        String sql = "UPDATE Categoria SET nombreCategoria = ? WHERE idCategoria = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombreCategoria());
            ps.setInt(2, categoria.getIdCategoria());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idCategoria) throws SQLException {
        String sql = "DELETE FROM Categoria WHERE idCategoria = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            ps.executeUpdate();
        }
    }
}
