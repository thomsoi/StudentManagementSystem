
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CourseView extends JPanel {

    private final DefaultTableModel model;
    private final JTable coursesTable;
    private final String COURSES_DATA_FILE_PATH = "./CoursesData.txt";
    private final TableRowSorter<TableModel> sorter;

    public CourseView() {
        this.setBackground(new Color(110, 130 ,100));
        this.setLayout(new BorderLayout()); // using border layout means this changes when the window is resized
        String[] courseColumnNames = {"Student ID", "Semester", "Course 1",
            "Course 1 grade", "Course 2", "Course 2 grade", "Course 3", "Course 3 grade"};
        model = new DefaultTableModel(new Object[0][courseColumnNames.length], courseColumnNames);
        coursesTable = new JTable(model);
        sorter = new TableRowSorter<>(model);
        coursesTable.setRowSorter(sorter);
        this.add(new JScrollPane(coursesTable), BorderLayout.CENTER); // use JScrollPane for correct formatting
    }

    public String getFileDataPath() {
        return this.COURSES_DATA_FILE_PATH;
    }

    // TODO: Refactor the following, along with StudentView

    public void displayCourse(Course c) {
        model.addRow(new Object[] {
            c.getCourseStudentID(),
            c.getCourseSemester(),
            c.getCourse1Name(),
            c.getCourse1Grade(),
            c.getCourse2Name(),
            c.getCourse2Grade(),
            c.getCourse3Name(),
            c.getCourse3Grade()
        });
    }

    public Boolean removeCourse() {
        int selectedRow = coursesTable.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
            return true;
        }
        return false;
    }

    public Boolean clearCourses() {
        if (model.getRowCount() == 0) {
            return false;
        }
        model.setRowCount(0);
        File f = new File(COURSES_DATA_FILE_PATH);
        f.delete();
        return true;
    }

    public void populateCourseTableData() {
        SaveData s = new SaveData();
        ArrayList<String> unprocessedData = s.readFromFile(COURSES_DATA_FILE_PATH);

        if (unprocessedData == null) {
            return;
        }

        ArrayList<String[]> processedData = s.splitData(unprocessedData);

        for (int r = 0; r < processedData.size(); r++) {
            model.addRow(processedData.get(r));
        }
    }

    public String[] getCourseTableData() {
        StringBuilder sb = new StringBuilder();
        String[] tableData = new String[model.getRowCount()];
        
        for (int r = 0; r < model.getRowCount(); r++) {
            for (int c = 0; c < model.getColumnCount(); c++) {
                String val = model.getValueAt(r, c).toString();
                sb.append(val);
                sb.append(",");
            }
            tableData[r] = sb.toString();
            sb.setLength(0);
        }

        return tableData;
    }

    public Boolean saveCourseData() {
        SaveData s = new SaveData();
        String[] tableData = getCourseTableData();
        return s.writeToFile(COURSES_DATA_FILE_PATH, tableData);
    }

    public String[] getCourseTableDataID(String id) {
        StringBuilder sb = new StringBuilder();
        String[] tableData = new String[model.getRowCount()];
        
        for (int r = 0; r < model.getRowCount(); r++) {
            if (model.getValueAt(r, 0) != id) {
                continue;
            }
            for (int c = 0; c < model.getColumnCount(); c++) {
                String val = model.getValueAt(r, c).toString();
                sb.append(val);
                sb.append(",");
            }
            tableData[r] = sb.toString();
            sb.setLength(0);
        }

        return tableData;
    }

    public void filterCoursesByID(String id) {
        // Match all rows
        if (id.equals("")) {
            sorter.setRowFilter(null);
        }
        else {
            RowFilter<TableModel, Object> rf = RowFilter.regexFilter("^" + id + "$", 0);
            sorter.setRowFilter(rf);
        }
    }

}
