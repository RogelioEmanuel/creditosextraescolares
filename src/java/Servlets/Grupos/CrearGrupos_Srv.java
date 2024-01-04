package Servlets.Grupos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.HorariosGrupo.HorariosGrupo_MB;
import ManageBean.Maestros.Maestros_MB;
import Servlets.ActividadExtraescolar.CrearActividadExtraescolar;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.Grupos.Grupos_CrearGrupo_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CrearGrupos_Srv extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");  
        HttpSession session = request.getSession();
        GenericResponse respuesta = new GenericResponse<>();         
        List<Maestros_MB> maestros = new ArrayList<>();  
        maestros = Grupos_CrearGrupo_DAO.consultarMaestro();  
        request.setAttribute("maestros", maestros);     
        
        
        
        int idActividad = Integer.parseInt(request.getParameter("idActividad")); 
        session.setAttribute("idActividad", idActividad); 
        ActividadExtraescolar_MB actividad = new ActividadExtraescolar_MB();  
        actividad = Grupos_CrearGrupo_DAO.consultarActividad(idActividad);        
        request.setAttribute("actividad", actividad);       
        request.getRequestDispatcher("/views/Grupos/Paginas/CrearGrupos_View.jsp").forward(request, response);
    }
    
    protected boolean confirmacion(String inicio, String fin){   
       
        return (inicio != null && !inicio.isEmpty() && fin != null && !fin.isEmpty()) || (inicio == null && fin == null);
    }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();    
        HttpSession session = request.getSession();
        GenericResponse resp = new GenericResponse(); 
        
        //Obtencion de Valores
        int cupo = Integer.parseInt( request.getParameter("cupo"));
        int idActividad_Extraescolar = Integer.parseInt (request.getParameter("idActividad")); 
        int idMaestros = Integer.parseInt(request.getParameter("maestro"));
        String periodo = request.getParameter("periodo");
        int totalhorassemanal = Integer.parseInt(request.getParameter("horastotales"));
        int noGrupo =Integer.parseInt(request.getParameter("noGrupo"));
        //Horario por dias
        String lunesInicio = request.getParameter("lunesInicio");
        String lunesFin = request.getParameter("lunesFin");

        String martesInicio = request.getParameter("martesInicio");
        String martesFin = request.getParameter("martesFin");

        String miercolesInicio = request.getParameter("miercolesInicio");
        String miercolesFin = request.getParameter("miercolesFin");

        String juevesInicio = request.getParameter("juevesInicio");
        String juevesFin = request.getParameter("juevesFin");

        String viernesInicio = request.getParameter("viernesInicio");
        String viernesFin = request.getParameter("viernesFin");

        String sabadoInicio = request.getParameter("sabadoInicio");
        String sabadoFin = request.getParameter("sabadoFin");
        
        //List<String> diasSeleccionados  = new ArrayList<>();
        //Verificamos si existe un grupo con ese número 
        if(Grupos_CrearGrupo_DAO.revisarExistencia(noGrupo, resp)){
            
            resp.setMensaje("El numero de grupo ya existe");
            
        }else{//Si no, creamos el grupo
            Grupos_MB grupo = new Grupos_MB(noGrupo,cupo,periodo,idActividad_Extraescolar,idMaestros,totalhorassemanal);        
                
            Grupos_CrearGrupo_DAO.insertar(grupo, resp);
                       
            if (confirmacion(lunesInicio, lunesFin)) {
                // diasSeleccionados.add("lunes");

                HorariosGrupo_MB horario = new HorariosGrupo_MB("lunes", lunesInicio, lunesFin);
                Grupos_CrearGrupo_DAO.insertarHorario(horario, resp);
            }
            // Verificar para el martes
            if (confirmacion(martesInicio, martesFin)) {
                // diasSeleccionados.add("martes");
                HorariosGrupo_MB horario = new HorariosGrupo_MB("martes", martesInicio, martesFin);
                Grupos_CrearGrupo_DAO.insertarHorario(horario, resp);
            }
            // Verificar para el miércoles
            if (confirmacion(miercolesInicio, miercolesFin)) {
                // diasSeleccionados.add("miércoles");
                HorariosGrupo_MB horario = new HorariosGrupo_MB("miércoles", miercolesInicio, miercolesFin);
                Grupos_CrearGrupo_DAO.insertarHorario(horario, resp);
            }
            // Verificar para el jueves
            if (confirmacion(juevesInicio, juevesFin)) {
                // diasSeleccionados.add("jueves");
                HorariosGrupo_MB horario = new HorariosGrupo_MB("jueves", juevesInicio, juevesFin);
                Grupos_CrearGrupo_DAO.insertarHorario(horario, resp);
            }
            // Verificar para el viernes
            if (confirmacion(viernesInicio, viernesFin)) {
                // diasSeleccionados.add("viernes");
                HorariosGrupo_MB horario = new HorariosGrupo_MB("viernes", viernesInicio, viernesFin);
                Grupos_CrearGrupo_DAO.insertarHorario(horario, resp);
            }
            // Verificar para el sábado
            if (confirmacion(sabadoInicio, sabadoFin)) {
                // diasSeleccionados.add("sábado");
                HorariosGrupo_MB horario = new HorariosGrupo_MB("sábado", sabadoInicio, sabadoFin);
                Grupos_CrearGrupo_DAO.insertarHorario(horario, resp);
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
