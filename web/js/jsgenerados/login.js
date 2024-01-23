

//console.log(captchaCode);



$("#formLogin").on("click", "#btnLogin", function (evento) {
    evento.preventDefault();
    var itt_username = $("#itt_username").val();
    var itt_password = $("#itt_password").val();  
   // var itt_captcha = document.getElementById("mainCaptcha").textContent;
   // var txtInput = $("#txtInput").val();
    //if(ValidateCaptcha()){
        //alert(itt_captcha);
         //alert(txtInput);
    enviarDatosActividad(itt_username, itt_password);
         
    //}else{
       // mostrarMensaje(iconoError, 'Error en la captura del captcha', 'Código Incorrecto, Ingresalo Nuevamente!!');
    //}
   
    

});

 $("#btnCaptcha").click(function (event) {
        recargar();
    });

function recargar() {
    $('#inpCaptcha').val('');
    $("#img_captcha").attr("src", "/creditosextraescolares/app/login/captcha.png?" + new Date().getTime());
    $("#spin_carga").hide();
    $("#btnInicioSesionIngresar").prop("disabled", false);
}



function enviarDatosActividad(itt_username, itt_password) {
    //Creamos un indicador de carga
    $("#pageLoader").show();
    var datos={
        itt_username: itt_username,
        itt_password: itt_password,
       // itt_captcha:itt_captcha,
        //txtInput:txtInput
                       
    };
    
    $("#pageLoader").show();
    var code = $("#inpCaptcha").val();
    
    if (code.toString().trim() === "") {
        $("#pageLoader").hide();
        TituloMensaje = "ERROR";
       // Mensaje("Error en la captura del captcha, El código es obligatorio");    
        mostrarMensaje(iconoError, TituloMensaje, "Error en la captura del captcha, El código es obligatorio");
    } else {

        $.ajax({
            type: "POST",
            url: "/creditosextraescolares/app/login/validarCaptcha.do",
            data: "inpCaptcha=" + code,
            dataType: 'json',
            success: function (data) {
                $("#pageLoader").hide();
                if (data === "no")
                {
                   // mensajeError("Error en la captura del captcha", "Código Incorrecto, Ingresalo Nuevamente!!");
                    TituloMensaje = "ERROR";
                    //Mensaje("Error en la captura del captcha  Código Incorrecto, Ingresalo Nuevamente!!");   
                    mostrarMensaje(iconoError, TituloMensaje, "Error en la captura del captcha  Código Incorrecto, Ingresalo Nuevamente!!");
                } else if (data === "si") {
                    $("#pageLoader").show();
                    //let inpUsuario = $("#inpUsuario").val();
                    //let inpPass = $("#inpPass").val();
                    let inpCaptcha = $("#inpCaptcha").val();

                    
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
                                
                                }
                            },
                            error: function (jqXHR, exception) {
                                $("#pageLoader").hide();
                                TituloMensaje = "ERROR";
                                Mensaje = "Ocurrió un error en el servidor";
                                mostrarMensaje(iconoError, TituloMensaje, Mensaje);

                            }
                        });
                } else {
                    recargar();
                    
                    TituloMensaje = "ERROR";
                    Mensaje("Error en la captura del captcha" + "Código Incorrecto, Ingresalo Nuevamente!!");                    
                    mostrarMensaje(iconoError, TituloMensaje, Mensaje);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThorwn) {
                $("#pageLoader").hide();
                recargar();
                
                TituloMensaje = "ERROR";
               // Mensaje("Error en la captura del captcha" + "Ocurrio un error");                
                mostrarMensaje(iconoError, TituloMensaje, "Error en la captura del captcha, Ocurrio un error");

            }
        });
    }
    
       
}
