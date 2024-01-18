
package Servlets.GruposAlumno;

import ManageBean.Alumnos.Alumnos_MB;
import Utilidades.GenericResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import dao.creditos.Creditos_EditarCredito_DAO;
import dao.gruposyalumno.GruposAlumno_ListaAlumnos_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static javax.ws.rs.client.Entity.json;

/**
 *
 * @author Emanuel
 */
public class CalificarAlumnos_Srv extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Alumnos_MB> alumnos = new ArrayList<>();
        HttpSession session = request.getSession();
        GenericResponse respuesta = new GenericResponse<>();
        int id = Integer.parseInt(request.getParameter("idGrupo"));                
        alumnos = GruposAlumno_ListaAlumnos_DAO.consultarAlumnoGrupo2(id);
        request.setAttribute("alumnos", alumnos);
        request.setAttribute("idGrupo", id);
        
       
        request.getRequestDispatcher("/views/Alumnos/Paginas/ColocarCalificacion_View.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));        
        JsonObject jsonObject = new JsonParser().parse(requestBody).getAsJsonObject();
        int idGrupo = jsonObject.get("idGrupo").getAsInt();

        
        Map<String, String> calificaciones = convertirJsonAMapa(requestBody);

        // Realizar la lógica necesaria con las calificaciones
        procesarCalificaciones(calificaciones,idGrupo);
        
        // Puedes enviar una respuesta al cliente si es necesario
        response.getWriter().write("Calificaciones recibidas correctamente");
        
        
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
       
    private Map<String, String> convertirJsonAMapa(String json) {
        try {
            Gson gson = new Gson();

            // Convierte el JSON a un mapa
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            JsonObject calificacionesJson = jsonObject.getAsJsonObject("calificaciones");

            // Convierte el objeto de calificaciones a un mapa
            return gson.fromJson(calificacionesJson, new TypeToken<Map<String, String>>() {}.getType());
        } catch (JsonSyntaxException e) {
            // Maneja las excepciones apropiadamente
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }
    
    private void procesarCalificaciones(Map<String, String> calificaciones,int idGrupo) {
        
        calificaciones.forEach((idAlumno, calificacion) -> {
            if(idAlumno!=null&&!idAlumno.isEmpty()&&!idAlumno.equals("undefined")){
                GenericResponse respuesta = new GenericResponse<>();
                System.out.println("ID Alumno: " + idAlumno + ", Calificación: " + calificacion);
                Creditos_EditarCredito_DAO.actualizarCredito(idAlumno,calificacion,idGrupo,respuesta);
            }
            
            
        });
        
      
    }

}
