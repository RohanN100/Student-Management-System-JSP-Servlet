package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.StudentDAO;
import model.Student;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private StudentDAO dao;

    public void init() {
        dao = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            if (action == null) {
                listStudents(request, response);
            }
            else if ("delete".equals(action)) {
                deleteStudent(request, response);
            }
            else if ("edit".equals(action)) {
                showEditForm(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        try {

            if (id == null || id.isEmpty()) {

                Student student = new Student(name, email, course);
                dao.insertStudent(student);

            } else {

                Student student = new Student(
                        Integer.parseInt(id),
                        name,
                        email,
                        course
                );

                dao.updateStudent(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("student");
    }

    private void listStudents(HttpServletRequest request,
                              HttpServletResponse response)
            throws Exception {

        List<Student> list = dao.selectAllStudents();
        request.setAttribute("studentList", list);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("list.jsp");

        dispatcher.forward(request, response);
    }

    private void deleteStudent(HttpServletRequest request,
                               HttpServletResponse response)
            throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));
        dao.deleteStudent(id);

        response.sendRedirect("student");
    }

    private void showEditForm(HttpServletRequest request,
                              HttpServletResponse response)
            throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));

        Student existingStudent = dao.selectStudent(id);

        request.setAttribute("student", existingStudent);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("index.jsp");

        dispatcher.forward(request, response);
    }
}