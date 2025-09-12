package interfaces;

import java.util.List;
import modelo.Comentario;

public interface IComentarioDAO {
    
    public List<Comentario> obtenerTodos();
    
    public Comentario obtenerPorId(int id);
    
    public void insertar(Comentario comentario);
    
    public void actualizar(Comentario comentario);
    
    public void eliminar(int id);
    
    public List<Comentario> obtenerPorIdProyecto(int idProyecto);
}
