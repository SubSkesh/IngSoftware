<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title> Products Inventory</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles.css">
    <meta charset="UTF-8">


</head>
<body>
<h1>Product Inventory</h1>
<form action="${pageContext.request.contextPath}/addProduct" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" required><br>
    <label for="price">Price:</label>
    <input type="number" step="0.01" id="price" name="price" required><br>
    <button type="submit">Add a product</button>
</form>

<h2>Product Stored:</h2>
<ul>
    <c:forEach items="${products}" var="product">
        <li>ID: ${product.id}, Name: ${product.name}, Quantity: ${product.quantity}, Price: ${product.price}
            <a href="${pageContext.request.contextPath}/deleteProduct/${product.id}">Delete</a>
            <a href="${pageContext.request.contextPath}/editProduct/${product.id}">Modify</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
