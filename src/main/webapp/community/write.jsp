
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="/header.jsp" />
<body>
<div class="container mx-auto my-8">
    <form id="myForm" class="max-w-md mx-auto bg-white p-8 rounded shadow-md">
        <div class="mb-4">
            <label for="title" class="block text-gray-700 text-sm font-bold mb-2">제목:</label>
            <input type="text" id="title" name="title" required class="w-full px-3 py-2 border rounded-md focus:outline-none focus:border-blue-500">
        </div>

        <div class="mb-6">
            <label for="content" class="block text-gray-700 text-sm font-bold mb-2">내용:</label>
            <textarea id="content" name="content" rows="4" required class="w-full px-3 py-2 border rounded-md focus:outline-none focus:border-blue-500"></textarea>
        </div>

        <button type="button" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-700 focus:outline-none"
                onclick=sendForm()>
            저장
        </button>
    </form>
</div>
<script>
 function sendForm() {
     var title = document.getElementById('title').value;
     var content = document.getElementById('content').value;

     var formData = new FormData(document.getElementById('myForm'));

     if(title.trim() !== '' && content.trim() !== '' ){
        Swal.fire({
            title: "글을 작성하시겠습니까?",
            text: "게시판에 업로드 합니다!",
            icon: "question",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "확인"
        }).then((result) => {
            if (result.isConfirmed) {
                // Ajax 요청 설정
                var xhr = new XMLHttpRequest();
                xhr.open('POST', '/community/write', true);

                // FormData를 직렬화하여 전송
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

                    // Ajax 요청 완료 후 처리
                xhr.onload = function(){
                    if (xhr.status >= 200 && xhr.status < 400){
                        // 성공적으로 서버 응답을 받았을 때
                        console.log(xhr.responseText);
                        Swal.fire({
                            title: "성공!",
                            text: "게시판에 업로드 되었습니다!",
                            icon: "success"
                        });
                        window.location.replace("http://localhost:8080/board");
                    } else {
                        // 서버에서 오류 응답을 받았을 때 처리
                        console.error('Error: ', xhr.statusText);
                        console.error('Error response:', xhr.responseText);
                    }
                };
                    // Ajax 요청 실패 처리
                xhr.onerror = function() {
                    console.error('Request failed');
                };

                // FormData를 직렬화하여 전송
                var serializedFormData = new URLSearchParams(formData).toString();
                xhr.send(serializedFormData);
            }
        });
        }
    }
</script>
</body>
</html>
