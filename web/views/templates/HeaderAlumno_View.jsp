


<%@page import="Utilidades.Constantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    Constantes constantes = new Constantes();

    
    boolean periodoInscripcionActivo = constantes.esAntesDeInscripciones();
%>
<!DOCTYPE html>

<div class="container" align="center">
    <img class="img-responsive" src="/CDN-ITT/img/header.png" alt="encabezado"/>
</div>
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>                        
                </button>
                <a class="navbar-brand" href="/creditosextraescolares/index.jsp">Creditos Extraescolares</a>
            </div>
            
            
            
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">     
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-cog"> </i> Rogelio <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/creditosextraescolares/app/actividadextraescolar/listaractividad.do"><span class="fa fa-user dropdown-item"></span>Mis Grupos</a></li>
                            
                           <% if (periodoInscripcionActivo) { %>
                                <li><a href="/creditosextraescolares/app/grupos/inscripciongrupo.do"><span class="fa fa-user dropdown-item"></span>Inscripción a Grupo</a></li>
                            <% } else { %>
                                <li><a href="#" disabled title="No disponible por el momento"><span class="fa fa-user dropdown-item"></span>Inscripción a Grupo</a></li>
                            <% } %>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
