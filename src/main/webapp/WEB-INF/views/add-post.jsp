<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Zarejestruj się - ask</title>
    <%@ include file="../segments/stylesheets.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/add-post-form.css">
</head>
<div class="container">
    <%@ include file="../segments/header.jspf" %>

    <form action="${pageContext.request.contextPath}/post/add" method="post" class="post-form">
        <h2 class="post-form-title">Dodaj nowy post</h2>
        <input name="title" placeholder="Tytuł" required>
        <select name="categoryId">
            <c:forEach var="category" items="${requestScope.categories}">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
        <textarea name="contents" placeholder="Opis"></textarea>
        <button class="post-form-button">Dodaj</button>
    </form>

    <%@ include file="../segments/footer.jspf" %>
</div>
</body>
</html>