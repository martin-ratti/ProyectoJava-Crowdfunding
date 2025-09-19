package interfaces;

import java.sql.SQLException;
import java.util.List;
import modelo.Categoria;

public interface ICategoriaDAO {
    
    public List<Categoria> obtenerTodos() throws SQLException;
    
    public Categoria obtenerPorId(int id) throws SQLException;
    
    public void insertar(Categoria categoria) throws SQLException;
    
    public void actualizar(Categoria categoria) throws SQLException;
    
    public void eliminar(int id) throws SQLException;
}
