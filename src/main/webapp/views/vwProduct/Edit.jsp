<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="product" scope="request" type="com.ute.web.beans.Product"/>
<t:main>
    <jsp:body>
        <form action="" method="post">
            <div class="card">
                <h4 class="card-header">
                    Product
                </h4>
                <div class="card-body">
                    <div class="form-group">
                        <label for="txtProID">#</label>
                        <input type="text" class="form-control" id="txtProID" name="ProID" readonly value="${product.proID}">
                    </div>
                    <div class="form-group">
                        <label for="txtProName">Product</label>
                        <input type="text" class="form-control" id="txtProName" name="ProName" autofocus value="${product.proName}">
                    </div>
                    <div class="form-group">
                        <label for="numPrice">Price</label>
                        <input type="number" class="form-control" id="numPrice" name="Price" value="${product.price}">
                    </div>
                    <div class="form-group">
                        <label for="numQuantity">Quantity</label>
                        <input type="number" class="form-control" id="numQuantity" name="Quantity" value="${product.quantity}">
                    </div>
                    <div class="form-group">
                        <label for="numCatID">Type (1:... ; 2:....)</label>
                        <input type="number" class="form-control" id="numCatID" name="CatID" value="${product.catID}">
                    </div>
                    <div class="form-group">
                        <label for="txtTinyDes">TinyDes</label>
                        <input type="text" class="form-control" id="txtTinyDes" name="TinyDes" value="${product.tinyDes}">
                    </div>
                    <div class="form-group">
                        <label for="txtFullDes">FullDes</label>
                        <input type="text" class="form-control" id="txtFullDes" name="FullDes" value="${product.fullDes}">
                    </div>
                </div>
                <div class="card-footer">
                    <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Admin/Product/" role="button">
                        <i class="fa fa-backward" aria-hidden="true"></i>
                        List
                    </a>
                    <button type="submit" class="btn btn-danger" formaction="${pageContext.request.contextPath}/Admin/Product/Delete">
                        <i class="fa fa-trash-o" aria-hidden="true"></i>
                        Delete
                    </button>
                    <button type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/Admin/Product/Update">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Save
                    </button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:main>