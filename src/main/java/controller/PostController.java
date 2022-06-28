package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Post;
import service.PostService;

import java.io.IOException;
import java.util.List;

@WebServlet("/post")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER")
        }
)
public class PostController extends HttpServlet {
    PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.valueOf(req.getParameter("id"));
        List<Post> post = postService.findByPostId(postId);
        req.setAttribute("post", post);
        req.getRequestDispatcher("/WEB-INF/views/post.jsp").forward(req,resp);
    }
}
