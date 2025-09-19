package interfaces;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import modelo.Cancelacion_Proyecto;
import modelo.Pais;
import modelo.Proyecto;

public interface IProyectoDAO {
	
    void insertar(Proyecto proyecto) throws SQLException;
    
    void actualizar(Proyecto proyecto) throws SQLException;
    
    void eliminar(int id) throws SQLException;
    
    Proyecto obtenerPorId(int id) throws SQLException;
    
    List<Proyecto> obtenerActivos() throws SQLException;

    List<Proyecto> buscarProyectos(String query, Integer idCategoria, Integer idPais) throws SQLException;
    
    List<String> buscarSugerencias(String query) throws SQLException;

    List<Proyecto> obtenerPendientes() throws SQLException;
    
    List<Proyecto> obtenerPorUsuario(int idUsuario) throws SQLException;
    
    void actualizarMontoRecaudado(int idProyecto, BigDecimal nuevoMontoRecaudado) throws SQLException;
    
    void actualizarEstado(int idProyecto, String nuevoEstado) throws SQLException;
    
    void cancelarProyecto(Proyecto proyecto, Cancelacion_Proyecto cancelacion) throws SQLException;
    
    void borrarDefinitivamente(int idProyecto) throws SQLException;
    
    List<Proyecto> obtenerProyectosDonadosPorUsuario(int idUsuario) throws SQLException;
    
    List<Pais> obtenerPaisesConProyectosActivos() throws SQLException;
}
