<%@ page import="dmu.dmuclub.dto.member.MemberDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://cdn.tailwindcss.com"></script> <!--테일윈드 라이브러리-->
    <meta charset="UTF-8">
    <title>DMU Club</title>
</head>
<body>

<!-- 성공 메시지가 있다면 Alert를 띄우고 세션에서 메시지를 삭제 -->
<% if (session.getAttribute("message") != null) { %>
<script>
    alert("<%= session.getAttribute("message") %>");
</script>
<% session.removeAttribute("message"); %>
<% } %>

<%
    MemberDto memberDto = (MemberDto) session.getAttribute("member");

    if (memberDto != null) {
        String nickname = memberDto.getNickname();
%>
<p>Welcome, <%= nickname %>!</p>
<%
    }
%>
    <!-- 메인영역  -->


<section class="flex flex-col h-screen">
    <header class="flex justify-center items-center p-4 bg-blue-500">
        <div class="flex items-center">
            <img
                    alt="DMU Club Logo"
                    class="rounded-full"
                    height="40"
                    width="40"
                    src="/placeholder.svg"
                    style="aspect-ratio: 40 / 40; object-fit: cover;"
            />
        </div>
    </header>
    <main class="flex-grow flex flex-col items-center justify-center p-4">
        <form class="space-y-4 w-1/6" action="/auth/sign-in" method="post">
            <input class="border rounded p-2 w-full" placeholder="ID" type="text" name="email" required />
            <input class="border rounded p-2 w-full" placeholder="PW" type="password" name="password" required />
                <button
                    class="inline-flex items-center justify-center font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-primary/90 h-10 px-4 w-full py-2 mt-4 bg-blue-500 text-white rounded text-sm"
                    type="button"
                    onclick="window.location.href='<%= request.getContextPath() %>/auth/sign-up'"
            >
                회원가입
            </button>
            <button
                    class="inline-flex items-center justify-center text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-primary/90 h-10 px-4 w-full py-2 mt-4 bg-blue-500 text-white rounded"
                    type="submit"
            >
                로그인
            </button>
        </form>
    </main>
</section>

    <!--  footer 부분  -->


</body>
</html>
