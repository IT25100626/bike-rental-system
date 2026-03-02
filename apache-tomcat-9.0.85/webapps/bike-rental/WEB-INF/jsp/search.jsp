<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Users - Bike Rental</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/app.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<main class="container my-5">
    <h2 class="mb-4">Search & Manage Users</h2>
    <% if (request.getAttribute("message") != null) { %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <%= request.getAttribute("message") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
    <% } %>
    <form class="d-flex gap-2 mb-4" action="${pageContext.request.contextPath}/user/search" method="get">
        <input class="form-control" type="search" name="keyword" placeholder="Search by name, email or phone..."
               value="${keyword}">
        <button class="btn btn-primary" type="submit">Search</button>
    </form>
    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th>Email</th>
                <th>Full Name</th>
                <th>Phone</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="u" items="${users}">
                <tr>
                    <td><c:out value="${u.email}"/></td>
                    <td><c:out value="${u.fullName}"/></td>
                    <td><c:out value="${u.phone}"/></td>
                    <td><c:out value="${u.role}"/></td>
                    <td class="text-nowrap">
                        <a href="${pageContext.request.contextPath}/user/update?id=${u.id}" class="btn btn-sm btn-outline-primary me-1">Update</a>
                        <form action="${pageContext.request.contextPath}/user/delete" method="post" class="d-inline" onsubmit="return confirm('Delete this user?');">
                            <input type="hidden" name="id" value="${u.id}">
                            <button type="submit" class="btn btn-sm btn-outline-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <c:if test="${empty users}">
        <p class="text-muted">No users found. <a href="${pageContext.request.contextPath}/user/register">Register a user</a>.</p>
    </c:if>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
