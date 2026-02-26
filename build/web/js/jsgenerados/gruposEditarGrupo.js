//var diasSeleccionados = [];
var lunesInicio = document.getElementById("lunesInicio").value;
var lunesFin = document.getElementById("lunesFin").value;
var martesInicio = document.getElementById("martesInicio").value;
var martesFin = document.getElementById("martesFin").value;
var miercolesInicio = document.getElementById("miercolesInicio").value;
var miercolesFin = document.getElementById("miercolesFin").value;
var juevesInicio = document.getElementById("juevesInicio").value;
var juevesFin = document.getElementById("juevesFin").value;
var viernesInicio = document.getElementById("viernesInicio").value;
var viernesFin = document.getElementById("viernesFin").value;
var sabadoInicio = document.getElementById("sabadoInicio").value;
var sabadoFin = document.getElementById("sabadoFin").value;

var idGrupo=$("#labelIdGrupo").text();

function handleDayCheckboxChange(checkbox, inicioInput, finInput) {
  checkbox.addEventListener('change', (event) => {
    var dia = checkbox.id;      
    if (event.currentTarget.checked) {       
     // diasSeleccionados.push(dia);
      inicioInput.style.display = "inline";
      finInput.style.display = "inline";
      
      inicioInput.addEventListener('input', () => obtenerDia(dia, inicioInput, finInput));
      finInput.addEventListener('input', () => obtenerDia(dia, inicioInput, finInput));
          
     //obtenerDia(dia); 
    } else {
      //diasSeleccionados = diasSeleccionados.filter(d => d !== dia);
      resetDia(dia);
      inicioInput.style.display = "none";
      finInput.style.display = "none";
      //obtenerDia(dia, inicioInput, finInput);
    }
  });
}

var daysConfig = [
  { checkbox: document.getElementById("lunes"), inicio: document.getElementById("lunesInicio"), fin: document.getElementById("lunesFin") },   
   { checkbox: document.getElementById("martes"), inicio: document.getElementById("martesInicio"), fin: document.getElementById("martesFin") },
   { checkbox: document.getElementById("miercoles"), inicio: document.getElementById("miercolesInicio"), fin: document.getElementById("miercolesFin") },
   { checkbox: document.getElementById("jueves"), inicio: document.getElementById("juevesInicio"), fin: document.getElementById("juevesFin") },   
   { checkbox: document.getElementById("viernes"), inicio: document.getElementById("viernesInicio"), fin: document.getElementById("viernesFin") },
   { checkbox: document.getElementById("sabado"), inicio: document.getElementById("sabadoInicio"), fin: document.getElementById("sabadoFin") }  
];

daysConfig.forEach(dayConfig => {
  handleDayCheckboxChange(dayConfig.checkbox, dayConfig.inicio, dayConfig.fin);
});

//Obtiene los dias dependiendo el parametro que se mande 
function obtenerDia(dia, inicioInput, finInput) {    
    switch (dia.toLowerCase()) {
        case "lunes":
            lunesInicio = inicioInput.value;
            lunesFin = finInput.value;
            validarHorasCorrectas(lunesInicio, lunesFin);
            break;
        case "martes":
            martesInicio = inicioInput.value;
            martesFin = finInput.value;
            validarHorasCorrectas(martesInicio, martesFin);
            break;
        case "miercoles":
            miercolesInicio = inicioInput.value;
            miercolesFin = finInput.value;
            break;
        case "jueves":
            juevesInicio = inicioInput.value;
            juevesFin = finInput.value;
            break;
        case "viernes":
            viernesInicio = inicioInput.value;
            viernesFin = finInput.value;
            break;
        case "sabado":
            sabadoInicio = inicioInput.value;
            sabadoFin = finInput.value;
            break;        
        default:
            console.log("Día no válido");
            return;
    }   
}
//Resetea el valor del dia a null
function resetDia(dia) {    
    switch (dia.toLowerCase()) {
        case "lunes":
            lunesInicio =  null;
            lunesFin =  null;
            document.getElementById('lunesInicio').value = ''; // Asigna un valor vacío al campo en tu vista
            document.getElementById('lunesFin').value = ''; // Asigna un valor vacío al campo en tu vista
            break;
        case "martes":
            martesInicio = null;
            martesFin = null;
            document.getElementById('martesInicio').value = ''; // Asigna un valor vacío al campo en tu vista
            document.getElementById('martesFin').value = ''; // Asigna un valor vacío al campo en tu vista
            break;
        case "miercoles":
            miercolesInicio = null;
            miercolesFin = null;
            document.getElementById('miercolesInicio').value = ''; // Asigna un valor vacío al campo en tu vista
            document.getElementById('miercolesFin').value = ''; // Asigna un valor vacío al campo en tu vista
            break;
        case "jueves":
            juevesInicio = null;
            juevesFin = null;
            document.getElementById('juevesInicio').value = ''; // Asigna un valor vacío al campo en tu vista
            document.getElementById('juevesFin').value = ''; // Asigna un valor vacío al campo en tu vista
            break;
        case "viernes":
            viernesInicio = null;
            viernesFin = null;
            document.getElementById('viernesInicio').value = ''; // Asigna un valor vacío al campo en tu vista
            document.getElementById('viernesFin').value = ''; // Asigna un valor vacío al campo en tu vista
            break;
        case "sabado":
            sabadoInicio = null;
            sabadoFin = null;
            document.getElementById('sabadoInicio').value = ''; // Asigna un valor vacío al campo en tu vista
            document.getElementById('sabadoFin').value = ''; // Asigna un valor vacío al campo en tu vista
            break;        
        default:
            console.log("Día no válido");
            return;
    }   
}

$("#FormCrearGrupo").on("click", "#btnRegistrar", function (evento) {
    evento.preventDefault();
    var cupo = $("#cupo").val();
    var periodo = $("#periodo").val();    
    var maestro = $("#maestro").val();    
    var horasTotales = parseInt($("#horastotales").val());        
    var idActividad=$("#idActividad").val();   
    var noGrupo=$("#noGrupo").val();
    
    if(validarHoras()&&validarFormulario(cupo,horasTotales,noGrupo)){
        enviarDatosGrupo(noGrupo,cupo,periodo,maestro,horasTotales,idActividad);
    }
});

//lista los errores posibles


function validarFormulario(cupo, horasTotales,noGrupo) {
    var errores = [];

    // Validar que los campos sean numéricos usando la función camposnumericosValidos
    if (!camposnumericosValidos(cupo)) {
        errores.push("El campo 'Cupo' debe contener solo números.");
    }
    
    if (!camposnumericosValidos(noGrupo)) {
        errores.push("El campo 'Número de grupo' debe contener solo números.");
    }

    if (!camposnumericosValidos(horasTotales)) {
        errores.push("El campo 'Horas Totales' debe contener solo números.");
    }    

    // Validar que los valores sean menores que 100
    if (parseInt(cupo) >= cupoMaximo && parseInt(cupo) <= cupoMinimo ) {
        errores.push("El campo 'Cupo' debe ser menor que 100.");
    }

    if (parseInt(horasTotales) >= 100) {
        errores.push("El campo 'Horas Totales' debe ser menor que 100.");
    }

    

   
    if (errores.length > 0) {
        for (var i = 0; i < errores.length; i++) {
            mostrarMensaje(iconoError, "ERROR", errores[i]);
        }
        return false;
    }

    return true;
}
//valida que los campos numericos solo tengan numeros
function camposnumericosValidos(valor){
     var expresionRegular = /^[0-9]+$/;
    return expresionRegular.test(valor);
}


//Valida que las horas totales correspondan al total de horas ingresado
function validarHoras() {
    // Obtén los valores del formulario
    var horasTotales = parseInt(document.getElementById('horastotales').value); 
    
    // Calcula la suma de horas
    var sumaHoras =sumarDiferencias();
    // Compara la suma de horas con las horas totales
    if (sumaHoras !== horasTotales) {
        TituloMensaje = "ERROR";
        Mensaje = "Las suma de horas por dia no coincide con el numero de horas semanales "+ sumaHoras;
        mostrarMensaje(iconoError, TituloMensaje, Mensaje);
        resetDia("lunes");  // Debes ajustar esto para que sea el día correcto
        resetDia("martes");
        resetDia("miercoles");
        resetDia("jueves");
        resetDia("viernes");
        resetDia("sabado");
        return false;
    } else {
        return true;
    }
}

//Valida que la hora de inicio sea menor que la hora de fin
function validarHorasCorrectas(horaInicio, horaFin) {
            
    // Convertir las cadenas de texto a objetos Date para comparar
    var fechaInicio = new Date("2000-01-01T" + horaInicio + ":00");
    var fechaFin = new Date("2000-01-01T" + horaFin + ":00");

     // Comparar las fechas
    if (fechaInicio < fechaFin || (fechaInicio ===null && fechaFin===null)) {
        
        return true;
    } else if (fechaInicio > fechaFin) {
        
        return false;
    } else {
        
        return false;
    }
}


//Funcion que calcula la diferencia en Horas
function calcularDiferenciaHoras(horaInicio, horaFin) {
    // Parsear las cadenas de tiempo en objetos Date
    var inicio = new Date("2000-01-01T" + horaInicio + ":00");
    var fin = new Date("2000-01-01T" + horaFin + ":00");

    // Calcular la diferencia en milisegundos
    const diferenciaMilisegundos = fin - inicio;

    // Convertir la diferencia a horas
    const diferenciaHoras = diferenciaMilisegundos / (1000 * 60 * 60);
    console.log(diferenciaHoras);
    return diferenciaHoras;
}

//funcion que suma las diferencias de horas
function sumarDiferencias(){
    var total=0;
    
    if(validarHorasCorrectas(lunesInicio,lunesFin)){
        total= total+ calcularDiferenciaHoras(lunesInicio,lunesFin);
        console.log(total);
        
    }
    // Martes
    if (validarHorasCorrectas(martesInicio, martesFin)) {
        total = total + calcularDiferenciaHoras(martesInicio, martesFin);
        console.log(total);
    }

    // Miércoles
    if (validarHorasCorrectas(miercolesInicio, miercolesFin)) {
        total = total + calcularDiferenciaHoras(miercolesInicio, miercolesFin);
        console.log(total);
    }

    // Jueves
    if (validarHorasCorrectas(juevesInicio, juevesFin)) {
        total = total + calcularDiferenciaHoras(juevesInicio, juevesFin);
        console.log(total);
    }

    // Viernes
    if (validarHorasCorrectas(viernesInicio, viernesFin)) {
        total = total + calcularDiferenciaHoras(viernesInicio, viernesFin);
        console.log(total);
    }

    // Sábado
    if (validarHorasCorrectas(sabadoInicio, sabadoFin)) {
        total = total + calcularDiferenciaHoras(sabadoInicio, sabadoFin);
        console.log(total);
    }
    
    return total;
}


function enviarDatosGrupo(noGrupo,cupo, periodo, maestro, horasTotales, idActividad ) {
    // Creamos un indicador de carga
    $("#pageLoader").show();
    var datos = {
        idGrupo: idGrupo,
        noGrupo:noGrupo,
        cupo: cupo,
        periodo: periodo,
        maestro: maestro,
        horastotales: horasTotales,
        idActividad: idActividad,        
        lunesInicio: lunesInicio,
        lunesFin: lunesFin,
        martesInicio: martesInicio,
        martesFin: martesFin,
        miercolesInicio: miercolesInicio,
        miercolesFin: miercolesFin,
        juevesInicio: juevesInicio,
        juevesFin: juevesFin,
        viernesInicio: viernesInicio,
        viernesFin: viernesFin,
        sabadoInicio: sabadoInicio,
        sabadoFin: sabadoFin
    };
    
    // Mandamos el formdata al servidor con un post
    $.ajax({
        url: '../../app/grupos/editargrupo.do',
        type: 'POST',
        dataType: 'json',
        data: datos,
        success: function (respuesta) {
            // Ocultamos el indicador de carga
            $("#pageLoader").hide();

            TituloMensaje = "Grupo Modificado con Éxito";
            Mensaje = "Grupo Modificado con Éxito";
            var ruta = "/creditosextraescolares/app/grupos/listargrupos.do?idActividad="+idActividad;
            mensajeRedirect(iconoCorrecto, TituloMensaje, Mensaje, ruta);
            // window.location = ruta;
        },
        error: function (jqXHR, exception) {
            $("#pageLoader").hide();
            TituloMensaje = "ERROR";
            Mensaje = "Ocurrió un error en el servidor";
            mostrarMensaje(iconoError, TituloMensaje, Mensaje);
        }
    });
}





