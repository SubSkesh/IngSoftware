<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventario Prodotti</title>
</head>
<body>
<h1>Inventario Prodotti</h1>
<form action="${pageContext.request.contextPath}/addProduct" method="post">
    <label for="name">Nome:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="quantity">Quantità:</label>
    <input type="number" id="quantity" name="quantity" required><br>
    <label for="price">Prezzo:</label>
    <input type="number" step="0.01" id="price" name="price" required><br>
    <button type="submit">Aggiungi Prodotto</button>
</form>

<h2>Prodotti in Inventario:</h2>
<ul>
    <c:forEach items="${products}" var="product">
        <li>ID: ${product.id}, Nome: ${product.name}, Quantità: ${product.quantity}, Prezzo: ${product.price}
            <a href="${pageContext.request.contextPath}/deleteProduct/${product.id}">Elimina</a>
            <a href="${pageContext.request.contextPath}/editProduct/${product.id}">Modifica</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
