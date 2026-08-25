public class Student {
    
    private String studentID;
    private String studentFirstName;
    private String studentLastName;
    private String studentDOB;
    private String studentGender;
    private String studentEmail;
    private String studentPhoneNumber;
    private String studentAddress1;
    private String studentAddress2;

    public Student(String studentID, String studentFirstName,
        String studentLastName, String studentDOB, String studentGender,
        String studentEmail, String studentPhoneNumber,
        String studentAddress1, String studentAddress2
    ) {
        this.studentID = studentID;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentDOB = studentDOB;
        this.studentGender = studentGender;
        this.studentEmail = studentEmail;
        this.studentPhoneNumber = studentPhoneNumber;
        this.studentAddress1 = studentAddress1;
        this.studentAddress2 = studentAddress2;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public String getStudentFirstName() {
        return this.studentFirstName;
    }
    
    public String getStudentLastName() {
        return this.studentLastName;
    }

    public String getStudentDOB() {
        return this.studentDOB;
    }

    public String getStudentGender() {
        return this.studentGender;
    }

    public String getStudentEmail() {
        return this.studentEmail;
    }

    public String getStudentPhoneNumber() {
        return this.studentPhoneNumber;
    }

    public String getStudentAddress1() {
        return this.studentAddress1;
    }

    public String getStudentAddress2() {
        return this.studentAddress2;
    }
}
