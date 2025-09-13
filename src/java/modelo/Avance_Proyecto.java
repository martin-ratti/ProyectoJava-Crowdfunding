package modelo;

import java.time.LocalDate;



public class Avance_Proyecto {

    private int idProyecto;
    private int idAvance;
    private String descripcion;
    private String foto;
    private LocalDate fecha;

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
