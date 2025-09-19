package utils;

public class ConfiguracionNoEncontradaException extends RuntimeException {

    public ConfiguracionNoEncontradaException(String mensaje) {
        super(mensaje);
    }

    public ConfiguracionNoEncontradaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ConfiguracionNoEncontradaException(Throwable causa) {
        super(causa);
    }

    public ConfiguracionNoEncontradaException() {
        super("No se pudo cargar la configuración de la base de datos.");
    }
}
