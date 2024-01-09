package dao.gruposyalumno;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.HorariosGrupo.HorariosGrupo_MB;
import ManageBean.Maestros.Maestros_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import dao.Grupos.Grupos_EditarGrupo_DAO;
import static dao.Grupos.Grupos_ListarGrupos_DAO.consultarMaestro;
import dao.actividadextraesscolar.ActividadExtraescolar_EditarActividad_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GruposAlumno_Inscripcion_DAO {
    
    
    private static Grupos_MB convertir(ResultSet rs) throws SQLException {
        int idGrupo = rs.getInt("idGrupo");        
        int totalhorassemanal = rs.getInt("totalhorassemanal"); 
        int noGrupo = rs.getInt("noGrupo");
        int cupo = rs.getInt("cupo"); 
        int idActividad = rs.getInt("idActividad_extraescolar"); 
        int idMaestros = rs.getInt("idMaestros"); 
        String periodo = rs.getString("periodo");
        String nombre;
        Grupos_MB grupo = new Grupos_MB();  
        ActividadExtraescolar_MB ac = ActividadExtraescolar_EditarActividad_DAO.consultar(idActividad);
        nombre = ac.getNombre();
        
        
        grupo.setCupo(cupo);
        
        grupo.setIdActividad(idActividad);
        grupo.setIdGrupo(idGrupo);
        grupo.setIdMaestros(idMaestros);
        grupo.setPeriodo(periodo);
        grupo.setNoGrupo(noGrupo);
        grupo.setTotalhorassemanales(totalhorassemanal);
        grupo.setNombreActividad(periodo);
        grupo.setNombreActividad(nombre);
        Maestros_MB mas= new Maestros_MB();
        
        mas = consultarMaestro(idMaestros);
        int a= mas.getIdMaestros();               
        
         if(grupo.getIdMaestros()==0){
             grupo.setNombreMaestro("Pendiente de contratar");
         }else{
             grupo.setNombreMaestro(mas.getNombre()+" "+mas.getApPaterno());
         }
        return grupo;
        
        
    }
    
    
    private static boolean confirma(Grupos_MB prueba){
        
        return consultarcuenta(prueba.getIdGrupo())<prueba.getCupo();
    }
    
    public static List<Grupos_MB> consultar() {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Grupos_MB> grupos = new ArrayList<>();
        
         
  
        try {
            if (conn != null) {
                String query = "SELECT idGrupo,noGrupo,  cupo,  idActividad_extraescolar, idMaestros, periodo, totalhorassemanal \n"
                        + "FROM Grupos  ";
                        
                ps = conn.prepareStatement(query);                
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    if(confirma(convertir(rs))){
                        grupos.add(convertir(rs));
                        
                    }
                                       
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
    
    public static int consultarcuenta(int id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Grupos_MB> grupos = new ArrayList<>();
        int contador=0;
         
            
  
        try {
            if (conn != null) {
                String query = "SELECT idGrupo,nocontrolalumno,  selectivo,  noReinscripcion \n"
                        + "FROM grupos_y_alumno "
                        + "where idGrupo = ?" ;
                        
                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    contador++;
                    
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
        
        return contador;
    }
    
    public static List<HorariosGrupo_MB> consultarhorario(int Grupo) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<HorariosGrupo_MB> horario = new ArrayList<>();
        
        
  
        try {
            if (conn != null) {
                String query = "SELECT  idHorarioGrupo, idGrupo, Dia, HoraInicio, HoraFinal \n"
                        + "FROM horariosgrupo \n"                        
                        + "WHERE idGrupo = ? ";
                ps = conn.prepareStatement(query);
                ps.setInt(1,Grupo );
                //ps.setString(2,dia );
                rs = ps.executeQuery();
                while (rs.next()) {
                    horario.add(convertirhorario(rs));
                      
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HorariosGrupo_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HorariosGrupo_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HorariosGrupo_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return horario;
    }
    
    private static HorariosGrupo_MB convertirhorario(ResultSet rs) throws SQLException {
        int idGrupo = rs.getInt("idGrupo");
        int idHorarioGrupo = rs.getInt("idHorarioGrupo");
        String dia = rs.getString("Dia");
        String horaInicio = rs.getString("HoraInicio");
        String horaFinal = rs.getString("HoraFinal");
        
       HorariosGrupo_MB grupo = new HorariosGrupo_MB( idHorarioGrupo, idGrupo,dia,horaInicio,horaFinal );          
        
        
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
                    grupo = convertir(rs);
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
    
    public static void inscribir(Alumnos_MB alumno, Grupos_MB grupo,GenericResponse respuesta, int nReinscripcion){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
                
        try {
            if (conn != null) {
                String query= "INSERT INTO grupos_y_alumno( idgrupo,nocontrolalumno,selectivo, noReinscripcion)"
                        + " VALUES (?,?,?,?)";
                
                ps = conn.prepareStatement(query);
                ps.setInt(1, grupo.getIdGrupo() ); 
                ps.setString(2,alumno.getNoControl());
                ps.setString(3, "No");
                ps.setInt(4,nReinscripcion);
                
                               
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
    
    public static void inscribir(Alumnos_MB alumno, Grupos_MB grupo,int nReinscripcion,String selectivo,GenericResponse respuesta){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
                
        try {
            if (conn != null) {
                String query= "INSERT INTO grupos_y_alumno( idgrupo,nocontrolalumno,selectivo, noReinscripcion)"
                        + " VALUES (?,?,?,?)";
                
                ps = conn.prepareStatement(query);
                ps.setInt(1, grupo.getIdGrupo() ); 
                ps.setString(2,alumno.getNoControl());
                ps.setString(3, selectivo);
                ps.setInt(4,nReinscripcion);
                
                               
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
    
    public static Alumnos_MB consultarAlumno(String noControl) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();

        PreparedStatement ps = null;
        ResultSet rs = null;
       Alumnos_MB alumno = new Alumnos_MB();

        try {
            if (conn != null) {
                String query = "SELECT Nocontrol ,nombre, semestre, edad, regular, correo, sexo,carrera \n"
                        + "FROM alumnos f \n"
                        + "WHERE Nocontrol = ?";
                
                ps = conn.prepareStatement(query);
                ps.setString(1,noControl );
                rs = ps.executeQuery();
                while (rs.next()) {
                    alumno = convertirAl(rs);
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
        return alumno;
    }
        
    private static Alumnos_MB convertirAl(ResultSet rs) throws SQLException {
        String noControl = rs.getString("Nocontrol");
        String nombre =rs.getString("nombre");
        int semestre = rs.getInt("semestre");
        int edad = rs.getInt("edad");
        int regular = rs.getInt("regular");
        boolean si=false;
        String correo = rs.getString("correo");
        String sexo = rs.getString("sexo");
        String carrera = rs.getString("carrera");
        if(regular == 1){
            si= true;
        }
        
        Alumnos_MB grupo = new Alumnos_MB(noControl,nombre,semestre,edad,correo,sexo,carrera,si);  
        
        
        return grupo;
        
        
    }
    
    
    public static boolean estaInscrito(Alumnos_MB alumno, Grupos_MB grupo,GenericResponse respuesta){
        
         ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        boolean esta=false;
        PreparedStatement ps = null;
        ResultSet rs = null;
       

        try {
            if (conn != null) {
                String query = "SELECT idGrupo,nocontrolalumno  \n"
                        + "FROM grupos_y_alumno  \n"
                        + "WHERE idGrupo = ? AND nocontrolalumno=?";
                
                ps = conn.prepareStatement(query);
                ps.setInt(1,grupo.getIdGrupo() );
                ps.setString(2,alumno.getNoControl() );
                rs = ps.executeQuery();
                while (rs.next()) {
                    esta=true;
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
        return esta;
        
    }
    
     public static boolean estaInscrito2(Alumnos_MB alumno, GenericResponse respuesta){
        
         ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        boolean esta=false;
        PreparedStatement ps = null;
        ResultSet rs = null;
       

        try {
            if (conn != null) {
                String query = "SELECT idGrupo,nocontrolalumno  \n"
                        + "FROM grupos_y_alumno  \n"
                        + "WHERE nocontrolalumno=?";
                
                ps = conn.prepareStatement(query);
                
                ps.setString(1,alumno.getNoControl() );
                rs = ps.executeQuery();
                while (rs.next()) {
                    esta=true;
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
        return esta;
        
    }

    
}
