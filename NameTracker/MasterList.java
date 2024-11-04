import java.util.ArrayList;
import java.util.stream.Stream;

public class MasterList {

    MasterNode headMasterNode; // Head node of the linked list containing name data

    ArrayList<YearNumber> yearNumbers = new ArrayList<>(); // List to track the number of occurrences of names per year

    // Calculates the total number of occurrences of names per year and stores the results in yearNumbers
    public void calculateYearNumbers() {
        for (MasterNode current = headMasterNode; current != null; current = current.nextMasterNode) {
            for (NameYearInformaions nameYearInfo : current.name.nameYearInformaions()) {
                YearNumber yearNumber = findOrCreateYearNumber(nameYearInfo.year());
                yearNumber.add(nameYearInfo.number());
            }
        }
    }

    // Finds a specific YearNumber object for a given year
    public YearNumber findYearNumber(int year) {
        return yearNumbers.stream().filter(yn -> yn.getYear() == year).findFirst().orElse(null);
    }

    // Finds or creates a YearNumber object for a specific year
    private YearNumber findOrCreateYearNumber(int year) {
        YearNumber yearNumber = findYearNumber(year);
        if (yearNumber == null) {
            yearNumber = new YearNumber(year, 0);
            yearNumbers.add(yearNumber);
        }
        return yearNumber;
    }

    // Returns the rank of a given MasterNode within the master list
    public int getMasterListRank(MasterNode masterNode) {
        int rank = 1;
        for (MasterNode current = headMasterNode; current != null; current = current.nextMasterNode) {
            if (current == masterNode) return rank;
            rank++;
        }
        return -1;
    }

    // Returns the total number of occurrences of the given name across all years
    public int getNumberOfNode(MasterNode masterNode) {
        return masterNode.name.nameYearInformaions().stream().mapToInt(NameYearInformaions::number).sum();
    }

    // Returns the rank of the given node based on its total count compared to other nodes
    public int getTotalRank(MasterNode masterNode) {
        return (int) headMasterNodeStream().filter(current -> getNumberOfNode(current) > getNumberOfNode(masterNode)).count() + 1;
    }

    // Returns the total number of occurrences across all years
    public int findYearNumberTotal() {
        return yearNumbers.stream().mapToInt(YearNumber::getNumber).sum();
    }

    // Adds a new MasterNode to the master list, keeping the list sorted by name
    public void add(MasterNode newMasterNode) {
        if (headMasterNode == null) {
            headMasterNode = newMasterNode;
            return;
        }

        MasterNode current = headMasterNode, previous = null;
        while (current != null && newMasterNode.name.name().compareTo(current.name.name()) > 0) {
            previous = current;
            current = current.nextMasterNode;
        }

        // If the name already exists, merge the new data with the existing node
        if (current != null && current.name.name().equals(newMasterNode.name.name())) {
            current.name.nameYearInformaions().addAll(newMasterNode.name.nameYearInformaions());
        } else {
            // Insert the new node into the sorted list
            newMasterNode.nextMasterNode = current;
            if (previous == null) {
                headMasterNode = newMasterNode;
            } else {
                previous.nextMasterNode = newMasterNode;
            }
        }
    }

    // Retrieves the MasterNode corresponding to a specific baby (name and gender)
    public MasterNode getMasterNode(Baby baby) {
        return headMasterNodeStream().filter(current -> current.name.name().equals(baby.name())).findFirst().orElse(null);
    }

    // Helper method to create a stream of MasterNodes from the linked list
    private Stream<MasterNode> headMasterNodeStream() {
        ArrayList<MasterNode> nodes = new ArrayList<>();
        for (MasterNode current = headMasterNode; current != null; current = current.nextMasterNode) {
            nodes.add(current);
        }
        return nodes.stream();
    }
}
