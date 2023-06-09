<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- reset css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <style>
        #wrap .container {
            border: 3px solid orange;
            box-shadow: 1px 1px 10px #333;

            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);

            padding: 15px;
            border-radius: 15px;

            text-align: center;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;

            height: 200px;
        }

        #wrap .container .name {
            width: 300px;
            height: 30px;
            font-weight: 700;
            font-size: 1.2em;

        }

        #wrap .container .information {
            width: 200px;
            height: 150px;
            line-height: 1.5;

            text-align: left;
        }
    </style>

</head>

<body>
    <div id="wrap">
        <div class="container">
            <h1 class="name">${student.name}님의 성적 정보</h1>
            <ul class="information">
                <li># 국어: ${student.kor}점</li>
                <li># 영어: ${student.eng}점</li>
                <li># 수학: ${student.math}점</li>
                <li># 총점: ${student.getTotal()}점</li>
                <li># 평균: ${student.average}점</li>
                <li># 학점: ${student.grade}</li>
            </ul>
            <div class="input">
                <button id="go-home" type="button">목록</button>
                <a type="button" href="/score/inputModify?stuNum=${student.stuNum}">수정</a>
            </div>
        </div>
    </div>

    <script>
        //홈화면으로 버튼 이벤트
        const $homeBtn = document.getElementById('go-home');
        $homeBtn.onclick = e => {
            window.location.href = '/score/list';
        };

        

    </script>

</body>

</html>