<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="groupCategory" scope="request" type="com.ute.web.beans.GroupCategory"/>
<t:main>
    <jsp:body>
        <form action="" method="post">
            <div class="card">
                <h4 class="card-header">
                    Group Category
                </h4>
                <div class="card-body">
                    <div class="form-group">
                        <label for="numGroupCatID">#</label>
                        <input type="text" class="form-control" id="numGroupCatID" name="GroupCatID" readonly value="${groupCategory.grCatID}">
                    </div>
                    <div class="form-group">
                        <label for="txtGroupCatName">Name Group Category</label>
                        <input type="text" class="form-control" id="txtGroupCatName" name="GroupCatName" autofocus value="${groupCategory.grCatName}">
                    </div>
                </div>
                <div class="card-footer">
                    <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Admin/GroupCategory/" role="button">
                        <i class="fa fa-backward" aria-hidden="true"></i>
                        Danh sách
                    </a>
                    <button type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/Admin/GroupCategory/Update">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Lưu
                    </button>
                    <button type="submit" class="btn btn-danger" formaction="${pageContext.request.contextPath}/Admin/GroupCategory/Delete">
                        <i class="fa fa-trash-o" aria-hidden="true"></i>
                        Xóa
                    </button>

                </div>
            </div>
        </form>
    </jsp:body>
</t:main>