package Servlets.Master;

import ManageBean.Departamento.DatosDepartamento_MB;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import masterDao.Empleado;
import masterDao.EmpleadoDao;

/**
 *
 * @author kike
 */
@WebServlet(name = "Master_Srv", urlPatterns = {"/Master_Srv"})
public class Master_Srv extends HttpServlet {

    protected String myParm = null;
    protected String myContextParam = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.myParm = config.getInitParameter("AppTitle");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        EmpleadoDao empleadoDao = new EmpleadoDao();
        Empleado empleado = empleadoDao.obtenerDatos(token);
        if (empleado != null && empleado.getLeer() == 1) {
            myContextParam = request.getSession().getServletContext().getInitParameter("AppName");
            HttpSession session = request.getSession();
            session.setAttribute("usuario", empleado);
            //session.setAttribute("User", empleado.getIdEmpleado() + "");
            //session.setAttribute("Nombre", empleado.getNombre());
            //session.setMaxInactiveInterval(600000);
            DatosDepartamento_MB departamento = EmpleadoDao.getDepartamento(empleado);
            session.setAttribute("departamento", departamento);

            if (empleado.getCrear() == 1 && empleado.getEditar() == 1 && empleado.getEliminar() == 1) {
                if (departamento.getIdDepartamento() == 56) {
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("app/Eventos/ListaEventos.do");
                }
            } else {
                response.sendRedirect("views/templates/error403.jsp");
            }

        } else {
            response.sendRedirect("views/templates/error403.jsp");
        }
    }

}
