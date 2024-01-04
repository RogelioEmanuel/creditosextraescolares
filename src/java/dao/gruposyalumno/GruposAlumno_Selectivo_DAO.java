
package dao.gruposyalumno;

import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.Grupos.Grupos_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GruposAlumno_Selectivo_DAO {
    
    
    public static boolean candidatoValido(Alumnos_MB a){        
        return a.getRegular() && a.getEdad()<28;        
    }
    
    public static void actualizarAlumno(Alumnos_MB alumno, Grupos_MB grupo,GenericResponse respuesta) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
            
       

        PreparedStatement ps = null;
        try {
            if (conn != null) {
                String update =" UPDATE grupos_y_alumno SET selectivo=?, noReinscripcion=? "
                        + "WHERE idGrupo=? AND nocontrolalumno=?";
                ps = conn.prepareStatement(update);
                ps.setString(1, alumno.getSelectivo());
                ps.setInt(2,alumno.getNoReinscripcion());
                ps.setInt(3,grupo.getIdGrupo() );   
                ps.setString(4,alumno.getNoControl());
                
                ps.executeUpdate();

                respuesta.setMensaje("Ok");
                respuesta.setStatus(Validaciones.VALIDATION_EXP);
                respuesta.setResponseObject(null);
            }
        } catch (SQLException ex) {
            respuesta.setStatus(Validaciones.VALIDATION_ERROR);
            respuesta.setMensaje(ex.toString());
            respuesta.setResponseObject(null);
            Logger.getLogger(GruposAlumno_Selectivo_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(GruposAlumno_Selectivo_DAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public static Alumnos_MB consultarAlumno(String nocontrol,int id) {
        
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Alumnos_MB alumnos = new Alumnos_MB();
        
           
        try {
            if (conn != null) {
                    String query = "SELECT A.Nocontrol ,A.nombre,  A.semestre,  A.edad, A.regular, A.correo, A.sexo,A.carrera, GA.noReinscripcion, GA.selectivo \n"
                        + "FROM alumnos A \n"
                        + "JOIN grupos_y_alumno GA ON A.Nocontrol= GA.nocontrolalumno \n"
                        + "where A.Nocontrol=? AND GA.idgrupo =?"  ;
                        
                        
                ps = conn.prepareStatement(query);
                ps.setString(1,nocontrol );
                ps.setInt(2,id );
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
}
