
function verDetalles(valor){
    switch (valor){
        case "delfin":
            var detalle=document.getElementById("detalleDelfin");
            if(detalle.hidden){
                detalle.hidden=false;
                document.getElementById("detalle3").innerHTML="Ver menos";
            }else{
                detalle.hidden=true;
                document.getElementById("detalle3").innerHTML="Ver más";
            }
            break;
        case "pila":
            var detalle=document.getElementById("detallePila");
            if(detalle.hidden){
                detalle.hidden=false;
                document.getElementById("detalle1").innerHTML="Ver menos";
            }else{
                detalle.hidden=true;
                document.getElementById("detalle1").innerHTML="Ver más";
            }
            break;
        case "anuies":
            var detalle=document.getElementById("detalleAnuies");
            if(detalle.hidden){
                detalle.hidden=false;
                document.getElementById("detalle2").innerHTML="Ver menos";
            }else{
                detalle.hidden=true;
                document.getElementById("detalle2").innerHTML="Ver más";
            }
            break;
        case "pdfVisor":
            var detalle=document.getElementById("pdfVisor");
            if(detalle.hidden){
                detalle.hidden=false;
                document.getElementById("verPDF").innerHTML=" Ocultar";
            }else{
                detalle.hidden=true;
                document.getElementById("verPDF").innerHTML=" Ver";
            }
            break;
    }
}

function hidden(){
    
}

function eliminar(valor){
    document.getElementById("tabla");
}
