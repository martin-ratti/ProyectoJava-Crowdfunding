package interfaces;

import java.sql.SQLException;
import java.util.List;
import modelo.Usuario;

public interface IUsuarioDAO {
    
    public List<Usuario> obtenerTodos() throws SQLException;
    
    public Usuario obtenerPorId(int id) throws SQLException;
    
    public Usuario insertar(Usuario usuario) throws SQLException;
    
    public void actualizar(Usuario usuario) throws SQLException;
    
    public void eliminar(int id) throws SQLException;
}
