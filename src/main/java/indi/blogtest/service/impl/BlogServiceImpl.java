package indi.blogtest.service.impl;

import indi.blogtest.dao.BlogDao;
import indi.blogtest.dao.CategoryDao;
import indi.blogtest.dao.impl.BlogDaoImpl;
import indi.blogtest.dao.impl.CategoryDaoImpl;
import indi.blogtest.domain.Blog;
import indi.blogtest.domain.PageBean;
import indi.blogtest.service.BlogService;
import indi.blogtest.util.CheckObjectIsNullUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogServiceImpl implements BlogService {
    private BlogDao blogDao = new BlogDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public int addBlog(Blog blog) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        blog.setUploadDate(sdf.format(date));
        boolean flag = CheckObjectIsNullUtils.checkObjectIsNull(blog);
        if(!flag){
            return -1;
        }
//        System.out.println("in: " + Integer.parseInt(blog.getBlogClass()) + " " +  Integer.parseInt(blog.getBlogLabel()));
        categoryDao.increaseClassAndLabel(Integer.parseInt(blog.getBlogClass()), Integer.parseInt(blog.getBlogLabel()));
        return blogDao.addBlog(blog);
    }
    @Override
    public int editBlog(Blog blog) {
        boolean flag = CheckObjectIsNullUtils.checkObjectIsNull(blog);
        if (!flag) {
            return -1;
        }
        Blog oldBlog = blogDao.getBlog(blog.getId());
//        System.out.println("de: " + Integer.parseInt(oldBlog.getBlogClass()) + "  " + Integer.parseInt(oldBlog.getBlogLabel()));
        categoryDao.decreaseClassAndLabel(Integer.parseInt(oldBlog.getBlogClass()), Integer.parseInt(oldBlog.getBlogLabel()));
        blogDao.deleteBlog(oldBlog);
        return addBlog(blog);
    }

    @Override
    public PageBean<Blog> getBlogList(String _currentPage, String _rows, String searchContent, int _blogClass, int _blogLabel) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        int totalCount = blogDao.totalCount(searchContent, _blogClass, _blogLabel);
        int totalPage = (int) Math.ceil(totalCount / (double) rows);
        int start = (currentPage - 1) * rows;
        List<Blog> blogs = blogDao.findByPage(start, rows, searchContent, _blogClass, _blogLabel);
        PageBean<Blog> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        pageBean.setList(blogs);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public Map<String, Object> getBlog(int id) {
        Blog blog = blogDao.getBlog(id);
        Map<String, Object> data = new HashMap<>();
        data.put(Integer.toString(blog.getId()), blog);
        return data;
    }

    @Override
    public int deleteBlog(int id) {
        Blog oldBlog = blogDao.getBlog(id);
        categoryDao.decreaseClassAndLabel(Integer.parseInt(oldBlog.getBlogClass()), Integer.parseInt(oldBlog.getBlogLabel()));
        return blogDao.deleteBlog(id);
    }
}
