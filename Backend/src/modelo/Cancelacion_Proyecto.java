package modelo;

import java.time.LocalDate;

public class Cancelacion_Proyecto {
    private int idProyecto;
    private String motivo;
    private LocalDate fecha;

    public Cancelacion_Proyecto(int idProyecto, String motivo, LocalDate fecha) {
        this.idProyecto = idProyecto;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
