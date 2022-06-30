<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="products" scope="request" type="java.util.List<com.ute.web.beans.Product>"/>

<t:main>
    <jsp:body>
        <div class="card">
            <h4 class="card-header d-flex justify-content-between">
                Products (Admin)
                <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Admin/Product/Add"
                   role="button">
                    <i class="fa fa-plus" aria-hidden="true"></i>
                    Add Product
                </a>
            </h4>
            <c:choose>
                <c:when test="${products.size() == 0}">
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
                                <th scope="col">Product</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">TinyDes</th>
                                <th scope="col">FullDes</th>
                                <th scope="col">&nbsp;</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${products}" var="p">
                                <tr>
                                    <th scope="row">${p.proID}</th>
                                    <td>${p.proName}</td>
                                    <td>
                                        <fmt:formatNumber value="${p.price}" type="number"/>
                                    </td>
                                    <td>${p.quantity}</td>
                                    <td>${p.tinyDes}</td>
                                    <td>${p.fullDes}</td>
                                    <td class="text-right">
                                        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/Admin/Product/Edit?id=${p.proID}" role="button">
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