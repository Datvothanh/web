<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="groupCategory" scope="request" type="com.ute.web.beans.GroupCategory"/>
<t:main>
    <jsp:attribute name="js">
        <script>
            $('#frmDelete').on('submit', function (e) {
                e.preventDefault();
                let GrCatID = Number($('#GroupCatID').val());
                $.getJSON('${pageContext.request.contextPath}/Admin/GroupCategory/IsAvailable?GrCatID=' + GrCatID, function (data) {//Kiểm tra UserName có tồn tại không
                    if (data === true) {
                        $('#frmDelete').off('submit').submit();
                    } else {
                        alert('Group Category còn sản phẩm.');
                    }
                });
            });
            $('#frmSave').on('submit', function (e) {
                e.preventDefault();
                const GroupCatName = $('#txtGroupCatName').val();
                if (GroupCatName.length === 0) {
                    alert('Vui lòng nhập tên')
                } else {
                    $('#frmSave').off('submit').submit();
                }
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <form id="frmDelete" method="post"
              action="${pageContext.request.contextPath}/Admin/GroupCategory/Delete" hidden>
            <label>
                <input type="text" class="form-control" id="GroupCatID" name="GroupCatID" readonly
                       value="${groupCategory.grCatID}">
            </label>
        </form>

        <div class="card">
            <h4 class="card-header">
                Group Category
            </h4>
            <div class="card-body">
                <form id="frmSave" method="post" action="${pageContext.request.contextPath}/Admin/GroupCategory/Update">
                    <div class="form-group">
                        <label for="numGroupCatID">#</label>
                        <input type="text" class="form-control" id="numGroupCatID" name="GroupCatID" readonly
                               value="${groupCategory.grCatID}">
                    </div>
                    <div class="form-group">
                        <label for="txtGroupCatName">Name Group Category</label>
                        <input type="text" class="form-control" id="txtGroupCatName" name="GroupCatName" autofocus
                               value="${groupCategory.grCatName}">
                    </div>
                </form>
            </div>
            <div class="card-footer">
                <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Admin/GroupCategory/"
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