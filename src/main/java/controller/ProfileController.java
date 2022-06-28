package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Post;
import model.User;
import service.PostService;
import service.ProfileService;
import service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER")
        }
)
public class ProfileController extends HttpServlet {
    private ProfileService profileService = new ProfileService();
    private PostService postService = new PostService();
    private UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loggedInUsername = req.getUserPrincipal().getName();
        User user = profileService.getUserFromUsername(loggedInUsername);
        req.setAttribute("user", user);
        List<Post> userPosts = postService.findAllByUserId(userService.returnUserId(loggedInUsername));
        req.setAttribute("posts", userPosts);
        List<Post> favouritePosts = postService.getFavouritedPosts(user.getId());
        req.setAttribute("fav_posts", favouritePosts);
        req.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
    }
}
