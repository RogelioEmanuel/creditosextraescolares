
package Servlets.Eventos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Eventos.Evento_MB;
import Servlets.ActividadExtraescolar.CrearActividadExtraescolar;
import Servlets.Maestros.CrearMaestro_Srv;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.eventos.Evento_EditarEvento_DAO;
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
public class EditarEventos_Srv extends HttpServlet {

   
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       int idEvento = Integer.parseInt(request.getParameter("idEvento"));
       HttpSession session = request.getSession();
       session.setAttribute("idEvento", idEvento);          
       Evento_MB evento = Evento_EditarEvento_DAO.consultar(idEvento);
       request.setAttribute("evento", evento);
       
       List<ActividadExtraescolar_MB> actividad = new ArrayList<>();  
       actividad = Evento_EditarEvento_DAO.consultarActividad(); 
        
       
       request.setAttribute("actividad", actividad); 
       request.getRequestDispatcher("/views/Eventos/Paginas/EditarEvento_View.jsp").forward(request, response);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //datos dede el request
        PrintWriter out = response.getWriter();    
        int idEvento =Integer.parseInt(request.getParameter("idEvento"));
        String nombre = request.getParameter("nombre");
        String institucionorganizadora = request.getParameter("institucionOrganizadora");
        String tipo = request.getParameter("tipo");        
        String periodo = request.getParameter("periodo");
        int actividad =Integer.parseInt(request.getParameter("actividad"));  
        String resultado= request.getParameter("resultado");
        int parth =Integer.parseInt(request.getParameter("parth"));
        int partm =Integer.parseInt(request.getParameter("partm"));
        //int anio = Integer.parseInt(request.getParameter("anio"));
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
        
        
              
        Evento_MB evento = new Evento_MB(idEvento,nombre,parth,partm, institucionorganizadora, tipo,periodo,fechaEvento,actividad,resultado);
        
        System.out.println("vacio"+idEvento);
        Evento_EditarEvento_DAO.actualizarEvento(evento, resp);
        
       
        

                
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
