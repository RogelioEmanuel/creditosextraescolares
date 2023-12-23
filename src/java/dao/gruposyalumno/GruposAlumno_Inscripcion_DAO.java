package dao.gruposyalumno;

import Utilidades.Constantes;
import java.util.Date;


public class GruposAlumno_Inscripcion_DAO {
    
    
    
    
    public static boolean inscripcionActiva(){
        Date fechaHoy= new Date();
        return fechaHoy.before(Constantes.declararInscripciones());
        
    }
    
    
    
}
