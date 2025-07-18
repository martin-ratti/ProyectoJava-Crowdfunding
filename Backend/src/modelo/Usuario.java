package modelo;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private String telefono;
    private String nombre;
    private String apellido;
    private String mail;

    public Usuario(String nombreUsuario, String contrasenia, String telefono,
                   String nombre, String apellido, String mail) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
    }

    public Usuario(int idUsuario, String nombreUsuario, String contrasenia, String telefono,
                   String nombre, String apellido, String mail) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
    }

    // Getters y setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", telefono='" + telefono + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
