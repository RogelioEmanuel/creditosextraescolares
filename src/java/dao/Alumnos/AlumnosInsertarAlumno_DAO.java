package dao.Alumnos;

import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.Grupos.Grupos_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import dao.gruposyalumno.GruposAlumno_Inscripcion_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AlumnosInsertarAlumno_DAO {
    
    public static Alumnos_MB consultarAlumno(String nocontrol) {
        
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Alumnos_MB alumnos = null;
        
           
        try {
            if (conn != null) {
                    String query = "SELECT A.Nocontrol ,A.nombre,  A.semestre,  A.edad, A.regular, A.correo, A.sexo,A.carrera \n"
                        + "FROM alumnos A \n"                        
                        + "where A.Nocontrol=? "  ;
                        
                        
                ps = conn.prepareStatement(query);
                ps.setString(1,nocontrol );               
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

        return alumnos;
        
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
        String selectivo = rs.getString("selectivo");
        Alumnos_MB alumno = new Alumnos_MB( noControl, nombre,  semestre,  edad,  correo,  sexo,  carrera,isRegular(regular),noReinscripcion);          
        alumno.setNoReinscripcion(noReinscripcion);
        alumno.setSelectivo(selectivo);
        return alumno;
    } 
    
     private static boolean isRegular(int regular){
        return regular==0;
        
    }
    
    public static void inscribir(Alumnos_MB alumno,GenericResponse respuesta){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        int var=0;
        try {
            if (conn != null) {
                String query= "INSERT INTO alumnos( Nocontrol ,nombre,apPaterno, apMaterno,edad,regular,correo,sexo,carrera)"
                        + " VALUES (?,?,?,?,?,?,?,?,?)";
                
                ps = conn.prepareStatement(query);
                ps.setString(1,alumno.getNoControl());
                ps.setString(2,alumno.getNombre());
                ps.setString(3,alumno.getApPaterno());
                ps.setString(4,alumno.getApMaterno());
                ps.setInt(5,alumno.getEdad());
                if(!alumno.getRegular()){
                    var=1;
                }
                ps.setInt(6, var);
                ps.setString(7,alumno.getCorreo());
                ps.setString(8,alumno.getSexo());
                ps.setString(9,alumno.getCarrera());               
                               
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
                Logger.getLogger(GruposAlumno_Inscripcion_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void actualizar(Alumnos_MB alumno, GenericResponse respuesta) {
         ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
            
        int var=0;

        PreparedStatement ps = null;
        try {
            if (conn != null) {
                String update =" UPDATE grupos SET nombre=?, nombre=?, apMaterno=?, edad=?, regular=?, correo=?, sexo=?, carrera=?, fecha_creacion=?"
                        + "WHERE nocontrolalumno=?";
                ps = conn.prepareStatement(update);
                ps.setString(1,alumno.getNoControl());
                ps.setString(2,alumno.getNombre());
                ps.setString(3,alumno.getApPaterno());
                ps.setString(4,alumno.getApMaterno());
                ps.setInt(5,alumno.getEdad());
                if(!alumno.getRegular()){
                    var=1;
                }
                ps.setInt(6, var);
                ps.setString(7,alumno.getCorreo());
                ps.setString(8,alumno.getSexo());
                ps.setString(9,alumno.getCarrera());
                ps.setString(10,alumno.getNoControl());
                ps.executeUpdate();
                respuesta.setMensaje("Ok");
                respuesta.setStatus(Validaciones.VALIDATION_EXP);
                respuesta.setResponseObject(null);
            }
        } catch (SQLException ex) {
            respuesta.setStatus(Validaciones.VALIDATION_ERROR);
            respuesta.setMensaje(ex.toString());
            respuesta.setResponseObject(null);
            Logger.getLogger(AlumnosInsertarAlumno_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(AlumnosInsertarAlumno_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Alumnos_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    
}
