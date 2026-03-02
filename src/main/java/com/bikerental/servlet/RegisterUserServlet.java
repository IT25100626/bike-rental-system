package com.bikerental.servlet;

import com.bikerental.model.User;
import com.bikerental.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterUserServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("phone");
        String role = req.getParameter("role");
        if (role == null || role.isEmpty()) role = "CUSTOMER";

        String message;
        if (email == null || email.trim().isEmpty()) {
            message = "Email is required.";
            req.setAttribute("message", message);
            req.setAttribute("messageType", "danger");
            req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
            return;
        }

        User user = new User(null, email.trim(), fullName != null ? fullName.trim() : "", phone != null ? phone.trim() : "", role);
        User created = userService.create(user);
        if (created != null) {
            message = "User registered successfully!";
            req.setAttribute("message", message);
            req.setAttribute("messageType", "success");
            resp.sendRedirect(req.getContextPath() + "/user/search?msg=registered");
        } else {
            message = "Registration failed. Email may already exist.";
            req.setAttribute("message", message);
            req.setAttribute("messageType", "danger");
            req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
        }
    }
}
