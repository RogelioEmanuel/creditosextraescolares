
package dao.creditos;

import ManageBean.CreditoExtraescolar.CreditoExtraescolar_MB;
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


public class Creditos_ListarCreditos_DAO {
     private static CreditoExtraescolar_MB convertir(ResultSet rs) throws SQLException {
        int idCredito = Integer.parseInt(rs.getString("idCreditoExtraescolar"));
        String periodo = rs.getString("periodo");
        String estado = rs.getString("estado");        
        String tipo = rs.getString("tipo");
       String noControl =rs.getString("noControlAlumno");
        String actividad = rs.getString("NombreActividad");
        int idGrupo = Integer.parseInt(rs.getString("noGrupo"));
        String nombreAlumno = rs.getString("nombreAlumno");
        int anio = Integer.parseInt(rs.getString("anio"));
        
        CreditoExtraescolar_MB creditos = new CreditoExtraescolar_MB();
        
        creditos.setAnio(anio);
        creditos.setEstado(estado);
        creditos.setIdCredito(idCredito);
        creditos.setIdGrupo(idGrupo);
        creditos.setNoControl(noControl);
        creditos.setNombreActividad(actividad);
        creditos.setNombreAlumno(nombreAlumno);
        creditos.setPeriodo(periodo);  
        creditos.setTipo(tipo);
        return creditos;
    }
    
     public static List<CreditoExtraescolar_MB> consultar() {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CreditoExtraescolar_MB> creditos = new ArrayList<>();
        
        
  
        try {
            if (conn != null) {
                String query = "SELECT \n" +
                                "    f.idCreditoExtraescolar,\n" +
                                "    f.periodo,\n" +
                                "    f.estado,\n" +
                                "    f.tipo,\n" +
                                "    f.noControlAlumno,\n" +
                                "    f.anio,\n" +
                                "    f.NombreActividad,\n" +
                                "    f.idGrupo,\n" +
                                "    g.noGrupo,  -- Agregar el campo noGrupo desde la tabla de grupos\n" +
                                "    f.nombreAlumno\n" +
                                "FROM \n" +
                                "    creditosextraescolares f\n" +
                                "JOIN \n" +
                                "    grupos g ON f.idGrupo = g.idGrupo\n" +
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
