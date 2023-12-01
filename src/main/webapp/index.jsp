<%@ page import="dmu.dmuclub.dto.member.MemberDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="header.jsp" flush="false" />
<body>

<%
    if (session.getAttribute("member") == null)
        response.sendRedirect("/auth/sign-in");
%>


// Alert
<% if (session.getAttribute("message") != null) { %>
<script>
  alert("<%= session.getAttribute("message") %>");
</script>
<% session.removeAttribute("message");
 }
%>


<p>Welcome, <%= session.getAttribute("member") %>!</p>


 <!-- 헤더 영역 -->
 <section class="flex flex-col h-screen">
  
  
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
  
  <!-- footer 부분  -->
  <footer class="border-t p-2 bg-white">
  
    <!-- nav 부분 -->
      <nav class="flex justify-around">
        <a class="text-center" href="<%= request.getContextPath() %>/matching/matching.jsp" rel="ugc">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="h-5 w-5 mx-auto mb-1"
          >
            <path d="M19 14c1.49-1.46 3-3.21 3-5.5A5.5 5.5 0 0 0 16.5 3c-1.76 0-3 .5-4.5 2-1.5-1.5-2.74-2-4.5-2A5.5 5.5 0 0 0 2 8.5c0 2.3 1.5 4.05 3 5.5l7 7Z"></path>
          </svg>
          <span class="text-sm">Matching</span>
        </a>
        
        <a class="text-center" href="<%= request.getContextPath() %>/message/message.jsp" rel="ugc">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="h-5 w-5 mx-auto mb-1"
          >
            <path d="M17 6.1H3"></path>
            <path d="M21 12.1H3"></path>
            <path d="M15.1 18H3"></path>
          </svg>
          <span class="text-sm">Message</span>
        </a>
        
        <a class="text-center" href="<%= request.getContextPath() %>/main/main.jsp" rel="ugc">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="h-5 w-5 mx-auto mb-1"
          >
            <path d="m3 9 9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
            <polyline points="9 22 9 12 15 12 15 22"></polyline>
          </svg>
          <span class="text-sm">Main</span>
        </a>
        
        <a class="text-center" href="<%= request.getContextPath() %>/community/community.jsp" rel="ugc">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="h-5 w-5 mx-auto mb-1"
          >
            <path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2"></path>
            <circle cx="9" cy="7" r="4"></circle>
            <path d="M22 21v-2a4 4 0 0 0-3-3.87"></path>
            <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
          </svg>
          <span class="text-sm">Community</span>
        </a>
        
        
        <a class="text-center" href="<%= request.getContextPath() %>/info/info.jsp" rel="ugc">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="h-5 w-5 mx-auto mb-1"
          >
            <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"></path>
            <circle cx="12" cy="7" r="4"></circle>
          </svg>
          <span class="text-sm">My Information</span>
        </a>
      </nav>
    </footer>
  
</section>

</body>
</html>
