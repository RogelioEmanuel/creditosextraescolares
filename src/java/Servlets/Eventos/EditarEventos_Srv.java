
package Servlets.Eventos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Eventos.Evento_MB;
import ManageBean.Usuarios.Usuarios_MB;
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
import masterDAO.Empleado;

/**
 *
 * @author Emanuel
 */
public class EditarEventos_Srv extends HttpServlet {

   
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session = request.getSession();
       int idEvento = Integer.parseInt(request.getParameter("idEvento"));
       Empleado a = (Empleado) session.getAttribute("usuario");
                
       Evento_MB evento = Evento_EditarEvento_DAO.consultar(idEvento);
       
       SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
       
       List<ActividadExtraescolar_MB> actividad = new ArrayList<>();  
       actividad = Evento_EditarEvento_DAO.consultarActividad(); 
       String fechaq="";
       if (evento.getFecha() != null) {
            fechaq = formatoFecha.format(evento.getFecha());
        }
       
       
       request.setAttribute("fecha", fechaq);
       request.setAttribute("idEvento", idEvento); 
       request.setAttribute("evento", evento);
       request.setAttribute("actividad", actividad); 
       request.getRequestDispatcher("/views/Eventos/Paginas/EditarEvento_View.jsp").forward(request, response);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Empleado a = (Empleado) session.getAttribute("usuario");
        
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
        String fecha = request.getParameter("fecha");
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");        
                
        System.out.println(fecha);
        Date fechaEvento=null;        
       
        try{
            
            fechaEvento= f.parse(fecha);
            System.out.println("Fecha parseada correctamente: " + f.format(fechaEvento));
        } catch (ParseException ex) {
            System.out.println("salio mal");
            Logger.getLogger(EditarEventos_Srv.class.getName()).log(Level.SEVERE, null, ex);
            fechaEvento=null;
            
        }
        
        
        
        
        GenericResponse resp = new GenericResponse();
        
        String ruta="";
        
        if(a.getNombrePuesto().equals("Admin")){
            ruta="'../../app/eventos/listarevento.do'";
        }else if(a.getNombrePuesto().equals("Maestro")){
            ruta="../../app/eventos/listareventomaestro.do";
        }
        
        
        Evento_MB evento = new Evento_MB(idEvento,nombre,parth,partm, institucionorganizadora, tipo,periodo,fechaEvento,actividad,resultado);
       
       
        Evento_EditarEvento_DAO.actualizarEvento(evento, resp);
        resp.setMensaje(ruta);
                       
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
