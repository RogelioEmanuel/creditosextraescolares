package dao.eventos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Eventos.Evento_MB;
import Utilidades.Constantes;
import config.conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Eventos_ListarEventos_DAO {
    
    private static Evento_MB convertir(ResultSet rs) throws SQLException {
        int idEvento = rs.getInt("idEventos");
        String nombreEvento = rs.getString("NombreEvento");
        int participantesH = rs.getInt("numParticipantesH");
        int participantesM = rs.getInt("numParticipantesM");
        String institucion=rs.getString("InstitucionOrganizadora");
        String tipoevento=rs.getString("tipoevento");
        String periodo=rs.getString("periodo");       
        Date fecha = rs.getDate("fecha");
        int anio = rs.getInt("anio");
        int idActividad = rs.getInt("idActividad"); 
        String resultado=rs.getString("resultado");
        Evento_MB evento = new Evento_MB();  
        ActividadExtraescolar_MB nombre = consultarActividad(idActividad);
        evento.setIdEvento(idEvento);
        evento.setNombreEvento(nombreEvento);
        evento.setNoParticipantesh(participantesH);
        evento.setNoParticipantesm(participantesM);
        evento.setTotalParticipantes();
        evento.setInstitucionOrganizadora(institucion);
        evento.setTipoEvento(tipoevento);       
        evento.setPeriodo(periodo);
        evento.setAnio(anio);
        evento.setIdActividad(idActividad);
        evento.setNombreActividad(nombre.getNombre());
        evento.setFecha(fecha);
        evento.setResultado(resultado);
        
        return evento;
    }
    
     public static List<Evento_MB> consultar() {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Evento_MB> maestros = new ArrayList<>();
        
        
  
        try {
            if (conn != null) {
                String query = "SELECT idEventos,NombreEvento, numParticipantesH, numParticipantesM, InstitucionOrganizadora, tipoevento, periodo, anio,  idActividad "
                        + ",fecha, resultado\n"
                        + "FROM eventos f \n"                        
                        + "limit 100";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    maestros.add(convertir(rs));
                    
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Evento_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Evento_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Evento_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return maestros;
    }
     
     
     public static List<Evento_MB> consultarEventos(int id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Evento_MB> maestros = new ArrayList<>();
        
        
  
        try {
            if (conn != null) {
                String query = "SELECT eventos.idEventos,eventos.NombreEvento, eventos.numParticipantesH, eventos.numParticipantesM, eventos.InstitucionOrganizadora, "
                        + "eventos.tipoevento, eventos.periodo, eventos.anio,  eventos.idActividad, "
                        + "eventos.fecha, eventos.resultado\n"
                        + "FROM eventos "
                        + "JOIN actividad_extraescolar ON Eventos.idActividad = actividad_extraescolar.idActividad_Extraescolar\n" 
                        + "JOIN grupos ON actividad_extraescolar.idActividad_Extraescolar = grupos.idActividad_Extraescolar\n" 
                        + "JOIN maestros ON grupos.idMaestros = maestros.idMaestros\n" 
                        + "WHERE maestros.idMaestros = ?";
                
                

                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                rs = ps.executeQuery();
                while (rs.next()) {
                    maestros.add(convertir(rs));
                    
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Evento_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Evento_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Evento_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return maestros;
    }
     
     
    public static ActividadExtraescolar_MB consultarActividad(int id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ActividadExtraescolar_MB actividades = new ActividadExtraescolar_MB();
        
        
  
        try {
            if (conn != null) {
                String query = "SELECT nombre \n"
                        + "FROM actividad_extraescolar f \n"                        
                        + "WHERE idActividad_Extraescolar = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                rs = ps.executeQuery();
                while (rs.next()) {
                    actividades=convertirActividad(rs);
                    
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

        return actividades;
    }
     
    public static ActividadExtraescolar_MB convertirActividad(ResultSet rs) throws SQLException {
       
        String nombre = rs.getString("nombre");        
        ActividadExtraescolar_MB actividad = new ActividadExtraescolar_MB(); 
        
        actividad.setNombre(nombre);
       
        return actividad;
    }
    
}
