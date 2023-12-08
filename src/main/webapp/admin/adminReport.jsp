<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dmu.dmuclub.dto.board.ViewBoardResponse" %>
<html>
<% List<ViewBoardResponse> boardResponses = (List<ViewBoardResponse>) request.getAttribute("boardList"); %>
<body>
<section class="flex h-screen bg-gray-100 ">
  <jsp:include page="sideBar.jsp" />
  <div class="w-3/4 bg-white overflow-y-auto justify-center" >
    <div class="p-4 flex flex-col">
      <h1 class="text-2xl text-gray-700 dark:text-gray-300 mb-4"> 고객 관리 </h1>
    </div>
    <h2 class="text-xl text-gray-700 dark:text-gray-300 p-4 border-t border-gray-300 dark:border-gray-700">
      신고 내역
    </h2>
    <ul class="divide-y divide-gray-300 dark:divide-gray-700">

      <!--Start Point-->
      <c:forEach var="report" items='#'>
        <li class="p-4 hover:bg-gray-300 dark:hover:bg-gray-700 cursor-pointer flex items-center space-x-2 justify-between">
          <div class="flex items-center space-x-2">
            <img
                    alt="Friend 1"
                    class="rounded-full border-2 border-blue-500"
                    height="40"
                    src="../img/default_img.jpg"
                    width="40"
                    style="aspect-ratio:40/40;object-fit:cover"
            />
            <div>
              <h2 class="text-gray-700 dark:text-gray-300">report.name</h2>
            </div>
          </div>
          <div class="flex space-x-2">
            <button
                    class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 h-10 px-2 py-1 text-sm bg-red-500 hover:bg-red-600 text-white"
                    type="button" onclick="reportText()"
            >
              신고 내역 확인
            </button>
          </div>
        </li>
        <!--끝-->
    </ul>
  </div>
</section>

<script>
  function reportText(){
    Swal.fire("{report.text}");
  }
</script>
</c:forEach>
</body>
</html>
