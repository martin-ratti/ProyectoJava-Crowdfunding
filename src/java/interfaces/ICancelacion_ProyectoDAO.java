package interfaces;

import java.util.List;
import modelo.Cancelacion_Proyecto;

public interface ICancelacion_ProyectoDAO {
    
    public List<Cancelacion_Proyecto> obtenerTodos();
    
    public Cancelacion_Proyecto obtenerPorId(int id);
    
    public void insertar(Cancelacion_Proyecto cancelacion);
    
    public void actualizar(Cancelacion_Proyecto cancelacion);
    
    public void eliminar(int id);
}
