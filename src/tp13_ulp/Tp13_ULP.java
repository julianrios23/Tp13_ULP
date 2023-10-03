package tp13_ulp;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Julian Development
 */
public class Tp13_ULP {

    public static void main(String[] args) {

        Connection con;
        con = Conexion.getConexion();
        
        
        String sql = "INSERT INTO alumno (dni, apellido, nombre, fechaNacimiento, estado) VALUES (?, ?, ?, ?, ?)";

         try (PreparedStatement ps = con.prepareStatement(sql)) {
               //alumno 1
                int dni = 30200666;
                String apellido = "Torrez";
                String nombre = "Gabriel";
                String fechaNac = "2000-01-15";
                boolean estado = true;

                ps.setInt(1, dni);
                ps.setString(2, apellido);
                ps.setString(3, nombre);
                ps.setString(4, fechaNac);
                ps.setBoolean(5, estado);
                ps.executeUpdate();

                System.out.println("Se cargo el alumno correctamenet");
            } catch (SQLException e) {
                System.err.println("Error al cargar datos: " + e.getMessage());
            }

    }
}
