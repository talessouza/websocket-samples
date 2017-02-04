/**
 * Adaptado de: https://netbeans.org/kb/docs/javaee/maven-websocketapi_pt_BR.html
 * @author TWS <tales (dot) wallace (at) gmail (dot) com>
 */

var wsUri = "ws://" + document.location.host + document.location.pathname + "quadrobrancoendpoint";
var websocket = new WebSocket(wsUri);
websocket.binaryType = "arraybuffer";

websocket.onerror = function (evt) {
    onError(evt)
};

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

var output = document.getElementById("output");
websocket.onopen = function (evt) {
    onOpen(evt)
};

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}

function onOpen() {
    writeToScreen("Conectado a " + wsUri);
}

websocket.onmessage = function (evt) {
    onMessage(evt)
};

function enviaFiguraJson(json) {
    console.log("enviando Json/text: " + json);
    websocket.send(json);
}

function enviaFiguraBinaria(bytes) {
    console.log("envia binario: " + Object.prototype.toString.call(bytes));
    websocket.send(bytes);
}

function onMessage(evt) {
    console.log("recebido: " + evt.data);
    if (typeof evt.data === "string") {
        desenhaImagemJson(evt.data);
    } else {
        desenhaImagemBinaria(evt.data);
    }
}

