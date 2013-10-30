var wsUri, websocket;

function inicializa(){
    wsUri = "ws://"+location.host+"/WebSocketsDemo/conObjeto";
    websocket = new WebSocket(wsUri); //creamos el socket
    websocket.onmessage = enMensaje;
    websocket.onopen = enAbrir;
    websocket.onerror = enError;
    miBoton = document.getElementById('btnGenerar');
    miBoton.addEventListener("click", enviarMensaje);
}

function enAbrir(evt) { //manejamos los eventos...
    log("Conectado..."); //... y aparecerá en la pantalla
};

 function enMensaje(evt) { // cuando se recibe un mensaje
    var recibido = JSON.parse(evt.data);
    var mensaje = "Nombre: "+recibido.nombre;
    mensaje += " Edad: "+recibido.edad;
    log("Mensaje recibido:" + mensaje);
};

function enError(evt) {
    log("oho!.. error:" + evt.data);
};

function enviarMensaje() {
    var miObjeto = {
        nombre: document.getElementById("txtNombre").value,
        edad: parseInt(document.getElementById("txtEdad").value)        
    };
    websocket.send(JSON.stringify(miObjeto));
    log("Enviando:" + JSON.stringify(miObjeto));
};

function log(mensaje) { //aqui mostrará el LOG de lo que está haciendo el WebSocket
    var logDiv = document.getElementById("log");
    logDiv.innerHTML += (mensaje + '<br/>');
};

window.addEventListener("onload",inicializa());