package interfaces;

import java.sql.SQLException;
import java.util.List;
import modelo.Proyecto;

public interface IProyectoDAO {
	
    void insertar(Proyecto proyecto) throws SQLException;
    
    void actualizar(Proyecto proyecto) throws SQLException;
    
    void eliminar(int id) throws SQLException;
    
    Proyecto obtenerPorId(int id) throws SQLException;
    
    List<Proyecto> obtenerActivos() throws SQLException;

    List<Proyecto> buscarProyectos(String query) throws SQLException;
    
    List<String> buscarSugerencias(String query) throws SQLException;

    List<Proyecto> obtenerPendientes() throws SQLException;
    
    List<Proyecto> obtenerPorUsuario(int idUsuario) throws SQLException;
}
