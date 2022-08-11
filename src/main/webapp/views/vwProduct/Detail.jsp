<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="product" scope="request" type="com.ute.web.beans.Product"/>
<jsp:useBean id="favourite" scope="request" type="java.util.List<com.ute.web.beans.Favourite>"/>
<t:main>
    <jsp:body>
        <form id="frmFavourite${product.proID}" method="post"
              action="${pageContext.request.contextPath}/Product/Favourite">
            <label>
                <input value="${authUser.id}" name="idUser" hidden>
            </label>
            <label>
                <input value="${product.proID}" name="idPro" hidden>
            </label>
            <label>
                <input value="0" name="favourite" hidden>
            </label>
        </form>
        <c:forEach items="${favourite}" var="f">
            <c:if test="${f.userID == authUser.id && f.proID == product.proID}">
                <form id="frmFavourite-Add${product.proID}" method="post"
                      action="${pageContext.request.contextPath}/Product/FavouriteEdit">
                    <label>
                        <input value="${authUser.id}" name="idUser"
                               hidden>
                    </label>
                    <label>
                        <input value="${product.proID}" name="idPro" hidden>
                    </label>
                    <label>
                        <input value="0" name="favourite" hidden>
                    </label>
                    <label>
                        <input value="${f.faID}" name="idFa" hidden>
                    </label>
                </form>
                <form id="frmFavourite-Delete${product.proID}" method="post"
                      action="${pageContext.request.contextPath}/Product/FavouriteEdit">
                    <label>
                        <input value="${authUser.id}" name="idUser"
                               hidden>
                    </label>
                    <label>
                        <input value="${product.proID}" name="idPro" hidden>
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
        <div class="card">
            <h4 class="card-header">
                    ${product.proName}
            </h4>
            <div class="card-body">
                <div class="col-sm-4 mb-3">
                <div class="card h-100">
                <img src="${pageContext.request.contextPath}/public/imgs/sp/${product.proID}/main.png"
                     alt="${product.proName}" title="${product.proName}"/>
                <p class="card-text mt-3">
                    Gía bán:
                    <span class="text-danger font-weight-bold">
                        <fmt:formatNumber value="${product.price}" type="number"/>
                    </span>
                </p>
                <p class="card-text">Tồn kho: ${product.quantity}</p>
                <p class="card-text">${product.fullDes}</p>
                    <div class="card-footer text-muted">
                        <c:choose>
                            <c:when test="${auth}">
                                <c:set var="Test" scope="session" value="${1}"/>
                                <c:forEach items="${favourite}" var="f">
                                    <c:if test="${f.userID == authUser.id && f.proID == product.proID && f.favourite == 1}">
                                        <a class="btn btn-sm btn-outline-danger"
                                           href="javascript:$('#frmFavourite-Add${product.proID}').submit()">
                                            <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                            Thêm vào danh sách yêu thích
                                            <c:set var="Test" scope="session" value="${0}"/>
                                        </a>
                                    </c:if>
                                    <c:if test="${f.userID == authUser.id && f.proID == product.proID && f.favourite == 0 }">
                                        <a class="btn btn-sm btn-outline-danger"
                                           href="javascript:$('#frmFavourite-Delete${product.proID}').submit()">
                                            <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                            Xóa khỏi danh sách yêu thích
                                            <c:set var="Test" scope="session" value="${0}"/>
                                        </a>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${Test == 1 }">
                                    <a class="btn btn-sm btn-outline-danger"
                                       href="javascript:$('#frmFavourite${product.proID}').submit()">
                                        <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                        Thêm vào danh sách yêu thích
                                    </a>
                                </c:if>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:main>