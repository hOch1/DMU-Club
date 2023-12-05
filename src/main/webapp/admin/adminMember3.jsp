<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Page</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="font-sans bg-gray-100 flex">

<!-- Sidebar -->
<div class="bg-gray-800 text-gray-100 w-1/6 p-4">
    <ul>
        <li class="mb-2"><a href="#" onclick="showPage('member')">회원관리</a></li>
        <li class="mb-2"><a href="#" onclick="showPage('post')">게시물관리</a></li>
    </ul>
</div>

<!-- Main Content -->
<div class="flex-1 p-10">
    <div id="memberPage" class="hidden">
        <h1 class="text-2xl font-semibold mb-4">회원관리 페이지</h1>
        <!-- 회원관리 페이지 내용 -->
    </div>

    <div id="postPage" class="hidden">
        <h1 class="text-2xl font-semibold mb-4">게시물관리 페이지</h1>
        <!-- 게시물관리 페이지 내용 -->
    </div>
</div>

<script>
    function showPage(page) {
        // 모든 페이지 숨기기
        document.getElementById('memberPage').classList.add('hidden');
        document.getElementById('postPage').classList.add('hidden');

        // 선택한 페이지 보이기
        document.getElementById(page + 'Page').classList.remove('hidden');
    }
</script>
</body>
</html>
