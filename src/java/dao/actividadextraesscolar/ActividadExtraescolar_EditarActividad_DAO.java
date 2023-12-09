package dao.actividadextraesscolar;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import config.conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;


public class ActividadExtraescolar_EditarActividad_DAO {
    
    private static ActividadExtraescolar_MB convertir(ResultSet rs) throws SQLException {
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

    public static ActividadExtraescolar_MB consultar(int idActividad) {
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
                    actividad = convertir(rs);
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
    
    
    public static void actualizarActividad(ActividadExtraescolar_MB actividad, GenericResponse respuesta) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        

       

        PreparedStatement ps = null;
        try {
            if (conn != null) {
                String update =" UPDATE actividad_extraescolar SET nombre=?, tipo=?, status=?, descripcion=? "
                        + "WHERE idActividad_Extraescolar=?";
                ps = conn.prepareStatement(update);
                ps.setString(1, actividad.getNombre());
                ps.setString(2, actividad.getTipo());
                ps.setInt(3, actividad.getStatus());
                ps.setString(4, actividad.getDescripcion());
                ps.setInt(5, actividad.getIdActividad_Extraescolar());
                
                ps.executeUpdate();

                respuesta.setMensaje("Ok");
                respuesta.setStatus(Validaciones.VALIDATION_EXP);
                respuesta.setResponseObject(null);
            }
        } catch (SQLException ex) {
            respuesta.setStatus(Validaciones.VALIDATION_ERROR);
            respuesta.setMensaje(ex.toString());
            respuesta.setResponseObject(null);
            Logger.getLogger(ActividadExtraescolar_EditarActividad_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(ActividadExtraescolar_EditarActividad_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
