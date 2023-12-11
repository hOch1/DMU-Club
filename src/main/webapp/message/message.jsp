<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/header.jsp" flush="false" />

<body>
    <section class="flex h-screen bg-gray-100 dark:bg-gray-900 justify-center">
        <main class="w-3/4 bg-white dark:bg-gray-800 overflow-y-auto">
          <ul class="divide-y divide-gray-300 dark:divide-gray-700">

              <c:forEach var="chat" items="${chatList}">
                  <a href="/message?nickname=${chat.nickname}">
            <li class="p-4 hover:bg-gray-300 dark:hover:bg-gray-700 cursor-pointer flex items-center space-x-2">
                <c:choose>
                    <c:when test="${not empty chat.filename}">
                        <img alt="상대방 이미지" class="rounded-full border-2 border-blue-500"
                             height="40"
                             src=src="/img/${chat.filename}"
                             width="40"
                             style="aspect-ratio:40/40;object-fit:cover"
                        />
                    </c:when>
                    <c:otherwise>
                        <!-- 기본 이미지를 여기에 추가 -->
                        <img alt="상대방 이미지" class="rounded-full border-2 border-blue-500"
                             height="40"
                             src="/img/default_img.jpg"
                             width="40"
                             style="aspect-ratio:40/40;object-fit:cover"
                        />
                    </c:otherwise>
                </c:choose>
              <div>
                <h2 class="text-gray-700 dark:text-gray-300">${chat.nickname}</h2>
              </div>
            </li>
                  </a>
            </c:forEach>

          </ul>
        </main>
      </section>

<!-- footer 영역 -->
<jsp:include page="../footer.jsp" flush="false" />

<script>

</script>
</body>
</html>