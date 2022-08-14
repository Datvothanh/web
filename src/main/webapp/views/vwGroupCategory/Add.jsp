<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:main>
    <jsp:body>
        <form action="" method="post">
            <div class="card">
                <h4 class="card-header">
                    New GroupCategory
                </h4>
                <div class="card-body">
                    <div class="form-group">
                        <label for="txtGroupCatName">Name Group Category</label>
                        <input type="text" class="form-control" id="txtGroupCatName" name="GroupCatName" autofocus>
                    </div>
                </div>
                <div class="card-footer">
                    <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Admin/GroupCategory/" role="button">
                        <i class="fa fa-backward" aria-hidden="true"></i>
                        Danh sách
                    </a>
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Lưu
                    </button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:main>