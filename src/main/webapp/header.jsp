<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 성공 메시지가 있다면 Alert를 띄우고 세션에서 메시지를 삭제 -->
<% if (session.getAttribute("message") != null) { %>
<script>
    alert("<%= session.getAttribute("message") %>");
</script>
<% session.removeAttribute("message"); %>
<% } %>
<head>
 <script src="https://cdn.tailwindcss.com"></script> <!--테일윈드 라이브러리-->
<meta charset="UTF-8">
<title>DMU Club</title>
</head>
<body>
<header class="flex justify-between items-center p-4 bg-blue-500">
    <div class="flex items-center" style=cursor:pointer; >
      <img
        src="${pageContext.request.contextPath}/img/default_img.jpg"
        height="40"
        width="40"
        alt="로고가 나타남"
        class="rounded-full"
        style="aspect-ratio:40/40;object-fit:cover"
      />
      <h1 class="text-2xl text-white ml-2" >DMU Club</h1>
    </div>
  </header>
</body>
</html>