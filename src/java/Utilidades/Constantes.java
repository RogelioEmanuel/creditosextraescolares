
package Utilidades;

import dao.Periodo.Periodo_DAO;
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
    
    public static final SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");  
    public static Date fecha=null;       
    public static Date fecha2=null;
    
    //Fechas Periodo, Dias Inscribir
    public static String PERIODOFECHAINICIO=f.format(Periodo_DAO.consultar().getFecha_inicio());
    public static String PERIODOFECHAFINAL=f.format(Periodo_DAO.consultar().getFecha_fin());
    
    public static int DIASINSCRIPCION = Periodo_DAO.consultar().getInsscripcion();
    public static int diasCalificacion=Periodo_DAO.consultar().getCierre();
               
    public static String periodoActual;
    public static final String MASTER_USER = "root";
    public static final String MASTER_PASS = "";
    //public static final String MASTER_BD = "jdbc:mysql://localhost:3306/sam?allowLoadLocalInfile=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String MASTER_BD2 = "http://sismaster.toluca.tecnm.mx:8090/SAM/";
    
    
         
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
        //System.out.println(Periodo_DAO.consultar().getFecha_inicio());
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
    
    public static Date declararCalif(){
        Calendar cal = Calendar.getInstance();
        declararPeriodo();
        cal.setTime(fecha2);
        cal.add(Calendar.DAY_OF_MONTH , -diasCalificacion);
        Date fechaConDiasRestados = cal.getTime();
        
        return fechaConDiasRestados;
    }
    
     public static boolean esAntesDeInscripciones() {
        Date fechaActual = new Date();
        Date fechaInscripciones = declararInscripciones();
                
        return fechaActual.before(fechaInscripciones)&&fechaActual.after(fecha);
    }
     
     public static boolean asentarCalificaciones(){
         Date fechaActual = new Date();
         Date fechaInscripciones = declararCalif();
         
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         System.out.println("Fecha con días agregados: " + dateFormat.format(fechaInscripciones));
         System.out.println("Fecha actual: " + dateFormat.format(fechaActual));
         System.out.println("Fecha 2: " + dateFormat.format(fecha2));
         //fecha actual es  antes de fecha 2 y fecha actual es despues de inscripciones
         System.out.println(fechaActual.before(fecha2)&&fechaActual.after(fechaInscripciones));
         return fechaActual.before(fecha2)&&fechaActual.after(fechaInscripciones);
         
         //return true;
     }
               
     public static boolean prueba(){
         System.out.println("Falso"+" Prueba");
         return false;
     }
    
}
