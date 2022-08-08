<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="categoriesWithDetails" scope="request" type="java.util.List<com.ute.web.beans.Category>"/>
<div class="card">
  <h4 class="card-header">
    <i class="bi bi-list"></i>
    Danh mục sản phẩm
  </h4>
  <div class="list-group list-group-flush">
    <c:forEach items="${categoriesWithDetails}" var="c">
      <a href="${pageContext.request.contextPath}/Product/ByCat?id=${c.catID}" class="list-group-item list-group-item-action">
        <i class="fa fa-caret-right" aria-hidden="true"></i>
        ${c.catName}
      </a>
    </c:forEach>
<%--    <a href="#" class="list-group-item list-group-item-action active" aria-current="true">--%>
<%--      The current link item--%>
<%--    </a>--%>
  </div>
</div>

<div class="card mt-3">
  <h4 class="card-header">
    Featured
  </h4>
  <div class="list-group list-group-flush">
    <a href="#" class="list-group-item list-group-item-action active" aria-current="true">
      The current link item (edited)
    </a>
    <a href="#" class="list-group-item list-group-item-action">A second link item</a>
    <a href="#" class="list-group-item list-group-item-action">A third link item</a>
    <a href="#" class="list-group-item list-group-item-action">A fourth link item</a>
    <a href="#" class="list-group-item list-group-item-action disabled" tabindex="-1" aria-disabled="true">A disabled link item</a>
  </div>
</div>