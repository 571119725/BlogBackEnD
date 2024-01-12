package indi.blogtest.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import indi.blogtest.domain.Blog;
import indi.blogtest.domain.PageBean;
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
import java.util.Map;

@WebServlet("/getBlogList")
public class GetBlogListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        reader.close();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        String currentPage = String.valueOf(map.get("currentPage"));
        String rows = String.valueOf(map.get("rows"));
        String content = (String) map.get("content");
        int blogClass = (Integer) map.get("blogClass");
        int blogLabel = (Integer) map.get("blogLabel");
        BlogService getBlogListService = new BlogServiceImpl();
        PageBean<Blog> data = getBlogListService.getBlogList(currentPage, rows, content, blogClass, blogLabel);

        resp.setCharacterEncoding("utf-8");
        mapper.writeValue(resp.getWriter(), ResultUtils.ok(data));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
