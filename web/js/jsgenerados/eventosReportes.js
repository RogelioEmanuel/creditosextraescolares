$(document).ready(function () {
    $("#divReporte").hide();
});


$("#generarReporte").click(function (evento) {
   evento.preventDefault();
    var actividad=$("#actividad").val();
    var periodo=$("#periodo").val();
    var anio=$("#anio").val();
    
    generarReporte(actividad,periodo,anio);
});



function generarReporte(actividad,periodo,anio) {
    $("#pageLoader").show();
    
    $.ajax({
        url: '../../app/eventos/reporteevento.do',
        type: 'POST',
        dataType: 'json',
        data: {
            actividad: actividad,
            periodo: periodo,
            anio: anio
        },
        success: function (respuesta) {
            $("#pageLoader").hide();
            
            if (respuesta.status === 0) {
                var byteCharacters = atob(respuesta.mensaje);
                var byteNumbers = new Array(byteCharacters.length);
                for (var i = 0; i < byteCharacters.length; i++) {
                    byteNumbers[i] = byteCharacters.charCodeAt(i);
                }
                var byteArray = new Uint8Array(byteNumbers);
                var blob = new Blob([byteArray], {type: 'application/pdf'});

                var url = URL.createObjectURL(blob);
                $("#ApartadoPDF").attr("src", url);
                $("#divReporte").show();
            }
        },
        error: function (jqXHR, exception) {
            $("#pageLoader").hide();
            TituloMensaje = "ERROR";
            Mensaje = "Ocurrió un error en el servidor";
            mostrarMensaje(iconoError, TituloMensaje, Mensaje);
        }
    });
}

