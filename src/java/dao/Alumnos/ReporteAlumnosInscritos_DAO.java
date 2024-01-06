package dao.Alumnos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.HorariosGrupo.HorariosGrupo_MB;
import ManageBean.Maestros.Maestros_MB;
import Utilidades.Constantes;
import Utilidades.Cuenta;
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


public class ReporteAlumnosInscritos_DAO {
    
    //Grupo
    
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
    public static Grupos_MB consultar(int idGrupo) {
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
    
    //Horario del grupo
    private static HorariosGrupo_MB convertirhorario(ResultSet rs) throws SQLException {
        int idGrupo = rs.getInt("idGrupo");
        int idHorarioGrupo = rs.getInt("idHorarioGrupo");
        String dia = rs.getString("Dia");
        String horaInicio = rs.getString("HoraInicio");
        String horaFinal = rs.getString("HoraFinal");
        
       HorariosGrupo_MB grupo = new HorariosGrupo_MB( idHorarioGrupo, idGrupo,dia,horaInicio,horaFinal );          
        
        
        return grupo;
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
    
    //Alumnos
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
    public static int consultarAlumnoGrupoHombres(int id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Alumnos_MB> alumnos = new ArrayList<>();
        int cuenta=0;
           
        try {
            if (conn != null) {
                    String query = "SELECT A.Nocontrol ,A.nombre,  A.semestre,  A.edad, A.regular, A.correo, A.sexo,A.carrera, GA.noReinscripcion \n"
                        + "FROM alumnos A \n"
                        + "JOIN grupos_y_alumno GA ON A.Nocontrol= GA.nocontrolalumno \n"
                        + "where GA.idgrupo =? AND A.sexo='M'" ;
                        
                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    alumnos.add(convertirAlumno(rs));
                    cuenta++;
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

        return cuenta;
    }
    public static int consultarAlumnoGrupoMujeres(int id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Alumnos_MB> alumnos = new ArrayList<>();
        int cuenta=0;
           
        try {
            if (conn != null) {
                    String query = "SELECT A.Nocontrol ,A.nombre,  A.semestre,  A.edad, A.regular, A.correo, A.sexo,A.carrera, GA.noReinscripcion \n"
                        + "FROM alumnos A \n"
                        + "JOIN grupos_y_alumno GA ON A.Nocontrol= GA.nocontrolalumno \n"
                        + "where GA.idgrupo =? AND A.sexo='F'"  ;
                        
                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    alumnos.add(convertirAlumno(rs));
                    cuenta++;
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

        return cuenta;
    }    
    public static int consultarAlumnoGrupoSemestre(int id, int semestre, String carrera) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Alumnos_MB> alumnos = new ArrayList<>();
        int cuenta=0;
           
        try {
            if (conn != null) {
                    String query = "SELECT A.Nocontrol ,A.nombre,  A.semestre,  A.edad, A.regular, A.correo, A.sexo,A.carrera, GA.noReinscripcion \n"
                        + "FROM alumnos A \n"
                        + "JOIN grupos_y_alumno GA ON A.Nocontrol= GA.nocontrolalumno \n"
                        + "where GA.idgrupo =? AND A.semestre=? AND A.carrera= ?"  ;
                        
                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                ps.setInt(2,semestre );
                ps.setString(3,carrera );
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    alumnos.add(convertirAlumno(rs));
                    
                    cuenta++;
                    System.out.println(cuenta);
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

        return cuenta;
    }
    public static List<Alumnos_MB> consultarAlumnoGrupo2(int id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Alumnos_MB> alumnos = new ArrayList<>();
        
           
        try {
            if (conn != null) {
                    String query =  "SELECT alumnos.NoControl, alumnos.Nombre, alumnos.Semestre, alumnos.Edad, alumnos.Regular, alumnos.Correo, alumnos.Sexo, alumnos.Carrera, " +
                                "creditosextraescolares.idCreditoExtraescolar, creditosextraescolares.periodo, creditosextraescolares.estado, creditosextraescolares.tipo, creditosextraescolares.noControlAlumno,"
                            +   "creditosextraescolares.Anio, creditosextraescolares.NombreActividad, creditosextraescolares.idGrupo, creditosextraescolares.fecha_creacion, creditosextraescolares.nombreAlumno " +
                                "FROM alumnos " +
                                "JOIN grupos_y_alumno ON alumnos.NoControl = grupos_y_alumno.NoControl " +
                                "JOIN creditosextraescolares ON grupos_y_alumno.dGrupo = creditosextraescolares.idGrupo " +
                                "WHERE grupos_y_alumno.idGrupo = ?";
                        
                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    alumnos.add(convertirAlumno2(rs));
                    
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
    private static Alumnos_MB convertirAlumno2(ResultSet rs) throws SQLException {
        String noControl = rs.getString("Nocontrol");
        String nombre =rs.getString("nombre");
        int semestre = rs.getInt("semestre");
        int edad = rs.getInt("edad");
        String correo = rs.getString("correo");
        String sexo = rs.getString("sexo");
        String carrera = rs.getString("carrera"); 
        int regular = rs.getInt("regular");
        int noReinscripcion = rs.getInt("noReinscripcion");            
        String apr= rs.getString("estadoCredito");
        Alumnos_MB alumno = new Alumnos_MB( noControl, nombre,  semestre,  edad,  correo,  sexo,  carrera,isRegular(regular),noReinscripcion);          
        alumno.setNoReinscripcion(noReinscripcion);
        
        if(apr.equals("Liberado")){
            alumno.setAprovado("A");
        }else if(apr.equals("No liberado")){
            alumno.setAprovado("NA");
        }else{
            alumno.setAprovado(" ");
        }
        
        
        return alumno;
    } 
    private static boolean isRegular(int regular){
        return regular==0;
        
    }
    
    
    
    
    
    
    //Actividad
    
    private static ActividadExtraescolar_MB convertirActividad(ResultSet rs) throws SQLException {
        int idActividad_Extraescolar = rs.getInt("idActividad_Extraescolar");
        String nombre = rs.getString("nombre");
        String tipo = rs.getString("tipo");
        int status = rs.getInt("status");            
        String descripcion = rs.getString("descripcion");
        ActividadExtraescolar_MB actividad = new ActividadExtraescolar_MB();          
        actividad.setIdActividad_Extraescolar(idActividad_Extraescolar);
        actividad.setNombre(nombre);
        actividad.setDescripcion(descripcion);
        actividad.setStatus(status);
        actividad.setTipo(tipo);
        
        
        return actividad;
    }

    public static ActividadExtraescolar_MB consultarActividad(int idActividad) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();

        PreparedStatement ps = null;
        ResultSet rs = null;
        ActividadExtraescolar_MB actividad = new ActividadExtraescolar_MB();

        try {
            if (conn != null) {
                String query = "SELECT idActividad_Extraescolar, nombre, tipo, status, descripcion \n"
                        + "FROM actividad_extraescolar f \n"
                        + "WHERE IdActividad_Extraescolar = ?";
                
                ps = conn.prepareStatement(query);
                ps.setInt(1,idActividad );
                rs = ps.executeQuery();
                while (rs.next()) {
                    actividad = convertirActividad(rs);
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
                Logger.getLogger(ActividadExtraescolar_EditarActividad_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        return actividad;
    }
}
