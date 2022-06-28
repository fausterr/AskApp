package service;

import dao.FavouritePostDao;
import dao.UserDao;
import model.FavouritePost;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FavouritePostService {
    FavouritePostDao favouritePostDao = new FavouritePostDao();
    FavouritePostMapper favouritePostMapper = new FavouritePostMapper();

    public void addPost(FavouritePost favouritePost) {
        favouritePostDao.save(favouritePostMapper.map(favouritePost));
    }
    
    public List<Integer> getFavouritesPostsId(int userId) {
        List<Integer> allPostId = new ArrayList<>();
        for(FavouritePost post: favouritePostDao.getFavouritePostByUserId(userId)) {
            allPostId.add(post.getPostId());
        }
        return allPostId;
    }

    private static class FavouritePostMapper {
        private final UserDao userDao = new UserDao();

        FavouritePost map(FavouritePost favouritePost) {
            Optional<User> user = userDao.findByUsername(favouritePost.getUsername());
            return new FavouritePost(
                    user.orElseThrow().getId(),
                    favouritePost.getPostId()
            );
        }
    }
}
