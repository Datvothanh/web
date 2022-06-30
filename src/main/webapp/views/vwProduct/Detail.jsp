<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="product" scope="request" type="com.ute.web.beans.Product"/>
<t:main>
    <jsp:body>
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
                </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:main>