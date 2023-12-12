<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/header.jsp"/>

<body>
<div class="bg-white p-6 shadow rounded-lg">
    <div class="border-b pb-4">
        <h1 class="text-2xl font-semibold">자유 게시판</h1>
    </div>

    <div class="pt-4 flex justify-between">
        <div class="flex items-center">
            <img src="img/default_img.png" alt="이미지" class="rounded-full h-10 w-10" width="40" height="40" style="aspect-ratio: 40 / 40; object-fit: cover;"/>
            <div class="ml-3">
                <div class="text-sm font-medium">user</div>
                <div class="text-xs text-gray-500">createDate</div>
            </div>
        </div>

        <div class="flex space-x-2">
            <button
                    class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 h-10 px-2 py-1 text-sm bg-red-500 hover:bg-red-600 text-white"
                    type="button" onclick="report()"
            >
                신고하기
            </button>
        </div>
    </div>

    <div class="py-4">
        <h2 class="text-lg font-semibold">title</h2>
        <p class="text-sm text-gray-600 mt-1">contents</p>
    </div>

    <div class="flex items-center space-x-4">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="text-gray-400">
            <path d="M7 10v12"></path>
            <path d="M15 5.88 14 10h5.83a2 2 0 0 1 1.92 2.56l-2.33 8A2 2 0 0 1 17.5 22H4a2 2 0 0 1-2-2v-8a2 2 0 0 1 2-2h2.76a2 2 0 0 0 1.79-1.11L12 2h0a3.13 3.13 0 0 1 3 3.88Z"></path>
        </svg>
        <span class="text-sm text-gray-500">0</span>
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="text-gray-400">
            <polyline points="9 17 4 12 9 7"></polyline>
            <path d="M20 18v-2a4 4 0 0 0-4-4H4"></path>
        </svg>
        <span class="text-sm text-gray-500">0</span>
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="text-gray-400">
            <polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"></polygon>
        </svg>
        <span class="text-sm text-gray-500">0</span>
    </div>

    <div class="flex space-x-2 mt-4">
        <button
                class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 text-primary-foreground h-10 px-2 py-1 text-sm bg-gray-300 dark:bg-gray-700 hover:bg-gray-400 dark:hover:bg-gray-600"
                type="button"
        >
            공감
        </button>
        <button
                class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 text-primary-foreground h-10 px-2 py-1 text-sm bg-gray-300 dark:bg-gray-700 hover:bg-gray-400 dark:hover:bg-gray-600"
                type="button"
        >
            스크랩
        </button>
    </div>
</div>
<button
        class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 text-primary-foreground h-10 px-2 py-1 text-sm bg-gray-300 dark:bg-gray-700 hover:bg-gray-400 dark:hover:bg-gray-600"
        type="button" onclick="location.href='/community'"
>
    글 목록
</button>


<script>
    function report(){
        (async () => {
            const { value: text } = await Swal.fire({
                input: "textarea",
                inputLabel: "Message",
                inputPlaceholder: "Type your message here...",
                inputAttributes: {
                    "aria-label": "Type your message here"
                },
                showCancelButton: true
            });
            if (text) {
                // 서버로 텍스트 전송
                sendReportToServer(text);

            }
        })()
    }

    function sendReportToServer(text) {
        // AJAX 요청을 사용하여 서버로 텍스트 전송
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "/report", true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // 서버 응답을 처리할 수 있음
                Swal.fire('신고가 완료되었습니다!!');
            }
        };

        // 텍스트를 JSON 형식으로 변환하여 전송
        xhr.send(JSON.stringify({ text: text }));
    }
</script>

</body>
</html>
