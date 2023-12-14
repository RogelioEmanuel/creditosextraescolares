package dao.Historico;


import ManageBean.CreditoExtraescolar.CreditoExtraescolar_MB;
import ManageBean.RegistroHistorico.RegistroHistorico_MB;
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

public class Historico_ListarHistorico {
    
    private static RegistroHistorico_MB convertir(ResultSet rs) throws SQLException {
       int id = Integer.parseInt("idRrgistrohistorcio");
        String periodo = rs.getString("periodo");
        String estado = rs.getString("estado");        
        String tipo = rs.getString("tipo");
        String noControl =rs.getString("nocontrolAlumno");
        String actividad = rs.getString("nombreactividad");
        
        int anio = Integer.parseInt(rs.getString("anio"));
        
        RegistroHistorico_MB creditos = new RegistroHistorico_MB();
        
        creditos.setAnio(anio);
        creditos.setEstado(estado);               
        creditos.setNoControl(noControl);
        creditos.setNomActividad(actividad);        
        creditos.setPeriodo(periodo);  
        creditos.setTipo(tipo);
        creditos.setIdRegistroHistorico(id);
        return creditos;
    }
    
     public static List<RegistroHistorico_MB> consultar() {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RegistroHistorico_MB> creditos = new ArrayList<>();
        
        
  
        try {
            if (conn != null) {
                String query = "SELECT \n" +
                                "    idRegistrohistorico,\n" +
                                "    periodo,\n" +
                                "    estado,\n" +
                                "    tipo,\n" +
                                "    nocontrolAlumno,\n" +
                                "    anio,\n" +
                                "    nombreactividad,\n" +                               
                                "FROM \n" +
                                "    registrohistorico f\n" +
                                "LIMIT 100;";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    creditos.add(convertir(rs));
                    
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreditoExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CreditoExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CreditoExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return creditos;
    }
    
}
