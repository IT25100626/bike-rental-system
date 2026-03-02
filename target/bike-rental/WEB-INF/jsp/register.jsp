<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register User - Bike Rental</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/app.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<main class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2 class="mb-4">Register New User</h2>
            <% if (request.getAttribute("message") != null) { %>
            <div class="alert alert-<%= request.getAttribute("messageType") != null ? request.getAttribute("messageType") : "info" %>" role="alert">
                <%= request.getAttribute("message") %>
            </div>
            <% } %>
            <form action="${pageContext.request.contextPath}/user/register" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email *</label>
                    <input type="email" class="form-control" id="email" name="email" required
                           value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>">
                </div>
                <div class="mb-3">
                    <label for="fullName" class="form-label">Full Name</label>
                    <input type="text" class="form-control" id="fullName" name="fullName"
                           value="<%= request.getParameter("fullName") != null ? request.getParameter("fullName") : "" %>">
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Phone</label>
                    <input type="text" class="form-control" id="phone" name="phone"
                           value="<%= request.getParameter("phone") != null ? request.getParameter("phone") : "" %>">
                </div>
                <div class="mb-3">
                    <label for="role" class="form-label">Role</label>
                    <select class="form-select" id="role" name="role">
                        <option value="CUSTOMER">Customer</option>
                        <option value="RIDER">Rider</option>
                        <option value="ADMIN">Admin</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
                <a href="${pageContext.request.contextPath}/user/search" class="btn btn-outline-secondary">Cancel</a>
            </form>
        </div>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
