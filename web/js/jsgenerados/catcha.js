/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function Captcha() {
    var alpha = new Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    var i;
    for (i = 0; i < 5; i++) {
        var a = alpha[Math.floor(Math.random() * alpha.length)];
        var b = alpha[Math.floor(Math.random() * alpha.length)];
        var c = alpha[Math.floor(Math.random() * alpha.length)];
        var d = alpha[Math.floor(Math.random() * alpha.length)];
        var e = alpha[Math.floor(Math.random() * alpha.length)];
        var f = alpha[Math.floor(Math.random() * alpha.length)];

    }

    var code = a + ' ' + b + ' ' + ' ' + c + ' ' + d + ' ' + e + ' ' + f + ' ';

    // Agrega un efecto de desplazamiento aleatorio a cada letra
    var newCode = '';
    for (var i = 0; i < code.length; i++) {
        var randomTop = Math.floor(Math.random() *10);
        var randomLeft = Math.floor(Math.random() * 12);
        newCode += '<span style="position: relative; top: ' + randomTop + 'px; left: ' + randomLeft + 'px;">' + code[i] + '</span>';
    }

    document.getElementById("mainCaptcha").innerHTML = newCode;
    document.getElementById("mainCaptcha").value = code;
}
function ValidateCaptcha() {
    var string1 = removeSpaces(document.getElementById('mainCaptcha').value);
    var string2 = removeSpaces(document.getElementById('txtInput').value);
    if (string1 !== string2) {
        return false;
    } else {
        return true;
    }

}
function removeSpaces(string) {
    return string.split(' ').join('');
}

$("body").on("click", '#btnLogin', function (event) {
    event.preventDefault();
    if(ValidateCaptcha()){
         $.ajax({
            type: 'POST',
            url: "/invitacioneseventos/app/Login/ValidaInicio.do",
            data: $("#formLogin").serialize(),
            dataType: 'JSON',
            beforeSend: function () {
                $("#pageLoader").show();
            },
            complete: function () {
                 $("#pageLoader").hide();
            },
            success: function (response) {
                if(response.status === 0){
                    var token = encodeURIComponent(response.mensaje);
                    //var url = "/invitacioneseventos/";
                    var url = "/invitacioneseventos/Master_Srv?token=" + token;
                    window.location = url;
                } else if(response.status === -401){
                    mostrarMensaje(iconoError, 'Error en el inicio de sesión', 'Usuario o contraseña incorrecto, acceso denegado.');
                }else{
                    mostrarMensaje(iconoError,'Ocurrió un error.', response.mensaje);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                mostrarMensaje(iconoError, 'Ocurrió un error.', 'Ocurrió un error al realizar la petición:<br/>' +
                        'Estatus: ' + '<strong>' + textStatus + ' </strong><br/>' +
                        'Error: ' + '<strong>' + errorThrown + '</strong><br/>' +
                        'Por favor, notifique a su administrador de este error<br/>.');
            }
        });
    }else{
        mostrarMensaje(iconoError, 'Error en la captura del captcha', 'Código Incorrecto, Ingresalo Nuevamente!!');
    }


});
