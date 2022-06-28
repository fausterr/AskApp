package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Post;
import service.CategoryService;
import service.PostService;
import service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/post/add")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER"),
                @HttpMethodConstraint(value = "POST", rolesAllowed = "USER")
        }
)
public class AddPostController extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    private PostService postService = new PostService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.findAndSortCategories();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/views/add-post.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post post = createPostFromRequest(req);
        postService.addPost(post);
        resp.sendRedirect(req.getContextPath());
    }

    private Post createPostFromRequest(HttpServletRequest request) {
        String loggedInUsername = request.getUserPrincipal().getName();
        userService.updateUserPostCounter(loggedInUsername);
        return new Post(
                request.getParameter("title"),
                request.getParameter("contents"),
                Integer.valueOf(request.getParameter("categoryId")),
                loggedInUsername
        );
    }
}
