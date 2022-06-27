<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="categories" scope="request" type="java.util.List<com.ute.web.beans.Category>"/>

<t:main>
    <jsp:body>
        <div class="card">
            <h4 class="card-header">
                Categories
            </h4>
            <c:choose>
                <c:when test="${categories.size() == 0}">
                    <div class="card-body">
                        <p class="card-text">Không có dữ liệu.</p>
                    </div>
                </c:when>
            </c:choose>
            <c:otherwise>
                <div class="card-body">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Category</th>
                            <th scope="col">&nbsp;</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${categories}" var="c">
                            <tr>
                                <th scope="row">${c.catID}</th>
                                <td>${c.catName}</td>
                                <td class="text-right">
                                    <a class="btn btn-outline-primary" href="#" role="button">
                                        <i class="fa fa-pencil" aria-hidden="true"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </div>
    </jsp:body>
</t:main>