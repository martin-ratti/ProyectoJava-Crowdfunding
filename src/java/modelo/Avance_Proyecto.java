package modelo;

import java.time.LocalDate;



public class Avance_Proyecto {

    private int idProyecto;
    private int idAvance;
    private String descripcion;
    private String foto;
    private LocalDate fecha;

    /**
     * Obtiene el ID del proyecto al que pertenece este avance.
     * @return El ID del proyecto.
     */
    public int getIdProyecto() {
        return idProyecto;
    }

    /**
     * Establece el ID del proyecto al que pertenece este avance.
     * @param idProyecto El ID del proyecto.
     */
    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    /**
     * Obtiene el ID único de este avance dentro del proyecto.
     * @return El ID del avance.
     */
    public int getIdAvance() {
        return idAvance;
    }

    /**
     * Establece el ID único de este avance dentro del proyecto.
     * @param idAvance El ID del avance.
     */
    public void setIdAvance(int idAvance) {
        this.idAvance = idAvance;
    }

    /**
     * Obtiene la descripción textual del avance.
     * @return La descripción del avance.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción textual del avance.
     * @param descripcion La descripción del avance.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el nombre del archivo de la foto asociada a este avance.
     * @return El nombre del archivo de la foto.
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Establece el nombre del archivo de la foto asociada a este avance.
     * @param foto El nombre del archivo de la foto.
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Obtiene la fecha en que se registró el avance.
     * @return La fecha del avance.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha en que se registró el avance.
     * @param fecha La fecha del avance.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
