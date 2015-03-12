/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri = getRootUri() + "/JavaWebSockets/echo";
var websocketSession;

function getRootUri() {
    return "ws://" + (document.location.hostname === "" ? "localhost" : document.location.hostname) + ":" +
                (document.location.port === "" ? "8080" : document.location.port);
}

//Opens the connection with the server
function openConnection(){
    if(!websocketSession){
       websocketSession = new WebSocket(wsUri); 
    }
    websocketSession.onmessage = onAnswer;
    //fired when the server answer the onMessage
}

//Send a message to the server through the websocket
function onMessage(msg){
    websocketSession.send(msg);
}

//Updates de log
function onAnswer(evt){
    var websocketMessages = document.getElementById('output');
    websocketMessages.innerHTML = websocketMessages.innerHTML + evt.data + '<br/>';
}

//Closes the section
function onClose(){
    if(websocketSession){
        websocketSession.close();
    }
}