<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../header.jsp" flush="false" />


<body>

<section class="flex h-screen bg-gray-100 dark:bg-gray-900 justify-center">
    <aside class="w-3/4 bg-white dark:bg-gray-800 overflow-y-auto">
        <ul id="chatList" class="divide-y divide-gray-300 dark:divide-gray-700">
            <!-- Dynamic content will be added here -->
        </ul>
    </aside>
</section>

<script>
    // Fetch data from the server (replace the URL with your actual server endpoint)
    fetch('/api/getChatData')
        .then(response => response.json())
        .then(data => {
            const chatList = document.getElementById('chatList');
            data.forEach(chat => {
                const listItem = document.createElement('li');
                listItem.className = 'p-4 hover:bg-gray-300 dark:hover:bg-gray-700 cursor-pointer flex items-center space-x-2';
                listItem.innerHTML = `
                    <img
                        alt="${chat.userName}"
                        class="rounded-full border-2 border-blue-500"
                        height="40"
                        src="${chat.imageUrl}"
                        width="40"
                        style="aspect-ratio: 40 / 40; object-fit: cover;"
                    />
                    <div>
                        <h2 class="text-gray-700 dark:text-gray-300"> ${chat.userName}</h2>
                        <p class="text-gray-500 dark:text-gray-400 text-sm">${chat.lastMessage}</p>
                    </div>
                `;
                chatList.appendChild(listItem);
            });
        })
        .catch(error => console.error('Error fetching data:', error));
</script>
<!-- footer 영역 -->
<jsp:include page="../footer.jsp" flush="false" />


</body>
</html>