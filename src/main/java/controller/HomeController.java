package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Comment;
import model.Post;
import service.CategoryService;
import service.CommentService;
import service.PostService;
import service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("")
public class HomeController extends HttpServlet {
    private PostService postService = new PostService();
    private CategoryService categoryService = new CategoryService();
    private CommentService commentService = new CommentService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = postService.findAll();
        req.setAttribute("posts", posts);
        List<Category> categories = categoryService.findAndSortCategories();
        req.setAttribute("categories", categories);
        List<Comment> comments = commentService.findAll();
        req.setAttribute("comments", comments);
        Map<String, Integer> topFiveUsers = userService.getTopFiveUsersByAsking();
        req.setAttribute("topUsers", topFiveUsers);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req,resp);
    }
}