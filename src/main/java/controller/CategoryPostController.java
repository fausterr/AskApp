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

@WebServlet("/category")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER")
        }
)
public class CategoryPostController extends HttpServlet {
    private PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("id"));
        List<Post> posts = postService.findAllByCategoryId(categoryId);
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/WEB-INF/views/posts-by-category.jsp").forward(req,resp);
    }
}
