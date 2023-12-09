
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Lista de Grupos de ${idActividad}</title>
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
                                <col span="1" style="width: 15%;">
                                <col span="1" style="width: 15%;">
                                <col span="1" style="width: 25%;">                                                                
                                <col span="1" style="width: 15%;">
                            </colgroup>
                            <thead>
                                <tr class="info">
                                    <th class="bordeTd"><input id="tblListaGrupos-colSearch1" type="text" placeholder="Buscar:" style="width: 100%" data-index="1"></th>                                    
                                    <th class="bordeTd"><input id="tblListaGrupos-colSearch2" type="text" placeholder="Buscar:" style="width: 100%" data-index="2"></th>
                                    <th class="bordeTd"><input id="tblListaGrupos-colSearch3" type="text" placeholder="Buscar:" style="width: 100%" data-index="3"></th>
                                    <th class="bordeTd"><input id="tblListaGrupos-colSearch4" type="text" placeholder="Buscar:" style="width: 100%" data-index="4"></th>                                                                        
                                    <th class="bordeTd"><i class="fa fa-filter iconoFiltro"></i></th>
                                </tr>
                                <tr class="info">
                                    <th class="bordeTd" style="font-size: 14px">Numero de Grupo</th>                                    
                                    <th class="bordeTd" style="font-size: 14px">Cupo</th>
                                    <th class="bordeTd" style="font-size: 14px">Periodo</th>
                                    <th class="bordeTd" style="font-size: 14px">Maestro</th>                                    
                                    <th class="bordeTd" style="font-size: 14px"></th>
                                </tr>

                            </thead>
                           <tbody>
                                <c:forEach var="row" items="${grupos}">
                                    <tr id="${row.idGrupo}" data-id="${row.idGrupo}">
                                        <td class="bordeTd  justify">${row.noGrupo}</td>  
                                        <td class="bordeTd  justify">${row.cupo}</td>
                                        <td class="bordeTd  justify">${row.periodo}</td>
                                        <td class="bordeTd  justify">${row.nombreMaestro}</td>
                                        <td class="bordeTd">
                                            <button title="Ver Detalles " data-table="tblListaGrupos" class="btn btn-sm btn-info" id="btnDetallar${row.idGrupo}"><i class="fa fa-eye"></i></button> 
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="row" align="right" style="margin: 1rem">                           
                            <button title="Agregar Grupo " data-table="tblListaGrupos" class="btn btn-sm btn-success" id="btnAgregar"><i class="fa fa-plus"></i> Agregar Grupo</button>
                            <a title="Regresar" id="btnRegresar" href="/creditosextraescolares/app/actividadextraescolar/listaractividad.do" class="btn btn-sm btn-danger"><i class="fa fa-reply"></i> Regresar</a>
                            <button title="Editar Grupo" data-table="tblListaGrupos" class="btn btn-warning btn-sm" id="btnEditar"><i class="fa fa-pencil"></i></button>
                            <button title="Borrar Grupo " data-table="tblListaGrupos" class="btn btn-borrar btn-sm" id="btnEliminar"><i class="fa fa-trash"></i></button>
                            
                        </div>
                        <div hidden="true" id="grpBtnGuardar" class="row" align="right" style="margin: 1rem">
                            <button title="Guardar Actividad Extraescolar" data-table="tblListaGrupos" class="btn btn-primary btn-sm" id="btnGuardar"><i class="fa fa-floppy-o"></i></button>
                            <button title="Cancelar" data-table="tblListaGrupos" class="btn btn-danger btn-sm" id="btnCancelar"><i class="fa fa-ban"></i></button>
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
        <script src="../../js/jsgenerados/gruposListarGrupos.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.plugin.autotable.min.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
    </body>
</html>
