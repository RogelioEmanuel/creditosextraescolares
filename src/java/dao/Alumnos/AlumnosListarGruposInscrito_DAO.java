package dao.Alumnos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.Maestros.Maestros_MB;
import Utilidades.Constantes;
import config.conexion.ConexionMySQL;
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


public class AlumnosListarGruposInscrito_DAO {
    
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
    
    public static List<Grupos_MB> consultarGr(String id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Grupos_MB> grupos = new ArrayList<>();
        
  
        try {
            if (conn != null) {
                String query = "SELECT Grupos.idGrupo, Grupos.noGrupo, Grupos.cupo, Grupos.idActividad_extraescolar, Grupos.idMaestros, Grupos.periodo, Grupos.totalhorassemanal \n"
                                + "FROM Grupos JOIN grupos_y_alumno ON Grupos.idGrupo = grupos_y_alumno.idgrupo WHERE grupos_y_alumno.nocontrolalumno  =  ? ";

                ps = conn.prepareStatement(query);
                ps.setString(1,id );
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    grupos.add(convertir(rs));
                    
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
}
