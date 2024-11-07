<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles.css">
</head>
<body>
<div class="container">
    <div class="header">

    <h2>Login</h2>
        <img src="${pageContext.request.contextPath}/static/images/logo_unife.png" alt="logo unife" style="width: 150px; height: 73px; margin-bottom: 20px;margin-bottom: 20px; ">

    </div>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit">Login</button>
    </form>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <c:if test="${not empty success}">
        <p style="color: green;">${success}</p>
    </c:if>
    <p>Not registered? <a href="${pageContext.request.contextPath}/register">Register here</a></p>
</div>
</body>
</html>
