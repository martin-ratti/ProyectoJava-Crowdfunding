package interfaces;

import java.util.List;
import modelo.Usuario;

public interface IUsuarioDAO {
    
    public List<Usuario> obtenerTodos();
    
    public Usuario obtenerPorId(int id);
    
    public Usuario insertar(Usuario usuario);
    
    public void actualizar(Usuario usuario);
    
    public void eliminar(int id);
}

