<%@ page import="dmu.dmuclub.dto.member.MemberDto" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="/header.jsp" flush="false" />
<body>

<%
    List<MemberDto> memberDtoList = (List<MemberDto>) request.getAttribute("memberList");
    for (MemberDto memberDto : memberDtoList){
        if (memberDto.getRole().equals("NORMAL"))
%>
<a href="/message?nickname=<%=memberDto.getNickname()%>"><%=memberDto.getNickname()%></a><br>
<% } %>


<jsp:include page="/footer.jsp" flush="false" />
</body>
</html>
