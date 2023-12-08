<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/header.jsp" flush="false" />
<body>
<section class="flex h-screen bg-gray-100 justify-center">
    <div class="w-3/4 bg-white overflow-y-auto">
        <div class="p-4 flex flex-col">
            <h1 class="text-2xl text-gray-700 dark:text-gray-300 mb-4">내 정보</h1>
        </div>
        <h2 class="text-xl text-gray-700 dark:text-gray-300 p-4 border-t border-gray-300 dark:border-gray-700">
            친구목록
        </h2>
        <ul class="divide-y divide-gray-300 dark:divide-gray-700">

            <!--Start Point-->
            <c:forEach var="item" items='#'>
            <li class="p-4 hover:bg-gray-300 dark:hover:bg-gray-700 cursor-pointer flex items-center space-x-2 justify-between">
                <div class="flex items-center space-x-2">
                    <img
                            alt="Friend 1"
                            class="rounded-full border-2 border-blue-500"
                            height="40"
                            src="../img/default_img.jpg"
                            width="40"
                            style="aspect-ratio:40/40;object-fit:cover"
                    />
                    <div>
                        <h2 class="text-gray-700 dark:text-gray-300">item.name</h2>
                    </div>
                </div>
                <div class="flex space-x-2">
                    <button
                            class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 text-primary-foreground h-10 px-2 py-1 text-sm bg-gray-300 dark:bg-gray-700 hover:bg-gray-400 dark:hover:bg-gray-600"
                            type="button"
                            onclick="rmConfirm()"
                    >
                        Remove
                    </button>
                    <button
                            class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 h-10 px-2 py-1 text-sm bg-red-500 hover:bg-red-600 text-white"
                            type="button" onclick="report()"
                    >
                        Report
                    </button>
                </div>
            </li>
            <!--끝-->
            </c:forEach>

    </ul>
    </div>
</section>

<script>
    function rmConfirm(){
        Swal.fire({
            title: "정말로 삭제하시겠어요?",
            text: "다시는 만날 수 없을지도 모릅니다!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, delete it!."
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    title: "삭제 완료!",
                    text: "인관간계 정리를 참 잘하시는군요!",
                    icon: "success"
                });
                //친구 삭제 내용 작성

            }
        });
    }
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

<!-- footer 영역 -->
<jsp:include page="/footer.jsp" flush="false" />


</body>
</html>