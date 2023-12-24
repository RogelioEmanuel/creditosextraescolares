package Servlets.PlandeTrabajo;

import ManageBean.Maestros.Maestros_MB;
import ManageBean.PlanSemana.PlanSemana_MB;
import ManageBean.PlandeTrabajo.PlanTrabajo_MB;
import dao.maestros.Maestros_EditarMaestros_DAO;
import dao.plandetrabajo.Planes_DetallarPlan_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DetallarPlanTrabajo extends HttpServlet {


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int idPlanTrabajo=  Integer.parseInt(request.getParameter("idPlan")); 
        PlanTrabajo_MB plant = Planes_DetallarPlan_DAO.consultarPlan(idPlanTrabajo) ;
        List<PlanSemana_MB> planessemanal = Planes_DetallarPlan_DAO.consultarPlanSemanal(plant.getIdPlan());
        HttpSession session = request.getSession();
        session.setAttribute("planessemanal", planessemanal);  
        session.setAttribute("plant", plant);
        
        
        request.getRequestDispatcher("/views/Planes_de_Trabajo/Paginas/DetallarPlan_View.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
