
var calificaciones = {};
$(document).ready(function () {
        // Manejar el evento de cambio en los menús desplegables
        
        
        $("select").change(function () {
            // Obtener el valor seleccionado
            var valorSeleccionado = $(this).val();

            // Obtener el ID de la fila
            var idFila = $(this).closest("tr").attr("id");
            calificaciones[idFila] = valorSeleccionado;
            
            console.log("NoControl"+idFila);
            console.log("valor"+valorSeleccionado);
        });
        $("select").each(function() {
            $(this).trigger('change');
        }); 
       

    });
    
    
    
let SeEstaModificando = false;

var tblListaAlumnos = $('#tblListaAlumnos').DataTable({
    "columnDefs": [
        {
            targets: [5],
            orderable: false
        }
    ],
    "language": {
        "search": "Buscar:",
        "lengthMenu": "Mostrando _MENU_ registros por página",
        "zeroRecords": "No se ha encontrado ningún registro.",
        "info": "Mostrando página _PAGE_ de _PAGES_",

        emptyTable: "No se ha encontrado ningún registro.",
        "infoEmpty": "Sin registros",
        "oPaginate": {
            "sNext": "Siguiente",
            "sPrevious": "Anterior"
        }
    }
});

filtradoColumna(tblListaAlumnos, "tblListaAlumnos");
crearTabla("tblListaAlumnos", updateRegistro, updateRegistro, updateRegistro, updateRegistro);
$("#tblListaAlumnos_filter").hide();

function eliminar_registro() {
    bootbox.alert("*Se elimina el registro");
    $("#BTN-Eliminar").hide();
    $("#BTN-Agregar").show();
    SeEstaModificando = false;
    $("#CerrarConfirmEliminacion").trigger("click");
}

function updateRegistro() {

}


$("#divGrpAlumnos").on("click", "#btnGuardar", function (evento) {
    evento.preventDefault();
    //var nombre = $("#nombreEvento").val();
    //var itt_password = $("#itt_password").val();    
    
    
    enviarDatosActividad(calificaciones);
    

});

function enviarDatosActividad(calificaciones) {
    //Creamos un indicador de carga
    $("#pageLoader").show();
    var a=parseInt($("#labelIdGrupo").text(),10);
    var datos={
        calificaciones:calificaciones, 
        idGrupo:a                   
    };
        
    $.ajax({
        url: '../../app/alumnos/calificaralumno.do',
        type: 'POST',
        dataType: 'json',
        data:      
            JSON.stringify(datos
           
            
        ),
              
        success: function (respuesta) {
            //ocultamos el indicador de carga
            $("#pageLoader").hide();
            
            TituloMensaje="Calificacion asentada con Exito";
            Mensaje="Calificacion asentada con Exito";
            var ruta  = "/creditosextraescolares/app/alumnos/calificaralumno.do?idGrupo="+a;
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