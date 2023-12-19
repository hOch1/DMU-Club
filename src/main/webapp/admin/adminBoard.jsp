<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <script src="https://cdn.tailwindcss.com"></script> <!--테일윈드 라이브러리-->
  <title>admin Page</title>
</head>
<body>
<div class="flex">
  <jsp:include page="sideBar.jsp" flush="false" />
  <!-- component -->
  <div class="bg-white p-8 rounded-md w-full">
    <div class=" flex items-center justify-between pb-6">
      <div>
        <h2 class="text-gray-600 font-semibold">게시물 관리</h2>
        <span class="text-xs">게시물 내역 </span>
      </div>
      <div class="flex items-center justify-between">
        <div class="flex bg-gray-50 items-center p-2 rounded-md">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-gray-400" viewBox="0 0 20 20" fill="currentColor">
            <path fill-rule="evenodd" d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z" clip-rule="evenodd" />
          </svg>
          <input class="bg-gray-50 outline-none ml-1 block " type="text" name="" id="" placeholder="search...">
        </div>
        <div class="lg:ml-40 ml-10 space-x-8">
          <button
                  class="inline-flex items-center
                        justify-center rounded-md font-medium
                        ring-offset-background transition-colors
                        focus-visible:outline-none focus-visible:ring-2
                        focus-visible:ring-ring focus-visible:ring-offset-2
                        disabled:pointer-events-none disabled:opacity-50 h-10 px-2
                        py-1 text-sm bg-red-500 hover:bg-red-600 text-white"
                  type="button" onclick="rmConfirm()"
          >
            게시물 삭제
          </button>
        </div>
      </div>
    </div>
    <div>
      <div class="-mx-4 sm:-mx-8 px-4 sm:px-8 py-4 overflow-x-auto">
        <div class="inline-block min-w-full shadow rounded-lg overflow-hidden">
          <table class="min-w-full leading-normal">
            <thead>
            <tr>
              <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                No
              </th>
              <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                작성자
              </th>
              <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                제목
              </th>
              <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                작성 시간
              </th>
              <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                선택
              </th>
            </tr>
            </thead>
            <tbody>


            <c:forEach items="${boardList}" var="board">
              <tr>
                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                  <p class="text-gray-900 whitespace-no-wrap">
                      ${board.id}
                  </p>
                </td>
                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                  <div class="flex items-center">
                    <div class="flex-shrink-0 w-10 h-10">
                      <img class="w-full h-full rounded-full" src="/img/default_img.jpg" alt="" />
                    </div>
                    <div class="ml-3">
                      <p class="text-gray-900 whitespace-no-wrap">
                          ${board.author}
                      </p>
                    </div>
                  </div>
                </td>
                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                  <p class="text-gray-900 whitespace-no-wrap">${board.title}</p>
                </td>
                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                  <p class="text-gray-900 whitespace-no-wrap">
                      ${board.createDate}
                  </p>
                </td>
                <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                  <input type="checkbox" class="ml-2 form-checkbox h-5 w-5 text-green-600" id="${board.id}" />
                </td>
              </tr>
            </c:forEach>


            </tbody>
          </table>
          <div class="px-5 py-5 bg-white border-t flex flex-col xs:flex-row items-center xs:justify-between          ">
                    <span class="text-xs xs:text-sm text-gray-900">
                        1~10 페이지 표시할 영역
                    </span>
            <!-- 버튼 -->
            <div class="inline-flex mt-2 xs:mt-0">
              <button class="text-sm text-indigo-50 transition duration-150 hover:bg-indigo-500 bg-indigo-600 font-semibold py-2 px-4 rounded-l">
                이전
              </button>
              &nbsp; &nbsp;
              <button class="text-sm text-indigo-50 transition duration-150 hover:bg-indigo-500 bg-indigo-600 font-semibold py-2 px-4 rounded-r">
                다음
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script>
  function rmConfirm(){
    // 모든 체크박스 확인
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');

    var selectedCheck = Array.from(checkboxes).some(function(checkbox) {
      return checkbox.checked;
    });
    if(selectedCheck){
      Swal.fire({
        title: "정말로 삭제하시겠어요?",
        text: "db에서 삭제작업 진행합니다.!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!."
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire({
            title: "삭제 완료!",
            text: "DB에서 삭제 완료하였습니다!!",
            icon: "success"
          });
          //삭제 내용 구성
          checkboxes.forEach(function(checkbox){
            if(checkbox.checked){
              //친구 삭제 내용 작성
              const xhr = new XMLHttpRequest();

              // POST 요청 설정
              xhr.open("POST", "/admin/board/delete", true);
              xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

              // POST 요청 본문 데이터 생성
              const requestData = `id=`+checkbox.id;

              // 요청 전송
              xhr.send(requestData);

              // 요청 완료 후의 처리
              xhr.onload = function () {
                if (xhr.status >= 200 && xhr.status < 300) {
                  // 성공적으로 요청이 완료된 경우
                  console.log("POST 요청이 성공적으로 완료되었습니다.");
                } else {
                  // 요청이 실패한 경우
                  console.error("POST 요청이 실패하였습니다.");
                }
              };
            }
          })
        }
      });
    }
  }
</script>
</body>
</html>
