public class Course {
    
    private String studentID;
    private String semester;
    private String course1Name;
    private String course1Grade;
    private String course2Name;
    private String course2Grade;
    private String course3Name;
    private String course3Grade;

    public Course(String studentID, String semester,
        String course1Name, String course1Grade,
        String course2Name, String course2Grade,
        String course3Name, String course3Grade
    ) {
        this.studentID = studentID;
        this.semester = semester;
        this.course1Name = course1Name;
        this.course1Grade = course1Grade;
        this.course2Name = course2Name;
        this.course2Grade = course2Grade;
        this.course3Name = course3Name;
        this.course3Grade = course3Grade;
    }

    public String getCourseStudentID() {
        return this.studentID;
    }

    public String getCourseSemester() {
        return this.semester;
    }

    public String getCourse1Name() {
        return this.course1Name;
    }

    public String getCourse1Grade() {
        return this.course1Grade;
    }

    public String getCourse2Name() {
        return this.course2Name;
    }

    public String getCourse2Grade() {
        return this.course2Grade;
    }

    public String getCourse3Name() {
        return this.course3Name;
    }

    public String getCourse3Grade() {
        return this.course3Grade;
    }
}
