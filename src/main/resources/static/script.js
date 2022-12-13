var stompClient=null;

$(document).ready(function(){
        console.log("index page is ready");
        connect();

        $("#send").click(function(){

                sendMessage();
            });

});

function connect(){
        var socket = new SockJS('/our-websocket');

        stompClient= Stomp.over(socket);
        stompClient.connect({}, function (frame){

                console.log("Connected +++: "+frame);
                stompClient.subscribe('/topic/messages', function (message){
                        showMessage(JSON.parse(message.body).responseMessage);
                });

                stompClient.subscribe('/user/topic/private-messages', function (message){
                        showMessage(JSON.parse(message.body).responseMessage);
                });
        });
}

function showMessage(message){

        $("#messages").append("<tbody style='color: black'><tr><td>"+ message + "</td></tr></tbody>");
}

function sendMessage(){
        console.log("Sending Message");
        stompClient.send("/ws/message", {},JSON.stringify({'messageContent': $("#message").val()}));
}

function sendPrivateMessage(){
        console.log("Sending Private Message");
        stompClient.send("/ws//private-message", {},JSON.stringify({'messageContent': $("#message").val()}));
}