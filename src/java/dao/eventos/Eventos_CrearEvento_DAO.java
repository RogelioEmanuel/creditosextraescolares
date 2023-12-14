package dao.eventos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Eventos.Evento_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import dao.actividadextraesscolar.ActividadExtraescolar_CrearActividad_DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Eventos_CrearEvento_DAO {
    
      public static void insertar(Evento_MB evento, GenericResponse respuesta){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        LocalDate fechaActual = LocalDate.now();
        int anioActual = fechaActual.getYear();
        
        try {
            if (conn != null) {
                String query= "INSERT INTO eventos( nombreEvento, InstitucionOrganizadora, tipoEvento, periodo, fecha,anio, idActividad, numParticipantesH,"
                        + "numParticipantesM,resultado) VALUES (?,?,?,?,?,?,?,?,?,?)";
                
                ps = conn.prepareStatement(query);
                ps.setString(1, evento.getNombreEvento()); 
                ps.setString(2,evento.getInstitucionOrganizadora());
                ps.setString(3, evento.getTipoEvento());
                ps.setString(4,evento.getPeriodo());
                ps.setDate(5,new Date(evento.getFecha().getTime()));                
                ps.setInt(6, anioActual);
                ps.setInt(7,evento.getIdActividad());
                ps.setInt(8,evento.getNoParticipantesh());
                ps.setInt(9,evento.getNoParticipantesm());
                ps.setString(10, evento.getResultado());
                               
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
                Logger.getLogger(ActividadExtraescolar_CrearActividad_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
