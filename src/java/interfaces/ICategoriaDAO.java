package interfaces;

import java.util.List;
import modelo.Categoria;

public interface ICategoriaDAO {
    
    public List<Categoria> obtenerTodos();
    
    public Categoria obtenerPorId(int id);
    
    public void insertar(Categoria categoria);
    
    public void actualizar(Categoria categoria);
    
    public void eliminar(int id);
}
