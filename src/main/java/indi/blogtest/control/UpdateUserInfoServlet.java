package indi.blogtest.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import indi.blogtest.service.CategoryService;
import indi.blogtest.service.UserService;
import indi.blogtest.service.impl.CategoryServiceImpl;
import indi.blogtest.service.impl.UserServiceImpl;
import indi.blogtest.util.ResultUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@WebServlet("/updateUserInfo")
public class UpdateUserInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        reader.close();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> reqData = mapper.readValue(json, Map.class);
        UserService us = new UserServiceImpl();
        int info = us.updateUserInfo(reqData.get("password"), reqData.get("email"), reqData.get("qq"));
        resp.setCharacterEncoding("utf-8");
        if (info == 1)
            mapper.writeValue(resp.getWriter(), ResultUtils.ok("添加成功"));
        else if(info == -1)
            mapper.writeValue(resp.getWriter(), ResultUtils.error("内容不得为空"));
        else
            mapper.writeValue(resp.getWriter(), ResultUtils.error("网络波动，请稍后再试。"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
