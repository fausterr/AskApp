<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Zaloguj się - ReadStack</title>
    <%@ include file="../segments/stylesheets.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
</head>
<body>
<div class="container">
    <%@ include file="../segments/header.jspf" %>
    <div class="form">
        <div class="form-triangle"></div>
        <h2 class="form-header">Zaloguj się</h2>
        <form action="j_security_check" method="post" class="form-container">
            <p>
                <input name="j_username" placeholder="Nazwa użytkownika" type="text" required>
            </p>
            <p>
                <input name="j_password" placeholder="Hasło" type="password" required>
            </p>
            <p>
                <input type="submit" class="submit"></input>
            </p>
            <p>Nie masz konta? <a href="${pageContext.request.contextPath}/signup">Zarejestruj się</a></p>
        </form>
    </div>
    <%@ include file="../segments/footer.jspf" %>
</div>
</body>
</html>