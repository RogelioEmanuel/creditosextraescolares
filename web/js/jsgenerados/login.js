

//console.log(captchaCode);



$("#formLogin").on("click", "#btnLogin", function (evento) {
    evento.preventDefault();
    var itt_username = $("#itt_username").val();
    var itt_password = $("#itt_password").val();  
    var itt_captcha = document.getElementById("mainCaptcha").textContent;
    var txtInput = $("#txtInput").val();
    //if(ValidateCaptcha()){
        //alert(itt_captcha);
         //alert(txtInput);
    enviarDatosActividad(itt_username, itt_password,itt_captcha,txtInput);
         
    //}else{
       // mostrarMensaje(iconoError, 'Error en la captura del captcha', 'Código Incorrecto, Ingresalo Nuevamente!!');
    //}
   
    

});


function enviarDatosActividad(itt_username, itt_password,itt_captcha,txtInput) {
    //Creamos un indicador de carga
    $("#pageLoader").show();
    var datos={
        itt_username: itt_username,
        itt_password: itt_password,
        itt_captcha:itt_captcha,
        txtInput:txtInput
                       
    };
    
    $.ajax({
        url: '/creditosextraescolares/app/usuarios/loginusuarios.do',
        type: 'POST',
        dataType: 'json',
        data: datos,
        success: function (respuesta) {
            //ocultamos el indicador de carga
            if(respuesta.status===15){
                 mostrarMensaje(iconoError, 'Error en la captura del captcha', 'Código Incorrecto, Ingresalo Nuevamente!!');
            }else{
                $("#pageLoader").hide();
            
                TituloMensaje="Bienvenido";
                Mensaje="Bienvenido";
                var ruta  = "/creditosextraescolares/app/Index/Home.do";
                mensajeRedirect(iconoCorrecto,TituloMensaje,Mensaje, ruta );
            //window.location = ruta;
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
