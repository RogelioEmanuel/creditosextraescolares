$("#FormCrearMaestro").on("click", "#btnRegistrar", function (evento) {
    evento.preventDefault();
    var nombre = $("#nombreMaestro").val();
    var appaterno = $("#apellidopaternoMaestro").val();
    var apmaterno = $("#apellidoMaternoMaestro").val();    
    var fechaNacimientoMaestro = $("#fechaNacimientoMaestro").val();
    var telefonoMaestro = $("#telefonoMaestro").val();
    var correo = $("#correoMaestro").val();
    var curp= $("#curpMaestro").val();
    var rfc=$("#rfcMaestro").val();
    var sexo=$("#sexo").val();
    var direccion=$("#direccionMaestro").val();
    var banco=$("#bancoMaestro").val();
    var claveinterbancaria=$("#claveinterbancaria").val();
           
          if(formValido(nombre,appaterno,apmaterno,fechaNacimientoMaestro,telefonoMaestro,correo,curp,rfc,sexo,direccion,banco,claveinterbancaria)){
                enviarDatosActividad(nombre,appaterno,apmaterno,fechaNacimientoMaestro,telefonoMaestro,correo,curp,rfc,sexo,direccion,banco,claveinterbancaria);
           }
           
   
    

});

function formValido(nombre, appaterno, apmaterno, fechaNacimientoMaestro, telefonoMaestro, correo, curp, rfc, sexo, direccion, banco, claveinterbancaria) {
    var errores = [];

    if (!fechaValida(fechaNacimientoMaestro)) {
        errores.push("La fecha de nacimiento no es válida.");
    }

    if (!camposnumericosValidos(telefonoMaestro)) {
        errores.push("El teléfono debe contener solo números.");
    }

    if (!camposnumericosValidos(claveinterbancaria)) {
        errores.push("La clave interbancaria debe contener solo números.");
    }

    if (!campostextovalidos(nombre)) {
        errores.push("El nombre debe contener solo letras y espacios.");
    }

    if (!campostextovalidos(appaterno)) {
        errores.push("El apellido paterno debe contener solo letras y espacios.");
    }

    if (!campostextovalidos(apmaterno)) {
        errores.push("El apellido materno debe contener solo letras y espacios.");
    }

    if (!validarCURP(curp)) {
        errores.push("El CURP no es válido.");
    }

    if (!validarRFC(rfc)) {
        errores.push("El RFC no es válido.");
    }

    if (!validarCorreo(correo)) {
        errores.push("La dirección de correo electrónico no es válida.");
    }

    if (errores.length > 0) {
        // Mostrar mensajes de error específicos para cada campo
        for (var i = 0; i < errores.length; i++) {
            mostrarMensaje(iconoError, "ERROR", errores[i]);
        }
        return false;
    }

    return true;
}


function validarCorreo(correo) {
    // Expresión regular para validar direcciones de correo electrónico
    var regexCorreo = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

    return regexCorreo.test(correo);
}

function fechaValida(fecha){
    var formato = /^\d{2}\/\d{2}\/\d{4}$/;
    
    if(formato.test(fecha)){
        var partesFecha = fecha.split("/");
        var dia = parseInt(partesFecha[0], 10);
        var mes = parseInt(partesFecha[1], 10);
        var anio = parseInt(partesFecha[2], 10);
        
        if(isNaN(dia)||isNaN(mes)||isNaN(anio)){
            return false;
        }else{
            
            var diasEnMes;
            switch (mes) {
            case 1:  // Enero
            case 3:  // Marzo
            case 5:  // Mayo
            case 7:  // Julio
            case 8:  // Agosto
            case 10: // Octubre
            case 12: // Diciembre
                diasEnMes = 31;
                break;
            case 2:  // Febrero
                var esBisiesto = (anio % 4 === 0 && (anio % 100 !== 0 || anio % 400 === 0));
                
                if(esBisiessto){
                    diasEnMes=29;
                }
                diasEnMes = 28; 
                break;
            default:
                diasEnMes = 30;
                break;
            }
            if (dia < 1 || dia > diasEnMes) {
                return false;
            }else{
                if (mes < 1 || mes > 12) {
                    return false;
                }
            }
        }      
    }else{
        return false;
    }
    
    return true;
}

function camposnumericosValidos(valor){
     var expresionRegular = /^[0-9]+$/;
    return expresionRegular.test(valor);
}

function campostextovalidos(campo){
     var expresionRegular = /^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\s]+$/;
    return expresionRegular.test(campo);
}

function validarCURP(curp) {
    
    var regexCURP = /^[A-Z]{4}[0-9]{6}[HM][A-Z]{5}[0-9]{2}\s*$/;
    return regexCURP.test(curp);
}

function validarRFC(rfc) {
    // Expresión regular para validar RFC en México
    var regexRFC = /^[A-Za-z]{4}\d{6}[A-Za-z0-9]{3}$/;

    return regexRFC.test(rfc);
}
function enviarDatosActividad(nombre,appaterno,apmaterno,fechaNacimientoMaestro,telefonoMaestro,correo,curp,rfc,sexo,direccion,banco,claveinterbancaria) {
    //Creamos un indicador de carga
    $("#pageLoader").show();
    var datos={
        nombre: nombre,
        appaterno: appaterno,
        apmaterno: apmaterno,
        fechanacimiento: fechaNacimientoMaestro,
        telefonoMaestro: telefonoMaestro,
        correo: correo,
        curp: curp,
        rfc: rfc,
        sexo: sexo,
        direccion: direccion,
        banco: banco,
        claveinterbancaria: claveinterbancaria
    };
    
    //mandamos el formdata al servidor con un post
    $.ajax({
        url: '../../app/maestros/crearmaestro.do',
        type: 'POST',
        dataType: 'json',
        data: datos,
        success: function (respuesta) {
            //ocultamos el indicador de carga
            $("#pageLoader").hide();
            
            TituloMensaje="Maestro Argegado con Exito";
            Mensaje="Maestro Agregado con Exito";
            var ruta  = "/creditosextraescolares/app/maestros/listarmaestros.do";
            mensajeRedirect(iconoCorrecto,TituloMensaje,Mensaje, ruta );
            
                        
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

