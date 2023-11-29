<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>채팅</title>
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="font-sans bg-gray-200">
<div class="container mx-auto my-8 flex">
  <!-- 왼쪽: 친구 목록 -->
  <div class="w-1/4 bg-white p-4 rounded-lg shadow-md">
    <h2 class="text-2xl font-bold mb-4">친구 목록</h2>
    <ul>
      <li class="mb-2 cursor-pointer" onclick="openChat('김준성')">김준성</li>
      <li class="mb-2 cursor-pointer" onclick="openChat('연수민')">연수민</li>
      <li class="mb-2 cursor-pointer" onclick="openChat('최준영')">최준영</li>
      <li class="mb-2 cursor-pointer" onclick="openChat('김수민')">김수민</li>
    </ul>
  </div>

  <!-- 오른쪽: 채팅 영역 -->
  <div class="w-3/4 bg-white p-4 rounded-lg shadow-md">
    <!-- 채팅 목록 -->
    <div id="chat-messages" class="h-64 overflow-y-auto border-b mb-4">
      <!-- 채팅 메세지가 동적으로 추가될 영역 -->
    </div>

    <!-- 메세지 입력창 -->
    <div class="flex">
      <input id="message" type="text" class="flex-grow border rounded-l-md p-2">
      <button onclick="sendMessage()" class="bg-blue-500 text-white px-4 rounded-r-md">전송</button>
    </div>
  </div>
</div>

<!-- WebSocket 연결 및 메세지 전송을 위한 스크립트 -->
<script>
  const socket = new WebSocket("ws://localhost:8080/chatSocket");
  const chatMessages = document.getElementById("chat-messages");
  const messageInput = document.getElementById("message");

  // WebSocket 연결
  socket.addEventListener("open", (event) => {
    console.log("WebSocket 연결 성공");
  });

  // WebSocket 메세지 수신
  socket.addEventListener("message", (event) => {
    const message = JSON.parse(event.data);
    appendMessage(message);
  });

  // 메세지 전송
  function sendMessage() {
    const message = messageInput.value;
    if (message.trim() !== "") {
      socket.send(JSON.stringify({ type: "chat", message }));
      messageInput.value = "";
    }
  }

  // 채팅 메세지를 목록에 추가
  function appendMessage(message) {
    const messageElement = document.createElement("div");
    messageElement.textContent = message;
    chatMessages.appendChild(messageElement);
    chatMessages.scrollTop = chatMessages.scrollHeight;
  }

  // 친구를 클릭하면 해당 친구와의 채팅창을 열기
  function openChat(friendName) {
    // 여기에서 선택한 친구와의 채팅창을 열도록 구현
    // 예: 채팅창 영역을 동적으로 생성하거나 특정 화면으로 이동
  }
</script>
</body>
</html>
