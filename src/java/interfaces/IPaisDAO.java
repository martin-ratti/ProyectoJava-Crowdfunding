package interfaces;

import java.sql.SQLException;
import java.util.List;
import modelo.Pais;

public interface IPaisDAO {
    
    public List<Pais> obtenerTodos() throws SQLException;
    
    public Pais obtenerPorId(int id) throws SQLException;
    
    public void insertar(Pais pais) throws SQLException;
    
    public void actualizar(Pais pais) throws SQLException;
    
    public void eliminar(int id) throws SQLException;
}
