package indi.blogtest.control;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebServlet("/getBlog")
public class GetBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        reader.close();
        int id = Integer.parseInt(json);

        BlogService getBlogService = new BlogServiceImpl();
        Map<String, Object> data = getBlogService.getBlog(id);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), ResultUtils.ok(data));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
