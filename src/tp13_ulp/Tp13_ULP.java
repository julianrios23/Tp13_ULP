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

         try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
               //alumno 1
                int dni = 30200666;
                String apellido = "Torrez";
                String nombre = "Gabriel";
                String fechaNac = "2000-01-15";
                boolean estado = true;

                preparedStatement.setInt(1, dni);
                preparedStatement.setString(2, apellido);
                preparedStatement.setString(3, nombre);
                preparedStatement.setString(4, fechaNac);
                preparedStatement.setBoolean(5, estado);
                preparedStatement.executeUpdate();

                System.out.println("Se cargo el alumno correctamenet");
            } catch (SQLException e) {
                System.err.println("Error al cargar datos: " + e.getMessage());
            }

    }
}
