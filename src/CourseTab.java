import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CourseTab extends JPanel {
    
    private final JTextField courseStudentIDInput;
    private final JComboBox<String> courseSemesterInput;
    private final JComboBox<String> courseName1Input;
    private final JComboBox<String> courseName2Input;
    private final JComboBox<String> courseName3Input;
    private final JTextField courseName1GradeInput;
    private final JTextField courseName2GradeInput;
    private final JTextField courseName3GradeInput;

    public CourseTab() {
        this.setLayout(new BorderLayout());

        JPanel courseSubPanelLeft = new JPanel();
        courseSubPanelLeft.setBackground(new Color(155, 200, 255));
        courseSubPanelLeft.setPreferredSize(new Dimension(100, 0));
        courseSubPanelLeft.setLayout(new GridLayout(10, 1));

        JLabel courseStudentID = new JLabel("Student ID");
        JLabel courseSemester = new JLabel("Semester");
        JLabel courseName1 = new JLabel("Course 1");
        JLabel courseName2 = new JLabel("Course 2");
        JLabel courseName3 = new JLabel("Course 3");
        JLabel courseName1Grade = new JLabel("Course 1 grade");
        JLabel courseName2Grade = new JLabel("Course 2 grade");
        JLabel courseName3Grade = new JLabel("Course 3 grade");
        courseStudentID.setHorizontalAlignment(SwingConstants.CENTER);
        courseSemester.setHorizontalAlignment(SwingConstants.CENTER);
        courseName1.setHorizontalAlignment(SwingConstants.CENTER);
        courseName2.setHorizontalAlignment(SwingConstants.CENTER);
        courseName3.setHorizontalAlignment(SwingConstants.CENTER);
        courseName1Grade.setHorizontalAlignment(SwingConstants.CENTER);
        courseName2Grade.setHorizontalAlignment(SwingConstants.CENTER);
        courseName3Grade.setHorizontalAlignment(SwingConstants.CENTER);
        courseSubPanelLeft.add(courseStudentID);
        courseSubPanelLeft.add(courseSemester);
        courseSubPanelLeft.add(courseName1);
        courseSubPanelLeft.add(courseName2);
        courseSubPanelLeft.add(courseName3);
        courseSubPanelLeft.add(courseName1Grade);
        courseSubPanelLeft.add(courseName2Grade);
        courseSubPanelLeft.add(courseName3Grade);

        JPanel courseSubPanelRight = new JPanel();
        courseSubPanelRight.setBackground(new Color(150, 200, 255));
        GridLayout g;
        g = new GridLayout(10, 1);
        g.setVgap(8);
        courseSubPanelRight.setLayout(g);

        courseStudentIDInput = new JTextField();
        String semArr[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        String course1Arr[] = {"CS1001", "CS1004", "CS2001", "CS2004", "CS3001", "CS3004", "CS4001", "CS4004"};
        String course2Arr[] = {"CS1002", "CS1005", "CS2002", "CS2005", "CS3002", "CS3005", "CS4002", "CS4005"};
        String course3Arr[] = {"CS1003", "CS1006", "CS2003", "CS2006", "CS3003", "CS3006", "CS4003", "CS4006"};
        courseSemesterInput = new JComboBox<>(semArr); 
        courseName1Input = new JComboBox<>(course1Arr);
        courseName2Input = new JComboBox<>(course2Arr);
        courseName3Input = new JComboBox<>(course3Arr);
        courseName1GradeInput = new JTextField();
        courseName2GradeInput = new JTextField();
        courseName3GradeInput = new JTextField();

        courseSemesterInput.setSelectedIndex(-1);
        courseName1Input.setSelectedIndex(-1);
        courseName2Input.setSelectedIndex(-1);
        courseName3Input.setSelectedIndex(-1);
        
        courseStudentIDInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        courseSemesterInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        courseName1Input.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        courseName2Input.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        courseName3Input.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        courseName1GradeInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        courseName2GradeInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        courseName3GradeInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        courseSubPanelRight.add(courseStudentIDInput);
        courseSubPanelRight.add(courseSemesterInput);
        courseSubPanelRight.add(courseName1Input);
        courseSubPanelRight.add(courseName2Input);
        courseSubPanelRight.add(courseName3Input);
        courseSubPanelRight.add(courseName1GradeInput);
        courseSubPanelRight.add(courseName2GradeInput);
        courseSubPanelRight.add(courseName3GradeInput);

        this.add(courseSubPanelLeft, BorderLayout.WEST);
        this.add(courseSubPanelRight, BorderLayout.CENTER);
    }

    public Course getCourse() {
        return new Course(
            courseStudentIDInput.getText(),
            courseSemesterInput.getSelectedItem().toString(),
            courseName1Input.getSelectedItem().toString(),
            courseName1GradeInput.getText(),
            courseName2Input.getSelectedItem().toString(),
            courseName2GradeInput.getText(),
            courseName3Input.getSelectedItem().toString(),
            courseName3GradeInput.getText()
        );
    }
}
