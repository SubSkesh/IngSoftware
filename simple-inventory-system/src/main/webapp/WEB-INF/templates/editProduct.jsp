        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>
        <head>
            <title>Modifiy Product</title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles.css">
            <meta charset="UTF-8">


        </head>
        <body>
        <h1>Modify Product</h1>
        <form action="${pageContext.request.contextPath}/updateProduct" method="post">
            <input type="hidden" name="id" value="${product.id}">

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${product.name}" required><br>

            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" value="${product.quantity}" required><br>

            <label for="price">Price:</label>
            <input type="number" id="price" step="0.01" name="price" value="${product.price}" required><br>

            <button type="submit">Update Product</button>
        </form>
        </body>
        </html>
