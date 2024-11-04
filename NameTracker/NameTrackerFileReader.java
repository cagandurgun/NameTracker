import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NameTrackerFileReader {

    // Reads a CSV file and returns a list of MasterNodes based on the gender
    public ArrayList<MasterNode> getMasterNodes(String fileName, Gender gender) {
        ArrayList<MasterNode> masterNodes = new ArrayList<>();
        String csvSplitBy = ",";
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                masterNodes.add(createMasterNode(fileName, data, gender));
            }
        } catch (IOException ignored) {}

        return masterNodes;
    }

    // Creates a MasterNode from a CSV row based on the gender
    private MasterNode createMasterNode(String fileName, String[] data, Gender gender) {
        ArrayList<NameYearInformaions> nameYearInformaionsArrayList = new ArrayList<>();
        int year = Integer.parseInt(fileName.substring(5, fileName.length() - 4)); // Extract year from file name
        int rank = Integer.parseInt(data[0]); // Extract rank from CSV data
        int count = gender.equals(Gender.MALE) ? Integer.parseInt(data[2]) : Integer.parseInt(data[4]); // Extract count based on gender
        String name = gender.equals(Gender.MALE) ? data[1] : data[3]; // Extract name based on gender

        nameYearInformaionsArrayList.add(new NameYearInformaions(year, rank, count));
        return new MasterNode(new Name(name, nameYearInformaionsArrayList));
    }
}
