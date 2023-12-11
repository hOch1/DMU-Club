
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="/header.jsp" />
<body>
<div class="container mx-auto my-8">
    <form id="boardWrite" class="max-w-md mx-auto bg-white p-8 rounded shadow-md">
        <div class="mb-4">
            <label for="title" class="block text-gray-700 text-sm font-bold mb-2">제목:</label>
            <input type="text" id="title" name="title" required class="w-full px-3 py-2 border rounded-md focus:outline-none focus:border-blue-500">
        </div>

        <div class="mb-6">
            <label for="content" class="block text-gray-700 text-sm font-bold mb-2">내용:</label>
            <textarea id="content" name="content" rows="4" required class="w-full px-3 py-2 border rounded-md focus:outline-none focus:border-blue-500"></textarea>
        </div>

        <button type="button" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-700 focus:outline-none"
                onclick=" submitForm() ">
            저장
        </button>
    </form>
</div>
<script>
    function submitForm() {
        // 폼 데이터 가져오기
        var formData = new FormData(document.getElementById('boardWrite'));

        Swal.fire({
            title: '확인',
            text: '글을 작성하시겠습니까?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: '확인',
            cancelButtonText: '취소',
        }).then((result) => {
          var xhr = new XMLHttpRequest();
          xhr.open('POST', '/community/write', true);
          xhr.onload = function() {
              if (xhr.status === 200) {
                  swal.fire("글 작성이 완료되었습니다!");
              }else {
                  console.error('전송이 실패하였습니다.');
              }
          };
          xhr.send(formData);
        });
    }
</script>
</body>
</html>
