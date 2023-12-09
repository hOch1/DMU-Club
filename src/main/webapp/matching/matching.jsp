<%@ page import="dmu.dmuclub.dto.member.MemberDto" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="/header.jsp" flush="false" />

<body>
<section class="text-gray-600 body-font">
    <div class="container px-5 py-5 mx-auto">
        <div class="flex flex-wrap -m-4">


            <c:forEach var="item" items='${memberList}'>
                <!---프로필-->
                <div class="p-4 md:w-1/3" >
                    <div class="h-full border-2 border-gray-200 border-opacity-60 rounded-lg overflow-hidden">
                        <div class="w-full">
                            <div class="w-full flex p-2">
                                <div class="p-2 ">
                                    <img
                                            src="img/logo.png" alt="logo"
                                            class="w-10 h-10 rounded-full overflow-hidden"/>
                                </div>
                                <div class="pl-2 pt-2 ">
                                    <p class="font-bold">MATCH</p>
                                </div>
                            </div>
                        </div>


                        <img class="lg:h-48 md:h-36 w-full object-cover object-center"
                             src="img/default_img.jpg" alt="image"/>
                        <div class="p-4">
                            <h2 class="tracking-widest text-xs title-font font-bold text-green-400 mb-1 uppercase ">${item.mbti}</h2>
                            <h1 class="title-font text-lg font-medium text-gray-900 mb-3">${item.username}</h1>
                            <div class="flex items-center flex-wrap ">

                                <!--여기서 상대방 프로필 보기 페이지로 넘김-->
                                <a href="matching/profile.jsp" class="text-green-800 md:mb-2 lg:mb-0" >
                                    <p class="inline-flex items-center">상대방 정보 더보기
                                        <svg class="w-4 h-4 ml-2" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round">
                                            <path d="M5 12h14"></path>
                                            <path d="M12 5l7 7-7 7"></path>
                                        </svg>
                                    </p>
                                </a>

                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <!--프로필  끝-->
        </div>
    </div>
</section>

<jsp:include page="/footer.jsp" flush="false" />
</body>
</html>
