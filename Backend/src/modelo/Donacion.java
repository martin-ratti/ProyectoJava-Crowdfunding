package modelo;

import java.time.LocalDate;

public class Donacion {
    private int idDonacion;
    private double monto;
    private String comentario;
    private LocalDate fecha;
    private int idDonante;
    private int idProyecto;

    public Donacion(int idDonacion, double monto, String comentario, LocalDate localdate, int idDonante, int idProyecto) {
        this.idDonacion = idDonacion;
        this.monto = monto;
        this.comentario = comentario;
        this.fecha = localdate;
        this.idDonante = idDonante;
        this.idProyecto = idProyecto;
    }

    public int getIdDonacion() {
        return idDonacion;
    }

    public void setIdDonacion(int idDonacion) {
        this.idDonacion = idDonacion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getIdDonante() {
        return idDonante;
    }

    public void setIdDonante(int idDonante) {
        this.idDonante = idDonante;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
}
