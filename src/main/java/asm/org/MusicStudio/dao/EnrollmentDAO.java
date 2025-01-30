package asm.org.MusicStudio.dao;

import asm.org.MusicStudio.entity.Enrollment;
import java.util.HashMap;
import java.util.Map;

public class EnrollmentDAO {
    private Map<Long, Enrollment> enrollments = new HashMap<>();
    private Long nextId = 1L;
    
    public boolean save(Enrollment enrollment) {
        try {
            if (enrollment.getId() == null) {
                enrollment.setId(nextId++);
            }
            enrollments.put(enrollment.getId(), enrollment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Enrollment findById(Long id) {
        return enrollments.get(id);
    }
}
