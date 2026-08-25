import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveData {

    public Boolean writeToFile(String fileName, String[] tableData) {
        if (tableData == null) {
            return false;
        }
        
        try (FileWriter myWriter = new FileWriter(fileName);) {
            for (String s : tableData) {
                myWriter.write(s);
                myWriter.write("\n");
            }
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    public ArrayList<String> readFromFile(String fileName) {
        File f = new File(fileName);
        ArrayList<String> fileContents = new ArrayList<>();

        try (Scanner r = new Scanner(f)) {
            while (r.hasNextLine()) {
                fileContents.add(r.nextLine());
            }
            return fileContents;
        }
        catch (FileNotFoundException e) {
            return null;
        }
    }

    public ArrayList<String[]> splitData(ArrayList<String> fileContents) {
        if (fileContents == null) {
            return null;
        }
        
        ArrayList<String[]> tableData = new ArrayList<>();

        for (String s : fileContents) {
            String[] row = s.split(",");
            tableData.add(row);
        }

        return tableData;
    }

}
