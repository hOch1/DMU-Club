<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdn.tailwindcss.com"></script> <!--테일윈드 라이브러리-->
    <title>admin Page</title>
</head>
<body>
<jsp:include page="sideBar.jsp"/>

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
        <div class="grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-3">
            <div class="rounded-lg border bg-card text-card-foreground shadow-sm" data-v0-t="card">
                <div class="flex flex-col space-y-1.5 p-6 pb-2">
                    <h3 class="text-2xl font-semibold leading-none tracking-tight">Post Management</h3>
                </div>
                <div class="p-6">
                    <button class="inline-flex items-center
                    justify-center rounded-md text-sm font-medium
                    ring-offset-background focus-visible:outline-none
                    focus-visible:ring-2 focus-visible:ring-ring
                    focus-visible:ring-offset-2 disabled:pointer-events-none
                    disabled:opacity-50 bg-primary text-primary-foreground h-10 px-4 py-2
                    transition-colors hover:bg-gray-200 dark:hover:bg-gray-700" >
                        <a href="<%= request.getContextPath() %>/admin/adminBoard.jsp">Manage Post</a>
                    </button>
                </div>
            </div>
            <div class="rounded-lg border bg-card text-card-foreground shadow-sm" data-v0-t="card">
                <div class="flex flex-col space-y-1.5 p-6 pb-2">
                    <h3 class="text-2xl font-semibold leading-none tracking-tight">Reporting</h3>
                </div>
                <div class="p-6">
                    <button class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground h-10 px-4 py-2 transition-colors hover:bg-gray-200 dark:hover:bg-gray-700">
                        <a href="<%= request.getContextPath() %>/admin/adminReport.jsp"> Go to Reporting </a>
                    </button>
                </div>
            </div>
            <div class="rounded-lg border bg-card text-card-foreground shadow-sm" data-v0-t="card">
                <div class="flex flex-col space-y-1.5 p-6 pb-2">
                    <h3 class="text-2xl font-semibold leading-none tracking-tight">Member Management</h3>
                </div>
                <div class="p-6">
                    <button class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground h-10 px-4 py-2 transition-colors hover:bg-gray-200 dark:hover:bg-gray-700">
                        <a href="<%= request.getContextPath() %>/admin/adminMember.jsp">Manage Members</a>
                    </button>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>
