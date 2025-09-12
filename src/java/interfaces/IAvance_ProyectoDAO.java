package interfaces;

import java.util.List;
import modelo.Avance_Proyecto;

public interface IAvance_ProyectoDAO {
    
    public List<Avance_Proyecto> obtenerTodos();
    
    // La clave primaria es compuesta, así que buscamos por ambos IDs
    public Avance_Proyecto obtenerPorId(int idProyecto, int idAvance);
    
    public void insertar(Avance_Proyecto avance);
    
    public void actualizar(Avance_Proyecto avance);
    
    public void eliminar(int idProyecto, int idAvance);

	List<Avance_Proyecto> obtenerPorProyecto(int idProyecto);
}
