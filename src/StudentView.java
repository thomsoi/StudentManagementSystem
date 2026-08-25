
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

public class StudentView extends JPanel {
    
    private final DefaultTableModel model;
    private final JTable studentsTable;
    private final String STUDENTS_DATA_FILE_PATH = "./StudentsData.txt";
    private final TableRowSorter<TableModel> sorter;

    public StudentView() {
        this.setBackground(new Color(10, 130 ,150));
        this.setLayout(new BorderLayout()); // using border layout means this changes when the window is resized
        String[] columnNames = {"Student ID", "Student name", "Date of birth",
            "Gender", "Email", "Phone number", "Address line 1", "Address line 2"};
        
        model = new DefaultTableModel(new Object[0][columnNames.length], columnNames);
        studentsTable = new JTable(model);
        sorter = new TableRowSorter<>(model);
        studentsTable.setRowSorter(sorter);
        this.add(new JScrollPane(studentsTable), BorderLayout.CENTER); // use JScrollPane for correct formatting
    }

    public String getFileDataPath() {
        return this.STUDENTS_DATA_FILE_PATH;
    }

    public void displayStudent(Student s) {
        String studentName = s.getStudentFirstName() + " " + s.getStudentLastName();

        model.addRow(new Object[] {
            s.getStudentID(),
            studentName,
            s.getStudentDOB(),
            s.getStudentGender(),
            s.getStudentEmail(),
            s.getStudentPhoneNumber(),
            s.getStudentAddress1(),
            s.getStudentAddress2()
        });
    }

    public Boolean removeStudent() {
        int selectedRow = studentsTable.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
            return true;
        }
        return false;
    }

    public Boolean clearStudents() {
        if (model.getRowCount() == 0) {
            return false;
        }
        model.setRowCount(0);
        File f = new File(STUDENTS_DATA_FILE_PATH);
        f.delete();
        return true;
    }

    public void populateStudentTableData() {
        SaveData s = new SaveData();
        ArrayList<String> unprocessedData = s.readFromFile(STUDENTS_DATA_FILE_PATH);

        if (unprocessedData == null) {
            return;
        }

        ArrayList<String[]> processedData = s.splitData(unprocessedData);

        for (int r = 0; r < processedData.size(); r++) {
            model.addRow(processedData.get(r));
        }
    }

    public String[] getStudentTableData() {
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

    public Boolean saveStudentData() {
        SaveData s = new SaveData();
        String[] tableData = getStudentTableData();
        return s.writeToFile(STUDENTS_DATA_FILE_PATH, tableData);
    }

    public void filterStudentsByID(String id) {
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
