/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.actividadextraesscolar;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
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


/**
 *
 * @author Emanuel
 */
public class ActividadExtraescolar_ListarActividad_DAO {
    
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
    
     public static List<ActividadExtraescolar_MB> consultar() {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ActividadExtraescolar_MB> actividades = new ArrayList<>();
        
        
  
        try {
            if (conn != null) {
                String query = "SELECT idActividad_Extraescolar, nombre, tipo, status, descripcion \n"
                        + "FROM actividad_extraescolar f \n"                        
                        + "limit 100";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    actividades.add(convertir(rs));
                    
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
    
}
