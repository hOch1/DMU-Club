<%@ page import="dmu.dmuclub.dto.board.ViewBoardResponse" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="/header.jsp" flush="false" />
<body>
<section class="flex h-screen text-gray-600 justify-center">
<div class="container mx-auto px-4 py-8">
    <div class="text-3xl font-bold mb-6">게시판</div>
    <div class="flex justify-between items-center mb-4">
        <input id="searchInput"
                class="flex h-10 rounded-md border-input bg-background text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 border p-2 w-full"
               placeholder="제목으로 입력해주세요."/>
        <button
                class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 text-primary-foreground h-10 px-2 py-1 text-sm bg-gray-300 dark:bg-gray-700 hover:bg-gray-400 dark:hover:bg-gray-600"
                type="button" onclick="searchByText()"
        >
            검색
        </button>
        <button
                class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 text-primary-foreground h-10 px-2 py-1 text-sm bg-gray-300 dark:bg-gray-700 hover:bg-gray-400 dark:hover:bg-gray-600"
                type="button" onclick="location.href='/community/write'"
        >
            글 쓰기
        </button>


    </div>

    <table class="w-full text-left border-t border-b border-gray-300">
        <thead>
        <tr class="text-sm font-medium">
            <th class="pb-3 pt-4">번호</th>
            <th class="pb-3 pt-4">제목</th>
            <th class="pb-3 pt-4">작성자</th>
            <th class="pb-3 pt-4">등록일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${boardList}" var="board" varStatus="loop">

                <tr class="border-t border-gray-300">
                    <td class="py-4">${loop.index + 1}</td>
                    <td>
                        <a class="custom-pointer" href="/community/view?id=${loop.index +1}">
                            ${board.title}
                        </a>
                    </td>
                    <td>${board.author}</td>
                    <td>${board.createDate}</td>
                </tr>

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
    function searchByText() {
        // 검색어 입력 필드에서 검색어 가져오기
        var searchKeyword = document.getElementById('searchInput').value.toLowerCase();

        // 모든 게시글 행 가져오기
        var rows = document.querySelectorAll('table tbody tr');

        // 각 행을 순회하며 검색어와 일치하는 행만 보이게 만들기
        rows.forEach(function (row) {
            var title = row.querySelector('td:nth-child(2) a').innerText.toLowerCase();
            if (title.includes(searchKeyword)) {
                // 검색어가 포함된 경우 행을 보이게 설정
                row.style.display = '';
            } else {
                // 검색어가 포함되지 않은 경우 행을 숨기기
                row.style.display = 'none';
            }
        });
    }
</script>
</body>


</html>