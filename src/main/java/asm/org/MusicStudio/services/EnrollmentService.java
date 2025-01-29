package asm.org.MusicStudio.services;

import asm.org.MusicStudio.db.DatabaseConnection;
import asm.org.MusicStudio.entity.Enrollment;
import asm.org.MusicStudio.entity.Student;
import asm.org.MusicStudio.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentService {
    
    private final DatabaseConnection databaseConnection;

    public EnrollmentService() {
        this.databaseConnection = DatabaseConnection.getInstance();
    }

    public boolean createEnrollment(Student student, Course course) {
        String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
        
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setLong(1, student.getStudentId());
            pstmt.setLong(2, course.getCourseId());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        Enrollment enrollment = new Enrollment();
                        enrollment.setId(rs.getLong(1));
                        enrollment.setStudent(student);
                        enrollment.setCourse(course);
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error creating enrollment: " + e.getMessage());
            return false;
        }
    }
}
