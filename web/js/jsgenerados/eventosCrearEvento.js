$("#FormCrearEvento").on("click", "#btnRegistrar", function (evento) {
    evento.preventDefault();
    var nombre = $("#nombreEvento").val();
    var institucionOrganizadora = $("#institucionOrganizadora").val();    
    var tipoEvento = $("#tipoEvento").val();
    var periodo = $("#periodo").val();
    var fecha = $("#fecha").val();    
    var actividad = $("#actividad").val();
    var parth=$("#numeroparticipantesm").val();
    var partm=$("#numeroparticipantesf").val();
    var res=$("#resultado").val();    
    
    
    enviarDatosActividad(nombre, institucionOrganizadora, tipoEvento,periodo,fecha,actividad,parth,partm,res);
    

});

function enviarDatosActividad(nombre, institucionOrganizadora, tipoEvento,periodo,fecha,actividad,parth,partm,res) {
    //Creamos un indicador de carga
    $("#pageLoader").show();
    var datos={
        nombre: nombre,
        institucionOrganizadora: institucionOrganizadora,
        tipo: tipoEvento,
        periodo: periodo,
        fecha: fecha,
        actividad: actividad,
        parth: parth,
        partm: partm,
        resultado:res
                       
    };
    
    
    $.ajax({
        url: '../../app/eventos/crearevento.do',
        type: 'POST',
        dataType: 'json',
        data: datos,
        success: function (respuesta) {
            //ocultamos el indicador de carga
            $("#pageLoader").hide();
            
            var a = respuesta.mensaje;
            TituloMensaje="Evento Argegado con Exito";
            Mensaje="Evento Agregado con Exito";
            var ruta  = "/creditosextraescolares/app/eventos/listarevento.do";
            mensajeRedirect(iconoCorrecto,TituloMensaje,Mensaje, a );
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

