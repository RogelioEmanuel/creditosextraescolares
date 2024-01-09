/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import masterDao.conexion;

/**
 *
 * @author Emanuel
 */
public class EmpleadoDao {
    public static Empleado obtenerDatos(String token) {
        Empleado empleado = null;
        conexion conne = new conexion();

        try {
            Connection con = conne.getConnection();
            String sql = "select rol.crear as Crear, rol.leer as Leer, rol.editar as Editar, rol.eliminar as eliminar, empleado.id_empleado as id_empleado, empleado.nombre as Nombre, empleado.apellidoPa as Apellido_Paterno, empleado.apellidoMa as Apellido_Materno, puest.nombre Nombre_Puesto"
                    + " from sesiones sesion, permisos permiso, paquete_roles rol, empleados empleado, puesto puest"
                    + " where empleado.id_empleado = sesion.id_empleado "
                    + "and puest.id_puesto = empleado.id_puesto "
                    + "and sesion.id_permiso = permiso.id_permiso "
                    + "and rol.id_paquete = permiso.id_paquete  "
                    + "and sesion.token = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int crear = rs.getInt("Crear");
                int leer = rs.getInt("Leer");
                int editar = rs.getInt("Editar");
                int eliminar = rs.getInt("Eliminar");
                int idEmpleado = rs.getInt("id_empleado");
                String nombreEmpleado = rs.getString("Nombre");
                String apellidoPaterno = rs.getString("Apellido_Paterno");
                String apellidoMaterno = rs.getString("Apellido_Materno");
                String nombrePuesto = rs.getString("Nombre_Puesto");

                // Creamos el objeto Empleado con los valores de la consulta
                empleado = new Empleado(crear, leer, editar, eliminar, idEmpleado, nombreEmpleado, apellidoPaterno, apellidoMaterno, nombrePuesto);

            }

            conne.cerrarConexion(con);
            return empleado;

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
    
   
    
    public static String getPuesto(Empleado empleado){
        String puesto = "";
        PreparedStatement ps=null;
        ResultSet rs = null;
        conexion conne = new conexion();

        try {
            Connection con = conne.getConnection();
            String sql = "SELECT p.nombre from empleados e JOIN puesto p ON e.id_puesto = p.id_puesto WHERE e.id_empleado = ?;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, empleado.getIdEmpleado());
            rs = ps.executeQuery();

            if (rs.next()) {
                puesto = rs.getString("p.nombre");

            }
            conne.cerrarConexion(con);
            if(rs!=null){
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        
        return puesto;
    }
    
    public static String getCorreo(Empleado empleado){
        String correo = "";
        PreparedStatement ps=null;
        ResultSet rs = null;
        conexion conne = new conexion();

        try {
            Connection con = conne.getConnection();
            String sql = "SELECT correo FROM empleados WHERE id_empleado = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, empleado.getNombrePuesto());
            rs = ps.executeQuery();

            if (rs.next()) {
                correo = rs.getString("correo");

            }
            conne.cerrarConexion(con);
            if(rs!=null){
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        
        return correo;
    }

    
}
