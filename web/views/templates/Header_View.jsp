


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
                            <li><a href="/creditosextraescolares/app/actividadextraescolar/listaractividad.do"><span class="fa fa-user dropdown-item"></span> Actividad Extraescolar</a></li>
                            <li><a href="/creditosextraescolares/app/creditoextraescolar/listarcredito.do"><span class="fa fa-user dropdown-item"></span>Creditos Extraescolares</a></li>
                            <li><a href="/creditosextraescolares/app/maestros/listarmaestros.do"><span class="fa fa-user dropdown-item"></span>Maestros</a></li>
                            <li><a href="/creditosextraescolares/app/eventos/listarevento.do"><span class="fa fa-user dropdown-item"></span>Eventos</a></li>
                            <li><a href="/creditosextraescolares/app/historico/listarhistorico.do"><span class="fa fa-user dropdown-item"></span>Registro Historico de creditos</a></li>
                            <li><a href="/creditosextraescolares/app/planesdetrabajo/ListarPlan.do"><span class="fa fa-user dropdown-item"></span>Planes de trabajo</a></li>
                            <li><a href="/creditosextraescolares/app/periodo/editarPeriodo.do"><span class="fa fa-user dropdown-item"></span>Datos Periodo actual</a></li>
                            <li><a href="/creditosextraescolares/app/salir.do"><span class="fa fa-user dropdown-item"></span>Salir</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
