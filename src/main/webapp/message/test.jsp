<%@ page import="java.util.List" %>
<%@ page import="dmu.dmuclub.dto.member.MemberDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>WebSocket Chat</title>
</head>
<body>
<h1>채팅방 리스트</h1>
<% List<MemberDto> memberDtoList = (List<MemberDto>) request.getAttribute("chatList");
for (MemberDto memberDto : memberDtoList){ %>
<a href="/message?nickname=<%=memberDto.getNickname()%>"><%=memberDto.getNickname()%></a>
<%}%>

<h1>WebSocket Chat</h1>
<input type="text" id="message" placeholder="메시지를 입력하세요.">
<button type="submit" onsubmit="sendMessage()" onclick="sendMessage()">메시지 보내기</button>
<div id="messages">
  <c:forEach items="${log}" var="logs">
    <p>${logs.message}</p>
  </c:forEach>
</div>

<script>
  <%
  String nickname=null;
  if (request.getAttribute("nickname") != null) {
    nickname = request.getAttribute("nickname").toString();
  }

 %>
  if ("<%=nickname%>" == null){
    var ws = new WebSocket("ws://localhost:8080/messagePoint");
  }else{
    var ws = new WebSocket("ws://localhost:8080/messagePoint/<%=nickname%>");
  }

  <%--ws.onopen = function(event) {--%>
  <%--  var message = event.data;--%>
  <%--  document.getElementById("messages").innerHTML += "<p>" + message + "</p>";--%>
  <%--  console.log("WebSocket 연결 성공");--%>
  <%--  console.log("<%=nickname%>님과의 채팅방")--%>
  <%--};--%>

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
