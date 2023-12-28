
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Lista de Grupos </title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/botones.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-modal.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/spinner.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/bootstrap-datepicker.estandarITT.css" rel="stylesheet">
        <link href="../../css/cssgenerados/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="pageLoader">
            <div id="pageSpinner">
                <%@include file="../../templates/spinner.estandarITT.jsp" %>
            </div>
        </div>
        <header><%@include file="../../templates/Header_View.jsp" %></header>
        <div class="container">
            
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <label id="labelIdActividad" style="display: none;">${idActividad}</label>
                    <h2 class="panel-title">
                        
                        Lista de Grupos 
                    </h2>
                </div>
                <div class="panel-body">
                    <div class="table" style="font-size: 12px;" id="divGrpGrupos">
                        <table id="tblListaGrupos" class="table table-striped table-bordered tablesorter table-responsive no-footer dtr-inline dataTable table_margen" 
                               style="width: 100%; font-size: 14px">
                            <colgroup>
                                <col span="1" style="width: 15%;">                                
                                <col span="1" style="width: 10%;">
                                <col span="1" style="width: 10%;">
                                <col span="1" style="width: 10%;"> 
                                <col span="1" style="width: 10%;">                                
                                <col span="1" style="width: 10%;">
                                <col span="1" style="width: 10%;">                                 
                                <col span="1" style="width: 15%;">
                                <col span="1" style="width: 10%;">
                            </colgroup>
                            <thead>
                                <tr class="info">
                                    <th class="bordeTd"><input id="tblListaGrupos-colSearch1" type="text" placeholder="Buscar:" style="width: 100%" data-index="1"></th>                                    
                                    <th class="bordeTd"><input id="tblListaGrupos-colSearch2" type="text" placeholder="Buscar:" style="width: 100%" data-index="2"></th>
                                    <th class="bordeTd"><input id="tblListaGrupos-colSearch3" type="text" placeholder="Buscar:" style="width: 100%" data-index="3"></th>
                                    <th class="bordeTd"><input id="tblListaGrupos-colSearch4" type="text" placeholder="Buscar:" style="width: 100%" data-index="4"></th>
                                    <th class="bordeTd"><input id="tblListaGrupos-colSearch5" type="text" placeholder="Buscar:" style="width: 100%" data-index="5"></th>                                    
                                    <th class="bordeTd"><input id="tblListaGrupos-colSearch6" type="text" placeholder="Buscar:" style="width: 100%" data-index="6"></th>
                                    <th class="bordeTd"><input id="tblListaGrupos-colSearch7" type="text" placeholder="Buscar:" style="width: 100%" data-index="7"></th>
                                    <th class="bordeTd"><input id="tblListaGrupos-colSearch8" type="text" placeholder="Buscar:" style="width: 100%" data-index="8"></th> 
                                    <th class="bordeTd"><i class="fa fa-filter iconoFiltro"></i></th>
                                </tr>
                                <tr class="info">
                                    <th class="bordeTd" style="font-size: 14px">Actividad</th>
                                    <th class="bordeTd" style="font-size: 14px">Lunes</th>
                                    <th class="bordeTd" style="font-size: 14px">Martes</th>
                                    <th class="bordeTd" style="font-size: 14px">Miercoles</th>
                                    <th class="bordeTd" style="font-size: 14px">Jueves</th>
                                    <th class="bordeTd" style="font-size: 14px">Viernes</th>
                                    <th class="bordeTd" style="font-size: 14px">Sabado</th>
                                    <th class="bordeTd" style="font-size: 14px">Maestro</th>                                    
                                    <th class="bordeTd" style="font-size: 14px"></th>
                                </tr>

                            </thead>
                           <tbody>
                                <c:forEach var="row" items="${gruposConHorarios}">
                                    <c:set var="grupoId" value="${row.key}" />
                                    <c:set var="horarios" value="${row.value}" />
                                    <tr id="${grupoId}" data-id="${grupoId}">
                                        <td class="bordeTd  justify">${nombreMaestro}</td>
                                         <c:forEach var="horario" items="${horarios}">
                                            <!-- Accede a las propiedades del horario -->
                                            <td class="bordeTd justify">${horario.dia}</td>
                                            <td class="bordeTd justify">${horario.horaInicio} / ${horario.horaFinal}</td>
                                            <!-- Agrega más propiedades según sea necesario -->
                                        </c:forEach>
                                        <!--td class="bordeTd  justify"></td-->
                                        <td class="bordeTd">
                                            
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="row" align="right" style="margin: 1rem">                           
                            
                            <a title="Regresar" id="btnRegresar" href="/creditosextraescolares/app/actividadextraescolar/listaractividad.do" class="btn btn-sm btn-danger"><i class="fa fa-reply"></i> Regresar</a>
                             
                        </div>
                        
                    </div>

                </div>    
            </div>

        </div>
        <footer><%@include file="../..//templates/Footer_View.jsp" %></footer>

        <script type="text/javascript" src ="\CDN-ITT\js\jquery.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery-ui.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\base.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\spinner.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery.dataTables.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\tablas.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\catalogos-modal.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\bootstrap-datepicker.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\bootstrap-datepicker.es.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\catalogos-tablas.estandarITT.js"></script>
        
        
        <script src="../../js/jsgenerados/Inicio.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/gruposInscripcionGrupos.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.plugin.autotable.min.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
    </body>
</html>
