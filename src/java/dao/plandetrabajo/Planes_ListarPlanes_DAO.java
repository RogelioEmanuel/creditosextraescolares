package dao.plandetrabajo;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.PlandeTrabajo.PlanTrabajo_MB;
import Utilidades.Constantes;
import config.conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Planes_ListarPlanes_DAO {
    
    
    private static PlanTrabajo_MB convertir(ResultSet rs) throws SQLException {
        int idActividad_Extraescolar = rs.getInt("id_Actividad_Extraescolar");
        
        int id_plan = rs.getInt("id_plan"); 
        int idMaestros = rs.getInt("idMaestros"); 
        PlanTrabajo_MB plan = new PlanTrabajo_MB();  
        plan.setActividadExtraescolar(idActividad_Extraescolar);
        plan.setIdPlan(id_plan);
        plan.setMaestro(idMaestros);
        
        return plan;
    }

    public static List<PlanTrabajo_MB> consultar() {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PlanTrabajo_MB> plantrabajo = new ArrayList<>();
        
        
  
        try {
            if (conn != null) {
                String query = "SELECT id_plan, id_Actividad_Extraescolar, idMaestros \n"
                        + "FROM planestrabajoactividades f \n"                        
                        + "limit 100";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    plantrabajo.add(convertir(rs));
                    
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

        return plantrabajo;
    }
    
}
