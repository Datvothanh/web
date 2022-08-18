<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="category" scope="request" type="com.ute.web.beans.Category"/>
<t:main>
    <jsp:attribute name="js">
        <script>
            $('#frmDelete').on('submit', function (e) {
                e.preventDefault();
                let CatID = Number($('#CatID').val());
                $.getJSON('${pageContext.request.contextPath}/Admin/Category/IsAvailable?catID=' + CatID, function (data) {//Kiểm tra UserName có tồn tại không
                    if (data === true) {
                        $('#frmDelete').off('submit').submit();
                    } else {
                        alert('Category còn sản phẩm.');
                    }
                });
            });
            $('#frmSave').on('submit', function (e) {
                e.preventDefault();
                const CatName = $('#txtCatName').val();
                if (CatName.length === 0) {
                    alert('Vui lòng nhập tên')
                } else {
                    $('#frmSave').off('submit').submit();
                }
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <form id="frmDelete" method="post"
              action="${pageContext.request.contextPath}/Admin/Category/Delete" hidden>
            <label>
                <input type="text" class="form-control" name="CatID" id="CatID" readonly value="${category.catID}">
            </label>
        </form>

        <div class="card">
            <h4 class="card-header">
                Category
            </h4>
            <div class="card-body">
                <form id="frmSave" method="post" action="${pageContext.request.contextPath}/Admin/Category/Update   ">
                    <div class="form-group">
                        <label for="txtCatID">#</label>
                        <input type="text" class="form-control" id="txtCatID" name="CatID" readonly
                               value="${category.catID}">
                    </div>
                    <div class="form-group">
                        <label for="txtCatName">Category</label>
                        <input type="text" class="form-control" id="txtCatName" name="CatName" autofocus
                               value="${category.catName}">
                    </div>
                    <div class="form-group">
                        <label for="numGroupCategory">Group Category</label>
                        <input type="text" class="form-control" id="numGroupCategory" name="GroupCategoryID"
                               value="${category.grCatID}">
                    </div>
                </form>
            </div>
            <div class="card-footer">
                <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Admin/Category/"
                   role="button">
                    <i class="fa fa-backward" aria-hidden="true"></i>
                    Danh sách
                </a>
                <a class="btn btn-primary" href="javascript:$('#frmSave').submit()">
                    <i class="fa fa-check" aria-hidden="true"></i>
                    Lưu
                </a>
                <a class="btn btn-danger" href="javascript:$('#frmDelete').submit()">
                    <i class="fa fa-trash-o" aria-hidden="true"></i>
                    Xóa
                </a>
            </div>
        </div>

    </jsp:body>
</t:main>