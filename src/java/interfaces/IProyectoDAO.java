package interfaces;

import java.util.List;
import modelo.Proyecto;

public interface IProyectoDAO {
	
    void insertar(Proyecto proyecto);
    
    void actualizar(Proyecto proyecto);
    
    void eliminar(int id);
    
    Proyecto obtenerPorId(int id);
    
    List<Proyecto> obtenerActivos();

    List<Proyecto> buscarProyectos(String query);
    
    List<String> buscarSugerencias(String query);

    List<Proyecto> obtenerPendientes();
    
    List<Proyecto> obtenerPorUsuario(int idUsuario);
}

