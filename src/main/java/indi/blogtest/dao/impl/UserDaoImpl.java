package indi.blogtest.dao.impl;

import indi.blogtest.dao.UserDao;
import indi.blogtest.domain.User;
import indi.blogtest.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public List<User> findAll(User loginUser) {
        String sql = "select * from loginUser where username = ? and password = ?";
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
    }
    @Override
    public User checkContactInfo() {
        String sql = "select * from loginUser where username=?";
        return template.queryForObject(
                sql,
                new BeanPropertyRowMapper<User>(User.class),
                "caozheng"
        );
    }

    @Override
    public int updateUser(String password, String email, String qq) {
        String temp = "update loginUser set id = 1 ";
        StringBuilder sb = new StringBuilder(temp);
        List<String> params = new ArrayList<>();
        if(password != null && !password.isEmpty()){
            sb.append(", password = ? ");
            params.add(password);
        }
        if(email != null && !email.isEmpty()){
            sb.append(", email = ? ");
            params.add(email);
        }
        if(qq != null && !qq.isEmpty()){
            sb.append(", qq = ? ");
            params.add(qq);
        }
        sb.append("where id = 1");
        String sql = sb.toString();
        return template.update(sql, params.toArray());
    }
}
