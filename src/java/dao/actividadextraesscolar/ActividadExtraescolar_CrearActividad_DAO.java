package dao.actividadextraesscolar;

/**
 *
 * @author Emanuel
 */
import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActividadExtraescolar_CrearActividad_DAO {
    
    
    public static void insertar(ActividadExtraescolar_MB actividad, GenericResponse respuesta){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        
        try {
            if (conn != null) {
                String query= "INSERT INTO actividad_extraescolar( nombre, tipo, status, descripcion) VALUES (?,?,?,?)";
                
                ps = conn.prepareStatement(query);
                ps.setString(1, actividad.getNombre());               
                ps.setString(4, actividad.getTipo());         
                ps.setInt(3, actividad.getStatus());
                ps.setString(2, actividad.getDescripcion());                
                ps.executeUpdate();
                if (!conn.isClosed()) {
                    conn.close();
                }
                
                respuesta.setMensaje("Ok");
                System.out.println(respuesta.getMensaje());
                //System.out.println(actividad.getNombre());
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
