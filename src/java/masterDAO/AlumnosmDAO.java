
package masterDAO;

import ManageBean.Alumnos.Alumnos_MB;
import Utilidades.GenericResponse;
import static Utilidades.Validaciones.VALIDATION_EXP;
import static Utilidades.Validaciones.VALIDATION_EXP_ERROR;
import dao.Alumnos.AlumnosInsertarAlumno_DAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import ws.DatosAlumnoMB;


public class AlumnosmDAO {
    
    public static void getAlumno(String usuario, String contra, GenericResponse<Alumnos_MB> response){
        
        DatosAlumnoMB obj = null;
        AlumnosITTol_MDAO man = new  AlumnosITTol_MDAO();
        obj = man.buscar(usuario,contra);
        
        if(obj!=null){            
            GenericResponse<Alumnos_MB> resp2 = new GenericResponse<>();
            Alumnos_MB ayu = new Alumnos_MB();
            
            //Consultamos si esta en la BD
            ayu =AlumnosInsertarAlumno_DAO.consultarAlumno2(usuario);     
            System.out.println("Verifica ");
            if(ayu!=null){
                
                //Si esta actualizamos
                String noControl= obj.getNumeroControl();
                String nombre = obj.getNombre();
                String apPaterno =obj.getApellidoPaterno();
                String apMaterno = obj.getApellidoMaterno();
                int semestre = obj.getSemestre();
                String regular = obj.getRegular();
                String correo = obj.getCorreo();
                String sexo = obj.getSexo();
                String carrera = obj.getCarrera();
                
                String fechaDeLanzamiento = obj.getFechaNacimiento();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date dataFormateada = null;
                try {
                    dataFormateada = (Date) formato.parse(fechaDeLanzamiento);
                } catch (ParseException ex) {

                }
                
                
               System.out.println(edad(fechaDeLanzamiento));
                if(fechaDeLanzamiento !=null){
                    ayu.setEdad(edad(fechaDeLanzamiento));
                }
                ayu.setApMaterno(apMaterno);
                ayu.setApPaterno(apPaterno);
                ayu.setNombre(nombre);
                ayu.setNoControl(noControl);
                ayu.setSemestre(semestre);
                if(regular.equals("IREGULAR")){
                    ayu.setRegular(false);
                }else{
                    ayu.setRegular(true);
                }
                ayu.setCorreo(correo);
                ayu.setCarrera(carrera);
                ayu.setSexo(sexo);
                
                AlumnosInsertarAlumno_DAO.actualizar(ayu,resp2);
                
                if(resp2.getStatus()==0){
                    resp2.setResponseObject(ayu);
                    response.setMensaje("Ok");
                    response.setResponseObject(resp2.getResponseObject());
                    response.setStatus(VALIDATION_EXP);
                }else{
                    response.setMensaje("Algo salio mal");
                    response.setResponseObject(null);
                    response.setStatus(VALIDATION_EXP_ERROR);
                }
            }else{
                 Alumnos_MB ayu2 = new Alumnos_MB();
                //Si no, Insertamos
                GenericResponse<Alumnos_MB> resp3 = new GenericResponse<>();
                String noControl= obj.getNumeroControl();
                String nombre = obj.getNombre();
                String apPaterno =obj.getApellidoPaterno();
                String apMaterno = obj.getApellidoMaterno();
                int semestre = obj.getSemestre();
                String regular = obj.getRegular();
                String correo = obj.getCorreo();
                String sexo = obj.getSexo();
                String carrera = obj.getCarrera();
                String fechaDeLanzamiento = obj.getFechaNacimiento();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date dataFormateada = null;
                try {
                    dataFormateada = (Date) formato.parse(fechaDeLanzamiento);
                } catch (ParseException ex) {

                }
                
                System.out.println(edad(fechaDeLanzamiento));
                int edad = (edad(fechaDeLanzamiento));
                
                ayu2.setEdad(edad);
                
                
                ayu2.setApMaterno(apMaterno);
                ayu2.setApPaterno(apPaterno);
                ayu2.setNombre(nombre);
                ayu2.setNoControl(noControl);
                ayu2.setSemestre(semestre);
                if(regular.equals("IREGULAR")){
                    ayu2.setRegular(false);
                }else{
                    ayu2.setRegular(true);
                }                
                ayu2.setCorreo(correo);
                ayu2.setCarrera(carrera);
                ayu2.setSexo(sexo);
                System.out.println(ayu2.getEdad());
                AlumnosInsertarAlumno_DAO.inscribir(ayu2, resp3);
                
                
                 if(resp3.getStatus()==0){
                    resp3.setResponseObject(ayu);
                    response.setMensaje("Ok");
                    response.setResponseObject(resp3.getResponseObject());
                    response.setStatus(VALIDATION_EXP);
                }else{
                    response.setMensaje("Algo salio mal");
                    response.setResponseObject(null);
                    response.setStatus(VALIDATION_EXP_ERROR);
                }
            }
        }else{
            response.setMensaje("Algo salio mal");
            response.setResponseObject(null);
            response.setStatus(VALIDATION_EXP_ERROR);
        }
        
       
        
                
    }
    
    public static int edad(String fechaDeNacimiento){
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimiento = null;
        int edad=0;
        try {
            fechaNacimiento = formato.parse(fechaDeNacimiento);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        if (fechaNacimiento != null) {
            Calendar calNacimiento = Calendar.getInstance();
            calNacimiento.setTime(fechaNacimiento);

            Calendar calHoy = Calendar.getInstance();

             edad = calHoy.get(Calendar.YEAR) - calNacimiento.get(Calendar.YEAR);

            // Verifica si aún no ha cumplido años en este año
            if (calHoy.get(Calendar.DAY_OF_YEAR) < calNacimiento.get(Calendar.DAY_OF_YEAR)) {
                edad--;
            }

           // System.out.println("Edad: " + edad + " años");
        } else {
            //System.out.println("Fecha de nacimiento no válida");
        }
        
        return edad;
        
    }
    
    
    
}
