
package dao.Asistencias;

import ManageBean.Alumnos.Alumnos_MB;
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
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Emanuel
 */
public class AsistenciasRegistrar_DAO {
    public static String diaHoy(){
      LocalDate fechaActual = LocalDate.now();  
      int diaActual = fechaActual.getDayOfMonth();
      DayOfWeek diaSemana = fechaActual.getDayOfWeek();
      System.out.println(diaSemana);
      String dia = diaSemana.toString();
      String diaSemanaEnEspanol = diaSemana.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
      System.out.println(diaSemanaEnEspanol);
      
      return diaSemanaEnEspanol;
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
    
    public static boolean esenhora(String hinicio,String hfin){
        boolean a= false;
        LocalTime horaActual = LocalTime.now();
        LocalTime horaInicio = LocalTime.parse(hinicio);
        LocalTime horaFin = LocalTime.parse(hfin);
        if (horaActual.isAfter(horaInicio) && horaActual.isBefore(horaFin)) {
            a=true;
        } 
        
        return a;
    }
    
    public static boolean hayClase(int idGrupo){
        boolean a=  false;
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        PreparedStatement ps2=null;
        ResultSet rs = null;
        
        
        
        try {
            if(conn != null){
                String query = "SELECT idGrupo, dia,HoraInicio,HoraFinal  FROM horariosgrupo WHERE idGrupo=? AND dia=?;";
                ps2 = conn.prepareStatement(query);
                ps2.setInt(1,idGrupo );                
                ps2.setString(2, diaHoy());
                
                rs = ps2.executeQuery();
                while(rs.next()){
                    System.out.println("Dia ok");
                    if(esenhora(rs.getString("HoraInicio"),rs.getString("HoraFinal"))){
                       a=true; 
                    }
                    
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

        
        return a;
    }
    
    public static boolean inscrito(String qr, int id){
        boolean a=false;
        
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
           
        try {
            if (conn != null) {
                   String query = "SELECT A.Nocontrol, A.nombre, A.semestre, A.edad, A.regular, A.correo, A.sexo, A.carrera, GA.noReinscripcion \n"
                                    + "FROM alumnos A \n"
                                    + "JOIN grupos_y_alumno GA ON A.Nocontrol = GA.nocontrolalumno \n"
                                    + "WHERE GA.idgrupo = ? AND GA.nocontrolalumno = ?";

                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                ps.setString(2,qr );
                
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

       
        
        
        return a;
        
    }
    
    
     private static Alumnos_MB convertirAlumno(ResultSet rs) throws SQLException {
        String noControl = rs.getString("Nocontrol");
        String nombre =rs.getString("nombre");
        int semestre = rs.getInt("semestre");
        int edad = rs.getInt("edad");
        String correo = rs.getString("correo");
        String sexo = rs.getString("sexo");
        String carrera = rs.getString("carrera"); 
        int regular = rs.getInt("regular");
        int noReinscripcion = rs.getInt("noReinscripcion");            
        
       Alumnos_MB alumno = new Alumnos_MB( noControl, nombre,  semestre,  edad,  correo,  sexo,  carrera,isRegular(regular),noReinscripcion);          
        alumno.setNoReinscripcion(noReinscripcion);
        
        return alumno;
    } 
     
     private static boolean isRegular(int regular){
        return regular==0;
        
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
                String query = "SELECT idClase,mes,dia  FROM asistencia WHERE  idClase=? AND nocontrolAlumno  =?;";
                ps2 = conn.prepareStatement(query);
                ps2.setInt(1,getClaseCreado() );                
                //ps2.setInt(2, getClaseCreado());
                ps2.setString(2,noControl);
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
                ps.setString(3, diaHoy());
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
                String query = "SELECT idGrupo,mes,dia  FROM clases WHERE idGrupo=? AND mes =? AND dia=?;";
                ps2 = conn.prepareStatement(query);
                ps2.setInt(1,idGrupo );                
                ps2.setString(2, diaHoy());
                ps2.setString(3,mesS() );
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
                ps.setString(2, diaHoy());
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
