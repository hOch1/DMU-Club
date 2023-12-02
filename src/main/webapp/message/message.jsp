<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/header.jsp" flush="false" />

<body>
    <section class="flex h-screen bg-gray-100 dark:bg-gray-900 justify-center">
        <main class="w-3/4 bg-white dark:bg-gray-800 overflow-y-auto">
          <ul class="divide-y divide-gray-300 dark:divide-gray-700">

            <li class="p-4 hover:bg-gray-300 dark:hover:bg-gray-700 cursor-pointer flex items-center space-x-2">
              <img
                alt="User 1"
                class="rounded-full border-2 border-blue-500"
                height="40"
                src="/placeholder.svg"
                width="40"
                style="aspect-ratio:40/40;object-fit:cover"
              />
              <div>
                <h2 class="text-gray-700 dark:text-gray-300">Chat 1 - User 1</h2>
                <p class="text-gray-500 dark:text-gray-400 text-sm">Last message preview...</p>
              </div>
            </li>

          </ul>
        </main>
      </section>

<!-- footer 영역 -->
<jsp:include page="../footer.jsp" flush="false" />

<script>

</script>
</body>
</html>