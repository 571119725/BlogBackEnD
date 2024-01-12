package indi.blogtest.dao;

import indi.blogtest.domain.SingleClass;
import indi.blogtest.domain.SingleLabel;

import java.util.List;

public interface CategoryDao {
    public int countAllClass();
    public int countAllLabel();
    public List<SingleClass> getAllClass();
    public List<SingleLabel> getAllLabel();

    public int increaseClassAndLabel(int cid, int lid);

    public int decreaseClassAndLabel(int cid, int lid);
}
