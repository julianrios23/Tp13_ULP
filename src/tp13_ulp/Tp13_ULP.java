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

        /* metodo para insertar alumnos*/
        String sql = "INSERT INTO alumno (dni, apellido, nombre, fechaNacimiento, estado) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            //alumno 1
            int dni = 200;
            String apellido = "Sboaadio";
            String nombre = "a";
            String fechaNac = "2004-10-18";
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
          /* metodo para inserta materias*/
        String sql1 = "INSERT INTO materia (nombre, año, estado) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql1)) {
            //  materia
            ps.setString(1, "sss");
            ps.setInt(2, 2023);
            ps.setBoolean(3, true);
            ps.executeUpdate();

            System.out.println("Se cargo la materia.");
        } catch (SQLException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());

        }

        /* metodo para inscribir*/
        String sql2 = "INSERT INTO inscripcion (nota, idalumno, idmateria) VALUES (?, ?, ?)";

        try (
                PreparedStatement preparedStatement = con.prepareStatement(sql2)) {

            preparedStatement.setInt(1, 8);
            preparedStatement.setInt(2, 4);
            preparedStatement.setInt(3, 1);

            preparedStatement.executeUpdate();

            System.out.println("Inscripcion exitosa.");
        } catch (SQLException e) {
            System.err.println("Error al insertar inscripcion: " + e.getMessage());
        }

        /* metodo para mostar notas*/
        String consulta = "SELECT a.idAlumno, a.dni, a.apellido, a.nombre, i.nota, m.nombre AS nombreMateria "
                + "FROM alumno a "
                + "INNER JOIN inscripcion i ON a.idAlumno = i.idAlumno "
                + "INNER JOIN materia m ON i.idMateria = m.idMateria "
                + "WHERE i.nota >= 8";

        try (PreparedStatement ps = con.prepareStatement(consulta); 
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idAlumno = rs.getInt("idAlumno");
                int dni = rs.getInt("dni");
                String apellido = rs.getString("apellido");
                String nombre = rs.getString("nombre");
                int nota = rs.getInt("nota");
                String nombreMateria = rs.getString("nombreMateria");

                System.out.println("ID Alumno: " + idAlumno + ", DNI: " + dni + ", Apellido: " + apellido + ", Nombre: " + nombre + ", Nota: " + nota + ", Materia: " + nombreMateria);
            }
        } catch (SQLException e) {
            System.out.println("error"+e.getMessage());
        }

         /* metodo eliminar inscrpcion*/

        
        try {
            PreparedStatement ps = null;
            int idAlumno = 1; 
            int idMateria = 2; 
            String consulta1 = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
            ps = con.prepareStatement(consulta1);
                   
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
           
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("desinscripcin ok.");
            } else {
                System.out.println("No hay  registros para desinscribir.");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
