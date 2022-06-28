package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comment;
import service.CommentService;

import java.io.IOException;

@WebServlet("/post/commentAdd")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "POST")
        }
)
public class CommentController extends HttpServlet {
    private CommentService commentService = new CommentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Comment comment = createCommentFromRequest(req);
        commentService.addComment(comment);
        resp.sendRedirect(req.getContextPath());
    }

    private Comment createCommentFromRequest(HttpServletRequest request) {
        String loggedInUsername = request.getUserPrincipal().getName();
        return new Comment(
                Integer.valueOf(request.getParameter("postId")),
                request.getParameter("commentText"),
                loggedInUsername
        );
    }
}