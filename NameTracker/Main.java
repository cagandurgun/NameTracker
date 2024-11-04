import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // List to store file names that contain names to track
        ArrayList<String> filesToTrack = new ArrayList<>();
        // List to store Baby objects that represent names to track
        ArrayList<Baby> namesToTrack = new ArrayList<>();
        // Master lists to track names for female and male respectively
        MasterList femaleMasterList = new MasterList();
        MasterList maleMasterList = new MasterList();

        // Parsing the input arguments
        int argumentCounter = 0;
        while (argumentCounter < args.length) {
            String argument = args[argumentCounter];
            // If the argument is a gender flag (-f or -m), create a Baby object and add to the namesToTrack list
            switch (argument) {
                case "-f", "-m" -> namesToTrack.add(new Baby(args[++argumentCounter], argument.equals("-m") ? Gender.MALE : Gender.FEMALE));
                default -> {
                    // If the argument matches the pattern for name files (e.g., namesXXXX.csv), add it to filesToTrack
                    if (argument.matches("names\\d{4}\\.csv")) {
                        filesToTrack.add(argument);
                    }
                }
            }
            argumentCounter++;
        }

        // Process each file and populate the male and female master lists with data
        for(String fileName: filesToTrack) {
            // For female names
            for(MasterNode masterNode: new NameTrackerFileReader().getMasterNodes(fileName, Gender.FEMALE)) {
                femaleMasterList.add(masterNode);
            }
            // For male names
            for(MasterNode masterNode: new NameTrackerFileReader().getMasterNodes(fileName, Gender.MALE)) {
                maleMasterList.add(masterNode);
            }
        }

        // Calculate the year totals for each list (female and male)
        femaleMasterList.calculateYearNumbers();
        maleMasterList.calculateYearNumbers();

        // For each Baby object, find and print statistics about the name
        for (Baby baby : namesToTrack) {
            // Determine if the baby is male or female, and select the appropriate master list
            MasterList currentMasterList = baby.gender().equals(Gender.MALE) ? maleMasterList : femaleMasterList;
            // Get the MasterNode for the baby name
            MasterNode masterNode = currentMasterList.getMasterNode(baby);
            if (masterNode == null) continue;

            // Print the baby's name and master list rank
            System.out.println("\n" + masterNode.name.name() + "'s master list rank: " + currentMasterList.getMasterListRank(masterNode) + "\n");

            // Print statistics (year, rank, count, and frequency) for each year the name appears
            masterNode.name.nameYearInformaions().forEach(info -> {
                double frequency = (double) info.number() / currentMasterList.findYearNumber(info.year()).getNumber();
                System.out.println(info.year() + "\n" + masterNode.name.name() + ": " + info.rank() + ", " + info.number() + ", " + String.format("%.6f%n", frequency));
            });

            // Calculate and print the total count and frequency of the name over all years
            int totalNumber = currentMasterList.getNumberOfNode(masterNode);
            double totalFrequency = (double) totalNumber / currentMasterList.findYearNumberTotal();
            System.out.print("Total\n" + masterNode.name.name() + ": " + currentMasterList.getTotalRank(masterNode) + ", " + totalNumber + ", " + String.format("%.6f%n", totalFrequency));
        }
    }
}
