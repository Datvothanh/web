<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="proEnd" scope="request" type="com.ute.web.beans.Product"/>
<t:main>
     <jsp:attribute name="css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css"
              integrity="sha512-f0tzWhCwVFS3WeYaofoLWkTP62ObhewQ1EZn65oSYDZUg1+CyywGKkWzm8BxaJj5HGKI72PnMH9jYyIFz+GH7g=="
              crossorigin="anonymous" referrerpolicy="no-referrer"/>
    </jsp:attribute>
    <jsp:attribute name="js">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"
                integrity="sha512-AIOTidJAcHBH2G/oZv9viEGXRqDNmfdPVPYOYKGy3fti0xIplnlgMHUGfuNRzC6FkzIo0iIxgFnr9RikFxK+sw=="
                crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <script>
            $('#txtStartDay').datetimepicker({
                format: 'd/m/Y',
                timepicker: false,
                mask: true
            });

            $('#txtEndDay').datetimepicker({
                format: 'd/m/Y',
                timepicker: false,
                mask: true
            });
        </script>

    </jsp:attribute>
    <jsp:body>
        <form action="" method="post" enctype="multipart/form-data">
            <div class="card">
                <h4 class="card-header d-flex justify-content-between">
                    Sản phẩm mới
                    <c:if test="${authUser.permission != 0}">
                        <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Account/Profile"
                           role="button">
                            <i class="bi bi-backspace-fill" aria-hidden="true"></i>
                            Trở về
                        </a>
                    </c:if>
                </h4>
                <div class="card-body">
                    <div class="form-group" hidden>
                        <label for="UserSellID">User Sell ID</label>
                        <input type="text" class="form-control" id="UserSellID" name="UserSellID"
                               value="${authUser.id}">
                    </div>
                    <div class="form-group">
                        <label for="ProID">ProID</label>
                        <input type="text" class="form-control" id="ProID" name="ProID" value="${proEnd.proID + 1}">
                    </div>
                    <div class="form-group">
                        <label for="txtProName">Tên sản phẩm</label>
                        <input type="text" class="form-control" id="txtProName" name="ProName" autofocus>
                    </div>
                    <div class="custom-file mb-4">
                        <label for="ImageMain" class="custom-file-label">Ảnh chính</label>
                        <input type="file" class="custom-file-input" id="ImageMain" name="ImageMain">
                    </div>
                    <div class="custom-file mb-4">
                        <label for="ImageSub1" class="custom-file-label">Ảnh phụ 1</label>
                        <input type="file" class="custom-file-input" id="ImageSub1" name="ImageSub1">
                    </div>
                    <div class="custom-file mb-4">
                        <label for="ImageSub2" class="custom-file-label">Ảnh phụ 2</label>
                        <input type="file" class="custom-file-input" id="ImageSub2" name="ImageSub2">
                    </div>
                    <div class="custom-file mb-4">
                        <label for="ImageSub3" class="custom-file-label">Ảnh phụ 3</label>
                        <input type="file" class="custom-file-input" id="ImageSub3" name="ImageSub3">
                    </div>
                    <div class="form-group">
                        <label for="numStartingPrice">Giá khởi điểm</label>
                        <input type="number" class="form-control" id="numStartingPrice" name="StartingPrice">
                    </div>
                    <div class="form-group">
                        <label for="numStepPrice">Bước giá</label>
                        <input type="number" class="form-control" id="numStepPrice" name="StepPrice">
                    </div>
                    <div class="form-group">
                        <label for="txtNowPrice">Giá mua ngay (Nếu có)</label>
                        <input type="number" class="form-control" id="txtNowPrice" name="NowPrice">
                    </div>
                    <div class="form-group">
                        <label for="numCatID">Loại sản phẩm (1:.. , 2:.. , 3:..)</label>
                        <input type="number" class="form-control" id="numCatID" name="CatID">
                    </div>
                    <div class="form-group">
                        <label for="txtTinyDes">Mô tả ngắn</label>
                        <input type="text" class="form-control" id="txtTinyDes" name="TinyDes">
                    </div>
                    <div class="form-group">
                        <label for="txtFullDes">Mô tả chi tiết</label>
                        <input type="text" class="form-control" id="txtFullDes" name="FullDes">
                    </div>
                    <div class="form-group">
                        <label for="txtStartDay">Ngày bắt đầu</label>
                        <input type="text" class="form-control" id="txtStartDay" name="StartDay">
                    </div>
                    <div class="form-group">
                        <label for="txtEndDay">Ngày kết thúc</label>
                        <input type="text" class="form-control" id="txtEndDay" name="EndDay">
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="1" id="autoExtend" name="AutoExtend">
                        <label class="form-check-label" for="autoExtend">
                            Tự động gia hạn ngày kết thúc
                        </label>
                    </div>
                </div>
                <div class="card-footer">
                    <c:if test="${authUser.permission == 0}">
                        <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Admin/Product/"
                           role="button">
                            <i class="fa fa-backward" aria-hidden="true"></i>
                            Danh sách sản phẩm
                        </a>
                    </c:if>
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Lưu
                    </button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:main>