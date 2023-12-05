<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>WebSocket Chat</title>
</head>
<body>
<h1>WebSocket Chat</h1>
<input type="text" id="message" placeholder="메시지를 입력하세요.">
<button onsubmit="sendMessage()" onclick="sendMessage()">메시지 보내기</button>
<div id="messages"></div>

<script>
  var ws = new WebSocket("ws://localhost:8080/messagePoint");

  ws.onopen = function() {
    console.log("WebSocket 연결 성공");
  };

  ws.onmessage = function(event) {
    var message = event.data;
    document.getElementById("messages").innerHTML += "<p>" + message + "</p>";
  };

  function sendMessage() {
    var message = document.getElementById("message").value;
    ws.send(message);
  }
</script>
</body>
</html>
