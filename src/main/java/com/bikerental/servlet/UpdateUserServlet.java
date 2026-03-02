package com.bikerental.servlet;

import com.bikerental.model.User;
import com.bikerental.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/user/search");
            return;
        }
        User user = userService.findById(id);
        if (user == null) {
            req.setAttribute("message", "User not found.");
            req.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/user/search");
            return;
        }
        User existing = userService.findById(id);
        if (existing == null) {
            resp.sendRedirect(req.getContextPath() + "/user/search");
            return;
        }
        existing.setEmail(req.getParameter("email") != null ? req.getParameter("email").trim() : "");
        existing.setFullName(req.getParameter("fullName") != null ? req.getParameter("fullName").trim() : "");
        existing.setPhone(req.getParameter("phone") != null ? req.getParameter("phone").trim() : "");
        existing.setRole(req.getParameter("role") != null ? req.getParameter("role").trim() : "CUSTOMER");

        boolean updated = userService.update(existing);
        if (updated) {
            resp.sendRedirect(req.getContextPath() + "/user/search?msg=updated");
        } else {
            req.setAttribute("message", "Update failed.");
            req.setAttribute("user", existing);
            req.getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(req, resp);
        }
    }
}
