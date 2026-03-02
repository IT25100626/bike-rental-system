<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bike Rental & Ride-Sharing - Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/app.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<main class="container my-5">
    <div class="row justify-content-center">
        <div class="col-lg-8 text-center">
            <h1 class="display-4 mb-4">Bike Rental & Ride-Sharing</h1>
            <p class="lead text-muted">Manage users and explore bike rental and ride-sharing services.</p>
            <hr class="my-4">
            <div class="d-flex flex-wrap gap-3 justify-content-center">
                <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/user/register">Register New User</a>
                <a class="btn btn-outline-primary btn-lg" href="${pageContext.request.contextPath}/user/search">Search / Manage Users</a>
            </div>
            <div class="card mt-5 text-start">
                <div class="card-body">
                    <h5 class="card-title">User Management</h5>
                    <p class="card-text mb-0">Register new users, search by name or email, update user details, and delete users. All data is stored in files (no database).</p>
                </div>
            </div>
        </div>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
