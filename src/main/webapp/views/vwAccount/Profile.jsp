<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userList" scope="request" type="java.util.List<com.ute.web.beans.User>"/>
<t:main>
    <jsp:body>
        <c:forEach items="${userList}" var="u">
            <c:if test="${u.id == authUser.id}">
                <c:if test="${u.permission == 0}">
                    <div class="card">
                        <h4 class="card-header">
                            Profile
                        </h4>
                        <div class="card-body">
                            Thông tin cá nhân của Admin!
                            <div class="card mt-3">
                                <h4 class="card-header">
                                    <i class="bi bi-list"></i>
                                    Danh mục người dùng
                                </h4>
                                <div class="list-group list-group-flush">
                                    <a href="${pageContext.request.contextPath}/Admin/Permission/Index"
                                       class="list-group-item list-group-item-action"><i class="fa fa-caret-right"
                                                                                         aria-hidden="true"></i>
                                        Xem danh sách tài khoản </a>
                                    <a href="${pageContext.request.contextPath}/Admin/Product/Index"
                                       class="list-group-item list-group-item-action"><i class="fa fa-caret-right"
                                                                                         aria-hidden="true"></i> Quản lý
                                        sản phẩm</a>
                                    <a href="${pageContext.request.contextPath}/Admin/GroupCategory/Index"
                                       class="list-group-item list-group-item-action"><i class="fa fa-caret-right"
                                                                                         aria-hidden="true"></i> Quản lý Group
                                        Category</a>
                                    <a href="${pageContext.request.contextPath}/Admin/Category/Index"
                                       class="list-group-item list-group-item-action"><i class="fa fa-caret-right"
                                                                                         aria-hidden="true"></i> Quản lý
                                        Category</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${u.permission == 1}">
                    <div class="card">
                        <h4 class="card-header">
                            Profile
                        </h4>
                        <div class="card-body">
                            Thông tin cá nhân của người bán!
                            <h4 class="card-header">
                                <i class="bi bi-list"></i>
                                Danh mục người dùng

                            </h4>
                            <div class="list-group list-group-flush">
                                <a href="${pageContext.request.contextPath}/Admin/Product/Add"
                                   class="list-group-item list-group-item-action"><i class="fa fa-caret-right"
                                                                                     aria-hidden="true"></i>
                                    Tạo sản phẩm muốn bán </a>
                                <a href="${pageContext.request.contextPath}/Account/SellingList"
                                   class="list-group-item list-group-item-action"><i class="fa fa-caret-right"
                                                                                     aria-hidden="true"></i>
                                    Danh sách sản phẩm đang bán </a>
                                <a href="${pageContext.request.contextPath}/Account/WatchList"
                                   class="list-group-item list-group-item-action"><i class="fa fa-caret-right"
                                                                                     aria-hidden="true"></i>
                                    Danh sách sản phầm yêu thích</a>
<%--                                <a href="${pageContext.request.contextPath}/Admin/Product/Add"--%>
<%--                                   class="list-group-item list-group-item-action"><i class="fa fa-caret-right"--%>
<%--                                                                                     aria-hidden="true"></i>--%>
<%--                                    Danh sách sản phẩm đã được đấu giá </a>--%>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${u.permission == 3 || u.permission == 2}">
                    <div class="card">
                        <h4 class="card-header">
                            Profile
                        </h4>
                        <div class="card-body">
                            Thông tin cá nhân của người mua!
                            <div class="card mt-3">
                                <h4 class="card-header">
                                    <i class="bi bi-list"></i>
                                    Danh mục người dùng
                                </h4>
                                <form id="frmAccountPermission" method="post"
                                      action="${pageContext.request.contextPath}/Account/UpdatePermission">
                                    <label>
                                        <input value="${u.id}" name="idUser"
                                               hidden>
                                    </label>
                                    <label>
                                        <input value="2" name="permission" hidden>
                                    </label>
                                </form>
                                <div class="list-group list-group-flush">
                                    <a href="${pageContext.request.contextPath}/Account/WatchList"
                                       class="list-group-item list-group-item-action"><i class="fa fa-caret-right"
                                                                                         aria-hidden="true"></i>
                                        Danh sách sản phầm yêu thích</a>
                                    <c:choose>
                                        <c:when test="${u.permission == 2}">
                                            <a class="list-group-item list-group-item-action"><i class="fa fa-caret-right"
                                                                                                 aria-hidden="true"></i>
                                                Đang chờ duyệt yêu cầu nâng
                                                cấp thành người bán</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascript:$('#frmAccountPermission').submit()"
                                               class="list-group-item list-group-item-action"><i class="fa fa-caret-right"
                                                                                                 aria-hidden="true"></i> Yêu cầu
                                                nâng
                                                cấp thành người bán</a>
                                        </c:otherwise>
                                    </c:choose>
                                    <a href="#" class="list-group-item list-group-item-action"><i class="fa fa-caret-right"
                                                                                                  aria-hidden="true"></i> A
                                        fourth link item</a>
                                    <a href="#" class="list-group-item list-group-item-action disabled" tabindex="-1"
                                       aria-disabled="true"><i class="fa fa-caret-right" aria-hidden="true"></i> A disabled link
                                        item</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:if>
        </c:forEach>

    </jsp:body>
</t:main>