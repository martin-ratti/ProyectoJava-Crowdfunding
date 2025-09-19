package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

    private static Connection conexion;
    private static final Properties props = new Properties();
    private static final String PROPERTIES_FILE = "database.properties";

    static {
        try (InputStream input = Conexion.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new RuntimeException("ERROR: No se pudo encontrar el archivo '" + PROPERTIES_FILE + "'. Asegúrate de que esté en 'src/main/resources'.");
            }
            props.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error al cargar el archivo de propiedades de la base de datos.", ex);
        }
    }

    private Conexion() {
    }
    public static Connection getConexion() throws SQLException {
        synchronized (Conexion.class) {
            if (conexion == null || conexion.isClosed()) {
                try {
                    Class.forName(props.getProperty("db.driver"));                    
                    String url = props.getProperty("db.url") + "?useUnicode=true&characterEncoding=UTF-8";                    
                    conexion = DriverManager.getConnection(url, props.getProperty("db.user"), props.getProperty("db.password"));
                    
                } catch (ClassNotFoundException e) {
                    System.err.println("Error: Driver de la base de datos no encontrado.");
                    throw new SQLException("Driver de la base de datos no encontrado. Asegúrate de que la dependencia de MySQL esté en el pom.xml.", e);
                } catch (SQLException e) {
                    System.err.println("Error al establecer la conexión con la base de datos.");
                    throw new SQLException("No se pudo conectar a la base de datos. Verifica la URL, usuario y contraseña en '" + PROPERTIES_FILE + "'.", e);
                }
            }
        }
        return conexion;
    }

    /* Cierra la conexión a la base de datos si está abierta. */
    public static void cerrarConexion() {
        synchronized (Conexion.class) {
            if (conexion != null) {
                try {
                    if (!conexion.isClosed()) {
                        conexion.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión a la base de datos.");
                    e.printStackTrace();
                } finally {
                    conexion = null; 
                }
            }
        }
    }
}
