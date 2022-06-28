package service;

import dao.UserDao;
import model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class UserService {

    private UserDao userDao = new UserDao();

    public void register(User user) {
        try {
            hashPassword(user);
            userDao.save(user);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    //sha256
    private void hashPassword(User user) throws NoSuchAlgorithmException{
        String sha256Password = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(sha256Password);
    }

    public int returnUserId(String username) {
        Optional<User> user = userDao.findByUsername(username);
        return user.orElseThrow().getId();
    }

    public void updateUserPostCounter(String username) {
        userDao.updateUserPostCounter(username);
    }

    public Map<String, Integer> getTopFiveUsersByAsking() {
        Map<String, Integer> map = userDao.findAllByPostCounter().
                stream().collect
                        (Collectors.toMap(User::getUsername, User::getPostCounter));
        return map;
    }
}