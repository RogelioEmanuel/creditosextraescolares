package dao.gruposyalumno;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.Grupos.Grupos_MB;
import Utilidades.Constantes;
import config.conexion.ConexionMySQL;
import dao.Grupos.Grupos_EditarGrupo_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GruposAlumno_ListaAlumnos_DAO {
    
    
    private static Grupos_MB convertirGrupo(ResultSet rs) throws SQLException {
        int idGrupo = rs.getInt("idGrupo");
        int noGrupo =rs.getInt("noGrupo");
        int cupo = rs.getInt("cupo");
        int idActividad = rs.getInt("idActividad_Extraescolar");
        int idMaestros = rs.getInt("idMaestros");
        String periodo = rs.getString("periodo");
        
        int totalhorassemanal = rs.getInt("totalhorassemanal"); 
        
                    
        
       Grupos_MB grupo = new Grupos_MB( idGrupo, noGrupo,  cupo,  periodo,  idActividad,  idMaestros,  totalhorassemanal);          
        
        
        return grupo;
    }
    
    public static Grupos_MB consultarGrupo(int idGrupo) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();

        PreparedStatement ps = null;
        ResultSet rs = null;
       Grupos_MB grupo = new Grupos_MB();

        try {
            if (conn != null) {
                String query = "SELECT idGrupo,noGrupo, cupo, idActividad_extraescolar, idMaestros, periodo, totalhorassemanal \n"
                        + "FROM grupos f \n"
                        + "WHERE idGrupo = ?";
                
                ps = conn.prepareStatement(query);
                ps.setInt(1,idGrupo );
                rs = ps.executeQuery();
                while (rs.next()) {
                    grupo = convertirGrupo(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Grupos_EditarGrupo_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        return grupo;
    }
    
    public static List<Grupos_MB> consultarGrupos(int id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Grupos_MB> grupos = new ArrayList<>();
        
         
        
  
        try {
            if (conn != null) {
                String query = "SELECT idGrupo,noGrupo,  cupo,  idActividad_extraescolar, idMaestros, periodo, totalhorassemanal \n"
                        + "FROM Grupos  \n"
                        + "where idActividad_extraescolar =?"  ;
                        
                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    grupos.add(convertirGrupo(rs));
                    
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

        return grupos;
    }
    
    public static List<Alumnos_MB> consultarAlumnoGrupo(int id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Alumnos_MB> alumnos = new ArrayList<>();
        
           
        try {
            if (conn != null) {
                    String query = "SELECT A.Nocontrol ,A.nombre,  A.semestre,  A.edad, A.regular, A.correo, A.sexo,A.carrrera, GA.noReinscripcion \n"
                        + "FROM alumnos A \n"
                        + "JOIN grupos_y_alumno GA ON A.Nocontrol= GA.nocontrolalumno \n"
                        + "where GA.idgrupo =?"  ;
                        
                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    alumnos.add(convertirAlumno(rs));
                    
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
        String correo = rs.getString("regular");
        String sexo = rs.getString("periodo");
        String carrera = rs.getString("totalhorassemanal"); 
        int regular = rs.getInt("regular");
        int noReinscripcion = rs.getInt("noReinscripcion");            
        
       Alumnos_MB alumno = new Alumnos_MB( noControl, nombre,  semestre,  edad,  correo,  sexo,  carrera,isRegular(regular),noReinscripcion);          
        
        
        return alumno;
    } 
     
    private static boolean isRegular(int regular){
        return regular==0;
        
    }
     
     
}
