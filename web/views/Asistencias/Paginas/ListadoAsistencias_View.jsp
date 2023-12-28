
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Lista de Asistencias</title>
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
        <header><%@include file="../../templates/Header_View.jsp" %></header>
        <div id="pageLoader">
            <div id="pageSpinner">
                <%@include file="../../templates/spinner.estandarITT.jsp" %>
            </div>
        </div>
        <div class="container">
            
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="panel-title">
                        Lista de Asistencia 
                    </h2>
                </div>
                <div class="panel-body">
                    <div class="table" style="font-size: 12px;" id="divGrpEventos">
                        <table id="tblListaActividades Extraescolares" class="table table-striped table-bordered tablesorter table-responsive no-footer dtr-inline dataTable table_margen" 
                               style="width: 100%; font-size: 14px">
                            <colgroup>
                                <col span="1" style="width: 20%;">                                
                                <col span="1" style="width: 30%;">
                                <col span="1" style="width: 15%;">
                                <col span="1" style="width: 15%;">
                                <col span="1" style="width: 5%;">
                            </colgroup>
                            <thead>
                                <tr class="info">
                                    <th class="bordeTd"><input id="tblListaAsistencia-colSearch1" type="text" placeholder="Buscar:" style="width: 100%" data-index="1"></th>                                    
                                    <th class="bordeTd"><input id="tblListaAsistencia-colSearch2" type="text" placeholder="Buscar:" style="width: 100%" data-index="2"></th>
                                    <th class="bordeTd"><input id="tblListaAsistencia-colSearch3" type="text" placeholder="Buscar:" style="width: 100%" data-index="3"></th>                                    
                                    <th class="bordeTd"><i class="fa fa-filter iconoFiltro"></i></th>
                                </tr>
                                <tr class="info">
                                    <th class="bordeTd" style="font-size: 14px">Fecha</th>
                                    <th class="bordeTd" style="font-size: 14px">Alumno</th>
                                    <th class="bordeTd" style="font-size: 14px">Grupo</th>                                    
                                    <th class="bordeTd" style="font-size: 14px"></th>
                                </tr>

                            </thead>
                            <tbody>
                                <c:forEach var="row" items="${eventos}">
                                    <tr id="${row.idEvento}" data-id="${row.idEvento}">
                                        <td class="bordeTd  justify">${row.nombreEvento}</td>
                                        
                                        <td class="bordeTd  justify">${row.descripcion}</td>
                                        <td class="bordeTd  justify">${row.fechaEvento}</td>
                                        <td class="bordeTd">${responsable}</td>
                                        <td class="bordeTd"></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="row" align="right" style="margin: 1rem">
                            <a title="Agregar Evento" id="btnAgregar" href="/creditosextraescolares/app/actividadextraescolar/crearactividad.do" class="btn btn-sm btn-success"><i class="fa fa-plus"></i></a>
                            <button title="Editar Asistencia" data-table="tblListaAsistencia" class="btn btn-warning btn-sm" id="btnEditar"><i class="fa fa-pencil"></i></button>
                            
                            
                        </div>
                        <div hidden="true" id="grpBtnAsistencia" class="row" align="right" style="margin: 1rem">
                            <button title="Guardar Asistencia" data-table="tblListaActividadesExtraescolares" class="btn btn-primary btn-sm" id="btnGuardar"><i class="fa fa-floppy-o"></i></button>
                            <button title="Cancelar" data-table="tblListaAsistencia" class="btn btn-danger btn-sm" id="btnCancelar"><i class="fa fa-ban"></i></button>
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
        <script src="../../js/jsgenerados/asistenciasListarAsistencia.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.plugin.autotable.min.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
    </body>
</html>
