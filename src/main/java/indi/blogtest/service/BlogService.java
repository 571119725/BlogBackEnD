package indi.blogtest.service;

import indi.blogtest.domain.Blog;
import indi.blogtest.domain.PageBean;

import java.util.Map;

public interface BlogService {
    public int addBlog(Blog blog);
    public int editBlog(Blog blog);
    public PageBean<Blog> getBlogList(String currentPage, String rows, String searchContent, int blogClass, int blogLabel);
    public Map<String, Object> getBlog(int id);
    public int deleteBlog(int id);
}
