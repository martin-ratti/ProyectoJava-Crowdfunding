package interfaces;

import java.sql.SQLException;
import java.util.List;
import modelo.Avance_Proyecto;

public interface IAvance_ProyectoDAO {
    
    public List<Avance_Proyecto> obtenerTodos() throws SQLException;
    
    public Avance_Proyecto obtenerPorId(int idProyecto, int idAvance) throws SQLException;
    
    public void insertar(Avance_Proyecto avance) throws SQLException;
    
    public void actualizar(Avance_Proyecto avance) throws SQLException;
    
    public void eliminar(int idProyecto, int idAvance) throws SQLException;

	List<Avance_Proyecto> obtenerPorProyecto(int idProyecto) throws SQLException;
}
