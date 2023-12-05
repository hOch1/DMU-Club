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
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script> <!-- sweetalert2 -->
  <title>회원가입</title>
</head>
<body>

<section class="bg-white">
  <header class="flex justify-between items-center p-4 bg-blue-500">
    <div class="flex items-center" style=cursor:pointer; onclick="location.href='/auth/sign-in'" >
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
  <div class="flex justify-center min-h-screen">
    <div class="hidden bg-cover lg:block lg:w-2/5" style="background-image: url('https://images.unsplash.com/photo-1494621930069-4fd4b2e24a11?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=715&q=80')">
    </div>

    <div class="flex items-center w-full max-w-3xl p-8 mx-auto lg:px-12 lg:w-3/5">
      <div class="w-full">
        <h1 class="text-2xl font-semibold tracking-wider text-gray-800 capitalize dark:text-white">
          회원가입을 시작해보세요.
        </h1>
<section class="flex flex-col h-screen">

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
            <input class="border rounded p-2 w-full" placeholder="핸드폰 번호 -를 제외하고 입력 예시)) 01012345678" type="text" name="phone" oninput="validatePhoneNumber(this)" required/>
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

                // mbti
                var ei = document.querySelector('input[name="EI"]:checked');
                var sn = document.querySelector('input[name="SN"]:checked');
                var ft = document.querySelector('input[name="FT"]:checked');
                var jp= document.querySelector('input[name="JP"]:checked');


                // 비밀번호
                if(pw1 !== pw2) {
                  alert("비밀번호가 다릅니다 다시 입력해주세요");
                  return false;
                }
                if (!ei || !sn || !ft || !jp ) {
                  alert("mbti를 골라주세요!.");
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

              // 핸드폰번호 숫자 입력 예제
              function validatePhoneNumber(input) {
                // 입력된 값에서 숫자만 추출
                var phoneNumber = input.value.replace(/\D/g, '');

                // 추출된 값과 원래 값이 다르다면 숫자가 아닌 문자가 포함된 것이므로 경고 표시
                if (phoneNumber !== input.value) {
                  alert("숫자만 입력해주세요.");
                  // 숫자가 아닌 문자를 제거한 값으로 필드를 업데이트
                  input.value = phoneNumber;
                }
              }
            </script>
          </form>
        </main>
</section>
      </div>
    </div>
  </div>
</section>
</body>
</html>