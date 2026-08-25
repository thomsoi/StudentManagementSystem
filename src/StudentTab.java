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

import com.toedter.calendar.JDateChooser;
import java.util.Calendar;
import java.util.Date;

public class StudentTab extends JPanel {
    
    private final JTextField studentIDInput;
    private final JTextField studentFirstNameInput;
    private final JTextField studentLastNameInput;
    private final JDateChooser studentDOBInput;
    private final JComboBox<String> studentGenderInput;
    private final JTextField studentEmailInput;
    private final JTextField studentPhoneNumberInput;
    private final JTextField studentAddress1Input;
    private final JTextField studentAddress2Input;

    public StudentTab() {
        
        this.setLayout(new BorderLayout());

        JPanel studentSubPanelLeft = new JPanel();
        studentSubPanelLeft.setBackground(new Color(150, 200, 255));
        studentSubPanelLeft.setPreferredSize(new Dimension(100, 0));
        studentSubPanelLeft.setLayout(new GridLayout(10, 1));

        JLabel studentID = new JLabel("Student ID");
        JLabel studentFirstName = new JLabel("First name");
        JLabel studentLastName = new JLabel("Last name");
        JLabel studentDOB = new JLabel("Date of birth");
        JLabel studentGender = new JLabel("Gender");
        JLabel studentEmail = new JLabel("Email");
        JLabel studentPhoneNumber = new JLabel("Phone number");
        JLabel studentAddress1 = new JLabel("Address line 1");
        JLabel studentAddress2 = new JLabel("Address line 2");
        studentID.setHorizontalAlignment(SwingConstants.CENTER);
        studentFirstName.setHorizontalAlignment(SwingConstants.CENTER);
        studentLastName.setHorizontalAlignment(SwingConstants.CENTER);
        studentDOB.setHorizontalAlignment(SwingConstants.CENTER);
        studentGender.setHorizontalAlignment(SwingConstants.CENTER);
        studentEmail.setHorizontalAlignment(SwingConstants.CENTER);
        studentPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
        studentAddress1.setHorizontalAlignment(SwingConstants.CENTER);
        studentAddress2.setHorizontalAlignment(SwingConstants.CENTER);
        studentSubPanelLeft.add(studentID);
        studentSubPanelLeft.add(studentFirstName);
        studentSubPanelLeft.add(studentLastName);
        studentSubPanelLeft.add(studentDOB);
        studentSubPanelLeft.add(studentGender);
        studentSubPanelLeft.add(studentEmail);
        studentSubPanelLeft.add(studentPhoneNumber);
        studentSubPanelLeft.add(studentAddress1);
        studentSubPanelLeft.add(studentAddress2);

        JPanel studentSubPanelRight = new JPanel();
        studentSubPanelRight.setBackground(new Color(150, 200, 255));
        GridLayout g = new GridLayout(10, 1);
        g.setVgap(8);
        studentSubPanelRight.setLayout(g);

        studentIDInput = new JTextField();
        studentFirstNameInput = new JTextField();
        studentLastNameInput = new JTextField();
        studentDOBInput = new JDateChooser();
        String arr[] = {"Male", "Female", "Non-binary", "Other", "Prefer not to say"};
        studentGenderInput = new JComboBox<>(arr);
        studentGenderInput.setSelectedIndex(-1);
        studentEmailInput = new JTextField();
        studentPhoneNumberInput = new JTextField();
        studentAddress1Input = new JTextField();
        studentAddress2Input = new JTextField();
        
        studentIDInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        studentFirstNameInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        studentLastNameInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        studentDOBInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        studentGenderInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        studentEmailInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        studentPhoneNumberInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        studentAddress1Input.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        studentAddress2Input.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        studentSubPanelRight.add(studentIDInput);
        studentSubPanelRight.add(studentFirstNameInput);
        studentSubPanelRight.add(studentLastNameInput);
        studentSubPanelRight.add(studentDOBInput);
        studentSubPanelRight.add(studentGenderInput);
        studentSubPanelRight.add(studentEmailInput);
        studentSubPanelRight.add(studentPhoneNumberInput);
        studentSubPanelRight.add(studentAddress1Input);
        studentSubPanelRight.add(studentAddress2Input);

        this.add(studentSubPanelLeft, BorderLayout.WEST);
        this.add(studentSubPanelRight, BorderLayout.CENTER);
    }

    public Student getStudent() {

        // Extract the day, month, year from the Date
        Date dob = studentDOBInput.getDate();
        Calendar c = Calendar.getInstance();
        c.setTime(dob);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);

        String dobString = Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);

        return new Student(
            studentIDInput.getText(),
            studentFirstNameInput.getText(),
            studentLastNameInput.getText(),
            dobString,
            studentGenderInput.getSelectedItem().toString(),
            studentEmailInput.getText(),
            studentPhoneNumberInput.getText(),
            studentAddress1Input.getText(),
            studentAddress2Input.getText()
        );
    }
}
