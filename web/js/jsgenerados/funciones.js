function mostrarMensaje(icono, titulo, mensaje) {
 bootbox.alert({
        title: icono+" "+titulo ,
        message: '<div class="row" style="text-align: justify">' +
                '<div class=col-md-12>' +
                '<h6 class=panel-title>' + mensaje + '</h6>' +
                '</div>' +
                '</div>',
        buttons: {
            ok: {
                label: 'Aceptar',
                className: "btn-primary"
            }
        }
    });
}

function mensajeRedirect(icono, titulo, mensaje, url){
    
bootbox.alert({
    title: icono+" "+titulo ,
    message: '<div class="row" style="text-align: justify">' +
                '<div class=col-md-12>' +
                '<h6 class=panel-title>' + mensaje + '</h6>' +
                '</div>' +
                '</div>',
    button:{
        ok:{
            label:"Aceptar",
            className:"btn-primary"
        }
    },  
    callback: function (result) {
        window.location = url;
    }
});    
    
}