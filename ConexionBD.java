package proyectopersonalizado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost/datos";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    /*
     Método para establecer y retornar la conexión a la base de datos MySQL.
     */
    public static Connection conectar() {
        Connection cnx = null;
        try {
            cnx = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Error de conexión a la BD: " + ex.getMessage());
        }
        return cnx;
    }
}