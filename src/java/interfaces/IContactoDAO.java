package interfaces;

import java.util.List;
import modelo.Contacto;

public interface IContactoDAO {
    void insertar(Contacto contacto);
    List<Contacto> obtenerTodos();
    Contacto obtenerPorId(int id);
    void eliminar(int id);
    void marcarComoVisto(int id);
}
