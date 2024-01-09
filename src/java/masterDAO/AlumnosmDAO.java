
package masterDAO;

import ManageBean.Alumnos.Alumnos_MB;
import Utilidades.GenericResponse;
import dao.Alumnos.AlumnosInsertarAlumno_DAO;


public class AlumnosmDAO {
    
    
    
    public static void getAlumno(String usuario, String contra, GenericResponse<Alumnos_MB> response){
        
        Alumnos_MB obj = null;
        AlumnosITTol_MDAO man = new  AlumnosITTol_MDAO();
        
        
        obj = man.buscar(usuario,contra);
        
        if(obj!=null){
            Alumnos_MB ayu = new Alumnos_MB();
            ayu =AlumnosInsertarAlumno_DAO.consultarAlumno(usuario);            
            if(ayu!=null){
                AlumnosInsertarAlumno_DAO.actualizar(ayu,response);
            }else{
                AlumnosInsertarAlumno_DAO.inscribir(ayu, response);
            }
        }
        
    }
    
    public Alumnos_MB buscarAlumno(String usuario,String contrasenia){
        Alumnos_MB alu= null;
        try{
            alu = getInfo(usuario,contrasenia);
        }catch(Exception e){
            alumnoSin(usuario,contrasenia);
        }
        
        
        return alu;
    }
    
    
    
    private Alumnos_MB alumnoSin(String usuario,String contraenia){
        Alumnos_MB alu= new Alumnos_MB();
        
        alu.setNombre("Rogelio Emanuel");
        alu.setApPaterno("Valencia");
        alu.setApMaterno("Gonzalez");
        alu.setNoControl("17280330");
        alu.setCorreo("rvalenciag@toluca.tecnm.mx");
        alu.setCarrera("INGENIERÍA EN SISTEMACS COMPUTACIONALES");
        alu.setRegular(true);
        alu.setSexo("M");
        alu.setEdad(24);
        alu.setSemestre(12);
        return alu;
    }

    private Alumnos_MB getInfo(String usuario, String contrasenia) {
        Alumnos_MB alu= new Alumnos_MB();
        return alu;
    }
    
}
