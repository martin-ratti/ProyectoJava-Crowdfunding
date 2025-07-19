package modelo;

import java.time.LocalDate;

public class Arrepentimiento_Donacion {
    private int idDonacion;
    private String motivo;
    private LocalDate fechaCancelacion;

    public Arrepentimiento_Donacion(int idDonacion, String motivo, LocalDate localDate) {
        this.idDonacion = idDonacion;
        this.motivo = motivo;
        this.fechaCancelacion = localDate;
    }

    public int getIdDonacion() {
        return idDonacion;
    }

    public void setIdDonacion(int idDonacion) {
        this.idDonacion = idDonacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDate fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }
}