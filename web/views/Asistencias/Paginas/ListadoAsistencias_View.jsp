<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Lista de Asistencia</title>
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
        <header><%@include file="../../templates/HeaderMaestro_View.jsp" %></header>
        <div id="pageLoader">
            <div id="pageSpinner">
                <%@include file="../../templates/spinner.estandarITT.jsp" %>
            </div>
        </div>
        <div class="container">
            
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="panel-title">
                        <label id="labelIdGrupo" style="display: none;">${idGrupo}</label>
                        Lista de Asistencia  
                    </h2>
                </div>
                     
                <div class="panel-body">
                    <div class="table" style="font-size: 12px;" id="divGrpAsistencias">
                        <table id="tblListaAsistencias" class="table table-striped table-bordered tablesorter table-responsive no-footer dtr-inline dataTable table_margen" 
                               style="width: 100%; font-size: 14px">
                            <colgroup>
                                <col span="1" class="col-auto">                                
                                <col span="1" class="col-auto">
                                <col span="1" class="col-auto">
                                <col span="1" class="col-auto">
                                <col span="1" class="col-auto">
                                <col span="1" class="col-auto">
                                <col span="1" class="col-auto">
                                <col span="1" class="col-auto">                                
                                <col span="1" class="col-auto">
                            </colgroup>
                            <thead>
                                <tr class="info">
                                    <th class="bordeTd"><input id="tblListaAsistencias-colSearch1" type="text" placeholder="Buscar:" style="width: 100%" data-index="1"></th>                                    
                                    <th class="bordeTd"><input id="tblListaAsistencias-colSearch2" type="text" placeholder="Buscar:" style="width: 100%" data-index="2"></th>
                                    <th class="bordeTd" colspan="${mes1}"></th>
                                    <c:if test="${periodo != 'Verano'}">
                                        <th class="bordeTd" colspan="${mes2}"></th>
                                        <th class="bordeTd" colspan="${mes3}"></th>
                                        <th class="bordeTd" colspan="${mes4}"></th>
                                        <th class="bordeTd" colspan="${mes5}"></th>
                                    </c:if>
                                    <c:if test="${periodo == 'Enero-Junio'}">
                                        <th class="bordeTd" colspan="${mes6}"></th>
                                    </c:if>
                                    <th class="bordeTd" ><i class="fa fa-filter iconoFiltro"></i></th>
                                </tr>
                                <tr class="info">
                                    <th class="bordeTd" rowspan="2" style="font-size: 14px">Numero de control</th>
                                    <th class="bordeTd" rowspan="2" style="font-size: 14px">Nombre del Alumno</th>
                                    <c:forEach var="valores" items="${mesdia}">
                                            <c:set var="meses" value="${valores.key}" />
                                            <c:set var="dias" value="${valores.value}" />  
                                            <c:choose>
                                                <c:when test="${meses eq 'Enero'}">
                                                    <th class="bordeTd" style="font-size: 14px" colspan="${mes1}">${meses}</th>
                                                </c:when>
                                                <c:when test="${meses eq 'Febrero'}">
                                                    <th class="bordeTd" style="font-size: 14px" colspan="${mes2}">${meses}</th>
                                                </c:when>
                                                <c:when test="${meses eq 'Marzo'}">
                                                    <th class="bordeTd" style="font-size: 14px" colspan="${mes3}">${meses}</th>
                                                </c:when>
                                                <c:when test="${meses eq 'Abril'}">
                                                    <th class="bordeTd" style="font-size: 14px" colspan="${mes4}">${meses}</th>
                                                </c:when> 
                                                <c:when test="${meses eq 'Mayo'}">
                                                    <th class="bordeTd" style="font-size: 14px" colspan="${mes5}">${meses}</th>
                                                </c:when>
                                                <c:when test="${meses eq 'Junio'}">
                                                    <th class="bordeTd" style="font-size: 14px" colspan="${mes6}">${meses}</th>
                                                </c:when>
                                                <c:when test="${meses eq 'Agosto'}">
                                                    <th class="bordeTd" style="font-size: 14px" colspan="${mes1}">${meses}</th>
                                                </c:when>
                                                <c:when test="${meses eq 'Septiembre'}">
                                                    <th class="bordeTd" style="font-size: 14px" colspan="${mes2}">${meses}</th>
                                                </c:when>
                                                <c:when test="${meses eq 'Octubre'}">
                                                    <th class="bordeTd" style="font-size: 14px" colspan="${mes3}">${meses}</th>
                                                </c:when>
                                                <c:when test="${meses eq 'Noviembre'}">
                                                    <th class="bordeTd" style="font-size: 14px" colspan="${mes4}">${meses}</th>
                                                </c:when> 
                                                <c:when test="${meses eq 'Diciembre'}">
                                                    <th class="bordeTd" style="font-size: 14px" colspan="${mes5}">${meses}</th>
                                                </c:when>
                                                <c:when test="${meses eq 'Julio'}">
                                                    <th class="bordeTd" style="font-size: 14px" colspan="${mes1}">${meses}</th>
                                                </c:when>    
                                                    
                                                <c:otherwise>
                                                    <th class="bordeTd" style="font-size: 14px" colspan="6">${meses}</th>
                                                </c:otherwise>
                                            </c:choose>
                               
                                    </c:forEach>
                                    <th class="bordeTd" style="font-size: 14px"></th>
                                </tr>                                
                                <tr class="info">
                                    <c:forEach var="valores" items="${mesdia}">
                                            <c:set var="meses" value="${valores.key}" />
                                            <c:set var="dias" value="${valores.value}" />                                            
                                            <c:forEach var="mes" items="${meses}">  
                                                <c:forEach var="dia" items="${dias}">       
                                                    <th class="bordeTd" style="font-size: 10px">${dia}</th> 
                                                </c:forEach> 
                                                
                                            </c:forEach>                                            
                                    </c:forEach>
                                    <th class="bordeTd" style="font-size: 14px"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="row" items="${asistencias}">
                                    <c:set var="alumno" value="${row.key}" />
                                    <c:set var="fechas" value="${row.value}" />                                    
                                    <tr id="${alumno.noControl}" data-id="${alumno.noControl}">
                                        <td class="bordeTd  justify">${alumno.noControl}</td>                                        
                                        <td class="bordeTd  justify">${alumno.nombre}</td>                                        
                                        <c:forEach var="valores" items="${fechas}">
                                            <c:set var="meses" value="${valores.key}" />
                                            <c:set var="dias" value="${valores.value}" />                                            
                                            <c:forEach var="mes" items="${meses}">  
                                                <c:forEach var="dia" items="${dias}">       
                                                    <td class="bordeTd" style="font-size: 10px">${dia}</td>     
                                                </c:forEach> 
                                                
                                            </c:forEach>                                            
                                        </c:forEach>
                                        <td class="bordeTd"></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="row" align="right" style="margin: 1rem">
                            
                            
                            
                            <a title="Regresar" id="btnregresar" href="../../app/grupos/detallargrupomaestro.do?idGrupo=${idGrupo}" class="btn btn-sm btn-danger" ><i class="fa fa-reply"></i> Regresar</a>                            
                            
                            
                            
                        </div>
                        <div hidden="true" id="grpBtnGuardar" class="row" align="right" style="margin: 1rem">
                            <button title="Guardar Alumno" data-table="tblListaAlumnos" class="btn btn-primary btn-sm" id="btnGuardar"><i class="fa fa-floppy-o"></i></button>
                            <button title="Cancelar" data-table="tblListaAlumnos" class="btn btn-danger btn-sm" id="btnCancelar"><i class="fa fa-ban"></i></button>
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
        <script src="../../js/jsgenerados/asistenciaListarAsistenci.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.plugin.autotable.min.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
    </body>
</html>
