package interfaces;

import java.util.List;
import modelo.Donacion;

public interface IDonacionDAO {
    
    List<Donacion> obtenerTodos();
    
    Donacion obtenerPorId(int id);
    
    void insertar(Donacion donacion);
    
    void actualizar(Donacion donacion);
    
    void eliminar(int id);
    
    List<Donacion> obtenerPorIdDonante(int idDonante);

    boolean haDonado(int idUsuario, int idProyecto);
}
