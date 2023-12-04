<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Page</title>
  <!-- 테일윈드 CSS 파일 import -->
  <link rel="stylesheet" href="path/to/your/tailwind.css">
</head>
<body class="bg-gray-100 font-sans leading-normal tracking-normal">

<!-- 네비게이션 바 -->
<nav id="header" class="fixed w-full z-10 top-0">

  <div id="progress" class="h-1 z-20 top-0"
       style="background:linear-gradient(to right, #4dc0b5 var(--scroll), transparent 0);"></div>

  <div class="w-full md:max-w-4xl mx-auto flex flex-wrap items-center justify-between mt-0 py-3">

    <div class="pl-4">
      <a class="text-gray-900 text-base no-underline hover:no-underline font-extrabold text-xl" href="#">
        Admin Page
      </a>
    </div>

    <div class="block lg:hidden pr-4">
      <button id="nav-toggle"
              class="flex items-center p-1 text-orange-800 hover:text-gray-900">
        <svg class="fill-current h-6 w-6"
             xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
          <path fill="none" d="M0 0h24v24H0z"/>
          <path d="M4 6h16M4 12h16m-7 6h7"></path>
        </svg>
      </button>
    </div>
  </div>
</nav>

<!-- 콘텐츠 영역 -->
<div class="container w-full mx-auto pt-20">
  <div class="w-full px-4 md:px-0 md:mt-8 mb-16 text-gray-800 leading-normal">
    <!-- 콘텐츠 내용을 추가합니다. -->
    <h1 class="text-3xl font-bold mb-4">Welcome to Admin Page</h1>
    <p class="mb-4">This is a simple admin page created with Tailwind CSS.</p>
    <!-- 추가적인 콘텐츠 및 기능을 구현하세요. -->
  </div>
</div>

<!-- 푸터 -->
<footer class="bg-white border-t border-gray-400 shadow">
  <div class="container mx-auto flex py-8">

    <div class="w-full mx-auto flex flex-wrap">
      <div class="flex w-full lg:w-1/2 ">
        <!-- 푸터 내용을 추가합니다. -->
        <p class="text-sm text-gray-500 font-bold items-center mb-2">
          &copy; 2023 Admin Page. All rights reserved.
        </p>
        <!-- 추가적인 푸터 내용을 구현하세요. -->
      </div>
    </div>
  </div>
</footer>

<!-- 테일윈드와 관련된 JavaScript 파일을 import합니다. -->
<script src="path/to/your/tailwind.js"></script>
</body>
</html>
