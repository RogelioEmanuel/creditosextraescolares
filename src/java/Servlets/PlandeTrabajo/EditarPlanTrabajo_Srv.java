
package Servlets.PlandeTrabajo;

import ManageBean.PlanSemana.PlanSemana_MB;
import ManageBean.PlandeTrabajo.PlanTrabajo_MB;
import Servlets.ActividadExtraescolar.CrearActividadExtraescolar;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.plandetrabajo.Planes_CrearPlan_DAO;
import dao.plandetrabajo.Planes_DetallarPlan_DAO;
import dao.plandetrabajo.Planes_EditarPlan_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EditarPlanTrabajo_Srv extends HttpServlet {


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        if(request.getParameter("idPlan")!=null){
            int idPlanTrabajo=  Integer.parseInt(request.getParameter("idPlan")); 
            PlanTrabajo_MB plant = Planes_DetallarPlan_DAO.consultarPlan(idPlanTrabajo) ;
            List<PlanSemana_MB> planessemanal = Planes_DetallarPlan_DAO.consultarPlanSemanal(plant.getIdPlan());
            HttpSession session = request.getSession();
            session.setAttribute("planessemanal", planessemanal);  
            session.setAttribute("plant", plant);
            request.getRequestDispatcher("/views/Planes_de_Trabajo/Paginas/EditarPlan_View.jsp").forward(request, response);
            
        }else{
            request.getRequestDispatcher("/views/Planes_de_Trabajo/Paginas/CrearPlan_View.jsp").forward(request, response);
        }
            
        
        
        
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
        
        String idPlanS = request.getParameter("idPlan");
        int idPlan = Integer.parseInt(idPlanS);
        
        
        for (int i = 1; i <= 16; i++) {
            String programa = request.getParameter("programasemana" + i);
            String plataforma = request.getParameter("plataformasemana" + i);
            String descripcion = request.getParameter("descripcionsemana" + i);
            
            
            if (confirmacion(programa, plataforma, descripcion)) {
                
                PlanSemana_MB plansemana = new PlanSemana_MB(idPlan,i, programa, plataforma, descripcion);
                Planes_EditarPlan_DAO.actualizarPlanSemanal(plansemana, resp);
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
