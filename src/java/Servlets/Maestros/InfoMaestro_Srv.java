package Servlets.Maestros;

import ManageBean.Maestros.Maestros_MB;
import dao.maestros.Maestros_EditarMaestros_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import masterDAO.Empleado;


public class InfoMaestro_Srv extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InfoMaestro_Srv</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InfoMaestro_Srv at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
       // int idMaestro = Integer.parseInt(request.getParameter("idMaestro"));   
       
       
        HttpSession session = request.getSession();
        Empleado maestro2= (Empleado) session.getAttribute("usuario");
        
        
        //System.out.println("DEberia"+maestro.getIdEmpleado());
        int idMaestro= maestro2.getIdEmpleado();
        
        session.setAttribute("idMaestro", idMaestro);   
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Maestros_MB maestro = Maestros_EditarMaestros_DAO.consultar(idMaestro);
        
        String nombreMaestro = maestro.getNombre();
        String apPaterno = maestro.getApPaterno();
        String apMaterno = maestro.getApMaterno();
        String correo = maestro.getCorreo();
        String telefono = maestro.getTelefono();
        
        String rfc =maestro.getRfc();
        String curp = maestro.getCurp();
        String banco = maestro.getBanco();
        String clave = maestro.getClave();
        String sexo = maestro.getSexo();
        String direccion = maestro.getDireccion();
        String fecha_nacimiento="";
        
        if (maestro.getFecha_nacimiento() != null) {
            fecha_nacimiento = formatoFecha.format(maestro.getFecha_nacimiento());
        }
        
        
        
        
        
        request.setAttribute("nombre", nombreMaestro);
        request.setAttribute("appaterno", apPaterno);
        request.setAttribute("apmaterno", apMaterno);
        request.setAttribute("correo", correo);
        request.setAttribute("telefono", telefono);
        request.setAttribute("rfc", rfc);
        request.setAttribute("curp", curp);
        request.setAttribute("banco", banco);
        request.setAttribute("claveinterbancaria", clave);
        request.setAttribute("sexo", sexo);
        request.setAttribute("fecha_nacimiento", fecha_nacimiento);
        request.setAttribute("direccion",direccion);       
        
        request.getRequestDispatcher("/views/Maestros/Paginas/InfoPersonal_View.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
