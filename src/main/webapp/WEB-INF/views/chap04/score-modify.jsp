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
            border: 3px solid rgb(255, 0, 0);
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

        #wrap .container .information form label {
            margin: 1px;
        }

        #wrap .container .input {
            margin: 10px;
            align-items: center;
            justify-content: center;
            display: flex;
        }

        label {
            display: block;
        }

        input {
            width: 50px;
        }
    </style>


</head>

<body>
    <div id="wrap">
        <div class="container">
            <h1 class="name">${student.name}님의 성적 정보 수정하기</h1>
            <ul class="information">
                <form action="/score/modify" method="POST">
                    <label>
                        # 이름: <input type="hidden" name="name" value="${student.name}">${student.name}
                    </label>
                    <label>
                        # 학번: <input type="hidden" name="stuNum" value="${student.stuNum}">${student.stuNum}
                    </label>
                    <label>
                        # 국어: <input type="text" name="kor" value="${student.kor}">
                    </label>
                    <label>
                        # 영어: <input type="text" name="eng" value="${student.eng}">
                    </label>
                    <label>
                        # 수학: <input type="text" name="math" value="${student.math}">
                    </label>
                    <div class="input">
                        <label>
                            <button type="submit">수정하기</button>
                        </label>
                    </div>
                </form>
            </ul>
        </div>

    </div>

</body>

</html>