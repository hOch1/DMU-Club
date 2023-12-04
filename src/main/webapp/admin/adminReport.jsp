<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Report List</title>
  <script src="https://cdn.tailwindcss.com"></script> <!--테일윈드 라이브러리-->
</head>
<body>
<h2>Reports</h2>

<div class="flex flex-col w-full min-h-screen bg-white dark:bg-gray-900">
  <header class="flex items-center justify-between h-16 px-4 border-b bg-gray-100 dark:bg-gray-800">
    <div class="flex items-center space-x-2">
      <img
              src="/placeholder.svg"
              alt="Admin Profile Image"
              height="32"
              width="32"
              class="rounded-full"
              style="aspect-ratio: 32 / 32; object-fit: cover;"
      />
      <h1 class="font-bold text-lg">Admin</h1>
    </div>
    <button class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:text-accent-foreground h-10 px-4 py-2 transition-colors hover:bg-gray-200 dark:hover:bg-gray-700">
      Logout
    </button>
  </header>
  <main class="flex-1 p-4">
    <div class="mt-8">
      <h2 class="font-bold text-xl mb-4">Reports</h2>
      <ul class="border divide-y">
        <li class="p-4">
          <h3 class="font-semibold">Report #1</h3>
          <p class="text-sm text-gray-600 dark:text-gray-400">This is the description of report #1.</p>
        </li>
      </ul>
    </div>
  </main>
</div>
<%= request.getAttribute("reportList") %>
</body>
</html>
