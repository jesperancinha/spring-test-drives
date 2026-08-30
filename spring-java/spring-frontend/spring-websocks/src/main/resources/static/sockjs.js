let sockJSStompClient = null;

let startConnection = () => {
    const socket = new SockJS('/ws');
    sockJSStompClient = Stomp.over(socket);
    sockJSStompClient.connect({}, function (frame) {
        console.log(frame);
        updateControls(true);
        sockJSStompClient.subscribe('/business/present', function (message) {
            displayMessages(JSON.parse(message.body));
        });
        sockJSStompClient.subscribe('/business/notification', function (message) {
            console.log(message);
        });
    });
}

let stopConnection = () => {
    if (sockJSStompClient !== null) {
        sockJSStompClient.disconnect();
    }
    updateControls(false);
}

let updateControls = (connected) => {
    $("#btnConnect").prop("disabled", connected);
    $("#btnDisconnect").prop("disabled", !connected);
    $("#btnSendMessage").prop("disabled", !connected);
    $("#messaging").html("");
    $("#console").prop("style", "display:none");
    $("#lblConnect").prop("class", connected ? "sockJSLive" : "sockJSOff");
}

let displayMessages = (message) => {
    let $messaging = $("#messaging");
    $messaging.append("<p>Message:'" + message.message + "' sent on " + message.localDateTime + " and received on " + message.systemDateTime + ". Server response is: " + message.response + "</p>");
    $("#console").prop("style", $messaging.html() !== "" ? "display" : "display:none");
}

let sendMessage = () => {
    let $textMessage = $("#textMessage");
    sockJSStompClient.send("/flash9/request", {}, JSON.stringify({
        'message': $textMessage.val(),
        'localDateTime': new Date()
    }));
    $("#requests").append("<p>" + $textMessage.val() + " at " + new Date() + "</p>");
}

let updateBtnControls = () => {
    $("#btnConnect").click(function () {
        startConnection();
    });
    $("#btnDisconnect").click(function () {
        stopConnection();
    });
    $("#btnSendMessage").click(function () {
        sendMessage();
    });
}

let setupForm = () => {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
}

$(() => {
    setupForm();
    updateBtnControls();
    updateControls(false);
});
