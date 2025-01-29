package asm.org.MusicStudio.dao;

import asm.org.MusicStudio.db.DatabaseConnection;
import asm.org.MusicStudio.entity.User;
import java.sql.*;

public class UserDAO {
    private final DatabaseConnection databaseConnection;

    public UserDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, role) VALUES (?, ?, ?)";
        
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getRole());
            
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role")
                    );
                }
            }
        }
        return null;
    }

    public boolean userExists(int userId) {
        String sql = "SELECT COUNT(*) FROM users WHERE id = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Error checking user existence: " + e.getMessage());
            return false;
        }
    }
} 