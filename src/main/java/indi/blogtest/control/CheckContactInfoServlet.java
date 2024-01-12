package indi.blogtest.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import indi.blogtest.domain.User;
import indi.blogtest.service.UserService;
import indi.blogtest.service.impl.UserServiceImpl;
import indi.blogtest.util.ResultUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/checkContactInfo")
public class CheckContactInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        User user = userService.checkContactInfo();
        Map<String, Object> contactInfo = new HashMap<>();
        contactInfo.put("qq", user.getQq());
        contactInfo.put("email", user.getEmail());

        ResultUtils r = ResultUtils.ok(contactInfo);
        ObjectMapper map = new ObjectMapper();
        map.writeValue(resp.getWriter(), r);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
