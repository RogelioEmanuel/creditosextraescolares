/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package masterDao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.Constantes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kike
 */
public class conexion {

    public static Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // la conexion a la base de datos con las especificaciones que es tipo de Base que usara, puertos, version, usuario y contraseña en local
            Connection con = DriverManager.getConnection(Constantes.MASTER_BD, Constantes.MASTER_USER, Constantes.INVITACIONES_PASS);
             return con;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public void cerrarConexion(Connection con)  {
        try {
            con.close();
        } catch (SQLException sqle) {
         
        }
    }

    public ResultSet getDatos(String com) {
        PreparedStatement pstm;
        ResultSet rs;
        try {
            Connection con = getConnection();
            pstm = con.prepareStatement(com);
            rs = pstm.executeQuery(com);
            return rs;
        } catch (Exception e) {

        }
        return null;
    }
}
