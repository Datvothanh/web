<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="product" scope="request" type="com.ute.web.beans.Product"/>
<jsp:useBean id="favourite" scope="request" type="java.util.List<com.ute.web.beans.Favourite>"/>
<jsp:useBean id="user" scope="request" type="java.util.List<com.ute.web.beans.User>"/>
<jsp:useBean id="TopAuctionHighestPrice" scope="request" type="java.util.List<com.ute.web.beans.Auction>"/>
<t:main>
    <jsp:body>
        <form id="frmFavourite${product.proID}" method="post"
              action="${pageContext.request.contextPath}/Product/Favourite" hidden>
            <label>
                <input value="${authUser.id}" name="idUser" hidden>
            </label>
            <label>
                <input value="${product.proID}" name="idPro" hidden>
            </label>
            <label>
                <input value="1" name="favourite" hidden>
            </label>
        </form>
        <c:forEach items="${favourite}" var="f">
            <c:if test="${f.userID == authUser.id && f.proID == product.proID}">
                <form id="frmFavourite-Add${product.proID}" method="post"
                      action="${pageContext.request.contextPath}/Product/FavouriteEdit" hidden>
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
                <form id="frmFavourite-Delete${product.proID}" method="post"
                      action="${pageContext.request.contextPath}/Product/FavouriteEdit" hidden>
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
            </c:if>
        </c:forEach>
        <div class="card">
            <h4 class="card-header">
                    ${product.proName}
            </h4>
            <div class="card-body">
                <div class="col-sm-4 mb-3">
                    <div class="card h-100">
                        <img src="${pageContext.request.contextPath}/public/imgs/sp/${product.proID}/main.jpg"
                             alt="${product.proName}" title="${product.proName}"/>
                        <h6 class="card-title">${product.proName}</h6>
                        <c:if test="${p.nowPrice != 0}">
                            <h5 class="card-title text-danger">
                                Gía có thể mua luôn:
                                <span class="text-danger font-weight-bold"><fmt:formatNumber
                                        value="${product.nowPrice}" type="number"/></span>
                            </h5>
                        </c:if>
                        <c:choose>
                            <c:when test="${product.highestPaidPrice == 0}">
                                <p class="card-text mt-3">
                                    Giá khởi điểm:
                                    <span class="text-danger font-weight-bold"><fmt:formatNumber
                                            value="${product.startingPrice}" type="number"/></span>
                                </p>
                            </c:when>
                            <c:otherwise>
                                <h5 class="card-title text-danger">
                                    <p class="card-text mt-3">
                                        Giá trả cao nhất:
                                        <span class="text-danger font-weight-bold"><fmt:formatNumber
                                                value="${product.highestPaidPrice}" type="number"/></span>
                                    </p>
                                </h5>
                                <h6 class="card-title text-danger">
                                    <c:forEach items="${user}" var="u">
                                        <c:if test="${u.id == product.userID}">
                                            Người đặt giá: ${u.name}
                                        </c:if>
                                    </c:forEach>
                                </h6>
                            </c:otherwise>
                        </c:choose>
                        <h6 class="card-title text-dark">
                            Ngày đăng sản phẩm: ${product.startDay}
                        </h6>
                        <p class="card-text">${product.fullDes}</p>
                        <c:choose>
                            <c:when test="${auth}">
                                <form id="frmAuction${product.proID}" method="post"
                                      action="${pageContext.request.contextPath}/Product/Auction">
                                    <label>
                                        <input value="${authUser.id}" name="idUser" hidden>
                                    </label>
                                    <label>
                                        <input value="${product.proID}" name="idPro" hidden>
                                    </label>
                                    <label>
                                        <input name="price">
                                    </label>
                                </form>
                                <a class="btn btn-sm btn-outline-danger"
                                   href="javascript:$('#frmAuction${product.proID}').submit()">
                                    <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                    Đấu giá
                                </a>
                                <c:set var="Test" scope="session" value="${1}"/>
                                <c:forEach items="${favourite}" var="f">
                                    <c:if test="${f.userID == authUser.id && f.proID == product.proID && f.favourite == 0}">
                                        <a class="btn btn-sm btn-outline-danger"
                                           href="javascript:$('#frmFavourite-Add${product.proID}').submit()">
                                            <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                            Thêm vào danh sách yêu thích
                                            <c:set var="Test" scope="session" value="${0}"/>
                                        </a>
                                    </c:if>
                                    <c:if test="${f.userID == authUser.id && f.proID == product.proID && f.favourite == 1 }">
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
            <div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Date</th>
                        <th scope="col">Name</th>
                        <th scope="col">Price</th>

                    </tr>
                    </thead>
                    <tbody>
                        <c:set var="STT" scope="session" value="${1}"/>
                    <c:forEach items="${TopAuctionHighestPrice}" var="a">
                    <c:forEach items="${user}" var="u">
                    <c:choose>
                    <c:when test="${product.proID == a.proID && a.userID == u.id}">
                    <tr>
                        <th scope="row">${STT}</th>
                        <td>Mark</td>
                        <td>${u.name}</td>
                        <td>${a.price}</td>
                        <c:set var="STT" scope="session" value="${STT + 1}"/>
                    </tr>
                    </c:when>
                    </c:choose>
                    </c:forEach>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="card-footer text-muted">
        </div>
    </jsp:body>
</t:main>