$("#formLogin").on("click", "#btnLogin", function (evento) {
    evento.preventDefault();
    var itt_username = $("#itt_username").val();
    var itt_password = $("#itt_password").val();    
    
    enviarDatosActividad(itt_username, itt_password);
    

});


function enviarDatosActividad(itt_username, itt_password) {
    //Creamos un indicador de carga
    $("#pageLoader").show();
    var datos={
        itt_username: itt_username,
        itt_password: itt_password
                       
    };
    
    $.ajax({
        url: 'app/usuarios/loginusuarios.do',
        type: 'POST',
        dataType: 'json',
        data: datos,
        success: function (respuesta) {
            //ocultamos el indicador de carga
            $("#pageLoader").hide();
            
            TituloMensaje="Bienvenido";
            Mensaje="Bienvenido";
            var ruta  = "/creditosextraescolares/app/Index/Home.do";
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
