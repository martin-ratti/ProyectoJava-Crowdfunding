package interfaces;

import java.util.List;
import modelo.Arrepentimiento_Donacion;

public interface IArrepentimiento_DonacionDAO {
    
    public List<Arrepentimiento_Donacion> obtenerTodos();
    
    public Arrepentimiento_Donacion obtenerPorId(int id);
    
    public void insertar(Arrepentimiento_Donacion arrepentimiento);
    
    public void actualizar(Arrepentimiento_Donacion arrepentimiento);
    
    public void eliminar(int id);
}
