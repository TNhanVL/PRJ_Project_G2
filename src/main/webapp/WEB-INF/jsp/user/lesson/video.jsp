<%-- 
    Document   : video
    Created on : Jul 5, 2023, 8:54:54 PM
    Author     : TTNhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<video id="my-video" src="<%out.print(request.getContextPath());%>/public/media/lesson/<%out.print(lesson.getID() + "/" + post.getContent());%>" controls>
    Trình duyệt của bạn không hỗ trợ video.
</video>

<script>
    var video = document.getElementById('my-video');

//    if (video) {
//        video.addEventListener('ended', function () {
//            console.log('End');
//        });
//    }

    let sendedCompletedVideo = false;

    function checkVideoProgress() {
        if (video && !sendedCompletedVideo) {
            //check if 90% video
            if (video.currentTime / video.duration >= 0.9) {
                fetch("<%out.print(request.getContextPath());%>/user/markLessonComplete/<%out.print(lesson.getID());%>", {method: 'POST'})
                                        .catch(error => console.error(error));
                                sendedCompletedVideo = true;
                                let checkLesson = $(".lesson.active i")[0];
                                checkLesson.classList = [];
                                checkLesson.classList.add("fa-solid");
                                checkLesson.classList.add("fa-square-check");
                            }
                        }
                    }
// Gọi hàm kiểm tra video theo một khoảng thời gian nhất định
                    setInterval(checkVideoProgress, 1000); // Kiểm tra sau mỗi giây (1000 milliseconds)
</script>