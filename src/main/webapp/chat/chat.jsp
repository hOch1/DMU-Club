<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>WebSocket Chat</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
  <script>
    function connectWebSocket(username) {
      var webSocket = new WebSocket("ws://" + location.host + "/chatSocket/" + username);

      webSocket.onopen = function (event) {
        console.log("WebSocket connection opened.");
      };

      webSocket.onmessage = function (event) {
        var message = event.data;
        var chatWindow = document.getElementById("chatWindow");
        chatWindow.innerHTML += "<p class='text-green-500'>" + message + "</p>";
        chatWindow.scrollTop = chatWindow.scrollHeight;
      };

      webSocket.onclose = function (event) {
        console.log("WebSocket connection closed.");
      };

      return webSocket;
    }

    function sendMessage(webSocket, message) {
      webSocket.send(message);
      document.getElementById("messageInput").value = "";
    }

    window.onload = function () {
      var username = <%= session.getAttribute("nickname")%>
      var webSocket = connectWebSocket(username);

      var sendButton = document.getElementById("sendButton");
      if (sendButton) {
        sendButton.addEventListener("click", function () {
          var message = document.getElementById("messageInput").value;
          sendMessage(webSocket, message);
        });
      } else {
        console.error("Button with ID 'sendButton' not found.");
      }
    };

  </script>

</head>
<body class="bg-gray-100 p-8">
<h1 class="text-3xl mb-4">WebSocket Chat</h1>
<div id="chatWindow" class="border p-4 mb-4 max-h-48 overflow-y-auto bg-white"></div>
<div class="flex">
  <input type="text" id="messageInput" class="flex-grow p-2 mr-2 border rounded" placeholder="Type your message">
  <button onclick="sendMessage()" class="p-2 bg-blue-500 text-white rounded">Send</button>
</div>
</body>
</html>
