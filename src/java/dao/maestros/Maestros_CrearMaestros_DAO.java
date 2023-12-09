
package dao.maestros;

import ManageBean.Maestros.Maestros_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import dao.actividadextraesscolar.ActividadExtraescolar_CrearActividad_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Maestros_CrearMaestros_DAO {
    
     public static void insertar(Maestros_MB maestro, GenericResponse respuesta){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        System.out.println(maestro.getDireccion()+"dao");
        try {
            if (conn != null) {
                String query= "INSERT INTO maestros( nombre, apPaterno, apMaterno, fecha_nacimiento,correo,telefono,RFC,CURP,banco,clave,Sexo,direccion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                
                ps = conn.prepareStatement(query);
                ps.setString(1, maestro.getNombre());               
                ps.setString(2,maestro.getApPaterno());
                ps.setString(3, maestro.getApMaterno()); 
                ps.setDate(4, new Date(maestro.getFecha_nacimiento().getTime()));
                ps.setString(5,maestro.getCorreo());
                ps.setString(6,maestro.getTelefono());
                ps.setString(7, maestro.getRfc());
                ps.setString(8, maestro.getCurp());
                System.out.println(maestro.getCurp());
                ps.setString(9, maestro.getBanco());
                ps.setString(10, maestro.getClave());
                ps.setString(11, maestro.getSexo());
                ps.setString(12,maestro.getDireccion());
                ps.executeUpdate();
                
                if (!conn.isClosed()) {
                    conn.close();
                }
                
                respuesta.setMensaje("Ok");
                System.out.println(respuesta.getMensaje());                
                respuesta.setStatus(Validaciones.VALIDATION_EXP);
                System.out.println(respuesta.getStatus());
                respuesta.setResponseObject(null);
            }
        } catch (SQLException ex) {
            respuesta.setStatus(Validaciones.VALIDATION_ERROR);
            
            respuesta.setMensaje(ex.toString());
            System.out.println(respuesta.getMensaje());
            respuesta.setResponseObject(null);
        } finally{
            try {
                if (conn!= null && !conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ActividadExtraescolar_CrearActividad_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
