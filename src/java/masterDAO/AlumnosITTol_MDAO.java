
package masterDAO;


import ws.DatosAlumnoMB;

/**
 *
 * @author Emanuel
 */
public class AlumnosITTol_MDAO {

    public DatosAlumnoMB buscar(String usuario, String contra) {
        DatosAlumnoMB alu=null;
        
        try{
            alu= getInfo(usuario,contra);
            System.out.println("alumno "+alu);
        }catch (javax.xml.ws.WebServiceException e){
            System.out.println(e.getMessage());
        }
        
        return alu;
    }

    private static DatosAlumnoMB getInfo(java.lang.String usuario, java.lang.String contrasenia)
    {
        ws.GetInfoMov_Service service = new ws.GetInfoMov_Service();
        ws.GetInfoMov port = service.getGetInfoMovPort();
        return port.getInfo(usuario, contrasenia);
    }
    

   
   
}
