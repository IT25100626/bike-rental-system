package com.bikerental.servlet;

import com.bikerental.model.User;
import com.bikerental.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchUserServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<User> users = userService.search(keyword);
        req.setAttribute("users", users);
        req.setAttribute("keyword", keyword != null ? keyword : "");
        String msg = req.getParameter("msg");
        if ("registered".equals(msg)) req.setAttribute("message", "User registered successfully.");
        if ("updated".equals(msg)) req.setAttribute("message", "User updated successfully.");
        if ("deleted".equals(msg)) req.setAttribute("message", "User deleted successfully.");
        req.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(req, resp);
    }
}
