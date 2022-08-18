<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="categories" scope="request" type="java.util.List<com.ute.web.beans.Category>"/>
<jsp:useBean id="groupCategories" scope="request" type="java.util.List<com.ute.web.beans.GroupCategory>"/>
<t:main>
    <jsp:body>
        <div class="card">
            <h4 class="card-header d-flex justify-content-between">
                Categories
                <div>
                    <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Account/Profile"
                       role="button">
                        <i class="bi bi-backspace-fill" aria-hidden="true"></i>
                        Trở về
                    </a>
                    <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Admin/Category/Add"
                       role="button">
                        <i class="fa fa-plus" aria-hidden="true"></i>
                        Add Category
                    </a>
                </div>
            </h4>
            <c:choose>
                <c:when test="${categories.size() == 0}">
                    <div class="card-body">
                        <p class="card-text">Không có dữ liệu.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Category</th>
                                <th scope="col">Group Category</th>
                                <th scope="col">&nbsp;</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${categories}" var="c">
                                <tr>
                                    <th scope="row">${c.catID}</th>
                                    <td>${c.catName}</td>
                                    <c:forEach items="${groupCategories}" var="g">
                                        <c:if test="${g.grCatID == c.grCatID}">
                                            <td>${g.grCatName}</td>
                                        </c:if>
                                    </c:forEach>
                                    <td class="text-right">
                                        <a class="btn btn-outline-primary"
                                           href="${pageContext.request.contextPath}/Admin/Category/Edit?id=${c.catID}"
                                           role="button">
                                            <i class="fa fa-pencil" aria-hidden="true"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </jsp:body>
</t:main>