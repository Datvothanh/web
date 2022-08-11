<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="products" scope="request" type="java.util.List<com.ute.web.beans.Product>"/>
<jsp:useBean id="favourite" scope="request" type="java.util.List<com.ute.web.beans.Favourite>"/>
<t:main>
    <jsp:body>
        <div class="card">
            <h4 class="card-header">
                Products
            </h4>
            <c:choose>
                <c:when test="${products.size() == 0}">
                    <div class="card-body">
                        <p class="card-text">Không có dữ liệu.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="card-body">
                        <div class="row">
                            <c:forEach items="${products}" var="p">
                                <div class="col-sm-4 mb-3">
                                    <div class="card h-100">
                                        <form id="frmFavourite${p.proID}" method="post"
                                              action="${pageContext.request.contextPath}/Product/Favourite">
                                            <label>
                                                <input value="${authUser.id}" name="idUser" hidden>
                                            </label>
                                            <label>
                                                <input value="${p.proID}" name="idPro" hidden>
                                            </label>
                                            <label>
                                                <input value="0" name="favourite" hidden>
                                            </label>
                                        </form>
                                        <c:forEach items="${favourite}" var="f">
                                            <c:if test="${f.userID == authUser.id && f.proID == p.proID}">
                                                <form id="frmFavourite-Add${p.proID}" method="post"
                                                      action="${pageContext.request.contextPath}/Product/FavouriteEdit">
                                                    <label>
                                                        <input value="${authUser.id}" name="idUser"
                                                               hidden>
                                                    </label>
                                                    <label>
                                                        <input value="${p.proID}" name="idPro" hidden>
                                                    </label>
                                                    <label>
                                                        <input value="0" name="favourite" hidden>
                                                    </label>
                                                    <label>
                                                        <input value="${f.faID}" name="idFa" hidden>
                                                    </label>
                                                </form>
                                                <form id="frmFavourite-Delete${p.proID}" method="post"
                                                      action="${pageContext.request.contextPath}/Product/FavouriteEdit">
                                                    <label>
                                                        <input value="${authUser.id}" name="idUser"
                                                               hidden>
                                                    </label>
                                                    <label>
                                                        <input value="${p.proID}" name="idPro" hidden>
                                                    </label>
                                                    <label>
                                                        <input value="1" name="favourite" hidden>
                                                    </label>
                                                    <label>
                                                        <input value="${f.faID}" name="idFa" hidden>
                                                    </label>
                                                </form>
                                            </c:if>
                                        </c:forEach>

                                        <img src="${pageContext.request.contextPath}/public/imgs/sp/${p.proID}/main.png"
                                             alt="${p.proName}" title="${p.proName}" class="card-img-top">
                                        <div class="card-body">
                                            <h6 class="card-title">${p.proName}</h6>
                                            <h5 class="card-title text-danger">
                                                <fmt:formatNumber value="${p.price}" type="number"/>
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
                                            <c:choose>
                                                <c:when test="${auth}">
                                                    <c:set var="Test" scope="session" value="${1}"/>
                                                    <c:forEach items="${favourite}" var="f">
                                                        <c:if test="${f.userID == authUser.id && f.proID == p.proID && f.favourite == 1}">
                                                            <a class="btn btn-sm btn-outline-danger"
                                                               href="javascript:$('#frmFavourite-Add${p.proID}').submit()">
                                                                <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                                                Thêm vào danh sách yêu thích
                                                                <c:set var="Test" scope="session" value="${0}"/>
                                                            </a>
                                                        </c:if>
                                                        <c:if test="${f.userID == authUser.id && f.proID == p.proID && f.favourite == 0 }">
                                                            <a class="btn btn-sm btn-outline-danger"
                                                               href="javascript:$('#frmFavourite-Delete${p.proID}').submit()">
                                                                <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                                                Xóa khỏi danh sách yêu thích
                                                                <c:set var="Test" scope="session" value="${0}"/>
                                                            </a>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${Test == 1 }">
                                                        <a class="btn btn-sm btn-outline-danger"
                                                           href="javascript:$('#frmFavourite${p.proID}').submit()">
                                                            <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                                            Thêm vào danh sách yêu thích
                                                        </a>
                                                    </c:if>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </jsp:body>
</t:main>