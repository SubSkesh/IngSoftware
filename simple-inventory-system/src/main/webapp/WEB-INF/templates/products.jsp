<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Inventory</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles.css">
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            // Aggiungi prodotto tramite AJAX
            $('#addProductForm').on('submit', function(event) {
                event.preventDefault();
                var formData = {
                    name: $('#name').val(),
                    quantity: $('#quantity').val(),
                    price: $('#price').val()
                };
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/api/products",
                    data: JSON.stringify(formData),
                    contentType: "application/json",
                    success: function(response) {
                        alert('Prodotto aggiunto con successo!');
                        location.reload();
                    },
                    error: function() {
                        alert('Errore nell\'aggiunta del prodotto.');
                    }
                });
            });

            // Eliminare prodotto tramite AJAX
            $(document).on('click', '.deleteProduct', function(event) {
                event.preventDefault();
                var productId = $(this).data('id');
                $.ajax({
                    type: "DELETE",
                    url: "${pageContext.request.contextPath}/api/products/" + productId,
                    success: function(response) {
                        alert('Prodotto eliminato con successo!');
                        location.reload();
                    },
                    error: function() {
                        alert('Errore nell\'eliminazione del prodotto.');
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="container">
    <h1>Product Inventory</h1>
    <form id="addProductForm">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br>
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required><br>
        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price" required><br>
        <button type="submit">Add Product</button>
    </form>

    <h2>Stored Products:</h2>
    <ul>
        <c:forEach items="${products}" var="product">
            <li>ID: ${product.id}, Name: ${product.name}, Quantity: ${product.quantity}, Price: ${product.price}
                <a href="#" class="deleteProduct" data-id="${product.id}">Delete</a>
                <a href="${pageContext.request.contextPath}/editProduct/${product.id}">Modify</a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
