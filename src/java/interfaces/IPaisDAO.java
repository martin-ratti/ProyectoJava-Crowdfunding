package interfaces;

import java.util.List;
import modelo.Pais;

public interface IPaisDAO {
    
    public List<Pais> obtenerTodos();
    
    public Pais obtenerPorId(int id);
    
    public void insertar(Pais pais);
    
    public void actualizar(Pais pais);
    
    public void eliminar(int id);
}
