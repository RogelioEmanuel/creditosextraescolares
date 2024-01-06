
package Servlets.Eventos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Eventos.Evento_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.eventos.Eventos_ReportesEventos_DAO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.codec.binary.Base64;


public class ReportesEventos_Srv extends HttpServlet {

   
    
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<ActividadExtraescolar_MB> actividades = new ArrayList<>();
        
        GenericResponse respuesta = new GenericResponse<>();
                
        actividades = Eventos_ReportesEventos_DAO.consultarActividades();
        request.setAttribute("actividad", actividades);
        
        request.getRequestDispatcher("/views/Eventos/Paginas/ReporteEventos_View.jsp").forward(request, response);
       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String periodoParam = request.getParameter("periodo");
        String anioString=request.getParameter("anio");
        String idactString= request.getParameter("actividad");
        int anio;
        //Calendar calendar = Calendar.getInstance();
        String periodo;
        int idActividad=0;
         List<Evento_MB> evento;
         
         
        if (anioString == null || anioString.isEmpty()) {
            anio = Calendar.getInstance().get(Calendar.YEAR);
        } else {
            anio = Integer.parseInt(anioString);
        }

        if (periodoParam == null || periodoParam.isEmpty()) {
            periodo = Constantes.declararPeriodoActual();
        } else {
            periodo = periodoParam;
        }
        
        if (idactString == null || idactString.isEmpty()) {
            evento = Eventos_ReportesEventos_DAO.consultar2(anio, periodo);
            
        } else {
            idActividad = Integer.parseInt(idactString);
            evento = Eventos_ReportesEventos_DAO.consultar(anio, periodo, idActividad);
            
        }
        
        
        ActividadExtraescolar_MB actnombre = Eventos_ReportesEventos_DAO.consultarActividad(idActividad);
        String actividad=actnombre.getNombre();
        
        PrintWriter out = response.getWriter();
        //evento = Eventos_ReportesEventos_DAO.consultar3();    
        GenericResponse respuesta = new GenericResponse();
        //Obtencion y creacion de logo ITT
        String imagenUrl = getClass().getClassLoader().getResource("img/header.png").toString().substring(6);            
        File file = new File(imagenUrl);
        InputStream imagenFile = new FileInputStream(file);
        String jrxmlFile;
        
        
        if(evento.isEmpty()){
            jrxmlFile = getClass().getClassLoader().getResource("Reportes/nada.jrxml").toString().substring(6);  
            System.out.println("vacio");
        }else{
            jrxmlFile = getClass().getClassLoader().getResource("Reportes/evento2.jrxml").toString().substring(6);
        }
         
        
        try {
            
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);
            
            
            
            
            Map<String, Object> map = new HashMap<>();
            
            
            for (Evento_MB maestro4 : evento) {
               
               //System.out.println(maestro4.getNombreEvento());
            }
            if(imagenFile.available()>0){
                map.put(Constantes.NOMBRELOGO, imagenFile);                 
                  
            }
            
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(evento);
            
            //System.out.println(":0 "+ds.toString());
            
                map.put("ds", ds); 
                map.put("actividad",actividad);
                map.put("periodo",periodo);
                map.put("ds", ds); 
                map.put("jefatura",Constantes.NOMBREJEFATURA);
                map.put("oficinaPromocion",Constantes.NOMBREJEFATURAPROMOCION);
            
           
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, ds);
            
            
            
            
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();   
            JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);            
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            String base64EncodedPDF = new String(Base64.encodeBase64(pdfBytes));
                        
            respuesta.setMensaje(base64EncodedPDF);
            respuesta.setStatus(0);
            respuesta.setResponseObject(null);
            
            try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
            } catch (Exception e) {
                System.out.println(e);
            }
            
            
            
            
        } catch (JRException ex) {
            Logger.getLogger(ReportesEventos_Srv.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
