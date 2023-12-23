$("#FormCrearPlan").on("click", "#btnRegistrar", function (evento) {
    evento.preventDefault();
    var nombre = $("#nombre").val();
    var descripcion = $("#descripcion").val();    
    var tipo = $("#tipo").val();
    
    enviarDatosPlan(nombre, descripcion, tipo);
    //mensajeConfirmacion(iconoInfo);

});

function enviarDatosPlan(nombre,descripcion,tipo) {
    //Creamos un indicador de carga
    $("#pageLoader").show();
    var datos={
        nombre: nombre,
        tipo: tipo,
        descripcion: descripcion                
    };
    
    //mandamos el formdata al servidor con un post
    $.ajax({
        url: '../../app/planesdetrabajo/CrearPlan.do',
        type: 'POST',
        dataType: 'json',
        data: datos,
        success: function (respuesta) {
            //ocultamos el indicador de carga
            $("#pageLoader").hide();
            
            TituloMensaje="Plan Argegado con Exito";
            Mensaje="Plan Agregadoo con Exito";
            var ruta  = "/creditosextraescolares/app/actividadextraescolar/listaractividad.do";
            mensajeRedirect(iconoCorrecto,TituloMensaje,Mensaje, ruta );
            //window.location = ruta;
            
            
        },
        error: function (jqXHR, exception) {
            $("#pageLoader").hide();
            TituloMensaje = "ERROR";
            Mensaje = "Ocurrió un error en el servidor";
            mostrarMensaje(iconoError, TituloMensaje, Mensaje);
            
        }
    });
}


;  
