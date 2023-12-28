package dao.gruposyalumno;

import ManageBean.Grupos.Grupos_MB;
import ManageBean.HorariosGrupo.HorariosGrupo_MB;
import ManageBean.Maestros.Maestros_MB;
import Utilidades.Constantes;
import config.conexion.ConexionMySQL;
import static dao.Grupos.Grupos_ListarGrupos_DAO.consultarMaestro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
        
        Grupos_MB grupo = new Grupos_MB();  
        
        grupo.setCupo(cupo);
        
        grupo.setIdActividad(idActividad);
        grupo.setIdGrupo(idGrupo);
        grupo.setIdMaestros(idMaestros);
        grupo.setPeriodo(periodo);
        grupo.setNoGrupo(noGrupo);
        grupo.setTotalhorassemanales(totalhorassemanal);
        
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
    
}
