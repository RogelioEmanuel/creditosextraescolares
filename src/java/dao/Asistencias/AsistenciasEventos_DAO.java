package dao.Asistencias;

import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.Eventos.Evento_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.Maestros.Maestros_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import static dao.Asistencias.AsistenciasRegistrar_DAO.diaHoy;
import static dao.Asistencias.AsistenciasRegistrar_DAO.getClaseCreado;
import static dao.Asistencias.AsistenciasRegistrar_DAO.mesS;
import dao.maestros.Maestros_EditarMaestros_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AsistenciasEventos_DAO {
    
    
    public static void obtSex(String noControl,int  idEvento, GenericResponse resp){
        
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Alumnos_MB alumnos = new Alumnos_MB();
        Evento_MB eve= new Evento_MB();
        eve.setIdEvento(idEvento);
        //GenericResponse resp
        try {
            if (conn != null) {
                    String query = "SELECT Nocontrol, nombre, semestre, edad, regular, correo, sexo "
                                    + "FROM alumnos "
                                    + "WHERE Nocontrol = ?";

                        
                ps = conn.prepareStatement(query);
                ps.setString(1,noControl );
                
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    alumnos =convertirAlumno(rs);
                    
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if(alumnos.getSexo().equals("M")){
            actualizarH(eve,resp);
        }else{
            actualizarM(eve,resp);
        }
        
        
        
    }
    
    
    private static Alumnos_MB convertirAlumno(ResultSet rs) throws SQLException {
        String noControl = rs.getString("Nocontrol");
        String sexo = rs.getString("sexo");
        
        Alumnos_MB alumno = new Alumnos_MB( );    
        alumno.setNoControl(noControl);
        alumno.setSexo(sexo);
        return alumno;
    } 
    
   
    
    
    public static void actualizarH(Evento_MB evento, GenericResponse respuesta){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        java.sql.Date fecha = null;
        
        if (evento.getFecha() != null) {
            fecha = new java.sql.Date(evento.getFecha().getTime());
        }
       

       

        PreparedStatement ps = null;
        try {
            if (conn != null) {
                String update = "UPDATE eventos SET numParticipantesHTec = numParticipantesHTec + 1 "
                                + "WHERE idEventos = ?";

                ps = conn.prepareStatement(update);
                
                ps.setInt(1, evento.getIdEvento());
                ps.executeUpdate();

                respuesta.setMensaje("Ok");
                respuesta.setStatus(Validaciones.VALIDATION_EXP);
                respuesta.setResponseObject(null);
            }
        } catch (SQLException ex) {
            respuesta.setStatus(Validaciones.VALIDATION_ERROR);
            respuesta.setMensaje(ex.toString());
            respuesta.setResponseObject(null);
            Logger.getLogger(Maestros_EditarMaestros_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Maestros_EditarMaestros_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Maestros_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void actualizarM(Evento_MB evento, GenericResponse respuesta){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        java.sql.Date fecha = null;
        
        if (evento.getFecha() != null) {
            fecha = new java.sql.Date(evento.getFecha().getTime());
        }
       

       

        PreparedStatement ps = null;
        try {
            if (conn != null) {
                String update = "UPDATE eventos SET numParticipantesMTec = numParticipantesMTec + 1 "
                                + "WHERE idEventos = ?";

                ps = conn.prepareStatement(update);
                
                ps.setInt(1, evento.getIdEvento());
                ps.executeUpdate();

                respuesta.setMensaje("Ok");
                respuesta.setStatus(Validaciones.VALIDATION_EXP);
                respuesta.setResponseObject(null);
            }
        } catch (SQLException ex) {
            respuesta.setStatus(Validaciones.VALIDATION_ERROR);
            respuesta.setMensaje(ex.toString());
            respuesta.setResponseObject(null);
            Logger.getLogger(Maestros_EditarMaestros_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Maestros_EditarMaestros_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Maestros_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    public static void registrarAsistenciaEvento(String noControl,int idEvento, GenericResponse respuesta){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        PreparedStatement ps2=null;
        ResultSet rs = null;
        
        
        try {
            if (conn != null) {
                String insert = "insert into asistenciaeventos (idEvento, noControl)"
                        + " values(?,?)";
                ps = conn.prepareStatement(insert);
                ps.setInt(1,idEvento);
                ps.setString(2, noControl);                                
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
    
    public static boolean yaPaso(String noControl, int idEvento){
        boolean a=false;
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Alumnos_MB alumnos = new Alumnos_MB();
        Evento_MB eve= new Evento_MB();
        eve.setIdEvento(idEvento);
        //GenericResponse resp
        try {
            if (conn != null) {
                    String query = "SELECT idEvento, noControl "
                                    + "FROM asistenciaeventos "
                                    + "WHERE noControl = ? AND idEvento = ? ";

                        
                ps = conn.prepareStatement(query);
                ps.setString(1,noControl );
                ps.setInt(1,idEvento );
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    //alumnos =convertirAlumno(rs);
                    a=true;
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return a;
    }
    
    
    public static boolean hayEvento(int idEvento){
        boolean a=false;
       
        // Obtener el día de hoy
       
        
         ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
           
        try {
            if (conn != null) {
                String query = "SELECT * FROM eventos WHERE idEventos = ? AND fecha = CURRENT_DATE";
                        
                ps = conn.prepareStatement(query);                
                ps.setInt(1,idEvento );                
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    //alumnos.add(convertirAlumno(rs));
                    a=true;
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        

        return  a;
         }
        
            
    }
    
    public static boolean inscrito(String noControl){
        boolean a=false;
        
        
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
           
        try {
            if (conn != null) {
                   String query = "SELECT DISTINCT a.idActividad_Extraescolar, a.nombre AS Nombre_Actividad, ga.idgruposyalumno, g.idGrupo "
                       + "FROM actividad_extraescolar a "
                       + "JOIN eventos e ON a.idActividad_Extraescolar = e.idActividad "
                       + "JOIN grupos g ON a.idActividad_Extraescolar = g.idActividad_extraescolar "
                       + "JOIN grupos_y_alumno ga ON g.idGrupo = ga.idgrupo "
                       + "WHERE ga.nocontrolalumno = ?";

                        
                ps = conn.prepareStatement(query);                
                ps.setString(1,noControl );
                
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    //alumnos.add(convertirAlumno(rs));
                    a=true;
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        
        
        return  a;
    }
    
}
