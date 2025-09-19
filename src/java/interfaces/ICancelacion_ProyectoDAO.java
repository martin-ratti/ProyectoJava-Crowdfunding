package interfaces;

import java.sql.SQLException;
import java.util.List;
import modelo.Cancelacion_Proyecto;

public interface ICancelacion_ProyectoDAO {
    
    public List<Cancelacion_Proyecto> obtenerTodos() throws SQLException;
    
    public Cancelacion_Proyecto obtenerPorId(int id) throws SQLException;
    
    public void insertar(Cancelacion_Proyecto cancelacion) throws SQLException;
    
    public void actualizar(Cancelacion_Proyecto cancelacion) throws SQLException;
    
    public void eliminar(int id) throws SQLException;
}
