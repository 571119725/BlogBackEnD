package indi.blogtest.service.impl;

import indi.blogtest.dao.BlogDao;
import indi.blogtest.dao.UserDao;
import indi.blogtest.dao.impl.BlogDaoImpl;
import indi.blogtest.dao.impl.UserDaoImpl;
import indi.blogtest.domain.Blog;
import indi.blogtest.domain.User;
import indi.blogtest.service.UserService;
import indi.blogtest.util.ResultUtils;
import indi.blogtest.util.TokenUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public String findAll(User loginUser) {
        List<User> list = userDao.findAll(loginUser);
        if (!list.isEmpty()) {
            return TokenUtils.generateToken();
        } else
            return "";
    }
    @Override
    public User checkContactInfo() {
        return userDao.checkContactInfo();
    }

    @Override
    public int updateUserInfo(String password, String email, String qq) {
        return userDao.updateUser(password, email, qq);
    }
}
