<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../header.jsp" flush="false" />
<body>
<section class="flex h-screen bg-gray-100 dark:bg-gray-900 justify-center">
    <div class="w-3/4 bg-white dark:bg-gray-800 overflow-y-auto">
        <div class="p-4 flex flex-col">
            <h1 class="text-2xl text-gray-700 dark:text-gray-300 mb-4">내 정보</h1>
        </div>
        <h2 class="text-xl text-gray-700 dark:text-gray-300 p-4 border-t border-gray-300 dark:border-gray-700">
            친구목록
        </h2>
        <ul class="divide-y divide-gray-300 dark:divide-gray-700">
            <li class="p-4 hover:bg-gray-300 dark:hover:bg-gray-700 cursor-pointer flex items-center space-x-2 justify-between">
                <div class="flex items-center space-x-2">
                    <img
                            alt="Friend 1"
                            class="rounded-full border-2 border-blue-500"
                            height="40"
                            src="/placeholder.svg"
                            width="40"
                            style="aspect-ratio:40/40;object-fit:cover"
                    />
                    <div>
                        <h2 class="text-gray-700 dark:text-gray-300">name</h2>
                    </div>
                </div>
                <div class="flex space-x-2">
                    <button
                            class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 text-primary-foreground h-10 px-2 py-1 text-sm bg-gray-300 dark:bg-gray-700 hover:bg-gray-400 dark:hover:bg-gray-600"
                            type="button"
                            onclick="showConfirm()"
                    >
                        Remove
                    </button>
                    <button
                            class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 h-10 px-2 py-1 text-sm bg-red-500 hover:bg-red-600 text-white"
                            type="button"
                    >
                        Report
                    </button>
                </div>
            </li>
    </ul>
    </div>
</section>

<script>
    function showConfirm(){
        if(confirm("정말로 삭제하시겠습니까?")){
            //추가해야할 삭제 내용


            alert("삭제되었습니다");
        }else{
            return;
        }
    }
</script>

<!-- footer 영역 -->
<jsp:include page="../footer.jsp" flush="false" />


</body>
</html>