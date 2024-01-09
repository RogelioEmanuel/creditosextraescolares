package dao.Asistencias;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.Asistencia.Asistencias_MB;
import ManageBean.Asistencia.Clases_MB;
import ManageBean.Grupos.Grupos_MB;
import Utilidades.Constantes;
import config.conexion.ConexionMySQL;
import dao.Grupos.Grupos_EditarGrupo_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Asistencias_ListarAsistencias_DAO {
    
    
    public static List<Alumnos_MB> consultarAlumnoGrupo(int id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Alumnos_MB> alumnos = new ArrayList<>();
        
           
        try {
            if (conn != null) {
                    String query = "SELECT A.Nocontrol ,A.nombre,  A.semestre,  A.edad, A.regular, A.correo, A.sexo,A.carrera, GA.noReinscripcion \n"
                        + "FROM alumnos A \n"
                        + "JOIN grupos_y_alumno GA ON A.Nocontrol= GA.nocontrolalumno \n"
                        + "where GA.idgrupo =?"  ;
                        
                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                rs = ps.executeQuery();
                
                
                int x=0;
                while (rs.next()) {
                    alumnos.add(convertirAlumno(rs));
                    //System.out.println("Cuenta " );
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
       
       Alumnos_MB alumno = new Alumnos_MB( noControl, nombre,  semestre,  edad,  correo,  sexo,  carrera,isRegular(regular),noReinscripcion);          
        alumno.setNoReinscripcion(noReinscripcion);
        
        return alumno;
    } 
    
    private static boolean isRegular(int regular){
        return regular==0;
        
    }
    
    //Clases
    public static List<Clases_MB> consultarClases(int id,String aa) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Clases_MB> clase = new ArrayList<>();
        
           
        try {
            if (conn != null) {
                    String query = "SELECT idClase ,idGrupo,  mes,  dia \n"
                        + "FROM clases  \n"                        
                        + "where idGrupo =? AND mes=?"  ;
                        
                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                ps.setString(2,aa );
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    clase.add(convertirClase(rs));
                    
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

        return clase;
    }

    private static Clases_MB convertirClase(ResultSet rs) throws SQLException {
       
        int idClase = rs.getInt("idClase");
        int idGrupo = rs.getInt("idGrupo");
        String mes = rs.getString("mes");
        int dia = rs.getInt("dia");
        
        Clases_MB clase = new Clases_MB(idClase,idGrupo,dia,mes);
        
        return clase;
    }
    
    //Asistencias    
    public static boolean consultarAsistencia(String alumno,int clase,String aa) {
        
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Asistencias_MB> asistencia = new ArrayList<>();
        boolean a= false;
        int cuenta=0;   
        try {
            if (conn != null) {
                    String query = "SELECT idasistencia,idClase ,nocontrolAlumno,  mes,  dia \n"
                        + "FROM asistencia  \n"                        
                        + "where idClase =? AND nocontrolAlumno=? AND mes=?"  ;
                        
                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,clase );
                ps.setString(2,alumno );
                ps.setString(3,aa );
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    asistencia.add(convertirAsistencia(rs));
                    a=true;
                    //cuenta++;
                    //System.out.println(cuenta);
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
    
       
    private static Asistencias_MB convertirAsistencia(ResultSet rs) throws SQLException {
        /*
        int idClase = rs.getInt("idClase");
        int idGrupo = rs.getInt("idGrupo");
        String mes = rs.getString("mes");
        int dia = rs.getInt("dia");
        */
        Asistencias_MB clase = new Asistencias_MB();
        
        return clase;
    }
    
    public static List<String> revisarsiVino(String noControl, List<Clases_MB> clase,String aa){
        List<String> lista = new ArrayList<>();
        List<String> lista2 = Arrays.asList("-", "-", "-", "-");

        if(clase.isEmpty()){
             return lista2;
        }
        for( Clases_MB ab: clase){
            
            if(consultarAsistencia(noControl,ab.getIdClase(),aa)){
                
                lista.add("✓");
                //System.out.println(ab.getMes()+" ✓"+ab.getDia());
            }else{
                lista.add("x");
               // System.out.println("No vino el alumno "+ noControl );
            }
        }
        
        return lista;
        
    }
    
    
    public static List<Integer> clasesFechas(int idGrupo,List<Clases_MB> clase,String aa){
        List<Integer> lista = new ArrayList<>();
        List<Integer> lista2 = Arrays.asList(0, 0, 0, 0);
        if(clase.isEmpty()){
            return lista2;
        }
        
        for(Clases_MB cl:clase){
            lista.add(cl.getDia());
        }
        return lista;
    }
    
    //Grupo
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
            Logger.getLogger(Asistencias_ListarAsistencias_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Asistencias_ListarAsistencias_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
}
