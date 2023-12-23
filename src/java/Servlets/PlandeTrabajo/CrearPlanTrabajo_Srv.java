package Servlets.PlandeTrabajo;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.HorariosGrupo.HorariosGrupo_MB;
import ManageBean.Maestros.Maestros_MB;
import ManageBean.PlanSemana.PlanSemana_MB;
import ManageBean.PlandeTrabajo.PlanTrabajo_MB;
import Servlets.ActividadExtraescolar.CrearActividadExtraescolar;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.Grupos.Grupos_CrearGrupo_DAO;
import dao.plandetrabajo.Planes_CrearPlan_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CrearPlanTrabajo_Srv extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        List<Maestros_MB> maestros = new ArrayList<>();  
        maestros = Planes_CrearPlan_DAO.consultarMaestro();  
        request.setAttribute("maestros", maestros);
        
        
        
        List<ActividadExtraescolar_MB> actividades = new ArrayList<>();  
        actividades = Planes_CrearPlan_DAO.consultarActividad();  
        
        request.setAttribute("actividades", actividades);
        
        
        request.getRequestDispatcher("/views/Planes_de_Trabajo/Paginas/CrearPlan_View.jsp").forward(request, response);
    }
    
    
    protected boolean confirmacion(String programa, String plataforma,String descripcion){   
        return (programa != null && !programa.isEmpty() && plataforma != null && !plataforma.isEmpty() && descripcion != null && !descripcion.isEmpty());
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();    
        HttpSession session = request.getSession();
        GenericResponse resp = new GenericResponse();
        
        int idActividad_Extraescolar = Integer.parseInt (request.getParameter("actividades")); 
        int idMaestros = Integer.parseInt(request.getParameter("maestro"));
               
        PlanTrabajo_MB plan = new PlanTrabajo_MB(idActividad_Extraescolar,idMaestros);   
        
        
        

        Planes_CrearPlan_DAO.insertar(plan, resp);
        
        
        for (int i = 1; i <= 16; i++) {
            String programa = request.getParameter("programasemana" + i);
            String plataforma = request.getParameter("plataformasemana" + i);
            String descripcion = request.getParameter("descripcionsemana" + i);
            
            
            if (confirmacion(programa, plataforma, descripcion)) {
                PlanSemana_MB plansemana = new PlanSemana_MB(i, programa, plataforma, descripcion);
                Planes_CrearPlan_DAO.insertarPlanSemanal(plansemana, resp);
            }
        }

        
         

                
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
