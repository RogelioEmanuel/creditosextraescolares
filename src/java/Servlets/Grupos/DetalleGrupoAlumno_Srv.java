
package Servlets.Grupos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.HorariosGrupo.HorariosGrupo_MB;
import ManageBean.Maestros.Maestros_MB;
import dao.Grupos.Grupos_CrearGrupo_DAO;
import dao.Grupos.Grupos_DetalleGrupo_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emanuel
 */
public class DetalleGrupoAlumno_Srv extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("idGrupo")!=null){
            int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));        
            HttpSession session = request.getSession();
            session.setAttribute("idGrupo", idGrupo);                  
            Grupos_MB grupo = Grupos_DetalleGrupo_DAO.consultar(idGrupo);


            String periodo = grupo.getPeriodo();
            int idActividad = grupo.getIdActividad();

            int cupo = grupo.getCupo();
            int noGrupo =grupo.getNoGrupo();
            int horastotales = grupo.getTotalhorassemanales();
            int idMaestros = grupo.getIdMaestros();
            System.out.println("horas"+horastotales);

            request.setAttribute("noGrupo", noGrupo);
            request.setAttribute("cupo", cupo);
            request.setAttribute("periodo", periodo);        
            request.setAttribute("idMaestros", idMaestros);        
            request.setAttribute("idActividad_Extraescolar", idActividad);        
            request.setAttribute("totalhorassemanal", horastotales); 
            List<Maestros_MB> maestros = new ArrayList<>();  
            maestros = Grupos_CrearGrupo_DAO.consultarMaestro();  
            request.setAttribute("maestros", maestros);

           session.setAttribute("idActividad", idActividad); 

           ActividadExtraescolar_MB actividad = new ActividadExtraescolar_MB();  
           actividad = Grupos_DetalleGrupo_DAO.consultarActividad(idActividad);

           //HorariosGrupo_MB horario = new HorariosGrupo_MB();
            List<HorariosGrupo_MB> horarios = new ArrayList<>();  
            horarios = Grupos_DetalleGrupo_DAO.consultarhorario(idGrupo);  

            List<HorariosGrupo_MB> horariosCompleta = new ArrayList<>();  // Crear una nueva lista

            List<String> diasSemana = Arrays.asList("lunes", "martes", "miercoles", "jueves", "viernes", "sabado");
            for (String dia : diasSemana) {
                // Verificar si hay un horario para este día
                boolean encontrado = false;
                for (HorariosGrupo_MB horario : horarios) {
                    if (dia.equals(horario.getDia())) {
                        horariosCompleta.add(horario);  // Agregar a la nueva lista
                        encontrado = true;
                        break;
                    }
                }

                // Si no se encontró un horario para este día, agregar una entrada predeterminada
                if (!encontrado) {
                    HorariosGrupo_MB horarioDefault = new HorariosGrupo_MB();
                    horarioDefault.setDia(dia);                
                    horarioDefault.setHoraInicio(null); // Establecer hora de inicio predeterminada
                    horarioDefault.setHoraFinal(null);   // Establecer hora de fin predeterminada
                    horariosCompleta.add(horarioDefault);  // Agregar a la nueva lista
                }
            }
            request.setAttribute("horarios", horariosCompleta); 


           request.setAttribute("actividad", actividad);
        }
        
       
        
        
        
        request.getRequestDispatcher("/views/Grupos/Paginas/DetallesGruposAlumno.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
