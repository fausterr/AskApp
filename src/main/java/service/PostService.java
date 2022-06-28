package service;

import dao.PostDao;
import dao.UserDao;
import dao.VoteDao;
import model.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostService {
    private PostDao postDao = new PostDao();
    private FavouritePostService favouritePostService = new FavouritePostService();
    private final PostMapper postMapper = new PostMapper();

    public void addPost(Post post) {
        Post postToSave = postMapper.saveMapper(post);
        postDao.save(postToSave);
    }

    public List<Post> findAll() {
        return postDao.findAll()
                .stream().map(PostMapper::getMapper)
                .collect(Collectors.toList());
    }

    public List<Post> findAllByUserId(int userId) {
        return postDao.findByUserId(userId)
                .stream().map(PostMapper::getMapper)
                .collect(Collectors.toList());
    }

    public List<Post> findAllByCategoryId(int categoryId) {
        return postDao.findByCategoryId(categoryId)
                .stream().map(PostMapper::getMapper)
                .collect(Collectors.toList());
    }

    public List<Post> findByPostId(int postId) {
        return postDao.findByPostId(postId)
                .stream().map(PostMapper::getMapper)
                .collect(Collectors.toList());
    }

    public List<Post> getFavouritedPosts(int userId) {
        List<Post> favouritePosts = new ArrayList<>();
        for(int i: favouritePostService.getFavouritesPostsId(userId)) {
            Post post = findByPostId(i).get(0);
            favouritePosts.add(post);
        }
        return favouritePosts;
    }

    private static class PostMapper {
        private static final UserDao userDao = new UserDao();
        private static final VoteDao voteDao = new VoteDao();

        static Post saveMapper(Post post) {
            return new Post(
                    post.getTitle(),
                    post.getContents(),
                    LocalDateTime.now(),
                    post.getCategoryId(),
                    userDao.findByUsername(post.getAuthor()).orElseThrow().getId()
            );
        }

        static Post getMapper(Post post) {
            return new Post(
                    post.getId(),
                    post.getTitle(),
                    post.getContents(),
                    post.getDateAdded(),
                    voteDao.countByPostId(post.getId()),
                    userDao.findById(post.getUserId()).orElseThrow().getUsername()
                );
        }
    }
}
