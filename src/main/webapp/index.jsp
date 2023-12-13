<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dmu.dmuclub.dto.member.MemberDto" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://cdn.tailwindcss.com"></script> <!--테일윈드 라이브러리-->
    <script src="https://cdn.jsdelivr.net/npm/alpinejs@2.8.2/dist/alpine.min.js" defer></script> <!--=Alpine.js -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>DMU Club</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

 <!-- 헤더 영역 -->
<section class="flex h-screen bg-gray-100 justify-center">
    <div class="container px-5 py-5 mx-auto">
        <div class="flex flex-wrap -m-4">


        <c:forEach var="item" items='${memberList}'>
    <!---프로필-->
            <div class="p-4 md:w-1/3">
                <div class="h-full border-2 border-gray-200 border-opacity-60 rounded-lg overflow-hidden bg-white">
                    <div class="w-full">
                        <div class="w-full flex p-2">
                            <div class="p-2 ">
                                <img
                                src="img/logo.png" alt="logo"
                                class="w-10 h-10 rounded-full overflow-hidden"/>
                            </div>
                        <div class="pl-2 pt-2 ">
                        <p class="font-bold">오늘의 추천</p>
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
                                <a href="/profile?id=${item.id}" class="text-green-800  md:mb-2 lg:mb-0">
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
            <!--프로필 끝-->
        </div>
    </div>

    <!-- 사이드바 컴포넌트 시작 -->
    <div class="flex">
        <div x-data="{ open: false }">
            <!-- Sidebar Overlay -->
            <div x-show="open" class="fixed inset-0 z-50 overflow-hidden">
                <div x-show="open" x-transition:enter="transition-opacity ease-out duration-300" x-transition:enter-start="opacity-0" x-transition:enter-end="opacity-100" x-transition:leave="transition-opacity ease-in duration-300" x-transition:leave-start="opacity-100" x-transition:leave-end="opacity-0" class="absolute inset-0 bg-gray-500 bg-opacity-75 transition-opacity"></div>
                <!-- Sidebar Content -->
                <section class="absolute inset-y-0 right-0 pl-10 max-w-full flex">
                    <div x-show="open" x-transition:enter="transition-transform ease-out duration-300" x-transition:enter-start="transform translate-x-full" x-transition:enter-end="transform translate-x-0" x-transition:leave="transition-transform ease-in duration-300" x-transition:leave-start="transform translate-x-0" x-transition:leave-end="transform translate-x-full" class="w-screen max-w-md">
                        <div class="h-full flex flex-col py-6 bg-white shadow-xl">

                            <!-- Sidebar Header -->
                            <div class="flex items-center justify-between px-4">
                                <h2 class="text-xl font-semibold text-black">친구 요청</h2>
                                <button x-on:click="open = false" class="text-gray-500 hover:text-gray-700">
                                    <svg class="h-6 w-6" x-description="Heroicon name: x" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" aria-hidden="true">
                                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                                    </svg>
                                </button>
                            </div>

                            <!-- 여기부터 시작  -->
                            <div class="mt-4 px-4 h-full overflow-auto">

                                <!--시작-->
                                <c:forEach items="${askFriend}" var="ask">
                                <div class="p-2 flex bg-white hover:bg-gray-100 cursor-pointer border-b border-gray-100">
                                    <div class="p-2 w-12"><img
                                            src="https://dummyimage.com/50x50/bababa/0011ff&amp;text=50x50" alt="상대방 이미지?">
                                    </div>
                                    <div class="flex-auto text-sm w-32">
                                        <div class="font-bold">${ask.username}</div>
                                        <div class="truncate">${ask.nickname}</div>
                                        <div class="text-gray-400">${ask.mbti}</div>
                                    </div>
                                    <div class="flex flex-col w-18 font-medium items-end">
                                        <div class="w-4 h-4 mb-6 hover:bg-green-200 rounded-full cursor-pointer text-blue-700">
                                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                                 stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                                <path stroke-linecap="round" stroke-linejoin="round"
                                                      d="M11.25 11.25l.041-.02a.75.75 0 011.063.852l-.708 2.836a.75.75 0 001.063.853l.041-.021M21 12a9 9 0 11-18 0 9 9 0 0118 0zm-9-3.75h.008v.008H12V8.25z" />
                                            </svg>
                                        </div>
                                        <div class="flex items-end">
                                            <form action="/member/acceptFriend?id=${ask.id}" method="post">
                                            <button
                                                    class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 text-primary-foreground h-8 px-2 py-1 text-sm bg-gray-300 dark:bg-gray-700 hover:bg-gray-400 dark:hover:bg-gray-600"
                                                    type="submit">
                                                accept
                                            </button>
                                            </form>
                                            <form action="/member/refuseFriend?id=${ask.id}" method="post">
                                            <button
                                                    class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 h-8 px-2 py-1 text-sm bg-red-500 hover:bg-red-600 text-white"
                                                    type="submit">
                                                refuse
                                            </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                </c:forEach>
                                <!--끝-->

                            </div>
                            <!-- sidebar footer -->
                        </div>
                    </div>
                </section>
            </div>

            <!-- Open sidebar button -->
            <button x-on:click="open = true" class="px-4 py-2 bg-yellow-400 text-white rounded-md">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                     stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                    <path stroke-linecap="round" stroke-linejoin="round"
                          d="M14.857 17.082a23.848 23.848 0 005.454-1.31A8.967 8.967 0 0118 9.75v-.7V9A6 6 0 006 9v.75a8.967 8.967 0 01-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 01-5.714 0m5.714 0a3 3 0 11-5.714 0M3.124 7.5A8.969 8.969 0 015.292 3m13.416 0a8.969 8.969 0 012.168 4.5" />
                </svg>
            </button>

        </div>
    </div>
    </div>
    <!--사이드바 끝 -->
</section>
    <jsp:include page="footer.jsp"/>
</body>
</html>
