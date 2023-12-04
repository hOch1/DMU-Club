
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="/header.jsp" />
<body>
<div class="container mx-auto my-8">
    <h1 class="text-3xl font-bold mb-6">글쓰기</h1>

    <form id="postForm" class="max-w-md mx-auto bg-white p-8 rounded shadow-md">
        <div class="mb-4">
            <label for="title" class="block text-gray-700 text-sm font-bold mb-2">제목:</label>
            <input type="text" id="title" name="title" required class="w-full px-3 py-2 border rounded-md focus:outline-none focus:border-blue-500">
        </div>

        <div class="mb-6">
            <label for="content" class="block text-gray-700 text-sm font-bold mb-2">내용:</label>
            <textarea id="content" name="content" rows="4" required class="w-full px-3 py-2 border rounded-md focus:outline-none focus:border-blue-500"></textarea>
        </div>

        <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-700 focus:outline-none">
            저장
        </button>
    </form>
</div>

</body>
</html>
