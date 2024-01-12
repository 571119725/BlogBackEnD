package indi.blogtest.domain;

public class SingleClass {
    private int cid;
    private String className;
    private int count;

    @Override
    public String toString() {
        return "ClassList{" +
                "cid=" + cid +
                ", className='" + className + '\'' +
                ", count=" + count +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
