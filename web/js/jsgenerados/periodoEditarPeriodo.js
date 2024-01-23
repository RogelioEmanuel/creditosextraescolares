
$("#FormEditarPeriodo").on("click", "#btnRegistrar", function (evento) {
    evento.preventDefault();    
    var periodo = $("#periodo").val();
    var fechaInicio = $("#fechaInicio").val();
    var insscripcion = $("#insscripcion").val(); 
    var fechaFin = $("#fechaFin").val();
    var cierre = $("#cierre").val(); 
   
    
    
    
    
    
    editarDatosMaestro(periodo,fechaInicio,insscripcion,fechaFin,cierre);
    
    
            
});





function editarDatosMaestro(periodo,fechaInicio,insscripcion,fechaFin,cierre) {
    $("#pageLoader").show();
    var datos={
        periodo: periodo,
        fechaInicio: fechaInicio,
        insscripcion: insscripcion,
        fechaFin: fechaFin,
        cierre: cierre
        
    };
    
    $.ajax({
        url: '../../app/periodo/editarPeriodo.do',
        type: 'POST',
        dataType: 'json',
        data: datos,
        success: function (respuesta) {
            $("#pageLoader").hide();
            if(respuesta.status===132){
                TituloMensaje = "ERROR";
                Mensaje = "Verifica Fecha de inicio de periodo";
                mostrarMensaje(iconoError,TituloMensaje, Mensaje);
            }else if(respuesta.status===131){
                TituloMensaje = "ERROR";
                Mensaje = "Verifica Fecha de fin de periodo";
                mostrarMensaje(iconoError,TituloMensaje, Mensaje);
            }else if(respuesta.status===130){
                TituloMensaje = "ERROR";
                Mensaje = "Verifica años en las fechas";
                mostrarMensaje(iconoError,TituloMensaje, Mensaje);
            }else{
                TituloMensaje = "Perido modificado";
                Mensaje = "La informacion fue modificada";
                mensajeRedirect(iconoCorrecto, TituloMensaje, Mensaje, '/creditosextraescolares/app/periodo/editarPeriodo.do');
            }
                
            
            
        },
        error: function (jqXHR, exception) {
            $("#pageLoader").hide();
            TituloMensaje = "ERROR";
            Mensaje = "Ocurrió un error en el servidor";
            mostrarMensaje(iconoError,TituloMensaje, Mensaje);
        }
    });
}



