
package dao.maestros;

import ManageBean.Grupos.Grupos_MB;
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
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import masterDAO.Empleado;


public class Maestros_CrearMaestros_DAO {
    
     public static void insertar(Maestros_MB maestro, GenericResponse respuesta){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        int idMaestro=getUsuarioCreado();
        try {
            if (conn != null) {
                String query= "INSERT INTO maestros(idMaestros , nombre, apPaterno, apMaterno, fecha_nacimiento,correo,telefono,RFC,CURP,banco,clave,Sexo,direccion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
                ps = conn.prepareStatement(query);
                ps.setInt(1, idMaestro); 
                ps.setString(2, maestro.getNombre());               
                ps.setString(3,maestro.getApPaterno());
                ps.setString(4, maestro.getApMaterno()); 
                ps.setDate(5, new Date(maestro.getFecha_nacimiento().getTime()));
                ps.setString(6,maestro.getCorreo());
                ps.setString(7,maestro.getTelefono());
                ps.setString(8, maestro.getRfc());
                ps.setString(9, maestro.getCurp());
                
                ps.setString(10, maestro.getBanco());
                ps.setString(11, maestro.getClave());
                ps.setString(12, maestro.getSexo());
                ps.setString(13,maestro.getDireccion());
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
     
     
     public static void insertarUsuario(Empleado empleado, String pass, GenericResponse respuesta){
          ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        
        
        try {
            if (conn != null) {
                String query= "INSERT INTO usuarios( Usuario, nombre_usuario, apellidoPaterno, apellidoMaterno,nombrePuesto,fechaNacimiento,correo,contrasenia,crear,leer,editar,eliminar)"
                        + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                
                ps = conn.prepareStatement(query);
                ps.setString(1, empleado.getNombre());  
                ps.setString(2, empleado.getUsuario());
                ps.setString(3,empleado.getApellidoPaterno());
                ps.setString(4, empleado.getApellidoMaterno());                 
                ps.setString(5,empleado.getNombrePuesto());
                ps.setString(6,empleado.getFechaNac());
                ps.setString(7, empleado.getCorreo());
                ps.setString(8, pass);                
                ps.setInt(9, 1);
                ps.setInt(10, 1);
                ps.setInt(11, 1);
                ps.setInt(12, 1);
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
     
     public static int getUsuarioCreado(){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idUsuario = -1;
        
        try {
            if(conn != null){
                String query = "SELECT idUsuario  FROM usuarios ORDER BY idUsuario DESC LIMIT 1;";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                while(rs.next()){
                    idUsuario = (rs.getInt("idUsuario"));
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
        
        return idUsuario;
    }
    
}
