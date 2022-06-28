package service;

import dao.UserDao;
import model.User;

import java.util.Optional;

public class ProfileService {

    private UserDao userDao = new UserDao();

    public User getUserFromUsername(String username) {
        Optional<User> user = userDao.findByUsername(username);
        return user.orElseThrow();
    }
}