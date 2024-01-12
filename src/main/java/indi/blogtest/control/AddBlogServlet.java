package indi.blogtest.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import indi.blogtest.domain.Blog;
import indi.blogtest.service.BlogService;
import indi.blogtest.service.impl.BlogServiceImpl;
import indi.blogtest.util.ResultUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/addBlog")
public class AddBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        reader.close();
        ObjectMapper map = new ObjectMapper();
        Blog blog = map.readValue(json, Blog.class);

        resp.setCharacterEncoding("utf-8");
        BlogService addBlogService = new BlogServiceImpl();

        int info = addBlogService.addBlog(blog);
        if(info == -1)
            map.writeValue(resp.getWriter(), ResultUtils.error("内容不得为空。"));
        else if(info == 1)
            map.writeValue(resp.getWriter(), ResultUtils.ok("添加成功！"));
        else
            map.writeValue(resp.getWriter(), ResultUtils.error("网络波动，请稍后重试。"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
