

window.onload = function() {
    var a = $("#periodoActivoLabel").text();
    var b=$("#bLabel").text();
    //alert(b);
    if(a === "Activo"){        
        //alert("Activo"+a);
        TituloMensaje = "Asentar Calificaciones";
        Mensaje = "El Periodo de Asentar calificaciones está activo, recuerda que el sistema se cierra el dia "+b;
        mostrarMensaje(iconoInfo, TituloMensaje, Mensaje);
        
    }else{
        
        
    }
    
    
}