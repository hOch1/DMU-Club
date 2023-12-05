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
    }
%>
    <!-- 메인영역  -->
<header class="flex justify-between items-center p-4 bg-blue-500">
    <div class="flex items-center" style=cursor:pointer; onclick="location.href='/auth/sign-in'" >
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
</header>

<section class="flex flex-col h-screen">
    <main class="flex-grow flex flex-col items-center justify-center p-4">
        <form  action="/auth/sign-in" method="post">
            <!-- component -->
            <div class="bg-[#F9FAFB] h-screen w-screen flex items-center">
                <div class="h-max mx-auto flex flex-col items-center">
                    <img class="h-[180px] w-[172px] mb-5" src="${pageContext.request.contextPath}/img/logo.png" alt="로고 이미지">
                    <h1 class="text-xl font-bold text-center pb-10">Sign in to your account</h1>
                    <div class="bg-white shadow-xl p-10 flex flex-col gap-4 text-sm">
                        <div>
                            <label class="text-gray-600 font-bold inline-block pb-2">Email</label>
                            <input class="border border-gray-400 focus:outline-slate-400 rounded-md w-full shadow-sm px-5 py-2" type="email" name="email" placeholder="sumin@naver.com" required>
                        </div>
                        <div>
                            <label class="text-gray-600 font-bold inline-block pb-2" >Password</label>
                            <input class="border border-gray-400 focus:outline-slate-400 rounded-md w-full shadow-sm px-5 py-2" type="password" name="password" placeholder="******" required>
                        </div>
                        <div class="flex">
                            <div class="w-1/2">
                                <input type="checkbox" name="remeberMe">
                                <label >아이디 기억하기</label>
                            </div>
                            <div class="w-1/2">
                                <a class="font-bold text-blue-600" href="">Forgot password ?</a>
                            </div>
                        </div>
                        <div>
                            <input class="bg-[#4F46E5] w-full py-2 rounded-md text-white font-bold cursor-pointer hover:bg-[#181196]" type="submit" value="로그인" >
                        </div>
                        <div>
                            <p class="text-center">혹은 처음이신가요?</p>
                        </div>
                        <div class="flex gap-4">
                            <button class="bg-[#1D9BF0] w-full py-2 rounded-md
                text-white font-bold cursor-pointer hover:bg-[#181196]"
                                    onclick="window.location.href='<%= request.getContextPath() %>/auth/sign-up'"
                            >회원가입</button>
                        </div>
                    </div>
                    <p class="text-sm text-gray-500 mt-10">Dongyang Mirae University <a href="https://www.dongyang.ac.kr/dongyang/index.do" class="text-[#4F46E5] font-bold">DMU</a></p>
                </div>
            </div>
        </form>
    </main>
</section>
</body>
</html>
