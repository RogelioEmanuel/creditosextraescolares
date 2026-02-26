$("#FormCrearActividad").on("click", "#btnRegistrar", function (evento) {
    evento.preventDefault();
    var nombre = $("#nombre").val();
    var descripcion = $("#descripcion").val();    
    var tipo = $("#tipo").val();
    
    enviarDatosActividad(nombre, descripcion, tipo);
    //mensajeConfirmacion(iconoInfo);

});

function enviarDatosActividad(nombre,descripcion,tipo) {
    //Creamos un indicador de carga
    $("#pageLoader").show();
    var datos={
        nombre: nombre,
        tipo: tipo,
        descripcion: descripcion                
    };
    
    //mandamos el formdata al servidor con un post
    $.ajax({
        url: '../../app/actividadextraescolar/crearactividad.do',
        type: 'POST',
        dataType: 'json',
        data: datos,
        success: function (respuesta) {
            //ocultamos el indicador de carga
            $("#pageLoader").hide();
            
            TituloMensaje="Actividad Argegada con Exito";
            Mensaje="Actividad Agregada con Exito";
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