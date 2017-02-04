/**
 * Adaptado de: https://netbeans.org/kb/docs/javaee/maven-websocketapi_pt_BR.html
 * @author TWS <tales (dot) wallace (at) gmail (dot) com>
 */


var canvas = document.getElementById("myCanvas");
var context = canvas.getContext("2d");
canvas.addEventListener("click", defineImage, false);

function leCoordenadasDoClick(evt) {
    var rect = canvas.getBoundingClientRect();
    return {
        x: evt.clientX - rect.left,
        y: evt.clientY - rect.top
    };
}

function defineImage(evt) {
    var posicaoAtual = leCoordenadasDoClick(evt);

    for (i = 0; i < document.formularioEntrada.forma.length; i++) {
        if (document.formularioEntrada.forma[i].checked) {
            var forma = document.formularioEntrada.forma[i];
            break;
        }
    }

    for (i = 0; i < document.formularioEntrada.cor.length; i++) {
        if (document.formularioEntrada.cor[i].checked) {
            var cor = document.formularioEntrada.cor[i];
            break;
        }
    }

    var json = JSON.stringify({
        "forma": forma.value,
        "cor": cor.value,
        "posicao": {
            "x": posicaoAtual.x,
            "y": posicaoAtual.y
        }
    });

    desenhaImagemJson(json);
    enviaFiguraJson(json);
}

function desenhaImagemJson(image) {
    console.log("desenhaImagemJson");
    var json = JSON.parse(image);
    context.fillStyle = json.cor;
    switch (json.forma) {
        case "circulo":
            context.beginPath();
            context.arc(json.posicao.x, json.posicao.y, 5, 0, 2 * Math.PI, false)
            context.fill();
            break;
        case "quadrado":
        default:
            context.fillRect(json.posicao.x, json.posicao.y, 10, 10)
            break;
    }
}

function defineImageBinaria() {
    var image = context.getImageData(0, 0, canvas.width, canvas.height);
    var buffer = new ArrayBuffer(image.data.length);
    var bytes = new Uint8Array(buffer);
    for (var i=0; i<bytes.length; i++) {
        bytes[i] = image.data[i];
    }
    enviaFiguraBinaria(buffer);
}

function desenhaImagemBinaria(blob) {
    var bytes = new Uint8Array(blob);
    console.log('drawImageBinary (bytes.length): ' + bytes.length);

    var imageData = context.createImageData(canvas.width, canvas.height);
    for (var i=8; i<imageData.data.length; i++) {
        imageData.data[i] = bytes[i];
    }

    context.putImageData(imageData, 0, 0);
    
    var img = document.createElement('img');
    img.height = canvas.height;
    img.width = canvas.width;
    img.src = canvas.toDataURL();
}

