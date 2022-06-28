package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PostVote;
import service.PostVoteService;

import java.io.IOException;

@WebServlet("/post/vote")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER")
        }
)
public class PostVoteController extends HttpServlet {
    private PostVoteService postVoteService = new PostVoteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostVote postVote = createPostVote(req);
        postVoteService.addVote(postVote);
        resp.sendRedirect(req.getContextPath());
    }

    private PostVote createPostVote(HttpServletRequest request) {
        Integer postId = Integer.valueOf(request.getParameter("id"));
        String voteType = request.getParameter("type");
        String username = request.getUserPrincipal().getName();
        PostVote postVote = new PostVote(username, postId, voteType);
        return postVote;
    }
}