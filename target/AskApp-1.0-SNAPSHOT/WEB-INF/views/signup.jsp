<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Zarejestruj się - ReadStack</title>
    <%@ include file="../segments/stylesheets.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
</head>
<body>
<div class="container">
    <%@ include file="../segments/header.jspf" %>

    <div class="form">
        <div class="form-triangle"></div>
        <h2 class="form-header">Zarejestruj się</h2>
        <form action="${pageContext.request.contextPath}/signup" method="post" class="form-container">
            <p>
                <input name="username" placeholder="Nazwa użytkownika" type="text" required>
            </p>
            <p>
                <input name="email" placeholder="Email" type="email" required>
            </p>
            <p>
                <input name="password" placeholder="Hasło" type="password" required>
            </p>
            <p>
                <input name="firstname" placeholder="Imię" type="text" required>
            </p>
            <p>
                <input name="lastname" placeholder="Nazwisko" type="text" required>
            </p>
            <p>
                <input type="submit" class="submit"></input>
            </p>

        </form>
    </div>
    <%@ include file="../segments/footer.jspf" %>
</div>
</body>
</html>