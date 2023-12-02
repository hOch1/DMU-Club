<%@ page import="dmu.dmuclub.dto.member.MemberDto" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="header.jsp" flush="false" />
<body>
 <!-- 헤더 영역 -->
 <section class="flex flex-col h-screen">

   <%
   List<MemberDto> memberDtoList = (List<MemberDto>) session.getAttribute("memberList");

   for (MemberDto memberDto : memberDtoList){
     System.out.println(memberDto);
   }
   %>
  
  <!-- 메인영역  -->
  <main class="flex-grow p-4 overflow-y-auto">
    <h2 class="text-2xl font-bold mb-4">오늘의 추천</h2>
    <div class="grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
      
      <!-- 프로필 1 -->
      <div class="border bg-card text-card-foreground rounded-lg shadow-md overflow-hidden" data-v0-t="card">
        <img
          src="<%= request.getContextPath() %>/img/default_img.jpg"
          height="200"
          width="300"
          alt="상대방의 프로필사진이 보이는 이미지"
          class="w-full h-48 object-cover"
          style="aspect-ratio:300/200;object-fit:cover"
        />
        <div class="p-4">
          <h3 class="text-lg font-bold">김준성</h3>
          <div class="inline-flex items-center rounded-full border px-2.5 py-0.5 text-xs font-semibold transition-colors focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 border-transparent bg-primary text-primary-foreground hover:bg-primary/80 mt-2">
            Online
          </div>
        </div>
      </div>

    </div>
  </main>
   <jsp:include page="footer.jsp" flush="false" />
</section>

</body>
</html>
