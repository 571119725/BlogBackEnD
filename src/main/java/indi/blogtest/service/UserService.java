package indi.blogtest.service;

import indi.blogtest.domain.User;

import java.util.List;

public interface UserService {
    public String findAll(User loginUser);
    public User checkContactInfo();
    public int updateUserInfo(String password, String email, String qq);
}
