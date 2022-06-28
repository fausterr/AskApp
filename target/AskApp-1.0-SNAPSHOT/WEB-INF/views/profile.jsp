<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Moj profil</title>
    <%@ include file="../segments/stylesheets.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/login-form.css">
</head>
<body>
<div class="container">
    <%@ include file="../segments/header.jspf" %>
    <h1>WITAJ <c:out value="${requestScope.user.firstname}"></c:out></h1>
    <h1>TWOJ MAIL: <c:out value="${requestScope.user.email}"></c:out></h1>
    <h2>tutaj moje posty</h2>
    <c:forEach var="post" items="${requestScope.posts}">
        <article class="post">
            <h2 class="post-header"><a href="${pageContext.request.contextPath.concat('/post?id=').concat(post.id)}"><c:out value="${post.title}"/></a></h2>
            <p class="post-details">Dodane
                przez: ${post.author}, ${post.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
            <p><c:out value="${post.contents}"/></p>
        </article>
    </c:forEach>
    <br>
    <br>
    <h2> ulubione posty</h2>
    <c:forEach var="post" items="${requestScope.fav_posts}">
        <article class="post">
            <h2 class="post-header"><a href="${pageContext.request.contextPath.concat('/post?id=').concat(post.id)}"><c:out value="${post.title}"/></a></h2>
            <p class="post-details">Dodane
                przez: ${post.author}, ${post.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
            <p><c:out value="${post.contents}"/></p>
        </article>
    </c:forEach>
    <%@ include file="../segments/footer.jspf" %>


</div>
</body>
</html>
