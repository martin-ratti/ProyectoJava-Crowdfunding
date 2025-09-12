package modelo;

/**
 * Representa un país en el sistema.
 * Se utiliza para asociar proyectos a una ubicación geográfica.
 */
public class Pais {
    private int idPais;
    private String nombrePais;

    /**
     * Obtiene el ID del país.
     * @return El ID del país.
     */
    public int getIdPais() {
        return idPais;
    }

    /**
     * Establece el ID del país.
     * @param idPais El nuevo ID del país.
     */
    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    /**
     * Obtiene el nombre del país.
     * @return El nombre del país.
     */
    public String getNombrePais() {
        return nombrePais;
    }

    /**
     * Establece el nombre del país.
     * @param nombrePais El nuevo nombre del país.
     */
    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
}
