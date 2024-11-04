// Class representing a node in the master list, which stores name data and a link to the next node
public class MasterNode {
    Name name;
    MasterNode nextMasterNode;

    public MasterNode(Name name) {
        this.name = name;
        this.nextMasterNode = null;
    }
}
