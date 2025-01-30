package asm.org.MusicStudio.entity;

import java.time.LocalTime;

public class Schedule {
    private Integer scheduleId;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private Course course;
    private Room room;

    // Default constructor
    public Schedule() {
    }

    // Parameterized constructor
    public Schedule(Integer scheduleId, String dayOfWeek, LocalTime startTime, LocalTime endTime, Course course, Room room) {
        this.scheduleId = scheduleId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.course = course;
        this.room = room;
    }

    // Getters
    public Integer getScheduleId() {
        return scheduleId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Course getCourse() {
        return course;
    }

    public Room getRoom() {
        return room;
    }

    // Setters
    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    // toString method
    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", course=" + course +
                ", room=" + room +
                '}';
    }
} 

