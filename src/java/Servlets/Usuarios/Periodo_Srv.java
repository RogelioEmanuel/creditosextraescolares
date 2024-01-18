
package Servlets.Usuarios;

import ManageBean.Periodo.Periodo;
import Servlets.ActividadExtraescolar.EditarActividadExtraescolar_Srv;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.Periodo.Periodo_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Periodo_Srv extends HttpServlet {

   

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
               
        
        
        Periodo per = Periodo_DAO.consultar();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fecha1="";
        String fecha2="";
        
        if(per.getFecha_inicio()!=null){
            fecha1=formatoFecha.format(per.getFecha_inicio());
        }
        if(per.getFecha_fin()!=null){
            fecha2=formatoFecha.format(per.getFecha_fin());
        }
        
        
        request.setAttribute("fechaI", fecha1);
        request.setAttribute("fechaF", fecha2);
        request.setAttribute("periodo", per);
        
        request.getRequestDispatcher("/views/Periodo/Paginas/EditarDatosPeriodo.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaComienzo = null;
        Date fechaTerminar = null;
        GenericResponse resp = new GenericResponse();
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");
        int diasi = Integer.parseInt(request.getParameter("insscripcion"));
        int diasf = Integer.parseInt(request.getParameter("cierre"));
        String periodo = request.getParameter("periodo");
        try {
            fechaComienzo= f.parse(fechaInicio);                        
        } catch (ParseException ex) {
            fechaComienzo = null;            
        }        
        try {            
            fechaTerminar= f.parse(fechaFin);
                        
        } catch (ParseException ex) {
            fechaTerminar = null;            
        }                
        Periodo per = new Periodo();
        per.setCierre(diasf);
        per.setFecha_fin(fechaTerminar);
        per.setFecha_inicio(fechaComienzo);
        per.setInsscripcion(diasi);
        per.setPeriodo(periodo);        
        Periodo_DAO.actualizarPeriodo(per, resp);        
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
        } catch (Exception e) {
            Logger.getLogger(Periodo_Srv.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
