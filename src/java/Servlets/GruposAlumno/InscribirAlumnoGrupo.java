
package Servlets.GruposAlumno;

import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.Grupos.Grupos_MB;
import Servlets.ActividadExtraescolar.CrearActividadExtraescolar;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.creditos.Creditos_CrearCredito_DAO;
import dao.gruposyalumno.GruposAlumno_Inscripcion_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class InscribirAlumnoGrupo extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       response.setContentType("text/html;charset=UTF-8");
       String id = request.getParameter("idGrupo");
       int idGrupo = Integer.parseInt(id);
       
       request.setCharacterEncoding("UTF-8");
       HttpSession session = request.getSession();
       request.setAttribute("idGrupo", idGrupo);
       
       
       request.getRequestDispatcher("/views/Alumnos/Paginas/AgregarAlumno_View.jsp").forward(request, response);
    }

    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();    
        String id = request.getParameter("idGrupo");
        int idGrupo = Integer.parseInt(id);
        String nReinscripcions = request.getParameter("nReinscripcion");
        int nReinscripcion = Integer.parseInt(nReinscripcions);
        HttpSession session = request.getSession();
        GenericResponse resp = new GenericResponse();
        
        
        Grupos_MB grupo = GruposAlumno_Inscripcion_DAO.consultarGrupo(idGrupo);
        
        Alumnos_MB alumno = GruposAlumno_Inscripcion_DAO.consultarAlumno("17280330");
         
        
        
        if(GruposAlumno_Inscripcion_DAO.estaInscrito(alumno,grupo,resp)||GruposAlumno_Inscripcion_DAO.estaInscrito2(alumno, resp)){
             resp.setMensaje("El alumno ya se encuentra inscrito a un grupo");
             resp.setStatus(800);
         }else{
            GruposAlumno_Inscripcion_DAO.inscribir(alumno,grupo,resp,nReinscripcion); 
            if(Creditos_CrearCredito_DAO.consultarCreditoSel(alumno.getNoControl())){
                
            }else if(Creditos_CrearCredito_DAO.consultarCredito(alumno.getNoControl())){
                
            }else{
                Creditos_CrearCredito_DAO.insertar(alumno, grupo, resp);
            }
         }
                            
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
        } catch (Exception e) {
            Logger.getLogger(CrearActividadExtraescolar.class.getName()).log(Level.SEVERE, null, e);
        }
        
        request.getRequestDispatcher("/views/Grupos/Paginas/InscripcionGrupo.jsp").forward(request, response);
        
        
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
