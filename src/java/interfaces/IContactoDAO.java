package interfaces;

import java.sql.SQLException;
import java.util.List;
import modelo.Contacto;

public interface IContactoDAO {
    void insertar(Contacto contacto) throws SQLException;
    
    List<Contacto> obtenerTodos() throws SQLException;
    
    Contacto obtenerPorId(int id) throws SQLException;
    
    void eliminar(int id) throws SQLException;
    
    void marcarComoVisto(int id) throws SQLException;
}
