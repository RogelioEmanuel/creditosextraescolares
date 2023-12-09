
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Agregar Actividad</title>
        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/botones.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-modal.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/spinner.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/bootstrap-datepicker.estandarITT.css" rel="stylesheet">
    </head>
    <header><%@include file="../../templates/Header_View.jsp" %></header>
    <body>
       
            
        <div class="container">
            <div id="PanelForm" class="panel panel-primary" style="margin-top: 2em">
                <div class="panel-heading">
                    <h2 class="panel-title" id="HEADINGDP">
                        Crear Actividad Extraescolar       
                    </h2>
                </div>
                <div class="panel panel-body" color: black; margin: 0">
                    <form id="FormCrearActividad">
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Nombre Actividad Extraescolar :</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="inputNombreActividadExtraescolar" 
                                           id="nombre" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Nombre de la Actividad Extraescolar" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           title="Nombre de la Actividad Extraescolar">
                                </div>
                            </div>
                            
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label >Tipo</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <select name="inputTipo" 
                                            id="tipo" 
                                            placeholder="Tipo"
                                            class="form-control input-sm"                                             
                                            required >
                                        <option value="Civico">Civico</option>
                                        <option value="Deportivo">Deportivo</option>
                                        <option value="Cultural">Cultural</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label >Estatus</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <select name="inputEstatus" 
                                            id="status" 
                                            placeholder="Estatus"
                                            class="form-control input-sm"  
                                            disabled="true"
                                            required >
                                        <option value="1">Activo</option>
                                    </select>
                                </div>
                            </div>
                                                                
                        </div>
                       
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>Descripcion:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcion" 
                                              id="descripcion" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="Descripcion de la Actividad" 
                                              required 
                                              minlength="1"
                                              rows="4"
                                              title="Descripcion de la Actividad"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 30px">                             
                            <div class="col-md-12" align="right">                                
                                <button id="btnRegistrar" form="FormCrearActividad" type="submit"   class="btn btn-primary btn-sm " style="font-size:14px">
                                    <i class="fa fa-save"></i> 
                                </button>    
                                <a title="Regresar" id="btnregresar" href="/creditosextraescolares/app/actividadextraescolar/listaractividad.do" class="btn btn-sm btn-danger" style="font-size:14px"><i class="fa fa-reply"></i></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        
        <!-- JS de CDN -->

        <script type="text/javascript" src ="\CDN-ITT\js\jquery.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery-ui.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\base.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\spinner.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery.dataTables.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\tablas.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\catalogos-modal.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\catalogos-tablas.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\bootstrap-datepicker.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\bootstrap-datepicker.es.estandarITT.js"></script>
        
       
        <!--JS propios -->

        <script src="../../js/jsgenerados/extraescolaresCrearActividad.js" type="text/javascript"></script>        
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.plugin.autotable.min.js" type="text/javascript"></script>
        
        
    </body>
    <footer><%@include file="../../templates/Footer_View.jsp" %></footer>
</html>
