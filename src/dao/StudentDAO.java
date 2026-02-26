package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Student;

public class StudentDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/studentdb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "rohan";   // apna password

    private static final String INSERT_SQL =
            "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";

    private static final String SELECT_BY_ID =
            "SELECT * FROM students WHERE id = ?";

    private static final String SELECT_ALL =
            "SELECT * FROM students";

    private static final String DELETE_SQL =
            "DELETE FROM students WHERE id = ?";

    private static final String UPDATE_SQL =
            "UPDATE students SET name=?, email=?, course=? WHERE id=?";

    // ================= CONNECTION =================
    protected Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
                jdbcURL, jdbcUsername, jdbcPassword);
    }

    // ================= INSERT =================
    public void insertStudent(Student student) throws Exception {

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_SQL)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getCourse());

            statement.executeUpdate();
        }
    }

    // ================= SELECT BY ID =================
    public Student selectStudent(int id) throws Exception {

        Student student = null;

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SELECT_BY_ID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                student = new Student(
                        id,
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course")
                );
            }
        }

        return student;
    }

    // ================= SELECT ALL =================
    public List<Student> selectAllStudents() throws Exception {

        List<Student> studentsList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SELECT_ALL);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                studentsList.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course")
                ));
            }
        }

        return studentsList;
    }

    // ================= DELETE =================
    public void deleteStudent(int id) throws Exception {

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(DELETE_SQL)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }

        // After delete check if table empty
        resetAutoIncrementIfEmpty();
    }

    // ================= UPDATE =================
    public void updateStudent(Student student) throws Exception {

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE_SQL)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getCourse());
            statement.setInt(4, student.getId());

            statement.executeUpdate();
        }
    }

    // ================= AUTO RESET =================
    private void resetAutoIncrementIfEmpty() throws Exception {

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM students")) {

            if (rs.next()) {

                int count = rs.getInt(1);

                if (count == 0) {

                    stmt.executeUpdate(
                            "ALTER TABLE students AUTO_INCREMENT = 1"
                    );
                }
            }
        }
    }
}