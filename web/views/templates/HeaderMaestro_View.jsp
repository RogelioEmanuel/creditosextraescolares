


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="usuario" value="${sessionScope.usuario}" />
<c:set var="rolUsuario" value="${usuario.nombrePuesto}" />
<c:set var="nombreUsuario" value="${usuario.nombre}" />
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
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-cog"> </i> ${nombreUsuario} <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/creditosextraescolares/app/grupos/listargruposmaestro.do"><span class="fa fa-user dropdown-item"></span> Mis Grupos</a></li>
                            <li><a href="/creditosextraescolares/app/eventos/listareventomaestro.do"><span class="fa fa-user dropdown-item"></span>Eventos</a></li>
                            <li><a href="/creditosextraescolares/app/maestros/infomaestro.do"><span class="fa fa-user dropdown-item"></span>Mi informacion</a></li>
                            <li><a href="/creditosextraescolares//app/salir.do"><span class="fa fa-user dropdown-item"></span>Salir</a></li>
                            
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
