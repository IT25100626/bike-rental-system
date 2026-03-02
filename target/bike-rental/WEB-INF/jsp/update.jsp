<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update User - Bike Rental</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/app.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<main class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2 class="mb-4">Update User</h2>
            <% if (request.getAttribute("message") != null) { %>
            <div class="alert alert-warning" role="alert"><%= request.getAttribute("message") %></div>
            <% } %>
            <c:set var="user" value="${user}"/>
            <c:if test="${user != null}">
            <form action="${pageContext.request.contextPath}/user/update" method="post">
                <input type="hidden" name="id" value="${user.id}">
                <div class="mb-3">
                    <label for="email" class="form-label">Email *</label>
                    <input type="email" class="form-control" id="email" name="email" required value="<c:out value="${user.email}"/>">
                </div>
                <div class="mb-3">
                    <label for="fullName" class="form-label">Full Name</label>
                    <input type="text" class="form-control" id="fullName" name="fullName" value="<c:out value="${user.fullName}"/>">
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Phone</label>
                    <input type="text" class="form-control" id="phone" name="phone" value="<c:out value="${user.phone}"/>">
                </div>
                <div class="mb-3">
                    <label for="role" class="form-label">Role</label>
                    <select class="form-select" id="role" name="role">
                        <option value="CUSTOMER" ${user.role == 'CUSTOMER' ? 'selected' : ''}>Customer</option>
                        <option value="RIDER" ${user.role == 'RIDER' ? 'selected' : ''}>Rider</option>
                        <option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>Admin</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Update</button>
                <a href="${pageContext.request.contextPath}/user/search" class="btn btn-outline-secondary">Cancel</a>
            </form>
            </c:if>
        </div>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
