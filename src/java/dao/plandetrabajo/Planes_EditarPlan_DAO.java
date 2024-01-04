package dao.plandetrabajo;

import ManageBean.PlanSemana.PlanSemana_MB;
import ManageBean.PlandeTrabajo.PlanTrabajo_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import dao.Grupos.Grupos_EditarGrupo_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Planes_EditarPlan_DAO {
    
    
    
     public static void actualizarPlanSemanal(PlanSemana_MB plan, GenericResponse respuesta) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        System.out.println("Programa"+plan.getId_plan());
        
        PreparedStatement ps = null;
        try {
            
            if (conn != null) {
                
                String update ="UPDATE semanasactividades SET programa=?, plataforma=?, llevara_acabo=? "
                                + "WHERE id_plan=? AND semana=?";
                ps = conn.prepareStatement(update);
                
                ps.setString(1, plan.getPrograma());
                
                ps.setString(2, plan.getPlataforma());
                ps.setString(3, plan.getLlevar_a_cabo());
                ps.setInt(4, plan.getId_plan());
                ps.setInt(5, plan.getNoSemana());
                
                ps.executeUpdate();
                respuesta.setMensaje("Ok");
                respuesta.setStatus(Validaciones.VALIDATION_EXP);
                respuesta.setResponseObject(null);
            }
        } catch (SQLException ex) {
            respuesta.setStatus(Validaciones.VALIDATION_ERROR);
            respuesta.setMensaje(ex.toString());
            respuesta.setResponseObject(null);
            Logger.getLogger(Planes_EditarPlan_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Planes_EditarPlan_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PlanTrabajo_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    
    
}
