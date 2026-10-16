package proyectopersonalizado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class PersonaDAO {

    /*
     Método para consultar todos los registros y cargarlos en la tabla.
     */
    public void consultar(DefaultTableModel modeloTabla) {
        try (Connection cnx = ConexionBD.conectar();
             PreparedStatement ps = cnx.prepareStatement("select * from datos_personales");
             ResultSet rs = ps.executeQuery()) {

            modeloTabla.setRowCount(0);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String rfc = rs.getString("RFC");

                modeloTabla.addRow(new Object[]{id, nombre, direccion, telefono, correo, rfc});
            }
            System.out.println("Consulta exitosa");

        } catch (SQLException ex) {
            System.out.println("ERROR al consultar: " + ex.getMessage());
        }
    }

    /*
    Método para agregar un nuevo registro a la base de datos.
     */
    public void agregar(String nombre, String direccion, String telefono, String correo, String rfc) {
        if (nombre.trim().isEmpty() || direccion.trim().isEmpty()) {
            return; 
        }

        try (Connection cnx = ConexionBD.conectar();
             PreparedStatement ps = cnx.prepareStatement("insert into datos_personales(nombre, direccion, telefono, correo, RFC) values(?, ?, ?, ?, ?)")) {
            
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, telefono);
            ps.setString(4, correo);
            ps.setString(5, rfc);
            
            ps.execute();
            System.out.println("Registro agregado");

        } catch (SQLException ex) {
            System.out.println("Error al agregar: " + ex.getMessage());
        }
    }

    /*
    Método para actualizar un registro existente por su ID.
     */
    public void actualizar(int id, String nombre, String direccion, String telefono, String correo, String rfc) {
        try (Connection cnx = ConexionBD.conectar();
             PreparedStatement ps = cnx.prepareStatement("update datos_personales set nombre = ?, direccion = ?, telefono = ?, correo = ?, RFC = ? where id = ?")) {
            
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, telefono);
            ps.setString(4, correo);
            ps.setString(5, rfc);
            ps.setInt(6, id);
            
            ps.executeUpdate();
            System.out.println("Registro actualizado correctamente");

        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        }
    }

    /*
    Método para eliminar un registro por su ID.
     */
    public void eliminar(int id) {
        try (Connection cnx = ConexionBD.conectar();
             PreparedStatement ps = cnx.prepareStatement("DELETE FROM datos_personales WHERE id = ?")) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Registro eliminado de la BD");

        } catch (SQLException ex) {
            System.out.println("Error de SQL al eliminar: " + ex.getMessage());
        }
    }
}