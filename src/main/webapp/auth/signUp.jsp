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
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
  <title>회원가입</title>
</head>
<body>
<<<<<<< HEAD
<section class="bg-white dark:bg-gray-900">
  <div class="flex justify-center min-h-screen">
    <div class="hidden bg-cover lg:block lg:w-2/5" style="background-image: url('https://images.unsplash.com/photo-1494621930069-4fd4b2e24a11?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=715&q=80')">
    </div>

    <div class="flex items-center w-full max-w-3xl p-8 mx-auto lg:px-12 lg:w-3/5">
      <div class="w-full">
        <h1 class="text-2xl font-semibold tracking-wider text-gray-800 capitalize dark:text-white">
          회원가입을 시작해보세요.
        </h1>
=======
<section class="flex flex-col h-screen">
  <jsp:include page="/header.jsp" flush="false" />
>>>>>>> 3975ca47f1f64de434ecbec7b14c3ec0b0dbcb72

        <p class="mt-4 text-gray-500 dark:text-gray-400">
          로그인하여 더 많은 정보 더 많은 사람들을 만나보세요!
        </p>

        <main class="flex-grow p-4 overflow-y-auto">
          <h2 class="text-2xl font-bold mb-4">회원가입</h2>
          <form class="space-y-4" onsubmit="return checkData()" action="/auth/sign-up" method="post">
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
              //각종 체크 bmti도 확인
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
        </div>
    </div>
  </div>
</section>
</body>
</html>