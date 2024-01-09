
package dao.Asistencias;

import ManageBean.Grupos.Grupos_MB;
import config.conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import java.sql.ResultSet;

/**
 *
 * @author Emanuel
 */
public class AsistenciasRegistrar_DAO {
     public static int diaHoy(){
      LocalDate fechaActual = LocalDate.now();  
      int diaActual = fechaActual.getDayOfMonth();
      return diaActual;
    }
    
    public static int mes(){
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue();
        return mesActual;
    }
    
    public static String mesS(){
        int mes= mes();
        switch(mes){
            case 1:
                return "Enero";
                
            case 2:
               return "Febrero";
            case 3:
                return "Marzo";
            case 4:
               return "Abril";
            case 5:
                return "Mayo";
            case 6:
               return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
               return "Diciembre";
            default:
                return "Algo salio mal";
        }
    }
    
    public static void insertarAsistencia(String noControl,int idGrupo, GenericResponse respuesta) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        PreparedStatement ps2=null;
        ResultSet rs = null;
        boolean a= false;
        
        
        try {
            if(conn != null){
                String query = "SELECT idGrupo,mes,dia  FROM clases WHERE idGrupo=? AND idClase=? AND nocontrolAlumno =?;";
                ps2 = conn.prepareStatement(query);
                ps.setInt(1,idGrupo );                
                ps.setInt(2, getClaseCreado());
                ps.setString(3,noControl);
                rs = ps2.executeQuery();
                while(rs.next()){
                    a=true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!a){
            try {
            if (conn != null) {
                String insert = "insert into asistencia (idClase, nocontrolAlumno ,"
                        + " dia, mes)"
                        + " values(?,?,?,?)";
                ps = conn.prepareStatement(insert);
                ps.setInt(1,getClaseCreado() );
                ps.setString(2, noControl);
                ps.setInt(3, diaHoy());
                ps.setString(4,mesS() );
                
                ps.executeUpdate();
                if (!conn.isClosed()) {
                    conn.close();
                }
                
                respuesta.setMensaje("Ok");
                respuesta.setStatus(Validaciones.VALIDATION_EXP);
                respuesta.setResponseObject(null);
            }
        } catch (SQLException ex) {
            respuesta.setStatus(Validaciones.VALIDATION_ERROR);
            respuesta.setMensaje(ex.toString());
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
                Logger.getLogger(AsistenciasRegistrar_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }

        
    }
    
    public static void insertarClase(int idGrupo,GenericResponse respuesta){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        PreparedStatement ps2=null;
        ResultSet rs = null;
        boolean a= false;
        
        
        try {
            if(conn != null){
                String query = "SELECT idGrupo,mes,dia  FROM clases WHERE idClase=? AND mes =? AND dia=?;";
                ps2 = conn.prepareStatement(query);
                ps.setInt(1,idGrupo );                
                ps.setInt(2, diaHoy());
                ps.setString(3,mesS() );
                rs = ps2.executeQuery();
                while(rs.next()){
                    a=true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        if(!a){
            try {
            if (conn != null) {
                String insert = "insert into clases (idGrupo, "
                        + " dia, mes)"
                        + " values(?,?,?)";
                ps = conn.prepareStatement(insert);
                ps.setInt(1, idGrupo);                
                ps.setInt(2, diaHoy());
                ps.setString(3,mesS() );
                
                ps.executeUpdate();
                if (!conn.isClosed()) {
                    conn.close();
                }
                
                respuesta.setMensaje("Ok");
                respuesta.setStatus(Validaciones.VALIDATION_EXP);
                respuesta.setResponseObject(null);
            }
            } catch (SQLException ex) {
                respuesta.setStatus(Validaciones.VALIDATION_ERROR);
                respuesta.setMensaje(ex.toString());
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
                    Logger.getLogger(AsistenciasRegistrar_DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                        
        }
        
        
    }
    
    public static int getClaseCreado(){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idGrupo = -1;
        
        try {
            if(conn != null){
                String query = "SELECT idClase  FROM clases ORDER BY idClase  DESC LIMIT 1;";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                while(rs.next()){
                    idGrupo = (rs.getInt("idClase"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return idGrupo;
    }
}
