<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="product" scope="request" type="com.ute.web.beans.Product"/>
<t:main>
    <jsp:body>
        <form action="" method="post">
            <div class="card">
                <h4 class="card-header">
                    Sản phẩm
                </h4>
                <div class="card-body">
                    <div class="form-group">
                        <label for="txtProID">#</label>
                        <input type="text" class="form-control" id="txtProID" name="ProID" readonly
                               value="${product.proID}">
                    </div>
                    <div class="form-group">
                        <label for="txtProName">Tên sản phẩm</label>
                        <input type="text" class="form-control" id="txtProName" name="ProName" autofocus
                               value="${product.proName}">
                    </div>
                        <%--                    <div class="custom-file mb-4">--%>
                        <%--                        <label for="ImageMain" class="custom-file-label">Ảnh chính</label>--%>
                        <%--                        <input type="file" class="custom-file-input" id="ImageMain" name="ImageMain" value="${product.proName}>--%>
                        <%--                    </div>--%>
                        <%--                    <div class="custom-file mb-4">--%>
                        <%--                        <label for="ImageSub1" class="custom-file-label">Ảnh phụ 1</label>--%>
                        <%--                        <input type="file" class="custom-file-input" id="ImageSub1" name="ImageSub1" value="${product.proName}>--%>
                        <%--                    </div>--%>
                        <%--                    <div class="custom-file mb-4">--%>
                        <%--                        <label for="ImageSub2" class="custom-file-label">Ảnh phụ 2</label>--%>
                        <%--                        <input type="file" class="custom-file-input" id="ImageSub2" name="ImageSub2">--%>
                        <%--                    </div>--%>
                        <%--                    <div class="custom-file mb-4">--%>
                        <%--                        <label for="ImageSub3" class="custom-file-label">Ảnh phụ 3</label>--%>
                        <%--                        <input type="file" class="custom-file-input" id="ImageSub3" name="ImageSub3">--%>
                        <%--                    </div>--%>
                    <div class="form-group">
                        <label for="numStartingPrice">Giá khởi điểm</label>
                        <input type="number" class="form-control" id="numStartingPrice" name="StartingPrice"
                               value="${product.proName}">
                    </div>
                    <div class="form-group">
                        <label for="numStepPrice">Bước giá</label>
                        <input type="number" class="form-control" id="numStepPrice" name="StepPrice"
                               value="${product.proName}">
                    </div>
                    <div class="form-group">
                        <label for="txtNowPrice">Giá mua ngay (Nếu có)</label>
                        <input type="number" class="form-control" id="txtNowPrice" name="NowPrice"
                               value="${product.proName}">
                    </div>
                    <div class="form-group">
                        <label for="numCatID">Loại sản phẩm (1:.. , 2:.. , 3:..)</label>
                        <input type="number" class="form-control" id="numCatID" name="CatID" value="${product.proName}">
                    </div>
                    <div class="form-group">
                        <label for="txtTinyDes">Mô tả ngắn</label>
                        <input type="text" class="form-control" id="txtTinyDes" name="TinyDes"
                               value="${product.proName}">
                    </div>
                    <div class="form-group">
                        <label for="txtFullDes">Mô tả chi tiết</label>
                        <input type="text" class="form-control" id="txtFullDes" name="FullDes"
                               value="${product.proName}">
                    </div>
                    <div class="form-group">
                        <label for="txtStartDay">Ngày bắt đầu</label>
                        <input type="text" class="form-control" id="txtStartDay" name="StartDay"
                               value="${product.proName}">
                    </div>
                    <div class="form-group">
                        <label for="txtEndDay">Ngày kết thúc</label>
                        <input type="text" class="form-control" id="txtEndDay" name="EndDay" value="${product.proName}">
                    </div>
                    <div class="form-check">
                        <c:choose>
                            <c:when test="${product.autoExtend == 1}">
                                <input class="form-check-input" type="checkbox" value="1" id="autoExtend"
                                       name="AutoExtend"
                                       checked>
                            </c:when>
                            <c:otherwise>
                                <input class="form-check-input" type="checkbox" value="1" id="autoExtend"
                                       name="AutoExtend">
                            </c:otherwise>
                        </c:choose>
                        <label class="form-check-label" for="autoExtend">
                            Tự động gia hạn ngày kết thúc
                        </label>
                    </div>
                </div>
                <div class="card-footer">
                    <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Admin/Product/"
                       role="button">
                        <i class="fa fa-backward" aria-hidden="true"></i>
                        Danh sách sản phẩm
                    </a>
                    <button type="submit" class="btn btn-danger"
                            formaction="${pageContext.request.contextPath}/Admin/Product/Delete">
                        <i class="fa fa-trash-o" aria-hidden="true"></i>
                        Xóa
                    </button>
                    <button type="submit" class="btn btn-primary"
                            formaction="${pageContext.request.contextPath}/Admin/Product/Update">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Lưu
                    </button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:main>