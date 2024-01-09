
package Utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Constantes {
    public static final String EXTRAESCOLARESPRUEBA_BD="baseejmplocreditosextraescolares";
    public static final String EXTRAESCOLARESPRUEBA_USER="root";
    public static final String EXTRAESCOLARESPRUEBA_PASS="";
    public static final String NOMBRECREDITONORMAL="Creditos Extraescolares";
    public static final String NOMBRECREDITOSELECTIVO="Creditos Extraescolares Selectivo";
    public static final String MSJ_CREDENCIALES_ERRONEAS = "Usuario o contraseña incorrectos, acceso denegado.";
    
    
    
    
    
    public static final List<String> CARRERAS = Arrays.asList(
                "INGENIERIA ELECTROMECANICA",
                "INGENIERIA ELECTRONICA",
                "INGENIERIA EN GESTION EMPRESARIAL",
                "INGENIERIA INDUSTRIAL",
                "INGENIERIA LOGISTICA",
                "INGENIERIA MECATRONICA",
                "INGENIERIA EN SISTEMAS COMPUTACIONALES",
                "INGENIERIA EN TECNOLOGIAS DE LA INFORMACION Y COMUNICACION",
                "INGENIERIA EN QUIMICA"
        );
    
    public static final List<String> CARRERASABRE = Arrays.asList(
                "IELE",
                "I",
                "IGE",
                "II",
                "ILOG",
                "IM",
                "ISC",
                "TICS",
                "IQU"
        );
    
    public static final Map<String, String> mapaCarreras = new HashMap<>();
    
    public static Map <String, String> getCarreras(){
        mapaCarreras.put("INGENIERIA ELECTROMECANICA", "IELE");
        mapaCarreras.put("INGENIERIA ELECTRONICA", "I");
        mapaCarreras.put("INGENIERIA EN GESTION EMPRESARIAL", "IGE");
        mapaCarreras.put("INGENIERIA INDUSTRIAL", "II");
        mapaCarreras.put("INGENIERIA LOGISTICA", "ILOG");
        mapaCarreras.put("INGENIERIA MECATRONICA", "IM");
        mapaCarreras.put("INGENIERIA EN SISTEMAS COMPUTACIONALES", "ISC");
        mapaCarreras.put("INGENIERIA EN TECNOLOGIAS DE LA INFORMACION Y COMUNICACION", "TICS");
        mapaCarreras.put("INGENIERIA EN QUIMICA", "IQU");
        return mapaCarreras;
    }
     
    public static final String NOMBREJEFATURA="IVÁN OMAR ORTEGA ROSALES";
    public static final String NOMBREJEFATURAPROMOCION="MANUEL R. CORREA ALDAPE";
    public static final String NOMBRELOGO="LogoITTol";
    public static  final String PERIODOFECHAINICIO="15/01/2024";
    public static  final String PERIODOFECHAFINAL="15/06/2024";
    public static final SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");  
    public static Date fecha=null;       
    public static Date fecha2=null;
    public static final int DIASINSCRIPCION = 30;
               
    public static String periodoActual;
    public static final String MASTER_USER = "root";
    public static final String MASTER_PASS = "";
    public static final String MASTER_BD = "jdbc:mysql://localhost:3306/sam?allowLoadLocalInfile=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String MASTER_BD2 = "sam?allowLoadLocalInfile=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    
         
    public static String declararPeriodoActual(){
        
        Calendar calendar = Calendar.getInstance();
        declararPeriodo();
        calendar.setTime(fecha);

        int mes = calendar.get(Calendar.MONTH) + 1;
        if (mes >= 1 && mes <= 6) {
            periodoActual=  "Enero-Junio";
            
        } else if (mes >= 8 && mes <= 12) {
            periodoActual=  "Agosto-Diciembre";
            
        } else {
           periodoActual=  "Verano";
           
        }
        return periodoActual;
    } 
     
     
           
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
    
     public static boolean esAntesDeInscripciones() {
        Date fechaActual = new Date();
        Date fechaInscripciones = declararInscripciones();
        System.out.println("Falso");
        return fechaActual.before(fechaInscripciones);
    }
               
     public static boolean prueba(){
         System.out.println("Falso");
         return false;
     }
    
}
