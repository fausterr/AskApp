package service;

import dao.CommentDao;
import dao.UserDao;
import model.Comment;
import model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CommentService {
    private CommentDao commentDao = new CommentDao();
    private CommentMapper commentMapper = new CommentMapper();

    public void addComment(Comment comment) {
        commentDao.save(commentMapper.map(comment));
    }

    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    private static class CommentMapper  {
        private final UserDao userDao = new UserDao();

        Comment map(Comment comment) {
            Optional<User> user = userDao.findByUsername(comment.getUsername());
            return new Comment(
                    user.orElseThrow().getId(),
                    comment.getPostId(),
                    comment.getCommentText(),
                    LocalDateTime.now()
            );
        }
    }
}
