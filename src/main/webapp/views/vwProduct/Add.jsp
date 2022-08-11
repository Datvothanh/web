<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:main>
    <jsp:body>
        <form action="" method="post">
            <div class="card">
                <h4 class="card-header">
                    New Product
                </h4>
                <div class="card-body">
                    <div class="form-group">
                        <label for="txtProName">Product</label>
                        <input type="text" class="form-control" id="txtProName" name="ProName" autofocus>
                    </div>
                    <div class="form-group">
                        <label for="numPrice">Price</label>
                        <input type="number" class="form-control" id="numPrice" name="Price">
                    </div>
                    <div class="form-group">
                        <label for="numQuantity">Quantity</label>
                        <input type="number" class="form-control" id="numQuantity" name="Quantity">
                    </div>
                    <div class="form-group">
                        <label for="numCatID">Type (1:... ; 2:....)</label>
                        <input type="number" class="form-control" id="numCatID" name="CatID">
                    </div>
                    <div class="form-group">
                        <label for="txtTinyDes">TinyDes</label>
                        <input type="text" class="form-control" id="txtTinyDes" name="TinyDes">
                    </div>
                    <div class="form-group">
                        <label for="txtFullDes">FullDes</label>
                        <input type="text" class="form-control" id="txtFullDes" name="FullDes">
                    </div>
                </div>
                <div class="card-footer">
                    <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Admin/Product/"
                       role="button">
                        <i class="fa fa-backward" aria-hidden="true"></i>
                        List
                    </a>
                    <c:choose>
                        <c:when test="">
                            <button type="submit" class="btn btn-primary">
                                <i class="fa fa-check" aria-hidden="true"></i>
                                Save
                            </button>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form>
    </jsp:body>
</t:main>