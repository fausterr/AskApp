<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Askeeeee</title>
    <%@ include file="../segments/stylesheets.jspf" %>
</head>
<body>
<div class="left-label">
    <p>
    <h2>Najczęściej pytający:</h2>
    <ul>
        <c:forEach var="user" items="${requestScope.topUsers}">
            <li>${user.key} ${user.value}</li>
        </c:forEach>
    </ul>
    </p>
</div>

<div class="container">
    <%@ include file="../segments/header.jspf" %>
    <aside class="categories">
        <ul>
            <c:forEach var="category" items="${requestScope.categories}">
                <li>
                    <a href="${pageContext.request.contextPath.concat('/category?id=').concat(category.id)}">${category.name}</a>
                </li>
            </c:forEach>
        </ul>
    </aside>

    <main>
        <c:forEach var="post" items="${requestScope.posts}">
            <article class="post">
                <h2 class="post-header"><a href="${pageContext.request.contextPath.concat('/post?id=').concat(post.id)}"><c:out value="${post.title}"></c:out></a>
                    <a href="${pageContext.request.contextPath.concat('/post/addfavouritepost?id=').concat(post.id)}" class="add-to-favourite">
                        <i class="fa fa-anchor" aria-hidden="true"></i>
                    </a>
                </h2>
                <p class="post-details">
                    Dodane przez: ${post.author}, ${post.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}
                </p>
                <p class="post-content">
                    <c:out value="${post.contents}"></c:out>
                </p>
                <section class="post-bar">
                    <a href="${pageContext.request.contextPath.concat('/post/vote?id=').concat(post.id).concat('&type=UP')}"
                       class="post-link upvote">
                        <i class="far fa-thumbs-up post-upvote"></i>
                    </a>
                    <p class="post-votes">${post.voteCount}</p>
                    <a href="${pageContext.request.contextPath.concat('/post/vote?id=').concat(post.id).concat('&type=DOWN')}"
                       class="post-link downvote">
                        <i class="far fa-thumbs-down post-downvote"></i>
                    </a>
                </section>
                <section class="comment">
                    <c:forEach var="comment" items="${requestScope.comments}">
                        <c:if test="${post.id == comment.postId}">
                            <p class="post-details">
                                Dodane przez <c:out value="${comment.userId}, "/> ${comment.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}
                            </p>
                            <p class="comment-text">
                                <c:out value="${comment.commentText}"></c:out>
                            </p>
                        </c:if>
                    </c:forEach>
                </section>

                <p class="add-comment">
                    <form action="${pageContext.request.contextPath}/post/commentAdd" method="post" class="comment-form">
                        <textarea name="commentText" placeholder="dodaj komentarz"></textarea>
                        <input type="hidden" name="postId" value="${post.id}">
                        <button class="comment-form-button">Dodaj</button>
                    </form>
                </p>
            </article>
        </c:forEach>
        <a href="${pageContext.request.contextPath}/post/add" class="post-add-button">
            <i class="far fa-comment-alt fa-lg">&nbsp;ZAPYTAJ</i>
        </a>
    </main>
    <%@ include file="../segments/footer.jspf" %>
</div>
</body>
</html>
