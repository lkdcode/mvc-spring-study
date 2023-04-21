<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">

    <!-- reset -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <!-- fontawesome css: https://fontawesome.com -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">

    <link rel="stylesheet" href="/assets/css/write.css">
    <link rel="stylesheet" href="/assets/css/main.css">
</head>

<body>

    <div id="wrap">

        <div class="main-title-wrapper">
            <h1 class="main-title">상세 내용 보기</h1>
            <button type="button" class="add-btn" onclick="history.back()">이전으로</button>
        </div>

        <div class="card-container">
            <div class="card-wrapper">
                <section class="card">
                    <div class="card-title-wrapper">
                        <h2 class="card-title">${board.title}</h2>
                    </div>
                    <div class="card-content">
                        <p>
                            ${board.content}
                        </p>
                    </div>
                </section>
                <div class="card-btn-group">
                    <button class="del-btn">
                        <i class="fas fa-times"></i>
                    </button>
                </div>
            </div>

        </div>

    </div>


</body>

</html>