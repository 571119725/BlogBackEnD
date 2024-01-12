package indi.blogtest.dao;

import indi.blogtest.domain.Blog;
import indi.blogtest.domain.User;

import java.util.List;

public interface BlogDao {
    public int addBlog(Blog blog);
    public int editBlog(Blog blog);
    public Blog getBlog(int i);
    public int totalCount(String searchContent, int _blogClass, int _blogLabel);
    public List<Blog> findByPage(int start, int rows, String searchContent, int _blogClass, int _blogLabel);

    public int deleteBlog(Blog blog);
    public int deleteBlog(int id);
}
