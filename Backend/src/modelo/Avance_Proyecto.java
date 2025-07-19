package modelo;

import java.time.LocalDate;

public class Avance_Proyecto {
    private int idProyecto;
    private int idAvance;
    private String descripcion;
    private String foto;
    private LocalDate fecha;

    public Avance_Proyecto(int idProyecto, int idAvance, String descripcion, String foto, LocalDate localDate) {
        this.idProyecto = idProyecto;
        this.idAvance = idAvance;
        this.descripcion = descripcion;
        this.foto = foto;
        this.fecha = localDate;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdAvance() {
        return idAvance;
    }

    public void setIdAvance(int idAvance) {
        this.idAvance = idAvance;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
