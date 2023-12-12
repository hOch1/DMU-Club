<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Andy
  Date: 2023-12-08
  Time: 오후 8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>WebSocket Chat</title>
    <script src="https://cdn.tailwindcss.com"></script> <!--테일윈드 라이브러리-->
</head>
<body>


<script>

    var ws = new WebSocket("ws://localhost:8080/messagePoint/${nickname}");

    // ws.onopen = function(event) {
    //   var message = event.data;
    //   document.getElementById("messages").innerHTML += "<p>" + message + "</p>";
    // };

    ws.onmessage = function(event) {
        var message = JSON.parse(event.data);
        console.log(message.msg);
        var meText =
            '<div class="flex items-center justify-start flex-row-reverse">' +
            '<div class="flex items-center justify-center h-10 w-10 rounded-full bg-indigo-500 flex-shrink-0">' +
            '<!-- Add content here if needed -->' +
            '</div>' +
            '<div class="relative mr-3 text-sm bg-indigo-100 py-2 px-4 shadow rounded-xl">' +
            '<div>'+message.msg+'</div>' +
            '</div>' +
            '</div>';

        var elseText =
            '<div class="flex flex-row items-center">' +
            '<div class="flex items-center justify-center h-10 w-10 rounded-full bg-indigo-500 flex-shrink-0">' +
            '<!-- Add content here if needed -->' +
            '</div>' +
            '<div class="relative mr-3 text-sm bg-indigo-100 py-2 px-4 shadow rounded-xl">' +
            '<div>'+message.msg+'</div>' +
            '</div>' +
            '</div>';


        if (msg.member_id === ${member.id}) {
            document.getElementById("viewMessage").innerHTML += '<div class="flex items-center justify-start flex-row-reverse">' +
                '<div class="flex items-center justify-center h-10 w-10 rounded-full bg-indigo-500 flex-shrink-0">' +
                '<!-- Add content here if needed -->' +
                '</div>' +
                '<div class="relative mr-3 text-sm bg-indigo-100 py-2 px-4 shadow rounded-xl">' +
                '<div>'+message.msg+'</div>' +
                '</div>' +
                '</div>';
        } else {
            // 다른 사용자가 보낸 메시지인 경우
            document.getElementById("viewMessage").innerHTML += '<div class="flex flex-row items-center">' +
                '<div class="flex items-center justify-center h-10 w-10 rounded-full bg-indigo-500 flex-shrink-0">' +
                '<!-- Add content here if needed -->' +
                '</div>' +
                '<div class="relative mr-3 text-sm bg-indigo-100 py-2 px-4 shadow rounded-xl">' +
                '<div>'+message.msg+'</div>' +
                '</div>' +
                '</div>';
        }
    };

    function sendMessage() {
        var message = document.getElementById("message").value;
        ws.send(message);
    }
</script>

<!-- 헤더 부분 -->
<header class="flex justify-between items-center p-4 bg-blue-500">
    <div class="flex items-center" style=cursor:pointer; onclick="location.href='/main'" >
        <img
                src="${pageContext.request.contextPath}/img/logo.png"
                height="40"
                width="40"
                alt="로고가 나타남"
                class="rounded-full"
                style="aspect-ratio:40/40;object-fit:cover"
        />
        <h1 class="text-2xl text-white ml-2" >DMU Club</h1>
    </div>
</header>


<!-- 시작 -->
<div class="flex h-screen antialiased text-gray-800">
    <div class="flex flex-row h-full w-full overflow-x-hidden">
        <div class="flex flex-col py-8 pl-6 pr-2 w-64 bg-white flex-shrink-0">
            <div class="flex flex-row items-center justify-center h-12 w-full">
                <div
                        class="flex items-center justify-center rounded-2xl text-indigo-700 bg-indigo-100 h-10 w-10"
                >
                    <svg
                            class="w-6 h-6"
                            fill="none"
                            stroke="currentColor"
                            viewBox="0 0 24 24"
                            xmlns="http://www.w3.org/2000/svg"
                    >
                        <path
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                d="M8 10h.01M12 10h.01M16 10h.01M9 16H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-5l-5 5v-5z"
                        ></path>
                    </svg>
                </div>
                <div class="ml-2 font-bold text-2xl">1:1 대화</div>
            </div>

            <!-- 왼쪽 위 내 프로필-->
            <div
                    class="flex flex-col items-center bg-indigo-100 border border-gray-200 mt-4 w-full py-6 px-4 rounded-lg"
            >
                <div class="h-20 w-20 rounded-full border overflow-hidden">
                    <img
                            src="/img/default_img.jpg"
                            alt="나의 프로필"
                            class="h-full w-full"
                    />
                </div>
                <div class="text-sm font-semibold mt-2">${member.nickname}</div>
                <div class="text-xs text-gray-500">${member.mbti}</div>
                <div class="flex flex-row items-center mt-3">
                    <div
                            class="flex flex-col justify-center h-4 w-8 bg-indigo-500 rounded-full"
                    >
                        <div class="h-3 w-3 bg-white rounded-full self-end mr-1"></div>
                    </div>
                    <div class="leading-none ml-1 text-xs">Active</div>
                </div>
            </div>

            <!-- 왼쪽 밑 대화 상대 리스트-->
            <div class="flex flex-col mt-8">
                <div class="flex flex-row items-center justify-between text-xs">
                    <span class="font-bold">대화 가능한 유저</span>
                    <span class="flex items-center justify-center bg-gray-300 h-4 w-4 rounded-full">
            <%--메시지 가능한 인원 수--%>
            </span>
                </div>

                <!-- 대화가능 유저 시작 -->
                <div class="flex flex-col space-y-1 mt-4 -mx-2 h-48 overflow-y-auto">
                    <c:forEach items="${chatList}" var="chat">
                    <button class="flex flex-row items-center hover:bg-gray-100 rounded-xl p-2">
                        <div class="flex items-center justify-center h-8 w-8 bg-indigo-200 rounded-full">
                            <c:set var="chatName" value="${chat.nickname}" />
                            <c:set var="firstCharacter" value="${fn:substring(chatName, 0, 1)}" />
                            ${firstCharacter}
                            <!-- 프로필 안에 성-->
                        </div>
                        <div class="ml-2 text-sm font-semibold">${chat.nickname}</div>
                        <div class="flex items-center justify-center ml-auto text-xs text-white bg-red-500 h-4 w-4 rounded leading-none" >3
                            <!-- 알림숫자 !-->
                        </div>

                    </button>
                    </c:forEach>

                    <!--대화가능 유저 끝 -->
                </div>
            </div>
        </div>

        <!--대화 시작 -->

        <div class="flex flex-col flex-auto h-full p-6">
            <div class="flex flex-col flex-auto flex-shrink-0 rounded-2xl bg-gray-100 h-full p-4">
                <div class="flex flex-col h-full overflow-x-auto mb-4">
                    <div class="flex flex-col h-full">
                        <div class="grid grid-cols-12 gap-y-2">
                            <c:forEach items="${logs}" var="log">
                                <c:choose>
                                    <c:when test="${log.myText}">
                                        <div id="messages" class="col-start-6 col-end-13 p-3 rounded-lg">
                                            <div class="flex items-center justify-start flex-row-reverse">
                                                <div class="flex items-center justify-center h-10 w-10 rounded-full bg-indigo-500 flex-shrink-0">
                                                        <%-- a.name.substring(0,1); --%>
                                                </div>
                                                <div class="relative mr-3 text-sm bg-indigo-100 py-2 px-4 shadow rounded-xl">
                                                    <div>${log.message}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div id="messages" class="col-start-1 col-end-8 p-3 rounded-lg">
                                            <div class="flex flex-row items-center">
                                                <div class="flex items-center justify-center h-10 w-10 rounded-full bg-indigo-500 flex-shrink-0">
                                                        <%-- a.name.substring(0,1); --%>
                                                </div>
                                                <div class="relative ml-3 text-sm bg-white py-2 px-4 shadow rounded-xl">
                                                    <div>${log.message}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        </div>


<%--                        실시간 채팅 내역--%>
                        <div id="viewMessage" class="col-start-1 col-end-8 p-3 rounded-lg">

                        </div>
                    </div>
                </div>
                <div class="flex flex-row items-center h-16 rounded-xl bg-white w-full px-4">
                    <div>
                        <button class="flex items-center justify-center text-gray-400 hover:text-gray-600">
                            <svg
                                    class="w-5 h-5"
                                    fill="none"
                                    stroke="currentColor"
                                    viewBox="0 0 24 24"
                                    xmlns="http://www.w3.org/2000/svg"
                            >
                                <path
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                        stroke-width="2"
                                        d="M15.172 7l-6.586 6.586a2 2 0 102.828 2.828l6.414-6.586a4 4 0 00-5.656-5.656l-6.415 6.585a6 6 0 108.486 8.486L20.5 13"
                                ></path>
                            </svg>
                        </button>
                    </div>
                    <div class="flex-grow ml-4">
                        <div class="relative w-full">
                            <input type="text" id="message" class="flex w-full border rounded-xl focus:outline-none focus:border-indigo-300 pl-4 h-10"/>
                            <button class="absolute flex items-center justify-center h-full w-12 right-0 top-0 text-gray-400 hover:text-gray-600">
                                <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14.828 14.828a4 4 0 01-5.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                                </svg>
                            </button>
                        </div>
                    </div>
                    <div class="ml-4">
                        <button class="flex items-center justify-center bg-indigo-500 hover:bg-indigo-600 rounded-xl text-white px-4 py-1 flex-shrink-0" onclick="sendMessage()">
                            <span>Send</span>
                            <span class="ml-2">
                <svg class="w-4 h-4 transform rotate-45 -mt-px"
                        fill="none"
                        stroke="currentColor"
                        viewBox="0 0 24 24"
                        xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          stroke-width="2"
                          d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8"
                  ></path>
                </svg>
              </span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 끝 -->
</body>
</html>