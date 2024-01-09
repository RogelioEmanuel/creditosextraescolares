
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Registrar Maestro</title>
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
       <div id="pageLoader">
            <div id="pageSpinner">
                <%@include file="../../templates/spinner.estandarITT.jsp" %>
            </div>
        </div>
            
        <div class="container">
            <div id="PanelForm" class="panel panel-primary" style="margin-top: 2em">
                <div class="panel-heading">
                    <h2 class="panel-title" id="HEADINGDP">
                        Agregar Maestro       
                    </h2>
                </div>
                <div class="panel panel-body" color: black; margin: 0">
                    <form id="FormCrearMaestro">
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
                                           title="Nombre de la Actividad Extraescolar">
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
                                        <option value="M">Masculino</option>
                                        <option value="F">Femenino</option>                                        
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
                                           maxlength="100"  
                                           
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
                                           maxlength="80"  
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
                                           maxlength="80"  
                                           title="Curp">
                                </div>
                            </div>
                            
                                                                
                        </div>
                        
                         <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Usuario</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="usuario" 
                                           id="usuario" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Usuario" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           title="Usuario">
                                </div>
                                                                
                            </div>
                            
                            <div class="col-md-6">
                                <label>Contraseña</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="pass" 
                                           id="pass" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="pass" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           title="pass"
                                           type="password"> 
                                </div>
                            </div>

                            <div class="col-md-6">
                                <label>Confirma Contraseña</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="confPasss" 
                                           id="confPasss" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="confPasss" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           title="confPasss"
                                           type="password"> 
                                </div>
                            </div>
                            
                                                                
                        </div>
                        <div class="row" style="margin-top: 30px">      

                            <div class="col-md-12" align="right">   
                            <a title="Regresar" id="btnregresar" href="/creditosextraescolares/app/maestros/listarmaestros.do" class="btn btn-sm btn-danger" style="font-size:14px"><i class="fa fa-reply"></i></a>
                                <button id="btnRegistrar" form="FormCrearMaestro" type="submit"   class="btn btn-primary btn-sm " style="font-size:14px">
                                    <i class="fa fa-save"></i> 
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
        
        
        
        <!--JS propios -->
        <script src="../../js/jsgenerados/Inicio.js" type="text/javascript"></script>   
        <script src="../../js/jsgenerados/maestrosCrearMaestro.js" type="text/javascript"></script>        
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.plugin.autotable.min.js" type="text/javascript"></script>
    </body>
    <footer><%@include file="../../templates/Footer_View.jsp" %></footer>
</html>
