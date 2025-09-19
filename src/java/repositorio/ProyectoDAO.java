package repositorio;

import db.Conexion;
import interfaces.IProyectoDAO;
import modelo.Cancelacion_Proyecto;
import modelo.Categoria;
import modelo.Pais;
import modelo.Proyecto;
import modelo.Usuario;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProyectoDAO implements IProyectoDAO {

    private Proyecto mapResultSetToProyecto(ResultSet rs) throws SQLException {
        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(rs.getInt("idProyecto"));
        proyecto.setNombreProyecto(rs.getString("nombreProyecto"));
        proyecto.setDescripcion(rs.getString("descripcion"));
        proyecto.setMontoMeta(rs.getBigDecimal("montoMeta"));
        proyecto.setMontoRecaudado(rs.getBigDecimal("montoRecaudado"));
        proyecto.setFechaIni(rs.getDate("fechaIni").toLocalDate());
        proyecto.setFechaFin(rs.getDate("fechaFin").toLocalDate());
        proyecto.setIdCreador(rs.getInt("idCreador"));
        proyecto.setEstado(rs.getString("estado"));
        proyecto.setFoto(rs.getString("foto"));

        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("idCategoria"));
        c.setNombreCategoria(rs.getString("nombreCategoria"));
        proyecto.setCategoria(c);

        Pais p = new Pais();
        p.setIdPais(rs.getInt("idPais"));
        p.setNombrePais(rs.getString("nombrePais"));
        proyecto.setPais(p);

        // Mapeo del creador si la query lo trae
        try {
            String nombreCreador = rs.getString("nombreCreador");
            if (nombreCreador != null) {
                Usuario creador = new Usuario();
                creador.setIdUsuario(rs.getInt("idCreador"));
                creador.setNombre(rs.getString("nombreCreador"));
                creador.setApellido(rs.getString("apellidoCreador"));
                proyecto.setCreador(creador);
            }
        } catch (SQLException ignore) {
            // si no hay columnas de creador, no se setea
        }

        return proyecto;
    }

    @Override
    public void insertar(Proyecto proyecto) throws SQLException {
        String sql = "INSERT INTO proyecto (nombreProyecto, descripcion, montoMeta, montoRecaudado, fechaIni, fechaFin, idCreador, idCategoria, idPais, estado, foto) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, proyecto.getNombreProyecto());
            ps.setString(2, proyecto.getDescripcion());
            ps.setBigDecimal(3, proyecto.getMontoMeta());
            ps.setBigDecimal(4, proyecto.getMontoRecaudado());
            ps.setDate(5, Date.valueOf(proyecto.getFechaIni()));
            ps.setDate(6, Date.valueOf(proyecto.getFechaFin()));
            ps.setInt(7, proyecto.getIdCreador());
            ps.setInt(8, proyecto.getCategoria().getIdCategoria());
            ps.setInt(9, proyecto.getPais().getIdPais());
            ps.setString(10, proyecto.getEstado());
            ps.setString(11, proyecto.getFoto());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al insertar proyecto.", e);
        }
    }

    @Override
    public void actualizar(Proyecto proyecto) throws SQLException {
        String sql = "UPDATE proyecto SET nombreProyecto = ?, descripcion = ?, montoMeta = ?, fechaFin = ? WHERE idProyecto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, proyecto.getNombreProyecto());
            ps.setString(2, proyecto.getDescripcion());
            ps.setBigDecimal(3, proyecto.getMontoMeta());
            ps.setDate(4, java.sql.Date.valueOf(proyecto.getFechaFin()));
            ps.setInt(5, proyecto.getIdProyecto());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar proyecto.", e);
        }
    }

    public void actualizarMontoRecaudado(int idProyecto, BigDecimal nuevoMontoRecaudado) throws SQLException {
        String sql = "UPDATE proyecto SET montoRecaudado = ? WHERE idProyecto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBigDecimal(1, nuevoMontoRecaudado);
            ps.setInt(2, idProyecto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar monto recaudado.", e);
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        borrarDefinitivamente(id);
    }

    @Override
    public Proyecto obtenerPorId(int id) throws SQLException {
        Proyecto proyecto = null;
        String sql = "SELECT p.*, c.nombreCategoria, pa.nombrePais, u.nombre AS nombreCreador, u.apellido AS apellidoCreador " +
                     "FROM proyecto p " +
                     "JOIN categoria c ON p.idCategoria = c.idCategoria " +
                     "JOIN pais pa ON p.idPais = pa.idPais " +
                     "JOIN usuario u ON p.idCreador = u.idUsuario " +
                     "WHERE p.idProyecto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    proyecto = mapResultSetToProyecto(rs);
                    if ("Cancelado".equalsIgnoreCase(proyecto.getEstado())) {
                        Cancelacion_Proyecto c = obtenerCancelacionPorProyecto(id);
                        proyecto.setCancelacion(c);
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener proyecto por ID.", e);
        }
        return proyecto;
    }

    public void actualizarEstado(int idProyecto, String nuevoEstado) throws SQLException {
        String sql = "UPDATE proyecto SET estado = ? WHERE idProyecto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idProyecto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar estado del proyecto.", e);
        }
    }

    private Cancelacion_Proyecto obtenerCancelacionPorProyecto(int idProyecto) throws SQLException {
        String sql = "SELECT * FROM cancelacion_proyecto WHERE idProyecto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cancelacion_Proyecto c = new Cancelacion_Proyecto();
                    c.setIdProyecto(rs.getInt("idProyecto"));
                    c.setMotivo(rs.getString("motivo"));
                    c.setFecha(rs.getDate("fecha").toLocalDate());
                    return c;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener datos de cancelación.", e);
        }
        return null;
    }

    @Override
    public List<Proyecto> obtenerActivos() throws SQLException {
        return buscarProyectos(null, null, null);
    }

    @Override
    public List<Proyecto> buscarProyectos(String query) throws SQLException {
        return buscarProyectos(query, null, null);
    }

    public List<Proyecto> buscarProyectos(String query, Integer idCategoria, Integer idPais) throws SQLException {
        List<Proyecto> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT p.*, c.nombreCategoria, pa.nombrePais " +
            "FROM proyecto p " +
            "JOIN categoria c ON p.idCategoria = c.idCategoria " +
            "JOIN pais pa ON p.idPais = pa.idPais " +
            "WHERE p.estado='Activo'"
        );

        if (query != null && !query.trim().isEmpty()) {
            sql.append(" AND (p.nombreProyecto LIKE ? OR p.descripcion LIKE ?)");
        }
        if (idCategoria != null && idCategoria > 0) {
            sql.append(" AND p.idCategoria = ?");
        }
        if (idPais != null && idPais > 0) {
            sql.append(" AND p.idPais = ?");
        }

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (query != null && !query.trim().isEmpty()) {
                ps.setString(paramIndex++, "%" + query + "%");
                ps.setString(paramIndex++, "%" + query + "%");
            }
            if (idCategoria != null && idCategoria > 0) {
                ps.setInt(paramIndex++, idCategoria);
            }
            if (idPais != null && idPais > 0) {
                ps.setInt(paramIndex, idPais);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapResultSetToProyecto(rs));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al buscar proyectos.", e);
        }
        return lista;
    }

    @Override
    public List<String> buscarSugerencias(String query) throws SQLException {
        List<String> sugerencias = new ArrayList<>();
        String sql = "SELECT nombreProyecto FROM proyecto WHERE nombreProyecto LIKE ? AND estado = 'Activo' LIMIT 5";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + query + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sugerencias.add(rs.getString("nombreProyecto"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al buscar sugerencias de proyectos.", e);
        }
        return sugerencias;
    }

    @Override
    public List<Proyecto> obtenerPendientes() throws SQLException {
        List<Proyecto> lista = new ArrayList<>();
        String sql = "SELECT p.*, c.nombreCategoria, pa.nombrePais " +
               "FROM proyecto p " +
               "JOIN categoria c ON p.idCategoria = c.idCategoria " +
               "JOIN pais pa ON p.idPais = pa.idPais " +
               "WHERE p.estado='Pendiente'";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapResultSetToProyecto(rs));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener proyectos pendientes.", e);
        }
        return lista;
    }

    @Override
    public List<Proyecto> obtenerPorUsuario(int idUsuario) throws SQLException {
        List<Proyecto> lista = new ArrayList<>();
        String sql = "SELECT p.*, c.nombreCategoria, pa.nombrePais " +
               "FROM proyecto p " +
               "JOIN categoria c ON p.idCategoria = c.idCategoria " +
               "JOIN pais pa ON p.idPais = pa.idPais " +
               "WHERE p.idCreador=? AND p.estado <> 'Borrado'";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Proyecto proyecto = mapResultSetToProyecto(rs);
                    if ("Cancelado".equalsIgnoreCase(proyecto.getEstado())) {
                        Cancelacion_Proyecto c = obtenerCancelacionPorProyecto(proyecto.getIdProyecto());
                        proyecto.setCancelacion(c);
                    }
                    lista.add(proyecto);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener proyectos por usuario.", e);
        }
        return lista;
    }

    public void cancelarProyecto(Proyecto proyecto, Cancelacion_Proyecto cancelacion) throws SQLException {
        String updateProyecto = "UPDATE proyecto SET estado = ? WHERE idProyecto = ?";
        String insertCancelacion = "INSERT INTO cancelacion_proyecto (idProyecto, motivo, fecha) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion()) {
            try (PreparedStatement ps = con.prepareStatement(updateProyecto)) {
                ps.setString(1, proyecto.getEstado());
                ps.setInt(2, proyecto.getIdProyecto());
                ps.executeUpdate();
            }
            try (PreparedStatement ps = con.prepareStatement(insertCancelacion)) {
                ps.setInt(1, cancelacion.getIdProyecto());
                ps.setString(2, cancelacion.getMotivo());
                ps.setDate(3, java.sql.Date.valueOf(cancelacion.getFecha()));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException("Error al cancelar el proyecto.", e);
        }
    }

    public void borrarDefinitivamente(int idProyecto) throws SQLException {
        String sql = "UPDATE proyecto SET estado = 'Borrado' WHERE idProyecto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al borrar el proyecto.", e);
        }
    }
    
    public List<Proyecto> obtenerProyectosDonadosPorUsuario(int idUsuario) throws SQLException {
        List<Proyecto> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT p.*, c.nombreCategoria, pa.nombrePais, u.nombre AS nombreCreador, u.apellido AS apellidoCreador " +
                     "FROM donacion d " +
                     "JOIN proyecto p ON d.idProyecto = p.idProyecto " +
                     "JOIN categoria c ON p.idCategoria = c.idCategoria " +
                     "JOIN pais pa ON p.idPais = pa.idPais " +
                     "JOIN usuario u ON p.idCreador = u.idUsuario " +
                     "WHERE d.idDonante = ?"; 

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Proyecto proyecto = new Proyecto();
                    proyecto.setIdProyecto(rs.getInt("idProyecto"));
                    proyecto.setNombreProyecto(rs.getString("nombreProyecto"));
                    proyecto.setDescripcion(rs.getString("descripcion"));
                    proyecto.setMontoMeta(rs.getBigDecimal("montoMeta"));
                    proyecto.setMontoRecaudado(rs.getBigDecimal("montoRecaudado"));
                    proyecto.setFechaIni(rs.getDate("fechaIni").toLocalDate());
                    proyecto.setFechaFin(rs.getDate("fechaFin").toLocalDate());
                    proyecto.setIdCreador(rs.getInt("idCreador"));
                    proyecto.setEstado(rs.getString("estado"));
                    proyecto.setFoto(rs.getString("foto"));

                    // Categoria
                    Categoria categoria = new Categoria();
                    categoria.setIdCategoria(rs.getInt("idCategoria"));
                    categoria.setNombreCategoria(rs.getString("nombreCategoria"));
                    proyecto.setCategoria(categoria);

                    // Pais
                    Pais pais = new Pais();
                    pais.setIdPais(rs.getInt("idPais"));
                    pais.setNombrePais(rs.getString("nombrePais"));
                    proyecto.setPais(pais);

                    // Creador
                    Usuario creador = new Usuario();
                    creador.setIdUsuario(rs.getInt("idCreador"));
                    creador.setNombre(rs.getString("nombreCreador"));
                    creador.setApellido(rs.getString("apellidoCreador"));
                    proyecto.setCreador(creador);

                    lista.add(proyecto);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener proyectos donados por el usuario.", e);
        }
        return lista;
    }

    public List<Pais> obtenerPaisesConProyectosActivos() throws SQLException {
        List<Pais> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT pa.idPais, pa.nombrePais " +
                     "FROM pais pa " +
                     "JOIN proyecto p ON pa.idPais = p.idPais " +
                     "WHERE p.estado = 'Activo' ORDER BY pa.nombrePais";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Pais pais = new Pais();
                pais.setIdPais(rs.getInt("idPais"));
                pais.setNombrePais(rs.getString("nombrePais"));
                lista.add(pais);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener países con proyectos activos.", e);
        }
        return lista;
    }
}
