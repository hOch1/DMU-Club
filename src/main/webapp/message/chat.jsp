<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <!--include header -->
    <jsp:include page="../header.jsp" flush="false" />

<body>

    <!-- Left Side 부분 -->
    <section class="flex h-screen">
      <aside class="w-1/4 bg-gray-200 dark:bg-gray-800 overflow-y-auto" id="userListContainer">
        <ul class="divide-y divide-gray-300 dark:divide-gray-700">
          <!-- 유저 목록 추가 -->
        </ul>
      </aside>

      <!-- Header 부분 -->
      <div class="flex flex-col w-3/4">
        <header class="flex justify-between items-center p-4 bg-blue-500">
          <div class="flex items-center">
            <img
              alt="DMU Club Logo"
              class="rounded-full"
              height="40"
              src="/placeholder.svg"
              width="40"
              style="aspect-ratio: 40 / 40; object-fit: cover;"
            />
            <h1 class="text-white text-xl ml-2" id="partnerName">상대방 이름</h1>
          </div>
          <a href="#">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2"
              stroke-linecap="round"
              stroke-linejoin="round"
              class="h-6 w-6 text-white"
            >
              <path d="M18 6 6 18"></path>
              <path d="m6 6 12 12"></path>
            </svg>
          </a>
        </header>

        <!-- 채팅창 구현 부분 -->
        <main class="flex-grow overflow-y-auto p-4 bg-gray-100 dark:bg-gray-800">
          <div id="chatContainer" class="flex flex-col space-y-4">
            <!--여기에 채팅 추가됌 -->
          </div>
        </main>

        <footer class="border-t p-2 bg-white dark:bg-gray-900">
          <form class="flex items-center space-x-2">
            <input
              class="border rounded p-2 flex-grow dark:bg-gray-800 dark:text-white"
              placeholder="Type a message..."
              type="text"
              id="messageInput"
            />
            <button
              class="inline-flex items-center justify-center text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-primary/90 h-10 px-4 py-2 bg-blue-500 text-white rounded"
              type="button"  
              id="sendButton"
            >
              Send
            </button>
          </form>
        </footer>
      </div>
    </section>

    <script>
      document.addEventListener("DOMContentLoaded", function () {
        const userListContainer = document.getElementById("userListContainer");
        const partnerNameElement = document.getElementById("partnerName");
        const chatContainer = document.getElementById("chatContainer");
        const messageInput = document.getElementById("messageInput");
        const sendButton = document.getElementById("sendButton");

        // Fetch API를 이용하여 서버에서 유저 데이터 및 채팅 데이터를 가져오기
        fetch("/api/users") // 여기 수정해야함
          .then((response) => response.json())
          .then((data) => {

            // ( 1 ) 가져온 유저 데이터를 이용하여 동적으로 유저 리스트 생성
            data.users.forEach((user) => {
              const userListItem = document.createElement("li");
              userListItem.className =
                "p-4 hover:bg-gray-300 dark:hover:bg-gray-700 cursor-pointer flex items-center space-x-2";
              userListItem.innerHTML = `
                  <img alt="${user.name}" class="rounded-full" height="40" src="${user.profileImage}" width="40" style="aspect-ratio: 40 / 40; object-fit: cover;" />
                  <div>
                      <h2 class="text-gray-700 dark:text-gray-300">${user.name}</h2>
                      <p class="text-gray-500 dark:text-gray-400 text-sm">${user.lastMessage}</p>
                  </div>
              `;
              userListContainer.appendChild(userListItem);
            });

            //( 2 ) 상대방 이름 업데이트
            partnerNameElement.textContent = data.partnerName;

            //( 3 ) 가져온 채팅 데이터를 이용하여 동적으로 채팅 추가
            data.chats.forEach((chat) => {
            const chatItem = document.createElement("div");
            chatItem.className = `flex flex-col items-${chat.sender == "user" ? "end" : "start"} mt-2`;
            chatItem.innerHTML = `
                <div class="rounded p-3 ${
                chat.sender == "user"
                    ? "bg-white dark:bg-gray-900"
                    : "bg-blue-500 text-white self-end"
                } inline-block">
                    <p class="${
                chat.sender == "user" ? "text-gray-700 dark:text-gray-300" : ""
            }">${chat.message}</p>
                </div>
                <small class="text-gray-500 dark:text-gray-400 text-xs">${formatMessageTime(chat.time)}</small>`;
            chatContainer.appendChild(chatItem);
        });
  
            // 메세지 밑에 통신한 시간 나타내는 함수 
            function formatMessageTime(time) {
            const messageTime = new Date(time);
            const options = { hour: "numeric", minute: "numeric", hour12: true };
            return new Intl.DateTimeFormat("en-US", options).format(messageTime); 
        }

            // "Send" 버튼 클릭 이벤트 처리
            sendButton.addEventListener("click", function () {
              const newMessage = messageInput.value;

              // 새로운 메시지를 서버로 전송 (여기에서는 가상의 API 엔드포인트 '/api/send-message'를 사용)
              fetch("/api/send-message", {
                method: "POST",
                headers: {
                  "Content-Type": "application/json",
                },
                body: JSON.stringify({
                  message: newMessage,
                }),
              })
                .then((response) => response.json())
                .then((newChat) => {
                  // 서버에서 새로운 메시지를 받아와서 화면에 추가
                  const chatItem = document.createElement("div");
                  chatItem.className = "rounded p-3 bg-white dark:bg-gray-900 inline-block";
                  chatItem.innerHTML = `<p class="text-gray-700 dark:text-gray-300">${newChat.message}</p>`;
                  chatContainer.appendChild(chatItem);

                  // 입력 필드 비우기
                  messageInput.value = "";
                })
                .catch((error) => console.error("Error sending message:", error));
            });

          })
          .catch((error) => {
            console.error("Error fetching data:", error);
          });
      });

      
    </script>
  </body>
</html>
