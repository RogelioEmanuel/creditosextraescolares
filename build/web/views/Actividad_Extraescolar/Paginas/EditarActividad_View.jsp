
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Editar Actividad Extraescolar</title>       
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
            <div id="PanelForm" class="panel panel-primary" style="margin-top: 2em">
                <div class="panel-heading">
                    <h2 class="panel-title" id="HEADINGDP">
                        Editar Actividad       
                    </h2>
                </div>
                <div class="panel panel-body" color: black; margin: 0">

                    <form id="FormEditarActividad">
                        <div class="row" style="margin-bottom: 20px">
                            
                            <div class="col-md-5">
                                <label >ID Actividad Extraescolar</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <select name="inputidActividad" 
                                            id="idActividad" 
                                            placeholder="idActividad"
                                            class="form-control input-sm" 
                                            disabled="true"
                                            required >
                                        <option value="${idActividad}">${idActividad}</option>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="col-md-6">
                                <label>Nombre: </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <input name="inputNombre" 
                                           id="nombreActividadExtraescolar" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="nombreActividadExtraescolar" 
                                           required 
                                           minlength="1"
                                           maxlength="100"
                                           value="${nombre}"
                                           title="nombreActividadExtraescolar">
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
                                        <option value="${tipo}">${tipo}</option>
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
                                            required >
                                        <option value="${status}">${status!= 0 ? 'Activa' : 'Inactiva'}</option>
                                        <option value="${status == 0 ? 1 : 0}">${status == 0 ? 'Activa' : 'Inactiva'}</option>
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
                                              title="Descripcion de la Actividad">${descripcion}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 30px">
                            <div class="col-md-12" align="right">
                                <button style="font-size:14px" id="btnRegresar" class="btn btn-danger btn-sm">
                                    <i class="fa fa-ban"></i> Cancelar
                                </button>
                                <button id="btnEditarActividad" form="FormEditarActividad" type="submit" class="btn btn-primary btn-sm" style="font-size:14px">
                                    <i class="fa fa-floppy-o"></i> Guardar
                                </button>    

                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        

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
        
        
        
        <script src="../../js/jsgenerados/Inicio.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>        
        <script src="../../js/jsgenerados/extraescolaresEditarActividad.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
    </body>
    <footer><%@include file="../../templates/Footer_View.jsp" %></footer>
</html>
