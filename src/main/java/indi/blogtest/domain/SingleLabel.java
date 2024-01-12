package indi.blogtest.domain;

public class SingleLabel {
    private int lid;
    private String labelName;
    private int count;

    @Override
    public String toString() {
        return "labelList{" +
                "lid=" + lid +
                ", labelName='" + labelName + '\'' +
                ", count=" + count +
                '}';
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
