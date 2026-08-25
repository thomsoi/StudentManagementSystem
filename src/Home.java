
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Home extends JFrame {
    
    public static final int FRAME_HEIGHT = 500;
    public static final int FRAME_WIDTH = 800;

    private final JPanel mainPanel;
    private final JPanel topPanel;
    private final JLabel topLabel;

    private final JSplitPane mainSplitPane;
    
    private final JTabbedPane tabPanel;
    private final StudentTab studentTab;
    private final CourseTab courseTab;

    public Home() {
        // Frame properties
        super("Student Management System");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // The main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setVisible(true);

        // The panel at the top
        topPanel = new JPanel();
        topPanel.setBackground(new Color(80, 175, 210));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        topPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);

        topLabel = new JLabel();
        topLabel.setText("Student Management System");
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setVerticalAlignment(JLabel.CENTER);
        topLabel.setFont(new Font("Serif", Font.PLAIN, 40));
        topPanel.add(topLabel);

        // The tabbed panel, to switch between tabs
        tabPanel = new JTabbedPane();
        studentTab = new StudentTab();
        courseTab = new CourseTab();

        tabPanel.add("Student", studentTab);
        tabPanel.add("Course", courseTab);
        tabPanel.setTabPlacement(JTabbedPane.TOP);

        // The split pane
        mainSplitPane = new JSplitPane();
        mainSplitPane.setLeftComponent(tabPanel);
        mainSplitPane.setDividerLocation(FRAME_WIDTH - 490);
        mainPanel.add(mainSplitPane, BorderLayout.CENTER);

        JPanel studentDisplay = new JPanel();
        studentDisplay.setLayout(new BorderLayout());
        studentDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        JPanel courseDisplay = new JPanel();
        courseDisplay.setLayout(new BorderLayout());
        courseDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // The student search bar
        JPanel studentSearch = getStudentSearchBar();
        JPanel studentButtons = getStudentButtons();
        JPanel courseSearch = getStudentSearchBar();
        JPanel courseButtons = getStudentButtons();

        studentDisplay.add(studentSearch, BorderLayout.NORTH);
        studentDisplay.add(studentButtons, BorderLayout.SOUTH);
        courseDisplay.add(courseSearch, BorderLayout.NORTH);
        courseDisplay.add(courseButtons, BorderLayout.SOUTH);

        // The right side of the tabbed panel.
        StudentView studentView = new StudentView();
        studentDisplay.add(studentView, BorderLayout.CENTER);
        studentView.populateStudentTableData();

        ((JButton) studentButtons.getComponent(0)).addActionListener(e -> {
            try {
                Student s = studentTab.getStudent();
                studentView.displayStudent(s);
            }
            catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Please ensure all fields are completed and the information is in the correct format.");
            }
        });

        ((JButton) studentButtons.getComponent(1)).addActionListener(e -> {
            if (!studentView.removeStudent()) {
                JOptionPane.showMessageDialog(null, "Please select a row to remove a student.");
            }
        });

        ((JButton) studentButtons.getComponent(2)).addActionListener(e -> {
            if (!studentView.saveStudentData()) {
                JOptionPane.showMessageDialog(null, "No students to save.");
            }
        });

        ((JButton) studentButtons.getComponent(3)).addActionListener(e -> {
            if (!studentView.clearStudents()) {
                JOptionPane.showMessageDialog(null, "No students to clear.");
            }
        });

        ((JButton) studentSearch.getComponent(2)).addActionListener(e -> {
            JTextField jtf = (JTextField) studentSearch.getComponent(1);
            studentView.filterStudentsByID(jtf.getText());
        });

        ((JButton) studentSearch.getComponent(3)).addActionListener(e -> {
            studentView.filterStudentsByID("");
        });

        CourseView courseView = new CourseView();
        courseDisplay.add(courseView, BorderLayout.CENTER);
        courseView.populateCourseTableData();

        ((JButton) courseButtons.getComponent(0)).addActionListener(e -> {
            try {
                Course c = courseTab.getCourse();
                courseView.displayCourse(c);
            }
            catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Please ensure all fields are completed and the information is in the correct format.");
            }
        });

        ((JButton) courseButtons.getComponent(1)).addActionListener(e -> {
            if (!courseView.removeCourse()) {
                JOptionPane.showMessageDialog(null, "Please select a row to remove a semester of courses.");
            }
        });

        ((JButton) courseButtons.getComponent(2)).addActionListener(e -> {
            if (!courseView.saveCourseData()) {
                JOptionPane.showMessageDialog(null, "No courses to save.");
            }
        });

        ((JButton) courseButtons.getComponent(3)).addActionListener(e -> {
            if (!courseView.clearCourses()) {
                JOptionPane.showMessageDialog(null, "No courses to clear.");
            }
        });

        ((JButton) courseSearch.getComponent(2)).addActionListener(e -> {
            JTextField jtf = (JTextField) courseSearch.getComponent(1);
            courseView.filterCoursesByID(jtf.getText());
        });

        ((JButton) courseSearch.getComponent(3)).addActionListener(e -> {
            courseView.filterCoursesByID("");
        });

        mainSplitPane.setRightComponent(studentDisplay);

        tabPanel.addChangeListener(e -> {
            int selectedTab = tabPanel.getSelectedIndex();

            if (selectedTab == 0) {
                mainSplitPane.setRightComponent(studentDisplay);
            }
            else {
                mainSplitPane.setRightComponent(courseDisplay);
            }
        });

        this.add(mainPanel);
    }

    // Reusable components
    private JPanel getStudentSearchBar() {
        JPanel studentSearch = new JPanel();
        studentSearch.setBackground(new Color(150, 200, 255));
        studentSearch.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        studentSearch.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // padding
        gbc.gridx = 0; // column 0
        gbc.gridy = 0; // row 0
        gbc.weightx = 0; // do not fill the extra space
        JLabel studentSearchText = new JLabel("Search student");
        studentSearch.add(studentSearchText, gbc);

        JTextField studentSearchBar = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1; // fill the extra space
        gbc.fill = GridBagConstraints.HORIZONTAL; // fill it horizontally
        studentSearch.add(studentSearchBar, gbc);

        JButton studentSearchButton = new JButton("Search"); // put outside to allow for access
        gbc.gridx = 2;
        gbc.weightx = 0;
        studentSearch.add(studentSearchButton, gbc);

        JButton studentRefreshButton = new JButton("Refresh");
        gbc.gridx = 3;
        studentSearch.add(studentRefreshButton, gbc);

        return studentSearch;
    }

    private JPanel getStudentButtons() {
        JPanel studentButtons = new JPanel();
        studentButtons.setBackground(new Color(150, 200, 255));
        studentButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        studentButtons.setPreferredSize(new Dimension(0, 60));
        studentButtons.setLayout(new GridBagLayout());

        JButton addNewStudentButton = new JButton("Add new");
        JButton removeStudentButton = new JButton("Remove");
        JButton saveStudentsButton = new JButton("Save");
        JButton clearStudentsButton = new JButton("Clear");

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(5, 7, 5, 7);
        gbc2.gridx = 0;
        studentButtons.add(addNewStudentButton, gbc2);

        gbc2.gridx = 1;
        studentButtons.add(removeStudentButton, gbc2);

        gbc2.gridx = 2;
        studentButtons.add(saveStudentsButton, gbc2);

        gbc2.gridx = 3;
        studentButtons.add(clearStudentsButton, gbc2);

        return studentButtons;
    }
}
