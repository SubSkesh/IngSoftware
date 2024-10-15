<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifica Prodotto</title>
</head>
<body>
<h1>Modifica Prodotto</h1>
<form action="${pageContext.request.contextPath}/updateProduct" method="post">
    <input type="hidden" name="id" value="${product.id}">

    <label for="name">Nome:</label>
    <input type="text" id="name" name="name" value="${product.name}" required><br>

    <label for="quantity">Quantità:</label>
    <input type="number" id="quantity" name="quantity" value="${product.quantity}" required><br>

    <label for="price">Prezzo:</label>
    <input type="number" id="price" step="0.01" name="price" value="${product.price}" required><br>

    <button type="submit">Aggiorna Prodotto</button>
</form>
</body>
</html>
