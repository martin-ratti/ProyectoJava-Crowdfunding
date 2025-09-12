package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Contacto {
    private int idContacto;
    private String nombre;
    private String email;
    private String asunto;
    private String mensaje;
    private LocalDateTime fecha;

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    /**
     * Devuelve la fecha y hora del mensaje en un formato legible.
     * @return La fecha formateada como String (ej: "04 de septiembre de 2025, 19:30 hs").
     */
    public String getFechaFormateada() {
        if (fecha == null) {
            return "";
        }
        // Usamos Locale para asegurar que el mes se muestre en español
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy, HH:mm 'hs'", new Locale("es", "ES"));
        return fecha.format(formatter);
    }
}
