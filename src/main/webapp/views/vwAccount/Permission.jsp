<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userList" scope="request" type="java.util.List<com.ute.web.beans.User>"/>
<t:main>
    <jsp:body>
        <div class="card">
            <h4 class="card-header d-flex justify-content-between">
                Danh sách tài khoản
                <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Account/Profile"
                   role="button">
                    <i class="bi bi-backspace-fill" aria-hidden="true"></i>
                    Trở về
                </a>
            </h4>
            <c:choose>
                <c:when test="${userList.size() == 0}">
                    <div class="card-body">
                        <p class="card-text">Không có dữ liệu.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Permission</th>
                                <th scope="col">&nbsp;</th>
                                <th scope="col">&nbsp;</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${userList}" var="u">
                                <tr>
                                    <form id="frmDown${u.id}" method="post"
                                          action="${pageContext.request.contextPath}/Account/UpdatePermission" hidden>
                                        <label>
                                            <input value="${u.id}" name="idUser" hidden>
                                        </label>
                                        <label>
                                            <input value="3" name="permission" hidden>
                                        </label>
                                    </form>
                                    <form id="frmUp${u.id}" method="post"
                                          action="${pageContext.request.contextPath}/Account/UpdatePermission" hidden>
                                        <label>
                                            <input value="${u.id}" name="idUser" hidden>
                                        </label>
                                        <label>
                                            <input value="1" name="permission" hidden>
                                        </label>
                                    </form>
                                    <form id="frmDelete${u.id}" method="post"
                                          action="${pageContext.request.contextPath}/Account/Delete" hidden>
                                        <label>
                                            <input value="${u.id}" name="idUser" hidden>
                                        </label>
                                    </form>
                                    <th scope="row">${u.id}</th>
                                    <td>${u.name}</td>
                                    <td>${u.email}</td>
                                    <c:if test="${u.permission == 4}">
                                        <td>Đang chờ xác nhận OTP</td>
                                    </c:if>
                                    <c:if test="${u.permission == 3}">
                                        <td>Người mua</td>
                                    </c:if>
                                    <c:if test="${u.permission == 2}">
                                        <td>Đang chờ xét thành người bán</td>
                                    </c:if>
                                    <c:if test="${u.permission == 1}">
                                        <td>Người bán</td>
                                    </c:if>
                                    <c:if test="${u.permission == 0}">
                                        <td>Admin</td>
                                    </c:if>
                                    <td class="text-right">
                                        <a class="btn btn-outline-danger"
                                           href="javascript:$('#frmDelete${u.id}').submit()" role="button">
                                            <i class="bi bi-x-lg" aria-hidden="true"></i></a>
                                    </td>
                                    <c:if test="${u.permission == 2}">
                                        <td class="text-right">
                                            <a class="btn btn-outline-info"
                                               href="javascript:$('#frmUp${u.id}').submit()" role="button">
                                                <i class="bi bi-arrow-up-square" aria-hidden="true"></i></a>
                                        </td>
                                    </c:if>
                                    <c:if test="${u.permission == 1}">
                                        <td class="text-right">
                                            <a class="btn btn-outline-info"
                                               href="javascript:$('#frmDown${u.id}').submit()" role="button">
                                                <i class="bi bi-arrow-down-square" aria-hidden="true"></i></a>
                                        </td>
                                    </c:if>
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