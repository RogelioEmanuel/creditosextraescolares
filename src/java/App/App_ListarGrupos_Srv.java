
package App;

import ManageBean.Grupos.Grupos_MB;
import Utilidades.GenericResponse;
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
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        GenericResponse resp = new GenericResponse<>();
        int idMaestro = -1;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case "idEvento":
                        if (cookie.getValue() != null) {
                            idMaestro = Integer.parseInt((cookie.getValue()));
                        }
                        List<Grupos_MB> grupos = new ArrayList<>();  
                        grupos = Grupos_ListarGrupos_DAO.consultarGr(idMaestro);
                        resp.setResponseObject(request);                        
                        break;
                }
            }
        }

        PrintWriter out = response.getWriter();

        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
