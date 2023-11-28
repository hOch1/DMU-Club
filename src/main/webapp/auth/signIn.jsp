<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>로그인</title>
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 rounded shadow-md w-96">
  <h2 class="text-2xl font-bold mb-6">로그인</h2>
  <form action="/auth/sign-in" method="post">
    <div class="mb-4">
      <label for="email" class="block text-sm font-medium text-gray-600">이메일</label>
      <input type="email" id="email" name="email" class="mt-1 p-2 w-full border rounded-md" required>
    </div>
    <div class="mb-4">
      <label for="password" class="block text-sm font-medium text-gray-600">비밀번호</label>
      <input type="password" id="password" name="password" class="mt-1 p-2 w-full border rounded-md" required>
    </div>
    <div class="mb-6">
      <button type="submit" class="w-full bg-blue-500 text-white p-2 rounded-md hover:bg-blue-600">로그인</button>
    </div>
  </form>
  <p class="text-sm text-gray-600">계정이 없으신가요? <a href="signUp.jsp" class="text-blue-500">회원가입</a></p>
</div>
</body>
</html>
