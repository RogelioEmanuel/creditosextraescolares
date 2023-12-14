package Servlets.Eventos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Eventos.Evento_MB;
import Servlets.ActividadExtraescolar.CrearActividadExtraescolar;
import Servlets.Maestros.CrearMaestro_Srv;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.eventos.Eventos_CrearEvento_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emanuel
 */
public class CrearEventos_Srv extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        List<ActividadExtraescolar_MB> actividad = new ArrayList<>();  
        actividad = Eventos_CrearEvento_DAO.consultarActividad(); 
        
       
        request.setAttribute("actividad", actividad); 
        
        
        request.getRequestDispatcher("/views/Eventos/Paginas/CrearEvento_View.jsp").forward(request, response);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        //datos dede el request
        PrintWriter out = response.getWriter();    
        String nombre = request.getParameter("nombre");
        String institucionorganizadora = request.getParameter("institucionOrganizadora");
        String tipo = request.getParameter("tipo");        
        String periodo = request.getParameter("periodo");
        int actividad =Integer.parseInt(request.getParameter("actividad"));  
        String resultado= request.getParameter("resultado");
        int parth =Integer.parseInt(request.getParameter("parth"));
        int partm =Integer.parseInt(request.getParameter("partm"));
        //fechas
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");        
        String fecha = request.getParameter("fecha");        
        Date fechaEvento=null;        
        try{
            
            fechaEvento= f.parse(fecha);
            System.out.println("Fecha parseada correctamente: " + f.format(fechaEvento));
        } catch (ParseException ex) {
            System.out.println("salio mal");
            Logger.getLogger(CrearMaestro_Srv.class.getName()).log(Level.SEVERE, null, ex);
            fechaEvento=null;
            
        }
        
        
        
        HttpSession session = request.getSession();
        GenericResponse resp = new GenericResponse();
        
        
              
        Evento_MB evento = new Evento_MB(nombre,parth,partm, institucionorganizadora, tipo,periodo,fechaEvento,actividad,resultado);
        
        
        Eventos_CrearEvento_DAO.insertar(evento, resp);
        
       
        

                
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
        } catch (Exception e) {
            Logger.getLogger(CrearActividadExtraescolar.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
