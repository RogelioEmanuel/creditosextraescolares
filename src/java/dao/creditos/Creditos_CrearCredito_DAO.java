package dao.creditos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.CreditoExtraescolar.CreditoExtraescolar_MB;
import ManageBean.Grupos.Grupos_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import dao.Grupos.Grupos_CrearGrupo_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Creditos_CrearCredito_DAO {
    
    
    
    public static void insertar(Alumnos_MB alumno, Grupos_MB grupo,  GenericResponse respuesta){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        LocalDate fechaActual = LocalDate.now();
        int anio= fechaActual.getYear();;
        try {
            if (conn != null) {
                String query= "INSERT into creditosextraescolares  (periodo,estado, tipo, noControlAlumno ,Anio,NombreActividad,idGrupo,nombreAlumno)"
                        + " VALUES (?,?,?,?,?,?,?,?)";
                
                ps = conn.prepareStatement(query);
                ps.setString(1,grupo.getPeriodo()); 
                ps.setString(2, "En Proceso");
                ps.setString(3,Constantes.NOMBRECREDITONORMAL);
                ps.setString(4,alumno.getNoControl());
                ps.setInt(5, anio);
                ps.setString(6,grupo.getNombreActividad());                               
                ps.setInt(7,grupo.getIdGrupo() );
                ps.setString(8,alumno.getNombre());
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
                Logger.getLogger(Grupos_CrearGrupo_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Confirmacion si existe o no
            
    public static boolean consultarCredito(String id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        CreditoExtraescolar_MB creditos = new CreditoExtraescolar_MB();
        
        boolean si=false;
  
        try {
            if (conn != null) {
                String query = "SELECT idCreditoExtraescolar, periodo, estado, tipo, noControlAlumno, Anio, "
                        + "NombreActividad, idGrupo, fecha_creacion, nombreAlumno FROM creditosextraescolares"
                        + "WHERE noControlAlumno =?";
                ps = conn.prepareStatement(query);
                ps.setString(1,id );
                rs = ps.executeQuery();
                while (rs.next()) {
                    //creditos=convertirCredito(rs);
                    si=true;
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return si;
    }
        
    public static boolean consultarCreditoHist(String id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        CreditoExtraescolar_MB creditos = new CreditoExtraescolar_MB();
        
        boolean si=false;
  
        try {
            if (conn != null) {
                String query = "SELECT nocontrolAlumno, nombreactividad FROM registrohistorico"
                        + "WHERE nocontrolAlumno=?";
                ps = conn.prepareStatement(query);
                ps.setString(1,id );
                rs = ps.executeQuery();
                while (rs.next()) {
                    //creditos=convertirCredito(rs);
                    si=true;
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return si;
    }
    
    public static boolean existe(String noControl){
        
        return consultarCredito(noControl)||consultarCreditoHist(noControl);
        
    }
    
    
    
    //Selectivo 
    
    public static void insertarSel(Alumnos_MB alumno, Grupos_MB grupo,  GenericResponse respuesta){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        LocalDate fechaActual = LocalDate.now();
        int anio= fechaActual.getYear();;
        try {
            if (conn != null) {
                String query= "INSERT into creditosextraescolares  (periodo,estado, tipo, noControlAlumno ,Anio,NombreActividad,idGrupo,nombreAlumno)"
                        + " VALUES (?,?,?,?,?,?,?,?)";
                
                ps = conn.prepareStatement(query);
                ps.setString(1,grupo.getPeriodo()); 
                ps.setString(2, "En Proceso");
                ps.setString(3,Constantes.NOMBRECREDITOSELECTIVO);
                ps.setString(4,alumno.getNoControl());
                ps.setInt(5, anio);
                ps.setString(6,grupo.getNombreActividad());                               
                ps.setInt(7,grupo.getIdGrupo() );
                ps.setString(8,alumno.getNombre());
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
                Logger.getLogger(Grupos_CrearGrupo_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static boolean consultarCreditoSel(String id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        CreditoExtraescolar_MB creditos = new CreditoExtraescolar_MB();
        
        boolean si=false;
  
        try {
            if (conn != null) {
                String query = "SELECT idCreditoExtraescolar, periodo, estado, tipo, noControlAlumno, Anio, "
                        + "NombreActividad, idGrupo, fecha_creacion, nombreAlumno FROM creditosextraescolares"
                        + "WHERE noControlAlumno =? AND tipo= ?";
                ps = conn.prepareStatement(query);
                ps.setString(1,id );
                ps.setString(2,Constantes.NOMBRECREDITOSELECTIVO);
                rs = ps.executeQuery();
                while (rs.next()) {
                    //creditos=convertirCredito(rs);
                    si=true;
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return si;
    }
        
    public static boolean consultarCreditoHistSel(String id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        CreditoExtraescolar_MB creditos = new CreditoExtraescolar_MB();
        
        boolean si=false;
  
        try {
            if (conn != null) {
                String query = "SELECT nocontrolAlumno, nombreactividad FROM registrohistorico"
                        + "WHERE nocontrolAlumno=? AND tipo=?";
                ps = conn.prepareStatement(query);
                ps.setString(1,id );
                ps.setString(2,Constantes.NOMBRECREDITOSELECTIVO);
                rs = ps.executeQuery();
                while (rs.next()) {
                    //creditos=convertirCredito(rs);
                    si=true;
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return si;
    }
    
    public static boolean existeSel(String noControl){
        
        return consultarCreditoSel(noControl)||consultarCreditoHistSel(noControl);
        
    }
    
}
