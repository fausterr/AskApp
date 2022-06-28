package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FavouritePost;
import service.FavouritePostService;
import service.UserService;

import java.io.IOException;

@WebServlet("/post/addfavouritepost")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER")
        }
)
public class FavouritePostController extends HttpServlet {

    private FavouritePostService favouritePostService = new FavouritePostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FavouritePost favouritePost = createFavouritePost(req);
        favouritePostService.addPost(favouritePost);
        resp.sendRedirect(req.getContextPath());
    }

    private FavouritePost createFavouritePost(HttpServletRequest request) {
        Integer postId = Integer.valueOf(request.getParameter("id"));
        String username = request.getUserPrincipal().getName();
        FavouritePost favouritePost = new FavouritePost(postId, username);
        return favouritePost;
    }
}