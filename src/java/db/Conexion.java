package db;

import utils.ConfiguracionNoEncontradaException;

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
    private static boolean initialized = false;

    private static synchronized void init() {
        if (!initialized) {
            try (InputStream input = Conexion.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
                if (input == null) {
                    throw new ConfiguracionNoEncontradaException(
                        "❌ Archivo de configuración no encontrado: '" + PROPERTIES_FILE + "'.\n" +
                        "Asegúrate de que esté ubicado en 'src/main/resources'."
                    );
                }
                props.load(input);
                initialized = true;
            } catch (IOException ex) {
                throw new ConfiguracionNoEncontradaException(
                    "⚠️ Error al cargar el archivo de configuración: '" + PROPERTIES_FILE + "'", ex
                );
            }
        }
    }

    private Conexion() {
    }

    public static Connection getConexion() throws SQLException {
        synchronized (Conexion.class) {
            if (!initialized) {
                init();
            }

            if (conexion == null || conexion.isClosed()) {
                try {
                    Class.forName(props.getProperty("db.driver"));
                    String url = props.getProperty("db.url") + "?useUnicode=true&characterEncoding=UTF-8";
                    conexion = DriverManager.getConnection(url, props.getProperty("db.user"), props.getProperty("db.password"));
                } catch (ClassNotFoundException e) {
                    throw new SQLException("❗ Driver JDBC no encontrado. Verifica la dependencia en el pom.xml.", e);
                } catch (SQLException e) {
                    throw new SQLException("❗ No se pudo conectar a la base de datos. Verifica URL, usuario y contraseña.", e);
                }
            }
            return conexion;
        }
    }

    public static void cerrarConexion() {
        synchronized (Conexion.class) {
            if (conexion != null) {
                try {
                    if (!conexion.isClosed()) {
                        conexion.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    conexion = null;
                }
            }
        }
    }
}
