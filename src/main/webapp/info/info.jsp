<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="/header.jsp" flush="false" />
<body>
<section class="flex h-screen bg-gray-100 justify-center">
    <div class="w-3/4 bg-white overflow-y-auto">
        <div class="p-4 flex flex-col">
            <h1 class="text-2xl text-gray-700 dark:text-gray-300 mb-4">내 정보
                <button
                        class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 text-primary-foreground h-10 px-2 py-1 text-sm bg-gray-300 dark:bg-gray-700 hover:bg-gray-400 dark:hover:bg-gray-600"
                        type="button"
                        onclick="insertImg()"
                >
                    프로필 사진 등록하기
                </button>
            </h1>
            <img
                    alt="내 프로필"
                    class="rounded-full border-2 border-blue-500"
                    height="40"
                    src="${member.filename}"
                    width="40"
                    style="aspect-ratio:40/40;object-fit:cover"
            />
            <p>나의 이메일 : ${member.email}</p>
            <p>이름 : ${member.username}</p>
            <p>별명 : ${member.nickname}</p>
            <p>번호 : ${member.phone}</p>
            <p>MBTI : ${member.mbti}</p>

        </div>
        <h2 class="text-xl text-gray-700 dark:text-gray-300 p-4 border-t border-gray-300 dark:border-gray-700">
            친구목록
        </h2>
        <ul class="divide-y divide-gray-300 dark:divide-gray-700">

            <!--Start Point-->
            <c:forEach var="item" items="${friendList}" varStatus="loop">
            <li class="p-4 hover:bg-gray-300 dark:hover:bg-gray-700 cursor-pointer flex items-center space-x-2 justify-between">
                <div class="flex items-center space-x-2">
                    <img
                            alt="Friend 1"
                            class="rounded-full border-2 border-blue-500"
                            height="40"
                            src="/img/default_img.jpg"
                            width="40"
                            style="aspect-ratio:40/40;object-fit:cover"
                    />
                    <div>
                        <h2 class="text-gray-700 dark:text-gray-300">${item.username}</h2>
                    </div>
                </div>
                <div class="flex space-x-2">
                    <button
                            class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 text-primary-foreground h-10 px-2 py-1 text-sm bg-gray-300 dark:bg-gray-700 hover:bg-gray-400 dark:hover:bg-gray-600"
                            type="button"
                            onclick="deleteFriend(${item.id})"
                    >
                        Remove
                    </button>
                    <button
                            class="inline-flex items-center justify-center rounded-md font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 h-10 px-2 py-1 text-sm bg-red-500 hover:bg-red-600 text-white"
                            type="button" onclick="report(${item.id})"
                    >
                        Report
                    </button>
                </div>
            </li>
            </c:forEach>
            <!--끝-->

    </ul>
    </div>
</section>

<script>
    //삭제 alert + 친구 삭제기능
function deleteFriend(friendId){
    let id = friendId;
    Swal.fire({
        title: "정말로 삭제하시겠어요?",
        text: "다시는 만날 수 없을지도 모릅니다!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!."
    }).then((result) => {

        if (result.isConfirmed) {


            //친구 삭제 내용 작성
            const xhr = new XMLHttpRequest();

            // POST 요청 설정
            xhr.open("POST", "/member/deleteFriend", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            // POST 요청 본문 데이터 생성
            const requestData = `id=`+friendId;

            // 요청 전송
            xhr.send(requestData);

            // 요청 완료 후의 처리
            xhr.onload = function () {
                if (xhr.status >= 200 && xhr.status < 300) {
                    // 성공적으로 요청이 완료된 경우
                    console.log("POST 요청이 성공적으로 완료되었습니다.");
                    Swal.fire({
                        title: "삭제 완료!",
                        text: "인관간계 정리를 참 잘하시는군요!",
                        icon: "success"
                    });
                } else {
                    // 요청이 실패한 경우
                    console.error("POST 요청이 실패하였습니다.");
                }
            };
        }
    });
}

// 신고 alert
function report(){
    (async () => {
        const { value: text } = await Swal.fire({
            input: "textarea",
            inputLabel: "Message",
            inputPlaceholder: "Type your message here...",
            inputAttributes: {
                "aria-label": "Type your message here"
            },
            showCancelButton: true
        });
        if (text) {
            // 서버로 텍스트 전송
            sendReportToServer(text);
            Swal.fire('신고가 완료되었습니다!!');
        }
    })()
}

//서버로 form 데이터 전송 (AJAX 사용)
function sendReportToServer(text) {
    // AJAX 요청을 사용하여 서버로 텍스트 전송
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/report", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 서버 응답을 처리할 수 있음
            Swal.fire('신고가 완료되었습니다!!');
        } else{
            console.error('Error: ', xhr.statusText);
            console.error('Error response:', xhr.responseText);
        }
    };

    // 텍스트를 form 데이터 형식으로 변환하여 전송
    // 여기서는 encodeURIComponent를 사용하여 특수 문자를 인코딩합니다.
    const formData = encodeURIComponent(text);
    xhr.send(formData);
}

// 이미지 넣기 구현
async function insertImg (){
    const { value: file } = await Swal.fire({
        title: "이미지를 골라주세요",
        input: "file",
        inputAttributes: {
            "accept": "image/*",
            "aria-label": "Upload your profile picture"
        }
    });
    if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            Swal.fire({
                title: "업로드 된 사진",
                imageUrl: e.target.result,
                imageAlt: "업로드 된 사진"
            });
        };
        reader.readAsDataURL(file);


        //폼 데이터로 전송하기
        var formData = new FormData();

        formData.append('image',file);
        //서버에 전송
        var xhr= new XMLHttpRequest();
        //api 경로 추가
        xhr.open('POST', '/member/updateImg', true);
        // 업로드 완료 시의 처리를 정의
        xhr.onload = function () {
            if (xhr.status === 200) {
                // 성공적으로 업로드된 경우의 처리
                console.log('이미지가 성공적으로 업로드되었습니다.');
            } else {
                // 업로드 중 오류가 발생한 경우의 처리
                console.error('이미지 업로드 중 오류가 발생했습니다.');
            }
        };
        //전송
        xhr.send(formData);
    } else {
        // 파일이 선택되지 않은 경우의 처리
        Swal.fire('이미지를 선택하세요.');
        console.error('이미지 선택이 되지 않음');
    }
}
</script>

<!-- footer 영역 -->
<jsp:include page="/footer.jsp" flush="false" />


</body>
</html>