package interfaces;

import java.sql.SQLException;
import java.util.List;
import modelo.Comentario;

public interface IComentarioDAO {
    
    public List<Comentario> obtenerTodos() throws SQLException;
    
    public Comentario obtenerPorId(int id) throws SQLException;
    
    public void insertar(Comentario comentario) throws SQLException;
    
    public void actualizar(Comentario comentario) throws SQLException;
    
    public void eliminar(int id) throws SQLException;
    
    public List<Comentario> obtenerPorIdProyecto(int idProyecto) throws SQLException;
}
