$("#FormCrearEvento").on("click", "#btnRegistrar", function (evento) {
    evento.preventDefault();
    var nombre = $("#nombreEvento").val();
    var institucionOrganizadora = $("#institucionOrganizadora").val();    
    var tipoEvento = $("#tipoEvento").val();
    var periodo = $("#periodo").val();
    var fecha = $("#fecha").val();    
    var actividad = $("#actividad").val();
    
    enviarDatosActividad(nombre, institucionOrganizadora, tipoEvento,periodo,fecha,actividad);
    //mensajeConfirmacion(iconoInfo);

});

function enviarDatosActividad(nombre, institucionOrganizadora, tipoEvento,periodo,fecha,actividad) {
    //Creamos un indicador de carga
    $("#pageLoader").show();
    var datos={
        nombre: nombre,
        institucionOrganizadora: institucionOrganizadora,
        tipo: tipoEvento,
        periodo: periodo,
        fecha: fecha,
        actividad: actividad
                       
    };
    
    //mandamos el formdata al servidor con un post
    $.ajax({
        url: '../../app/eventos/crearevento.do',
        type: 'POST',
        dataType: 'json',
        data: datos,
        success: function (respuesta) {
            //ocultamos el indicador de carga
            $("#pageLoader").hide();
            
            TituloMensaje="Evento Argegado con Exito";
            Mensaje="Evento Agregado con Exito";
            var ruta  = "/creditosextraescolares/app/eventos/listarevento.do";
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

