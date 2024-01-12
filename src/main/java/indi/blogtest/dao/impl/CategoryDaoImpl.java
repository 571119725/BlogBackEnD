package indi.blogtest.dao.impl;

import indi.blogtest.dao.CategoryDao;
import indi.blogtest.domain.SingleClass;
import indi.blogtest.domain.SingleLabel;
import indi.blogtest.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public int countAllClass() {
        String sql = "select count(*) from classList";
        return template.queryForObject(sql, Integer.class);
    }

    @Override
    public int countAllLabel() {
        String sql = "select count(*) from labelList";
        return template.queryForObject(sql, Integer.class);
    }

    @Override
    public List<SingleClass> getAllClass() {
        String sql = "select * from classList";
        return template.query(sql, new BeanPropertyRowMapper<SingleClass>(SingleClass.class));
    }

    @Override
    public List<SingleLabel> getAllLabel() {
        String sql = "select * from labelList";
        return template.query(sql, new BeanPropertyRowMapper<SingleLabel>(SingleLabel.class));
    }

    @Override
    public int increaseClassAndLabel(int cid, int lid) {
        String sql1 = "update classList set count = count + 1 where cid = ?; ";
        String sql2 = "update labelList set count = count + 1 where lid = ?;";
        template.update(sql1, cid);
        return template.update(sql2, lid);
    }

    @Override
    public int decreaseClassAndLabel(int cid, int lid) {
        String sql1 = "update classList set count = count - 1 where cid = ?;";
        String sql2 = "update labelList set count = count - 1 where lid = ?;";
        template.update(sql1, cid);
        return template.update(sql2, lid);
    }
}
