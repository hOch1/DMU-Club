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
    <form class="space-y-4" onsubmit="return checkData()" action="/auth/sign-up" method="post">
      <input class="border rounded p-2 w-full" placeholder="아이디" type="text" name="id" required/>
      <input class="border rounded p-2 w-full" placeholder="이메일" type="email" name="email" required/>
      <input class="border rounded p-2 w-full" id="pw1" placeholder="비밀번호" type="password" name="password" required/>
      <input class="border rounded p-2 w-full" id="pw2" placeholder="비밀번호 재입력" type="password" required/>
      <input class="border rounded p-2 w-full" placeholder="이름" type="text" name="username" required/>
      <input class="border rounded p-2 w-full" placeholder="별명" type="text" name="nickname" required/>
      <input class="border rounded p-2 w-full" placeholder="핸드폰 번호" type="text" name="phone" required/>
      <input class="border rounded p-2 w-full" maxlength="10"
             placeholder="취미를 10글자 이내로 작성 ex(축구, 카페)"
             type="text" name="hobby" required oninput="limitText(this,10)"/>

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
          <input type="radio" value="F" name="FT" /> F
        </label>
        <label class="border p-2 rounded text-center">
          <input type="radio" value="T" name="FT" /> T
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

      <script>
        //각종 체크
        function checkData(){
          var pw1 = document.getElementById("pw1").value;
          var pw2 = document.getElementById("pw2").value;

          // 비밀번호
          if(pw1 !== pw2){
            alert("비밀번호가 다릅니다 다시 입력해주세요");
            return false;
          }
          return true;
        }

        // 텍스트 수 제한
        function limitText(element, maxLength){
          var value = element.value;

          if (value.length > maxLength){
            element.value=value.slice(0, maxLength);
          }
        }
      </script>
    </form>
  </main>
</section>
</body>
</html>