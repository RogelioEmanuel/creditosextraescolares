
package dao.eventos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Eventos.Evento_MB;
import ManageBean.Maestros.Maestros_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import dao.maestros.Maestros_EditarMaestros_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Evento_EditarEvento_DAO {
    private static Evento_MB convertir(ResultSet rs) throws SQLException {
        
        
        int idEvento = rs.getInt("idEventos");
        String nombre = rs.getString("NombreEvento");
        int numParticipantesH = rs.getInt("numParticipantesH");
        int numParticipantesM = rs.getInt("numParticipantesM");
        String institucion = rs.getString("InstitucionOrganizadora");
        String tipoevento = rs.getString("tipoevento");
        String periodo = rs.getString("periodo");
        Date fecha = rs.getDate("fecha");
        int anio = rs.getInt("anio");
        String resultado = rs.getString("resultado");
        int idActividad = rs.getInt("idActividad");
        Evento_MB evento = new Evento_MB();   
        
        evento.setAnio(anio);
        evento.setFecha(fecha);
        evento.setIdActividad(idActividad);
        evento.setIdEvento(idEvento);
        evento.setInstitucionOrganizadora(institucion);
        evento.setNoParticipantesh(numParticipantesH);
        evento.setNoParticipantesm(numParticipantesM);        
        evento.setNombreEvento(nombre);
        evento.setResultado(resultado);
        evento.setPeriodo(periodo);
        evento.setTotalParticipantes();
        evento.setTipoEvento(tipoevento);
        
           
        return evento;
    }

    public static Evento_MB consultar(int idEvento) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Evento_MB evento = new Evento_MB();

        try {
            if (conn != null) {
                String query = "SELECT idEventos, NombreEvento, numParticipantesH, numParticipantesM, InstitucionOrganizadora,"
                        + "tipoevento, periodo,fecha, anio,resultado,idActividad \n"
                        + "FROM eventos f \n"
                        + "WHERE idEventos = ?";
                
                ps = conn.prepareStatement(query);
                ps.setInt(1,idEvento );
                rs = ps.executeQuery();
                while (rs.next()) {
                    evento = convertir(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Evento_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Evento_EditarEvento_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        return evento;
    }
    
    
    public static void actualizarEvento(Evento_MB evento, GenericResponse respuesta) {
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
                String update =" UPDATE eventos SET NombreEvento=?,numParticipantesH=?, numParticipantesM=?, InstitucionOrganizadora=?,"
                        + "tipoevento=?, periodo=?,fecha=?, anio=?,resultado=?,idActividad=? "
                        + "WHERE idEventos=?";
                ps = conn.prepareStatement(update);
                ps.setString(1, evento.getNombreEvento());
                ps.setInt(2,evento.getNoParticipantesh());
                ps.setInt(3,evento.getNoParticipantesm());
                ps.setString(4, evento.getInstitucionOrganizadora());
                ps.setString(5, evento.getTipoEvento());
                ps.setString(6, evento.getPeriodo());
                ps.setDate(7, fecha);   
                ps.setInt(8, evento.getAnio());
                ps.setString(9, evento.getResultado());
                ps.setInt(10, evento.getIdActividad());
                ps.setInt(11, evento.getIdEvento());
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
    
    public static List<ActividadExtraescolar_MB> consultarActividad() {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ActividadExtraescolar_MB> actividad = new ArrayList<>();

        try {
            if (conn != null) {
                String query = "SELECT idActividad_Extraescolar,nombre,tipo, status,descripcion  \n"
                        + "FROM actividad_extraescolar f \n";
                
                ps = conn.prepareStatement(query);
                
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    actividad.add(convertirActividad(rs));                    
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
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Eventos_CrearEvento_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
      
      
      public static ActividadExtraescolar_MB convertirActividad(ResultSet rs) throws SQLException {
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
}
