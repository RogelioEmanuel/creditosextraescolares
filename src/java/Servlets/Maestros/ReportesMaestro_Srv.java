package Servlets.Maestros;

import ManageBean.Maestros.Maestros_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.maestros.Maestros_EditarMaestros_DAO;
import dao.maestros.Maestros_ReportesMaestros_DAO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;


import org.apache.commons.codec.binary.Base64;


public class ReportesMaestro_Srv extends HttpServlet {

  

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         GenericResponse resp = new GenericResponse();
        HttpSession session = request.getSession();
        
        
        
        int id=Integer.parseInt(request.getParameter("idMaestro"));
        List<Maestros_MB> maestro = Maestros_ReportesMaestros_DAO.consultar( id);
        request.setAttribute("maestro", maestro);
        //request.getRequestDispatcher("/views/Maestros/Maestro/DetallesMaestro.jsp").forward(request, response);
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        GenericResponse respuesta = new GenericResponse();
        HttpSession session = request.getSession();
        int id=Integer.parseInt(request.getParameter("idMaestro"));
        Maestros_MB maestro =  Maestros_EditarMaestros_DAO.consultar( id);
        try {
            
            
            //Obtencion de Datos para mandar al reporte
            List<Maestros_MB> maestros = new ArrayList<>();
            List<Maestros_MB> maestros2 = Maestros_ReportesMaestros_DAO.consultar( maestro.getIdMaestros());            
            maestros.addAll(maestros2);
            
            //Obtencion y creacion de logo ITT
            String imagenUrl = getClass().getClassLoader().getResource("img/header.png").toString().substring(6);            
            File file = new File(imagenUrl);
            InputStream imagenFile = new FileInputStream(file);
           
            
            String jrxmlFile = getClass().getClassLoader().getResource("Reportes/Maestrosinformacion.jrxml").toString().substring(6);            
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);
            
         
            
            Maestros_MB[] maesstro = maestros.toArray(new Maestros_MB[0]);
            for (Maestros_MB maestro4 : maesstro) {
                System.out.println("Nombre"+maestro4.getNombre());
                System.out.println("apmaterno"+maestro4.getApMaterno());
                System.out.println("appaterno"+maestro4.getApPaterno());
                System.out.println("banco"+maestro4.getBanco());
                System.out.println("clave"+maestro4.getClave());
                System.out.println("rfc"+maestro4.getRfc());
                System.out.println("curp"+maestro4.getCurp());
                System.out.println("telefono"+maestro4.getTelefono());
                System.out.println("Dir"+maestro4.getDireccion());
                System.out.println("sex"+maestro4.getSexo());
                System.out.println("correo"+maestro4.getCorreo());
            }
                   
            JRBeanArrayDataSource ds = new JRBeanArrayDataSource(maesstro);
            Map<String, Object> map = new HashMap<>();
            
            
            
            if(imagenFile.available()>0){
                map.put(Constantes.NOMBRELOGO, imagenFile);  
                map.put("ds", ds);                
            }
            
            Object[] data = ds.getData();

            for (Object obj : data) {
                System.out.println(obj.toString());
                 
            }
           
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
            
            
        } catch (JRException e) {
            System.out.println(e);
        }
    }

       @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
