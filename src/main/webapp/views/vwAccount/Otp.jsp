<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Web App</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <style>
        body {
            height: 100vh;
            /*background-image: url("https://img.freepik.com/free-photo/abstract-grunge-decorative-relief-navy-blue-stucco-wall-texture-wide-angle-rough-colored-background_1258-28311.jpg?w=2000");*/
        }
    </style>
</head>
<body>

<div class="container-fluid h-100">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="col-sm-4">
            <c:if test="${hasError}">
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    ${errorMessage}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">x</span>
                    </button>
                </div>
            </c:if>
            <form action="" method="post">
                <div class="form-group">
                    <label class="sr-only" for="txtCode">Mã OTP</label>
                    <div class="input-group">
                        <input type="text" class="from-control form-control-lg " id="txtCode" name="code"
                               placeholder="Nhập mã OTP"
                               autofocus>
                        <div class="input-group-append">
                            <span class="input-group-text bg-white">
                                 <i class="bi bi-envelope"></i>
                            </span>
                        </div>
                    </div>
                </div>

                <div>
                    <button type="submit" class="btn btn-info btn-block">
                        <i class="fa fa-sign-in" aria-hidden="true"></i>
                        Nhập mã
                    </button>
                </div>
            </form>

        </div>
    </div>

</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
        integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
        crossorigin="anonymous"></script>
</body>
</html>