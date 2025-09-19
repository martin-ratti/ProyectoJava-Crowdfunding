package interfaces;

import java.sql.SQLException;
import java.util.List;
import modelo.Donacion;

public interface IDonacionDAO {
    
    List<Donacion> obtenerTodos() throws SQLException;
    
    Donacion obtenerPorId(int id) throws SQLException;
    
    void insertar(Donacion donacion) throws SQLException;
    
    void actualizar(Donacion donacion) throws SQLException;
    
    void eliminar(int id) throws SQLException;
    
    List<Donacion> obtenerPorIdDonante(int idDonante) throws SQLException;

    boolean haDonado(int idUsuario, int idProyecto) throws SQLException;
    
    List<Donacion> obtenerDonacionesPorUsuarioYProyecto(int idDonante, int idProyecto) throws SQLException;
}
