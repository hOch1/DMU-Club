<%@ page import="dmu.dmuclub.dto.board.ViewBoardResponse" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="/header.jsp" flush="false" />
<% List<ViewBoardResponse> boardResponses = (List<ViewBoardResponse>) request.getAttribute("boardList"); %>
<body>
<section class="flex h-screen bg-gray-100 justify-center">
<div class="container mx-auto px-4 py-8">
    <div class="text-3xl font-bold mb-6">게시판</div>
    <div class="flex justify-between items-center mb-4">
        <input id='searchInput' class="flex h-10 rounded-md border-input bg-background text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 border p-2 w-full"
               placeholder="게시물 제목 검색어를 입력해주세요."/>
        <button class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 text-primary-foreground h-10 px-2 py-1 text-sm bg-gray-300 dark:bg-gray-700 hover:bg-gray-400 dark:hover:bg-gray-600"
                type="button"
                onclick="searchBoard()">
            검색
        </button>
        <button
                class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 text-primary-foreground h-10 px-2 py-1 text-sm bg-gray-300 dark:bg-gray-700 hover:bg-gray-400 dark:hover:bg-gray-600"
                type="button" onclick="location.href='/community/write'"
        >
            글 쓰기
        </button>


    </div>

    <table id='boardTable' class="w-full text-left border-t border-b border-gray-300">
        <thead>
        <tr class="text-sm font-medium">
            <th class="pb-3 pt-4">번호</th>
            <th class="pb-3 pt-4"><a href="#">제목</a></th>
            <th class="pb-3 pt-4">작성자</th>
            <th class="pb-3 pt-4">등록일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${boardList}" var="board" varStatus="loop">
            <a onclick="location.href='/community/view?id=#}'">
                <tr class="border-t border-gray-300">
                    <td class="py-4">${loop.index +1}</td>
                    <td>
                        <a href="/community/view?id=${loop.index +1}">
                                ${board.title}
                        </a>
                    </td>
                    <td>${board.author}</td>
                    <td>${board.createDate}</td>
                </tr>
            </a>
        </c:forEach>
        </tbody>
    </table>
    <div class="flex justify-end mt-4">
        <button class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-primary/90 h-10 px-4 py-2 bg-gray-400 text-white mr-2">
            이전
        </button>
        <button class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 hover:bg-primary/90 h-10 px-4 py-2 bg-gray-400 text-white">
            다음
        </button>
    </div>
</div>
</section>

<!-- footer 영역 -->
<jsp:include page="/footer.jsp" flush="false" />

<script>
    //검색 기능 구현
    function searchBoard() {
        var searchInput = document.getElementById("searchInput").value.toLowerCase();
        var tableRows = document.getElementById("boardTable").querySelectorAll("tbody tr");

        tableRows.forEach(function (row) {
            var title = row.querySelector("td:nth-child(2)").innerText.toLowerCase();
            if (title.includes(searchInput)) {
                row.style.display = ""; // 검색어와 일치하는 경우 표시
            } else {
                row.style.display = "none"; // 검색어와 일치하지 않는 경우 숨김
            }
        });
    }
</script>
</body>


</html>