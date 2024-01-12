package indi.blogtest.dao.impl;

import indi.blogtest.dao.BlogDao;
import indi.blogtest.domain.Blog;
import indi.blogtest.util.JdbcUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class BlogDaoImpl implements BlogDao {
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
    @Override
    public int addBlog(Blog blog) {
        if(blog.getId() == -1){
            String sql = "insert into blogList " +
                    "(blogTitle, blogIntro, blogContent, blogClass, blogLabel, uploadDate) " +
                    "values(?, ?, ?, ? , ?, ?)";
            return template.update(sql,
                    blog.getBlogTitle(),
                    blog.getBlogIntro(),
                    blog.getBlogContent(),
                    blog.getBlogClass(),
                    blog.getBlogLabel(),
                    blog.getUploadDate());
        }else{
            String sql = "insert into blogList " +
                    "(id, blogTitle, blogIntro, blogContent, blogClass, blogLabel, uploadDate) " +
                    "values(?, ?, ?, ? ,? , ?, ?)";
            return template.update(sql,
                    blog.getId(),
                    blog.getBlogTitle(),
                    blog.getBlogIntro(),
                    blog.getBlogContent(),
                    blog.getBlogClass(),
                    blog.getBlogLabel(),
                    blog.getUploadDate());
        }
    }
    @Override
    public int editBlog(Blog blog) {
        String sql = "update blogList " +
                "set blogTitle = ?, blogIntro = ?, blogContent = ?, blogClass = ?, blogLabel = ?, uploadDate = ? where id = ?";
        return template.update(sql,
                blog.getBlogTitle(),
                blog.getBlogIntro(),
                blog.getBlogContent(),
                blog.getBlogClass(),
                blog.getBlogLabel(),
                blog.getUploadDate(),
                blog.getId());
    }
    @Override
    public Blog getBlog(int i) {
        String sql = "select * from blogList where id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Blog>(Blog.class), i);
    }
    @Override
    public int totalCount(String searchContent, int blogClass, int blogLabel){
        String temp = "select count(*) from blogList where 1=1 ";
        StringBuilder sb = new StringBuilder(temp);
        List<Object> params = new ArrayList<>();
        if(searchContent != null && !searchContent.isEmpty()){
            sb.append("and blogTitle like ? or blogIntro like ? ");
            params.add("%" + searchContent + "%");
            params.add("%" + searchContent + "%");
        }
        if(blogClass != -1){
            sb.append("and blogClass = ? ");
            params.add(blogClass);
        }
        if(blogLabel != -1){
            sb.append("and blogLabel = ? ");
            params.add(blogLabel);
        }
        String sql = sb.toString();
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Blog> findByPage(int start, int rows, String searchContent, int blogClass, int blogLabel) {
        String temp = "select * from blogList where 1=1 ";
        StringBuilder sb = new StringBuilder(temp);
        List<Object> params = new ArrayList<>();
        if(searchContent != null && !searchContent.isEmpty()){
            sb.append("and (blogTitle like ? or blogIntro like ?) ");
            params.add("%" + searchContent + "%");
            params.add("%" + searchContent + "%");
        }
        if(blogClass != -1){
            sb.append("and blogClass = ? ");
            params.add(blogClass);
        }
        if(blogLabel != -1){
            sb.append("and blogLabel = ? ");
            params.add(blogLabel);
        }
        sb.append("order by id desc limit ?, ?");
        String sql = sb.toString();
        params.add(start);
        params.add(rows);
        return template.query(sql, new BeanPropertyRowMapper<Blog>(Blog.class), params.toArray());
    }

    @Override
    public int deleteBlog(Blog blog) {
        String sql = "delete from blogList where id = ?";
        return template.update(sql, blog.getId());
    }
    @Override
    public int deleteBlog(int id) {
        String sql = "delete from blogList where id = ?";
        return template.update(sql, id);
    }
}
