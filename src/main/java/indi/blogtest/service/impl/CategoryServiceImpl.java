package indi.blogtest.service.impl;

import indi.blogtest.dao.BlogDao;
import indi.blogtest.dao.CategoryDao;
import indi.blogtest.dao.impl.BlogDaoImpl;
import indi.blogtest.dao.impl.CategoryDaoImpl;
import indi.blogtest.domain.Blog;
import indi.blogtest.domain.SingleClass;
import indi.blogtest.domain.SingleLabel;
import indi.blogtest.service.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private BlogDao blogDao = new BlogDaoImpl();

    @Override
    public Map<Integer, Integer> getOverviewInfo() {
        Map<Integer, Integer> data = new HashMap<>();
        data.put(0, blogDao.totalCount("", -1, -1));
        data.put(1, categoryDao.countAllClass());
        data.put(2, categoryDao.countAllLabel());
        return data;
    }

    @Override
    public int countAllClass() {
        return categoryDao.countAllClass();
    }

    @Override
    public int countAllLabel() {
        return categoryDao.countAllLabel();
    }

    @Override
    public Map<Integer, Integer> getAllClass() {
        Map<Integer, Integer> data = new HashMap<>();
        List<SingleClass> list = categoryDao.getAllClass();
        for(SingleClass sc : list){
            data.put(sc.getCid(), sc.getCount());
        }
        return data;
    }

    @Override
    public Map<Integer, Integer> getAllLabel() {
        Map<Integer, Integer> data = new HashMap<>();
        List<SingleLabel> list = categoryDao.getAllLabel();
        for(SingleLabel sc : list){
            data.put(sc.getLid(), sc.getCount());
        }
        return data;
    }
}
