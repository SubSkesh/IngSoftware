<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Inventory</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles.css">
    <meta charset="UTF-8">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Product Inventory</h1>
        <div class="user-info">
            <span>Logged in as: <strong>${pageContext.request.userPrincipal.name}</strong></span>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
        </div>
    </div>

    <fmt:setLocale value="it_IT" />

    <form id="addProductForm" action="${pageContext.request.contextPath}/addProduct" method="post" class="add-product-form">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required>

        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price" required>

        <button type="submit">Add Product</button>
    </form>

    <h2>Stored Products:</h2>
    <ul class="product-list">
        <c:forEach items="${products}" var="product">
            <li>
                ID: ${product.id},
                Name: ${product.name},
                Quantity: ${product.quantity},
                Price: <fmt:formatNumber value="${product.price}" pattern="#,##0.00" /> &euro;
                <div>
                    <a href="${pageContext.request.contextPath}/deleteProduct/${product.id}">Delete</a>
                    <a href="${pageContext.request.contextPath}/editProduct/${product.id}">Modify</a>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
