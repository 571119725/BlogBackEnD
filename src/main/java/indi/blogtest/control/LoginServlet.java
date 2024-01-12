package indi.blogtest.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import indi.blogtest.domain.User;
import indi.blogtest.service.UserService;
import indi.blogtest.service.impl.UserServiceImpl;
import indi.blogtest.util.ResultUtils;
import indi.blogtest.util.Token;
import indi.blogtest.util.TokenUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        reader.close();
        ObjectMapper mapper = new ObjectMapper();
        User loginUser = mapper.readValue(json, User.class);

        UserService userService = new UserServiceImpl();
        String token = userService.findAll(loginUser);
        ResultUtils r;
        if(token.isEmpty())
            r = ResultUtils.error();
        else {
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            r = ResultUtils.ok(data);
//            HttpSession session = req.getSession();
//            session.setAttribute("token", token);
            Token.setToken(token);
        }
        mapper.writeValue(resp.getWriter(), r);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
