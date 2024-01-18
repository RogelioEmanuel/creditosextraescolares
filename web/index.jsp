
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="usuario" value="${sessionScope.usuario}" />
<c:set var="rolUsuario" value="${usuario.nombrePuesto}" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Constantes constantes2 = new Constantes();
    boolean periodoCalifActivo = constantes2.asentarCalificaciones();
%>
        

<!DOCTYPE html>
<html>
     <head>
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Home</title>
        <meta charset="UTF-8">
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/CDN-ITT/css/jquery-ui.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="../CDN-ITT/css/botones.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="../CDN-ITT/css/spinner.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="css/cssgenerados/styles.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/cssgenerados/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <header>
        <c:choose>
            <c:when test="${rolUsuario eq 'Estudiante'}">
                <%@include file="views/templates/HeaderAlumno_View.jsp" %>
            </c:when>
            <c:when test="${rolUsuario eq 'Admin'}">
                <%@include file="views/templates/Header_View.jsp" %>
            </c:when>
            <c:when test="${rolUsuario eq 'Maestro'}">
                <%@include file="views/templates/HeaderMaestro_View.jsp" %>
            </c:when>
            <c:otherwise>
                <%-- Manejo para otros roles o situaciones --%>
                <%@include file="/views/templates/HeaderInvitado_View.jsp" %>
            </c:otherwise>
        </c:choose>
        
    </header>
   <%
        String rolUsuario = (String) pageContext.getAttribute("rolUsuario");
        if(periodoCalifActivo && !rolUsuario.equals("Estudiante")) {
    %>
        <label id="periodoActivoLabel" style="display: none;">Activo</label>
    <%
        }
    %>


    <body>
        
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <label id="bLabel" style="display: none;">${periodo.fecha_fin}</label>
                </div>
                <div class="panel-body">
                    <div class="jumbotron centerfy">
                        <h1 align="center" style="border-radius:14px;background-color:#FF8D57">Bienvenido</h1>
                         <div class="content-center" ><img class="img-responsive" src="/creditosextraescolares/img/logo.png" alt="Home" width="350px" height="350px"/></div>
                         
                    </div>

                </div>
            </div>
        </div>

       
        
        <script type="text/javascript" src ="\CDN-ITT\js\jquery.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery-ui.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\base.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\spinner.estandarITT.js"></script>
              
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.plugin.autotable.min.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
        
        <script src="js/jsgenerados/Inicio.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/AlertaPeriodo.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/Inicio.js" type="text/javascript"></script>
    </body>
    <footer><%@include file="views/templates/Footer_View.jsp" %></footer>
</html>
