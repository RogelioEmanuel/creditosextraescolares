
package masterDAO;


import ws.DatosAlumnoMB;

/**
 *
 * @author Emanuel
 */
class AlumnosITTol_MDAO {

    public DatosAlumnoMB buscar(String usuario, String contra) {
        DatosAlumnoMB alu=null;
        
        try{
            alu= getInfo(usuario,contra);
        }catch (javax.xml.ws.WebServiceException e){
            //alu = getInfoSinInternet(usuario, contra);
        }
        
        return alu;
    }
    
    
    private DatosAlumnoMB getInfo(String usuario, String contra){
        ws.GetInfoMov_Service service = new ws.GetInfoMov_Service();
        ws.GetInfoMov port = service.getGetInfoMovPort();
        return port.getInfo(usuario, contra);
        
    }
    
    
    
     private static DatosAlumnoMB getInfoSinInternet(java.lang.String usuario, java.lang.String contrasenia) {
        DatosAlumnoMB alumno = new DatosAlumnoMB();
        if (usuario.isEmpty() || contrasenia.isEmpty()) {
            alumno.setCodigoError(100);
            alumno.setMensajeError("Ocurrió un error desconocido");
            return alumno;
        }
        if (usuario.equals("ALU_17280378") && contrasenia.equals("PW717")) {
            alumno.setCodigoError(0);
            alumno.setMensajeError("TERMINACIÓN CORRECTA");
            alumno.setNombre("JUAN OSCAR");
            alumno.setApellidoPaterno("VILLAGOMEZ");
            alumno.setApellidoMaterno("HUERTA");
            alumno.setNumeroControl("ALU_17280378");
            alumno.setSemestre(12);
            alumno.setCarrera("INGENIERÍA EN SISTEMAS COMPUTACIONALES");
            alumno.setTipoSangre("O-");
            alumno.setFechaNacimiento("24/06/1999");
            alumno.setSexo("M");
            alumno.setCorreo("jvillagomezh3@toluca.tecnm.mx");
            alumno.setCreditosAcumulados(260);
            alumno.setPromedioGeneral(100.0);
            alumno.setRegular("IREGULAR");
            alumno.setTelefonoFijo("527135554433");
            alumno.setTelefonoCelular("527135554433");
            alumno.setCodigoPostal(52372);
            alumno.setNumeroExterior("2");
            alumno.setCalle("LIBRAMIENTO  No Int.1");
            alumno.setColonia("SAN JUAN JAJAL");
            alumno.setEstado("MEXICO");
            alumno.setMunicipio("MEXICALZINGO");
            return alumno;
        }

        alumno.setCodigoError(100);
        alumno.setMensajeError("Usuario o password incorrecto, acceso denegado.");

        return alumno;
    }
    
    
}
