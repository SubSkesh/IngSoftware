<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modify Product</title>
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

    <h2>Modify Product</h2>
    <form action="${pageContext.request.contextPath}/updateProduct" method="post" class="add-product-form">
        <input type="hidden" name="id" value="${product.id}">

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${product.name}" required>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" value="${product.quantity}" required>

        <label for="price">Price:</label>
        <input type="number" id="price" step="0.01" name="price" value="${product.price}" required>

        <button type="submit">Update Product</button>
    </form>
</div>
</body>
</html>
