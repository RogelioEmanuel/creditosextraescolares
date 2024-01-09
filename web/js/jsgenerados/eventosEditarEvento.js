$("#FormCrearEvento").on("click", "#btnEditarActividad", function (evento) {
    
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
    var idEvento=$("#labelIdEvento").text();
    
    editarDatosEvento(idEvento,nombre, institucionOrganizadora, tipoEvento,periodo,fecha,actividad,parth,partm,res);
            
});

$("#FormCrearEvento").on("click", "#btnRegresar", function (evento) {
   evento.preventDefault();
   
    window.location.reload();
});

function editarDatosEvento(idEvento,nombre, institucionOrganizadora, tipoEvento,periodo,fecha,actividad,parth,partm,res) {
    $("#pageLoader").show();
    
    
    
    $.ajax({
        url: '../../app/eventos/editarevento.do',
        type: 'POST',
        dataType: 'json',
        data: {
            idEvento: idEvento,
            nombre: nombre,
            institucionOrganizadora: institucionOrganizadora,
            tipo: tipoEvento,
            periodo: periodo,
            fecha: fecha,
            actividad: actividad,
            parth: parth,
            partm: partm,
            resultado:res
            },
        success: function (respuesta) {
            $("#pageLoader").hide();
            var respuestaObj = JSON.parse(respuesta);
                var ruta =respuestaObj.ResponseObject;
                TituloMensaje = "Evento modificado";
                Mensaje = "El Evento fue modificado";
                mensajeRedirect(iconoCorrecto, TituloMensaje, Mensaje, ruta);
            
            
        },
        error: function (jqXHR, exception) {
            $("#pageLoader").hide();
            TituloMensaje = "ERROR";
            Mensaje = "Ocurrió un error en el servidor";
            mostrarMensaje(iconoError,TituloMensaje, Mensaje);
        }
    });
}