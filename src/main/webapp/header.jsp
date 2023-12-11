<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <script src="https://cdn.tailwindcss.com"></script> <!--tailwind css-->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!--sweetalert2 -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>DMU Club</title>
</head>

<%
    if (session.getAttribute("member") == null)
        response.sendRedirect("/auth/sign-in");
%>


<% if (session.getAttribute("message") != null) { %>
<script>
    Swal.fire("${member.nickname} 님 환영합니다 <%= session.getAttribute("message") %>");
</script>
<% session.removeAttribute("message");
}
%>
<body>
<header class="flex justify-between items-center p-4 bg-blue-500">
    <div class="flex items-center" style=cursor:pointer; onclick="location.href='/main'" >
      <img
        src="${pageContext.request.contextPath}/img/logo.png"
        height="40"
        width="40"
        alt="로고가 나타남"
        class="rounded-full"
        style="aspect-ratio:40/40;object-fit:cover"
      />
      <h1 class="text-2xl text-white ml-2" >DMU Club</h1>
    </div>
    <button class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background
        focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2  disabled:opacity-50 border border-input
        bg-background hover:text-accent-foound h-10 px-4 py-2
        transition-colors hover:bg-gray-200 dark:hover:bg-gray-700"
            onclick="location.href='/auth/logout'"
        >
        Logout
    </button>
  </header>
</body>
</html>