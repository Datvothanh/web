<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="category" scope="request" type="com.ute.web.beans.Category"/>
<t:main>
    <jsp:body>
        <form action="" method="post">
            <div class="card">
                <h4 class="card-header">
                    Category
                </h4>
                <div class="card-body">
                    <div class="form-group">
                        <label for="txtCatID">#</label>
                        <input type="text" class="form-control" id="txtCatID" name="CatID" readonly value="${category.catID}">
                    </div>
                    <div class="form-group">
                        <label for="txtCatName">Category</label>
                        <input type="text" class="form-control" id="txtCatName" name="CatName" autofocus value="${category.catName}">
                    </div>
                    <div class="form-group">
                        <label for="numGroupCategory">Group Category</label>
                        <input type="text" class="form-control" id="numGroupCategory" name="GroupCategoryID" value="${category.grCatID}">
                    </div>
                </div>
                <div class="card-footer">
                    <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Admin/Category/" role="button">
                        <i class="fa fa-backward" aria-hidden="true"></i>
                        Danh sách
                    </a>
                    <button type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/Admin/Category/Update">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Lưu
                    </button>
                    <button type="submit" class="btn btn-danger" formaction="${pageContext.request.contextPath}/Admin/Category/Delete">
                        <i class="fa fa-trash-o" aria-hidden="true"></i>
                        Xóa
                    </button>

                </div>
            </div>
        </form>
    </jsp:body>
</t:main>