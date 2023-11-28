<!-- websocket-example.jsp -->
<%@ page import="java.util.*" %>
<%@ page import="javax.websocket.*" %>

<!DOCTYPE html>
<html>
<head>
  <title>WebSocket Example</title>
  <script type="text/javascript">
    var socket;

    function connect() {
      var wsUri = "ws://localhost:8080/websocket";
      socket = new WebSocket(wsUri);

      socket.onopen = function (event) {
        console.log("WebSocket opened");
      };

      socket.onmessage = function (event) {
        console.log("Received message: " + event.data);
      };

      socket.onclose = function (event) {
        console.log("WebSocket closed");
      };

      socket.onerror = function (event) {
        console.error("WebSocket error");
      };
    }

    function disconnect() {
      if (socket) {
        socket.close();
      }
    }

    function sendMessage() {
      var message = document.getElementById("message").value;
      socket.send(message);
    }
  </script>
</head>
<body>
<h1>WebSocket Example</h1>

<button onclick="connect()">Connect</button>
<button onclick="disconnect()">Disconnect</button>

<br>

<label for="message">Message:</label>
<input type="text" id="message">
<button onclick="sendMessage()">Send Message</button>
</body>
</html>
