package modelo;

import java.math.BigDecimal;
import java.sql.Date;

public class Proyecto {
    private int idProyecto;
    private String nombreProyecto;
    private String descripcion;
    private BigDecimal montoMeta;
    private Date fechaIni;
    private Date fechaFin;
    private String foto;
    private String estado;
    private BigDecimal montoRecaudado;
    private int idPais;
    private int idCreador;
    private int idCategoria;

    public Proyecto(String nombreProyecto, String descripcion, BigDecimal montoMeta, Date fechaIni, Date fechaFin,
                    String foto, String estado, BigDecimal montoRecaudado, int idPais, int idCreador, int idCategoria) {
        this.nombreProyecto = nombreProyecto;
        this.descripcion = descripcion;
        this.montoMeta = montoMeta;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.foto = foto;
        this.estado = estado;
        this.montoRecaudado = montoRecaudado;
        this.idPais = idPais;
        this.idCreador = idCreador;
        this.idCategoria = idCategoria;
    }

    public Proyecto(int idProyecto, String nombreProyecto, String descripcion, BigDecimal montoMeta, Date fechaIni, Date fechaFin,
                    String foto, String estado, BigDecimal montoRecaudado, int idPais, int idCreador, int idCategoria) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.descripcion = descripcion;
        this.montoMeta = montoMeta;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.foto = foto;
        this.estado = estado;
        this.montoRecaudado = montoRecaudado;
        this.idPais = idPais;
        this.idCreador = idCreador;
        this.idCategoria = idCategoria;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMontoMeta() {
        return montoMeta;
    }

    public void setMontoMeta(BigDecimal montoMeta) {
        this.montoMeta = montoMeta;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getMontoRecaudado() {
        return montoRecaudado;
    }

    public void setMontoRecaudado(BigDecimal montoRecaudado) {
        this.montoRecaudado = montoRecaudado;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public int getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(int idCreador) {
        this.idCreador = idCreador;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

}
