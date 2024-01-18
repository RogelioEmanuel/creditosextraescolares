/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Eventos;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import masterDAO.Empleado;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Emanuel
 */
public class ReportesEventosMaestro_Srv extends HttpServlet {

    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        Empleado maestro2= (Empleado) session.getAttribute("usuario");
        int idMaestro= maestro2.getIdEmpleado();
        List<Evento_MB> evento = Eventos_ReportesEventos_DAO.consultarEventos(idMaestro);    
        GenericResponse respuesta = new GenericResponse();
        
        //Obtencion y creacion de logo ITT
        String imagenUrl = getClass().getClassLoader().getResource("img/header.png").toString().substring(6);            
        File file = new File(imagenUrl);
        InputStream imagenFile = new FileInputStream(file);
        
        
        if(evento.isEmpty()){
            System.out.println("Vacio, sin datos");
            
            respuesta.setMensaje("No hay ningun dato para el reporte");
            respuesta.setStatus(24);
            try {
                response.setContentType("application/json");
                Gson json = new Gson();
                out.print(json.toJson(respuesta));
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }else{
            String jrxmlFile = getClass().getClassLoader().getResource("Reportes/evento3.jrxml").toString().substring(6);   
        
            try {

                JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);

                //Evento_MB[] eventos =evento.toArray(new Evento_MB[0]);


                Map<String, Object> map = new HashMap<>();
                //JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(eventos);

                for (Evento_MB maestro4 : evento) {

                   //System.out.println(maestro4.getNombreEvento());
                }
                if(imagenFile.available()>0){
                    map.put(Constantes.NOMBRELOGO, imagenFile);                 

                }
                String actividad="";
                String periodo="";
                JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(evento);

                //System.out.println(":0 "+ds.toString());
                map.put("ds", ds); 
                map.put("jefatura",Constantes.NOMBREJEFATURA);
                map.put("oficinaPromocion",Constantes.NOMBREJEFATURAPROMOCION);
                map.put("actividad",actividad);
                map.put("periodo",periodo);
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
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
