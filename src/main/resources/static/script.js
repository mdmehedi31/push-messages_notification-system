var stompClient=null;
var notificationCount= 0;
$(document).ready(function(){
        console.log("index page is ready");
        connect();

        $("#send").click(function(){

                sendMessage();
            });
        $("#send-private").click(function(){

                sendPrivateMessage();
        });

        $("#notifications").click(function() {
                resetNotificationCount();
        });
});

function connect(){
        var socket = new SockJS('/our-websocket');

        stompClient= Stomp.over(socket);
        stompClient.connect({}, function (frame){

                console.log("Connected +++: "+frame);
                updateNotificationDisplay();
                stompClient.subscribe('/topic/messages', function (message){
                        showMessage(JSON.parse(message.body).responseMessage);
                });

                stompClient.subscribe('/user/topic/private-messages', function (message){
                        showPrivateMessage(JSON.parse(message.body).responseMessage);
                });

                stompClient.subscribe('/topic/global-notifications', function (message) {
                        notificationCount++;
                        updateNotificationDisplay();
                });

                stompClient.subscribe('/user/topic/private-notifications', function (message) {
                        notificationCount++;
                        updateNotificationDisplay();
                });
        });
}

function showMessage(message){

        $("#messages").append("<tbody style='color: black'><tr><td>"+ message + "</td></tr></tbody>");
}

function showPrivateMessage(message){

        $("#messages").append("<tbody style='color: black'><tr><td>"+ message + "</td></tr></tbody>");
}

function sendMessage(){
        console.log("Sending Message");
        stompClient.send("/ws/message", {},JSON.stringify({'messageContent': $("#message").val()}));
}

function sendPrivateMessage(){
        console.log("Sending Private Message");
        stompClient.send("/ws/private-message", {},JSON.stringify({'messageContent': $("#private-message").val()}));
}

function updateNotificationDisplay() {
        if (notificationCount == 0) {
                $('#notifications').hide();
        } else {
                $('#notifications').show();
                $('#notifications').text(notificationCount);
        }
}
function resetNotificationCount() {
        notificationCount = 0;
        updateNotificationDisplay();
}