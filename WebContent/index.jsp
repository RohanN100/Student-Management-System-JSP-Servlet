<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
model.Student student = (model.Student) request.getAttribute("student");
boolean isEdit = (student != null);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student CRUD</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body class="bg-light">

<div class="container mt-5">

    <div class="card shadow p-4">
        <h2 class="text-center text-primary mb-4">
            <%= isEdit ? "Update Student" : "Add Student" %>
        </h2>

        <form action="student" method="post">

            <% if(isEdit){ %>
                <input type="hidden" name="id"
                       value="<%= student.getId() %>">
            <% } %>

            <div class="mb-3">
                <label class="form-label">Name</label>
                <input type="text" name="name"
                       class="form-control"
                       value="<%= isEdit ? student.getName() : "" %>"
                       required>
            </div>

            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" name="email"
                       class="form-control"
                       value="<%= isEdit ? student.getEmail() : "" %>"
                       required>
            </div>

            <div class="mb-3">
                <label class="form-label">Course</label>
                <input type="text" name="course"
                       class="form-control"
                       value="<%= isEdit ? student.getCourse() : "" %>"
                       required>
            </div>

            <button type="submit"
                    class="btn btn-success w-100">
                <%= isEdit ? "Update Student" : "Save Student" %>
            </button>

        </form>

        <div class="mt-3 text-center">
            <a href="student" class="btn btn-secondary">
                Back to List
            </a>
        </div>

    </div>

</div>

</body>
</html>