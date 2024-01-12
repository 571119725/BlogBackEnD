package indi.blogtest.domain;

import java.util.Date;

public class Blog {
    private int id;
    private String blogTitle;
    private String blogIntro;
    private String blogContent;
    private String blogClass;
    private String blogLabel;
    private String uploadDate;

    public Blog() {
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogIntro='" + blogIntro + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", blogClass='" + blogClass + '\'' +
                ", blogLabel='" + blogLabel + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                '}';
    }

    public Blog(String blogTitle, String blogIntro, String blogContent, String blogClass, String blogLabel, String uploadDate) {
        this.blogTitle = blogTitle;
        this.blogIntro = blogIntro;
        this.blogContent = blogContent;
        this.blogClass = blogClass;
        this.blogLabel = blogLabel;
        this.uploadDate = uploadDate;
    }

    public Blog(int id, String blogTitle, String blogIntro, String blogContent, String blogClass, String blogLabel, String uploadDate) {
        this.id = id;
        this.blogTitle = blogTitle;
        this.blogIntro = blogIntro;
        this.blogContent = blogContent;
        this.blogClass = blogClass;
        this.blogLabel = blogLabel;
        this.uploadDate = uploadDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogIntro() {
        return blogIntro;
    }

    public void setBlogIntro(String blogIntro) {
        this.blogIntro = blogIntro;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogClass() {
        return blogClass;
    }

    public void setBlogClass(String blogClass) {
        this.blogClass = blogClass;
    }

    public String getBlogLabel() {
        return blogLabel;
    }

    public void setBlogLabel(String blogLabel) {
        this.blogLabel = blogLabel;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}
