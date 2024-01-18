
package App;

import ManageBean.Grupos.Grupos_MB;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import com.google.gson.Gson;
import dao.Grupos.Grupos_ListarGrupos_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "App_ListarGrupos_Srv", urlPatterns = {"/app/grupos/ListarGruposApp.do"})
public class App_ListarGrupos_Srv extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        GenericResponse resp = new GenericResponse<>();
        List<Grupos_MB> grupos = new ArrayList<>();
        
        int idMaestro = -1;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case "idEmpleado":
                        if (cookie.getValue() != null) {
                            
                            idMaestro = Integer.parseInt((cookie.getValue()));
                            
                        }
                          grupos = Grupos_ListarGrupos_DAO.consultarGr(idMaestro);
                        
                                                
                        break;
                }
            }
        }

        resp.setStatus(Validaciones.VALIDATION_EXP);
        resp.setMensaje("Ok");
        resp.setResponseObject(grupos);
        
        
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
            String jsonResponse = json.toJson(resp);
            //System.out.println("JSON Response: " + jsonResponse);
        } catch (Exception e) {
            System.out.println(e);
        }
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
