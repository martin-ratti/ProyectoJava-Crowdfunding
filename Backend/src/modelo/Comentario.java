package modelo;

import java.time.LocalDate;

public class Comentario {
    private int idComentario;
    private String titulo;
    private LocalDate fecha;
    private String descripcion;
    private int idProyecto;
    private int idUsuario;

    public Comentario(int idComentario, String titulo, LocalDate localDate, String descripcion, int idProyecto, int idUsuario) {
        this.idComentario = idComentario;
        this.titulo = titulo;
        this.fecha = localDate;
        this.descripcion = descripcion;
        this.idProyecto = idProyecto;
        this.idUsuario = idUsuario;
    }


    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
