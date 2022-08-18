<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="categoriesWithDetails" scope="request" type="java.util.List<com.ute.web.beans.Category>"/>
<jsp:useBean id="groupCategoriesWithDetails" scope="request" type="java.util.List<com.ute.web.beans.GroupCategory>"/>
<div class="card">
    <h4 class="card-header">
        <i class="bi bi-list"></i>
        Danh mục sản phẩm
    </h4>
    <div class="list-group list-group-flush">
        <c:forEach items="${groupCategoriesWithDetails}" var="g">
            <%--            <a href="${pageContext.request.contextPath}/Product/ByCat?id=${c.catID}"--%>
            <%--               class="list-group-item list-group-item-action">--%>
            <%--                <i class="fa fa-caret-right" aria-hidden="true"></i>--%>
            <%--                    ${c.catName}--%>
            <%--            </a>--%>
            <div class="list-group-item list-group-item-action">
                <div class="dropdown">
                    <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-toggle="dropdown"
                       aria-expanded="false">
                            ${g.grCatName}
                    </a>

                    <div class="dropdown-menu">
                        <c:forEach items="${categoriesWithDetails}" var="c">
                            <c:if test="${g.grCatID == c.grCatID}">
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/Product/ByCat?id=${c.catID}"><i
                                        class="fa fa-caret-right" aria-hidden="true"></i>
                                        ${c.catName}</a>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:forEach>
        <%--    <a href="#" class="list-group-item list-group-item-action active" aria-current="true">--%>
        <%--      The current link item--%>
        <%--    </a>--%>
    </div>
</div>
