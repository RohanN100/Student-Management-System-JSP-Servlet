<%@ page import="java.util.*, model.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Management System</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Font Awesome -->
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

<style>
body {
    background: linear-gradient(135deg, #667eea, #764ba2);
    min-height: 100vh;
}

.navbar {
    background: rgba(0,0,0,0.6);
}

.card-custom {
    background: rgba(255,255,255,0.95);
    border-radius: 20px;
    box-shadow: 0 15px 35px rgba(0,0,0,0.2);
}

.table thead {
    background-color: #343a40;
    color: white;
}

.btn-custom {
    border-radius: 25px;
    padding: 6px 15px;
}

.table tbody tr:hover {
    background-color: #f2f2f2;
    transition: 0.3s;
}
</style>
</head>

<body>

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark px-4">
    <span class="navbar-brand fw-bold">
        <i class="fa-solid fa-graduation-cap"></i> Student Management
    </span>
</nav>

<div class="container mt-5">

    <div class="card card-custom p-4">

        <div class="d-flex justify-content-between align-items-center mb-4">
            <h3 class="text-primary fw-bold">
                <i class="fa-solid fa-list"></i> Student List
            </h3>

            <a href="index.jsp" class="btn btn-success btn-custom">
                <i class="fa-solid fa-plus"></i> Add Student
            </a>
        </div>

        <table class="table table-hover align-middle">

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Course</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>

            <%
            List<Student> list = (List<Student>) request.getAttribute("studentList");

            if(list != null){
                for(Student s : list){
            %>

                <tr>
                    <td><%= s.getId() %></td>
                    <td><%= s.getName() %></td>
                    <td><%= s.getEmail() %></td>
                    <td><%= s.getCourse() %></td>
                    <td>

                        <a href="student?action=edit&id=<%= s.getId() %>"
                           class="btn btn-primary btn-sm btn-custom">
                           <i class="fa-solid fa-pen"></i>
                        </a>

                        <a href="student?action=delete&id=<%= s.getId() %>"
                           class="btn btn-danger btn-sm btn-custom"
                           onclick="return confirm('Are you sure you want to delete?');">
                           <i class="fa-solid fa-trash"></i>
                        </a>

                    </td>
                </tr>

            <%
                }
            }
            %>

            </tbody>
        </table>

    </div>
</div>

</body>
</html>