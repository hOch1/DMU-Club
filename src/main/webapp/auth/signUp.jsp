<%--
  Created by IntelliJ IDEA.
  User: Andy
  Date: 2023-11-28
  Time: 오후 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <script src="https://cdn.tailwindcss.com"></script> <!--테일윈드 라이브러리-->
  <meta charset="UTF-8">
  <title>DMU Club</title>
</head>
<body>
<section class="flex flex-col h-screen">
  <header class="flex justify-between items-center p-4 bg-blue-500">
    <div class="flex items-center">
      <img
              alt="DMU Club Logo"
              class="rounded-full"
              height="40"
              src="placeholder.svg"
              width="40"
              style="aspect-ratio: 40 / 40; object-fit: cover;"
      />
      <h1 class="text-2xl text-white ml-2">DMU Club</h1>
    </div>
  </header>


  <!-- email, nickname, phone 중복 금지 -->

  <main class="flex-grow p-4 overflow-y-auto">
    <h2 class="text-2xl font-bold mb-4">회원가입</h2>
    <form class="space-y-4" action="/auth/sign-up" method="post" >
      <input class="border rounded p-2 w-full" placeholder="Email" type="email" name="email"/>
      <input class="border rounded p-2 w-full" placeholder="Password" type="password" name="password"/>
      <input class="border rounded p-2 w-full" placeholder="Confirm Password" type="confirm_pwd" />
      <input class="border rounded p-2 w-full" placeholder="username" type="text" name="username" />
      <input class="border rounded p-2 w-full" placeholder="nickname" type="text" name="nickname"/>
      <input class="border rounded p-2 w-full" placeholder="phone" type="text" name="phone"/>
      <input class="border rounded p-2 w-full" maxlength="10" placeholder="취미를 10글자 이내로 작성 ex(축구, 카페)" type="text" name="hobby"/>

      <!-- mbti 설정 부분 -->
      <h1 class="text-2xl font-bold mb-4">본인의 mbti를 골라주세요</h1>
      <div class="grid grid-cols-2 gap-2">
        <label class="border p-2 rounded text-center">
          <input type="radio" value="E" name="EI" /> E
        </label>
        <label class="border p-2 rounded text-center">
          <input type="radio" value="I" name="EI" /> I
        </label>
        <label class="border p-2 rounded text-center">
          <input type="radio" value="S" name="SN" /> S
        </label>
        <label class="border p-2 rounded text-center">
          <input type="radio" value="N" name="SN" /> N
        </label>
        <label class="border p-2 rounded text-center">
          <input type="radio" value="F" name="FP" /> F
        </label>
        <label class="border p-2 rounded text-center">
          <input type="radio" value="T" name="FP" /> T
        </label>
        <label class="border p-2 rounded text-center">
          <input type="radio" value="P" name="JP" /> P
        </label>
        <label class="border p-2 rounded text-center">
          <input type="radio" value="J" name="JP" /> J
        </label>
      </div>

      <button
              class="inline-flex items-center justify-center text-sm font-medium ring-offset-background
                    transition-colors focus-visible:outline-none focus-visible:ring-2
                    focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none
                    disabled:opacity-50 hover:bg-primary/90 h-10 px-4 w-full py-2 mt-4 bg-blue-500 text-white rounded"
              type="submit"
      >
        확인
      </button>
    </form>
  </main>
</section>
</body>
</html>
