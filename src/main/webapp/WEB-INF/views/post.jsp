<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Moj profil</title>
    <%@ include file="../segments/stylesheets.jspf" %>
</head>
<body>
<div class="container">
    <%@ include file="../segments/header.jspf" %>

    <c:forEach var="post" items="${requestScope.post}">
        <article class="post">
            <h2 class="post-header"><c:out value="${post.title}"/></h2>
            <p class="post-details">Dodane
                przez: ${post.author}, ${post.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
            <p><c:out value="${post.contents}"/></p>
        </article>
    </c:forEach>

    <%@ include file="../segments/footer.jspf" %>
</div>
</body>
</html>
