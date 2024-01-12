package indi.blogtest.service;

import indi.blogtest.domain.SingleClass;
import indi.blogtest.domain.SingleLabel;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    public Map<Integer, Integer> getOverviewInfo();
    public int countAllClass();
    public int countAllLabel();
    public Map<Integer, Integer> getAllClass();
    public Map<Integer, Integer> getAllLabel();
}
