<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="usuario" value="${sessionScope.usuario}" />


<c:set var="rolUsuario" value="${usuario.nombrePuesto}" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Iniciar Sesion</title>
        <link rel="icon" href="/CDN-ITT/img/logo.png">

        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/jquery-ui.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/botones.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="/CDN-ITT/css/spinner.estandarITT.css" rel="stylesheet" type="text/css"/>
        <link href="css/cssgenerados/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:choose>
            <c:when test="${rolUsuario eq 'Estudiante'}">
                <%@include file="../../views/templates/HeaderAlumno_View.jsp" %>
            </c:when>
            <c:when test="${rolUsuario eq 'Admin'}">
                <%@include file="../../views/templates/Header_View.jsp" %>
            </c:when>
            <c:when test="${rolUsuario eq 'Maestro'}">
                <%@include file="../../views/templates/HeaderMaestro_View.jsp" %>
            </c:when>
            <c:otherwise>
                <%-- Manejo para otros roles o situaciones --%>
                <%@include file="../../views/templates/HeaderInvitado_View.jsp" %>
            </c:otherwise>
        </c:choose>
        
        <div class="container">
            <div class="row">
                <div class="col-sm-offset-4 col-sm-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="panel-title">Iniciar sesión</div>
                        </div>
                        <div class="panel-body">
                            <form id="formLogin">
                                <div class="form-group">
                                    <label for="itt_username">Usuario</label>
                                    <input
                                        id="itt_username"
                                        name="itt_username"
                                        type="text"
                                        class="form-control"
                                        placeholder="Usuario"
                                        required
                                        autofocus
                                        maxlength="30"
                                        autocomplete="off"
                                        
                                        />
                                </div>
                                <div class="form-group">
                                    <label for="itt_password">Contraseña</label>
                                    <input
                                        id="itt_password"
                                        name="itt_password"
                                        type="password"
                                        class="form-control"
                                        placeholder="Contraseña"
                                        required
                                        maxlength="30"
                                        
                                        autocomplete="off"
                                        />
                                </div>
                                <div id="divCaptcha" class="form-group">
                                    
                                    <div class="form-group">
                                        <button
                                            id="btnLogin" type="submit" class="btn btn-primary pull-right" value="Ingresar" >
                                            <i id="spin_carga" class="fa fa-spinner fa-spin" style="display: none;"></i>
                                            Ingresar
                                        </button>
                                    </div>
                            
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            <%@include file="../../views/templates/Footer_View.jsp"%>
            </div>
        <!-- Third party scripts -->
        
        <script>
          

        </script>
        
        </div>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery-ui.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\base.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\spinner.estandarITT.js"></script>
        
        
        
        <script src="js/jsgenerados/Inicio.js" type="text/javascript"></script>
        <script src="js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="js/lib/bootbox.min.js" type="text/javascript"></script>
        <script src="js/jsgenerados/constantes.js" type="text/javascript"></script>
        <script src="js/jsgenerados/login.js" type="text/javascript"></script>
        <script src="js/jsgenerados/animaciones.js" type="text/javascript"></script>
        
    </body>
</html>
