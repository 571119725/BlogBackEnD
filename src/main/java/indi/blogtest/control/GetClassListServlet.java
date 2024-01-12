package indi.blogtest.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import indi.blogtest.service.CategoryService;
import indi.blogtest.service.impl.CategoryServiceImpl;
import indi.blogtest.util.ResultUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getClassList")
public class GetClassListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryService cs = new CategoryServiceImpl();
        ObjectMapper mapper = new ObjectMapper();
        resp.setCharacterEncoding("utf-8");
        mapper.writeValue(resp.getWriter(), ResultUtils.ok(cs.getAllClass()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
