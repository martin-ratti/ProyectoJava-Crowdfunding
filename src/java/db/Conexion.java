package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

    private static Connection conexion;
    private static Properties props = new Properties();

    static {
        try (InputStream input = Conexion.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                System.err.println("ERROR: No se pudo encontrar el archivo 'database.properties'. Asegúrate de que esté en 'src/main/resources'.");
            } else {
                props.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Conexion() {
    }

    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                Class.forName(props.getProperty("db.driver"));
                String url = props.getProperty("db.url") + "?useUnicode=true&characterEncoding=UTF-8";
                conexion = DriverManager.getConnection(url, props.getProperty("db.user"), props.getProperty("db.password"));
                System.out.println("Conexión a la base de datos establecida exitosamente.");
            } catch (ClassNotFoundException e) {
                System.err.println("Error: Driver de la base de datos no encontrado.");
                throw new SQLException("Driver no encontrado", e);
            }
        }
        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
                System.out.println("Conexión a la base de datos cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
