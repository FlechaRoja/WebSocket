var host = "ws://localhost:8080/WebSocketsDemo/servidor";
var wSocket = new WebSocket(host);
var browserSupport = ("WebSocket" in window) ? true : false;

// called  body onLoad()
function initializeReception()
{
    if (browserSupport)
    {
        wSocket.onopen = function ()
        {
          
        };
    } else
    {
        // No hay soporte, posiblemente un navegador obsoleto
        alert("WebSocket no es soportado en su browser. Utilice uno moderno.");
    }
}

wSocket.onmessage = onMessage;    

 function onMessage(evt) { 
    var received_msg = evt.data;
    document.getElementById('chatForm:serverMsg').value = received_msg + "\n"+document.getElementById('chatForm:serverMsg').value;
};

// called when socket closes
wSocket.onclose = function ()
{
    //alert("Connection is closed...");
};



function addMsg() {
    wSocket.send(document.getElementById('chatForm:msg').value);
    document.getElementById('chatForm:msg').value="";
}
