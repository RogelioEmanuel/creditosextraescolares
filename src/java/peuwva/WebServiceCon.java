/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peuwva;

import ws.DatosAlumnoMB;



/**
 *
 * @author mauro
 */
public class WebServiceCon
{

   public static void main(String[] args)
    {
        System.out.println(System.getProperty("javax.net.ssl.trustStore"));
       System.out.println( getInfo("Alu_17280330", "PW3769").getNombre());
    }
   
   public static  DatosAlumnoMB buscar(String us ,String pass){
       return getInfo(us,pass);
   }

    private static DatosAlumnoMB getInfo(java.lang.String usuario, java.lang.String contrasenia)
    {
        ws.GetInfoMov_Service service = new ws.GetInfoMov_Service();
        ws.GetInfoMov port = service.getGetInfoMovPort();
        return port.getInfo(usuario, contrasenia);
    }
    
}
