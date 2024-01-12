package indi.blogtest.dao;

import indi.blogtest.domain.User;

import java.util.List;

public interface UserDao {
    public List<User> findAll(User loginuser);
    public User checkContactInfo();
    public int updateUser(String password, String email, String qq);
}
