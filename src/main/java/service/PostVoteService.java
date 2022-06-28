package service;

import dao.UserDao;
import dao.VoteDao;
import model.PostVote;
import model.User;
import model.Vote;

import java.time.LocalDateTime;
import java.util.Optional;

public class PostVoteService {
    private VoteDao voteDao = new VoteDao();
    private PostVoteMapper postVoteMapper = new PostVoteMapper();

    public void addVote(PostVote postVote) {
        Vote voteToSave = postVoteMapper.map(postVote);
        voteDao.save(voteToSave);
    }

    private static class PostVoteMapper {
        private final UserDao userDao = new UserDao();

        Vote map(PostVote vote) {
            Optional<User> user = userDao.findByUsername(vote.getUsername());
            return new Vote(
                    user.orElseThrow().getId(),
                    vote.getPostId(),
                    Vote.Type.valueOf(vote.getType()),
                    LocalDateTime.now()
            );
        }

    }
}
