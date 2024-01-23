/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Usuarios;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emanuel
 */
public class ValidarCaptcha_Srv extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ValidarCaptcha_Srv</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ValidarCaptcha_Srv at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
         String respuesta = "no";
        try {
            String cadena_ingresada = request.getParameter("inpCaptcha");
            HttpSession session_nueva = request.getSession(false);
            String key = "";

            if (session_nueva == null) {
                respuesta = "El captcha caducó.";
            } else {
                Enumeration<String> attributeNames = session_nueva.getAttributeNames();
                boolean b = false;
                while (attributeNames.hasMoreElements()) {
                    if ("codigoCaptcha".equals(attributeNames.nextElement())) {
                        b = true;
                        break;
                    }
                }
                if (b) {
                    if (session_nueva.getAttribute("codigoCaptcha") == null) {
                        respuesta = "Error al cargar el código";

                    } else {
                        key = (String) session_nueva.getAttribute("codigoCaptcha");
                        if (cadena_ingresada == null) {
                            respuesta = "El código es obligatorio";

                        } else {
                            if (key.equals(cadena_ingresada)) {
                                respuesta = "si";
                            } else {
                                respuesta = "no";
                            }
                        }
                    }

                } else {
                    respuesta = "Error al cargar el código";
                }
            }

        } catch (Exception e) {
            respuesta = "Error fatal: " + getStackTrace(e);

        }
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }

    }
    
     public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
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
