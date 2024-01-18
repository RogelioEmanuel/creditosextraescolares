<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Editar Maestro</title>       
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
            <div id="PanelForm" class="panel panel-primary" style="margin-top: 2em">
                <div class="panel-heading">
                    <h2 class="panel-title" id="HEADINGDP">
                        Editar mi informacion       
                    </h2>
                </div>
                <div class="panel panel-body" color: black; margin: 0">

                    <form id="FormEditarMaestro">
                        <div class="row" style="margin-bottom: 20px">
                            
                            <div class="col-md-5">
                                <label >ID Maestro</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <select name="inputidMaestro" 
                                            id="idMaestro" 
                                            placeholder="idMaestro"
                                            class="form-control input-sm" 
                                            disabled="true"
                                            required >
                                        <option value="${idMaestro}">${idMaestro}</option>
                                    </select>
                                </div>
                            </div>
                            
                            
                        </div>
                        
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Nombre:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="inputNombreMaestro" 
                                           id="nombreMaestro" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Nombre del Maestro" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           value="${nombre}"
                                           title="Nombre del Maestro">
                                </div>
                            </div>
                            
                            <div class="col-md-6">
                                <label>Apellido Paterno:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="inputapellidopaternoMaestro" 
                                           id="apellidopaternoMaestro" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Apellido Paterno" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           value="${appaterno}"
                                           title="Apellido Paterno">
                                </div>
                            </div>
                            
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Apellido Materno:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="inputapellidoMaternoMaestro" 
                                           id="apellidoMaternoMaestro" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Apellido Materno" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           value="${apmaterno}"
                                           title="Apellido Materno">
                                </div>
                            </div>
                            
                            <div class="col-md-4">
                                <label >Sexo</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <select name="inputSexo" 
                                            id="sexo" 
                                            placeholder="Sexo"
                                            class="form-control input-sm"                                             
                                            required >
                                        <option value="F" <c:if test="${sexo eq 'F'}">
                                                                 selected
                                                           </c:if>>Femenino</option>
                                        <option value="M" <c:if test="${sexo eq 'M'}">
                                                                 selected
                                                           </c:if>>Masculino</option>                                         
                                    </select>
                                </div>
                            </div>
                            
                                                                
                        </div>
                       
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-4">
                                <label >Fecha de Nacimiento:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                    <input name="inputFechaNacimientoMaestro" 
                                           id="fechaNacimientoMaestro" 
                                           autocomplete="off"
                                           date-date-format="dd/mm/yyyy"
                                           placeholder="dd/mm/yyyy" 
                                           maxlength="10"
                                           value="${fecha_nacimiento}"
                                           class="form-control input-sm datepicker" 
                                           required >
                                </div>
                            </div>

                            
                                                                
                        </div>
                        
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Correo</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-at"></i></span>
                                    <input name="inputcorreoMaestro" 
                                           id="correoMaestro" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Correo" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           value="${correo}"
                                           title="Correo">
                                </div>
                                                                
                            </div>
                            
                            <div class="col-md-6">
                                <label>Telefono:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputtelefonoMaesstro" 
                                           id="telefonoMaestro" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Numero de Telefono" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           value="${telefono}"
                                           title="Numero de telefono">
                                </div>
                            </div>
                            
                                                                
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Direccion</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="inputcorreoMaestro" 
                                           id="direccionMaestro" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Direccion" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           value="${direccion}"
                                           title="Direccion">
                                </div>
                                                                
                            </div>
            

                                                                
                        </div>
                        
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Banco</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="inputbancoMaestro" 
                                           id="bancoMaestro" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Banco" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           value="${banco}"
                                           title="Banco">
                                </div>
                                                                
                            </div>
                            
                            <div class="col-md-6">
                                <label>Clave Interbancaria:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputClaveinterbancariaMaestro" 
                                           id="claveinterbancaria" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Clave interbancaria" 
                                           required 
                                           minlength="1"
                                           maxlength="18"
                                           value="${claveinterbancaria}"
                                           title="Clave interbancaria">
                                </div>
                            </div>
                            
                                                                
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>RFC</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputrfcMaestro" 
                                           id="rfcMaestro" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="RFC" 
                                           required 
                                           minlength="1"
                                           maxlength="13"  
                                           value="${rfc}"
                                           title="RFC">
                                </div>
                                                                
                            </div>
                            
                            <div class="col-md-6">
                                <label>Curp:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputtelefonoMaesstro" 
                                           id="curpMaestro" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="CURP" 
                                           required 
                                           minlength="1"
                                           maxlength="18"  
                                           value="${curp}"
                                           title="Curp">
                                </div>
                            </div>
                            
                                                                
                        </div>
                        <div class="row" style="margin-top: 30px">
                            <div class="col-md-12" align="right">
                                <button style="font-size:14px" id="btnRegresar" class="btn btn-danger btn-sm">
                                    <i class="fa fa-ban"></i> Cancelar
                                </button>
                                <button id="btnEditarMaestro" form="FormEditarMaestro" type="submit" class="btn btn-primary btn-sm" style="font-size:14px">
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
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.plugin.autotable.min.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>        
        <script src="../../js/jsgenerados/maestrosEditarInfo.js" type="text/javascript"></script>
        
    </body>
    <footer><%@include file="../../templates/Footer_View.jsp" %></footer>
</html>
