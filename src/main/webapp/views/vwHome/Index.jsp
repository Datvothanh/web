<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="listTop5HighestPrice" scope="request" type="java.util.List<com.ute.web.beans.Product>"/>

<t:main>
    <jsp:body>
        <div class="card">
            <h4 class="card-header">
                Index
            </h4>
            <div class="card-body">
                <ul class="list-group">
                    <li class="list-group-item active" aria-current="true">Top 5 sản phẩm gần kết thúc</li>
                    <li class="list-group-item">A second item</li>
                    <li class="list-group-item">A third item</li>
                    <li class="list-group-item">A fourth item</li>
                    <li class="list-group-item">And a fifth one</li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item active" aria-current="true">Top 5 sản phẩm có nhiều lượt ra giá nhất</li>
                    <li class="list-group-item">A second item</li>
                    <li class="list-group-item">A third item</li>
                    <li class="list-group-item">A fourth item</li>
                    <li class="list-group-item">And a fifth one</li>
                </ul>
                <ul class="list-group">
                    <li class="list-group-item active" aria-current="true">Top 5 sản phẩm có giá cao nhất</li>
                    <c:choose>
                        <c:when test="${listTop5HighestPrice.size() == 0}">
                            <div class="card-body">
                                <p class="card-text">Không có dữ liệu.</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="card-body">
                                <div class="row">
                                    <c:forEach items="${listTop5HighestPrice}" var="p">
                                        <div class="col-sm-4 mb-3">
                                            <div class="card h-100">
                                                <img src="${pageContext.request.contextPath}/public/imgs/sp/${p.proID}/main.png"
                                                     alt="${p.proName}" title="${p.proName}" class="card-img-top">
                                                <div class="card-body">
                                                    <h6 class="card-title">${p.proName}</h6>
                                                    <h5 class="card-title text-danger">
                                                        <fmt:formatNumber value="${p.startingPrice}" type="number"/>
                                                    </h5>
                                                    <p class="card-text">${p.tinyDes}</p>
                                                </div>
                                                <div class="card-footer text-muted">
                                                    <a class="btn btn-sm btn-outline-primary"
                                                       href="${pageContext.request.contextPath}/Product/Detail?id=${p.proID}"
                                                       role="button">
                                                        <i class="fa fa-eye" aria-hidden="true"></i>
                                                        Details
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </jsp:body>
</t:main>