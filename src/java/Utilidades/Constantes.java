
package Utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Constantes {
    public static final String EXTRAESCOLARESPRUEBA_BD="baseejmplocreditosextraescolares";
    public static final String EXTRAESCOLARESPRUEBA_USER="root";
    public static final String EXTRAESCOLARESPRUEBA_PASS="";
     
     
    public static final String NOMBREJEFATURA="IVÁN OMAR ORTEGA ROSALES";
    public static final String NOMBRELOGO="LogoITTol";
    public static  final String PERIODOFECHAINICIO="15/01/2024";
    public static  final String PERIODOFECHAFINAL="15/06/2024";
    public static final SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");  
    public static Date fecha=null;       
    public static Date fecha2=null;
    public static final int DIASINSCRIPCION = 30;
               
    
     
     
     
     
           
    public static void declararPeriodo() {
        try {
            fecha = f.parse(PERIODOFECHAINICIO);
            fecha2=f.parse(PERIODOFECHAFINAL);
        } catch (ParseException ex) {
            Logger.getLogger(Constantes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                
    public static Date declararInscripciones(){       
        
        Calendar cal = Calendar.getInstance();
        declararPeriodo();
        cal.setTime(fecha);
        
        cal.add(Calendar.DAY_OF_MONTH , DIASINSCRIPCION);
        
        Date fechaConDiasAgregados = cal.getTime();
        
        return fechaConDiasAgregados;
    }
               
     
    
}
