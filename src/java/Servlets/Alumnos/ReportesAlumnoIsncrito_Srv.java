/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Alumnos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.Eventos.Evento_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.HorariosGrupo.HorariosGrupo_MB;
import Servlets.Eventos.ReportesEventos_Srv;
import Utilidades.Constantes;
import Utilidades.Cuenta;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.Alumnos.ReporteAlumnosInscritos_DAO;
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

/**
 *
 * @author Emanuel
 */
public class ReportesAlumnoIsncrito_Srv extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idGrupo  = request.getParameter("idGrupo");
        request.setAttribute("idGrupo", idGrupo);
        //int idGrupo2= Integer.parseInt(idGrupo);
        //Grupos_MB a = ReporteAlumnosInscritos_DAO.consultar(idGrupo2);
        request.getRequestDispatcher("/views/Alumnos/Paginas/ReportesAlumnos_View.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();            
        GenericResponse respuesta = new GenericResponse();
        //Obtener Reporte a generar
        String num= request.getParameter("numero");
        int numero = Integer.parseInt(num);
        
        if(numero==1){
            
            //Obtener Parametros
            String idGrupo  = request.getParameter("idGrupo");
            int anio;
            List<Alumnos_MB> alumnos;

            //Grupo 
            int idGrupo2= Integer.parseInt(idGrupo);
            Grupos_MB a = ReporteAlumnosInscritos_DAO.consultar(idGrupo2) ;

            //Periodo
            String periodo = a.getPeriodo();
            
            //Nombre de Actividad        
            ActividadExtraescolar_MB actnombre = ReporteAlumnosInscritos_DAO.consultarActividad(a.getIdActividad());
            String actividad=actnombre.getNombre();

            //Tipo Actividad
            String tipo = actnombre.getTipo();
            System.out.println(tipo);

            //Obtencion y creacion de logo ITT
            String imagenUrl = getClass().getClassLoader().getResource("img/header.png").toString().substring(6);            
            File file = new File(imagenUrl);
            InputStream imagenFile = new FileInputStream(file);
            String jrxmlFile;


            //Ruta Reporte
            jrxmlFile = getClass().getClassLoader().getResource("Reportes/Alumnos.jrxml").toString().substring(6);        

            //Alumnos
            alumnos = ReporteAlumnosInscritos_DAO.consultarAlumnoGrupo(idGrupo2);

            //Horario

            String horariocompleto="";
            List<HorariosGrupo_MB>  horarios = ReporteAlumnosInscritos_DAO.consultarhorario(idGrupo2);
            
            
            StringBuilder concatenacion = new StringBuilder();
            
            for (HorariosGrupo_MB horario : horarios) {
                concatenacion.append(horario.getDia());
                concatenacion.append(" ");
                concatenacion.append(horario.getHoraInicio());
                concatenacion.append("-");
                concatenacion.append(horario.getHoraFinal());
                concatenacion.append(",");
                // Asumiendo que HorariosGrupo_MB tiene un método toString() adecuado
                // Puedes ajustar la concatenación según la estructura y el formato que desees
            }
            horariocompleto=concatenacion.toString();
            
            try {

                JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);            
                Map<String, Object> map = new HashMap<>();


                if(imagenFile.available()>0){
                    //Hace el put
                    
                    map.put(Constantes.NOMBRELOGO, imagenFile);                

                }

                JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(alumnos);

                //System.out.println(":0 "+ds.toString());
                


                    map.put("Actividad",actividad);
                    map.put("Promocion",tipo);
                    map.put("periodo",periodo);                    
                    map.put("anio",idGrupo2);
                    System.out.println(horariocompleto);
                    map.put("Horario",horariocompleto);
                    map.put("ds", ds); 
                    //map.put("jefatura",Constantes.NOMBREJEFATURA);
                    //map.put("oficinaPromocion",Constantes.NOMBREJEFATURAPROMOCION);
                


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
            
            
        }else if(numero==2){
            //Obtener Parametros
            String idGrupo  = request.getParameter("idGrupo");
            int anio=0;
            List<Cuenta> cuentas= new ArrayList<>();;

            //Grupo 
            int idGrupo2= Integer.parseInt(idGrupo);
            Grupos_MB a = ReporteAlumnosInscritos_DAO.consultar(idGrupo2) ;

            //Periodo
            String periodo = a.getPeriodo();
            
            //Nombre de Actividad        
            ActividadExtraescolar_MB actnombre = ReporteAlumnosInscritos_DAO.consultarActividad(a.getIdActividad());
            String actividad=actnombre.getNombre();
            
            //Obtencion y creacion de logo ITT
            String imagenUrl = getClass().getClassLoader().getResource("img/header.png").toString().substring(6);            
            File file = new File(imagenUrl);
            InputStream imagenFile = new FileInputStream(file);
            String jrxmlFile;

            //Ruta Reporte
            jrxmlFile = getClass().getClassLoader().getResource("Reportes/AlumnosCarreraActividad.jrxml").toString().substring(6); 
            
            
            //Datos
            
            int cuentatotalH=ReporteAlumnosInscritos_DAO.consultarAlumnoGrupoHombres(idGrupo2);
            int cuentatotalM=ReporteAlumnosInscritos_DAO.consultarAlumnoGrupoMujeres(idGrupo2);
            int cuentaaux;
            
            
            try {

                JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);            
                Map<String, Object> map = new HashMap<>();


                if(imagenFile.available()>0){
                    //Hace el put
                    
                    map.put(Constantes.NOMBRELOGO, imagenFile);                

                }

                
                for(String aux : Constantes.CARRERASABRE){
                    Cuenta cuenta = new Cuenta();
                    cuenta.setCarrera(aux);
                    for(int i=1;i<=13;i++){
                        cuentaaux=ReporteAlumnosInscritos_DAO.consultarAlumnoGrupoSemestre(idGrupo2, i, aux); 
                        //System.out.println(aux);
                        //System.out.println(idGrupo2);
                        //System.out.println(cuentaaux);
                        //System.out.println(i);
                        switch(i){
                            case 1:
                                cuenta.setCarrera1(cuentaaux);
                                break;
                            case 2:
                                cuenta.setCarrera2(cuentaaux);
                            break;
                            case 3:
                                cuenta.setCarrera3(cuentaaux);
                                break;
                            case 4:
                                cuenta.setCarrera4(cuentaaux);
                            break;
                            case 5:
                                cuenta.setCarrera5(cuentaaux);
                                break;
                            case 6:
                                cuenta.setCarrera6(cuentaaux);
                            break;
                            case 7:
                                cuenta.setCarrera7(cuentaaux);
                                break;
                            case 8:
                                cuenta.setCarrera8(cuentaaux);
                            break;
                            case 9:
                                cuenta.setCarrera9(cuentaaux);
                                break;
                            case 10:
                                cuenta.setCarrera10(cuentaaux);
                            break;
                            case 11:
                                cuenta.setCarrera11(cuentaaux);
                                break;
                            case 12:
                                cuenta.setCarrera12(cuentaaux);
                            break;
                            case 13:
                                cuenta.setCarrera13(cuentaaux);
                            break;                                
                        }                      
                    }    
                    cuenta.setOtros(0); 
                    cuentas.add(cuenta);
                }
                
                JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(cuentas);
                //System.out.println(":0 "+ds.toString());
                

                    map.put("TotalM",cuentatotalH);
                    map.put("TotalF",cuentatotalM);
                    map.put("Actividad",actividad);                    
                    map.put("periodo",periodo);                    
                    map.put("anio",anio);
                    map.put("ds", ds); 
                    //map.put("jefatura",Constantes.NOMBREJEFATURA);
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
            
            
            
            
        }else if(numero==3){
            
            System.out.println(":D");
            
            //Obtener Parametros
            String idGrupo  = request.getParameter("idGrupo");
            int anio;
            List<Alumnos_MB> alumnos;

            //Grupo 
            int idGrupo2= Integer.parseInt(idGrupo);
            Grupos_MB a = ReporteAlumnosInscritos_DAO.consultar(idGrupo2) ;

            //Periodo
            String periodo = a.getPeriodo();
            
            //Nombre de Actividad        
            ActividadExtraescolar_MB actnombre = ReporteAlumnosInscritos_DAO.consultarActividad(a.getIdActividad());
            String actividad=actnombre.getNombre();

            //Tipo Actividad
            String tipo = actnombre.getTipo();
            System.out.println(tipo);

            //Obtencion y creacion de logo ITT
            String imagenUrl = getClass().getClassLoader().getResource("img/header.png").toString().substring(6);            
            File file = new File(imagenUrl);
            InputStream imagenFile = new FileInputStream(file);
            String jrxmlFile;
            
            //Ruta Reporte
            jrxmlFile = getClass().getClassLoader().getResource("Reportes/Final.jrxml").toString().substring(6);        

            //Alumnos
            alumnos = ReporteAlumnosInscritos_DAO.consultarAlumnoGrupo2(idGrupo2);

            //Horario

            String horariocompleto="";
            List<HorariosGrupo_MB>  horarios = ReporteAlumnosInscritos_DAO.consultarhorario(idGrupo2);
            
            
            StringBuilder concatenacion = new StringBuilder();
            
            for (HorariosGrupo_MB horario : horarios) {
                concatenacion.append(horario.getDia());
                concatenacion.append(" ");
                concatenacion.append(horario.getHoraInicio());
                concatenacion.append("-");
                concatenacion.append(horario.getHoraFinal());
                concatenacion.append(",");
                // Asumiendo que HorariosGrupo_MB tiene un método toString() adecuado
                // Puedes ajustar la concatenación según la estructura y el formato que desees
            }
            horariocompleto=concatenacion.toString();
            
            try {

                JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);            
                Map<String, Object> map = new HashMap<>();


                if(imagenFile.available()>0){
                    //Hace el put
                    
                    map.put(Constantes.NOMBRELOGO, imagenFile);                

                }

                JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(alumnos);

                //System.out.println(":0 "+ds.toString());
                


                    map.put("Actividad",actividad);
                    map.put("Promocion",tipo);
                    map.put("periodo",periodo);                    
                    map.put("anio",idGrupo2);
                    System.out.println(horariocompleto);
                    map.put("Horario",horariocompleto);
                    map.put("ds", ds); 
                    //map.put("jefatura",Constantes.NOMBREJEFATURA);
                    //map.put("oficinaPromocion",Constantes.NOMBREJEFATURAPROMOCION);
                


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

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
