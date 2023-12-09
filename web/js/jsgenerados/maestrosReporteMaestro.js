$(document).ready(function () {
    $("#divReporte").hide();
});


$("#generarReporte").click(function () {
    generarReporte();
});



function generarReporte() {
    $("#pageLoader").show();
    $.ajax({
        url: '../../app/Eventos/ReportesEventos.do',
        type: 'POST',
        dataType: 'json',
        data: {},
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