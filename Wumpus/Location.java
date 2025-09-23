public class Location {
    private int locationNumber;
    private int[] adjacentLocations;
    private String description;
    private boolean hasLotso;
    private boolean hasBigBabys;
    private boolean hasIncinerator;
    private boolean isVisited;
	private boolean isToyChest;
	private boolean hasFlyingMonkeys;

    // this is my constructor that assigns variable names to those three things
    Location(int number, int[] locations, String desc) {
        locationNumber = number;
        adjacentLocations = locations;
        description = desc;
    }

    // these are my getters 
    public int getlocationNumber() {
        return locationNumber;
    }
    public int[] getAdjacentLocations() {
        return adjacentLocations;
    }
    public String getDescription() {
        return description;
    }
    public boolean hasLotso() {
        return hasLotso;
    }
    public boolean hasBigBabys() {
        return hasBigBabys;
    }
    public boolean hasIncinerator() {
        return hasIncinerator;
    }
    public boolean isVisited() {
        return isVisited;
    }
	public boolean isToyChest() {
        return isToyChest;
    }
	public boolean hasFlyingMonkeys() {
        return hasFlyingMonkeys;
    }

    // these are my setters that sets the value of true or false into the new names -> hasSomething, so that the locations know if they contain something
    public void setHasLotso(boolean value) {
        hasLotso = value;
    }
    public void setHasBigBabys(boolean value) {
        hasBigBabys = value;
    }
    public void setHasIncinerator(boolean value) {
        hasIncinerator = value;
    }
    public void visitlocation() {
        isVisited = true;
    }
	public void setIsToyChest(boolean value) {
        isToyChest = value;
    }
	public void setHasFlyingMonkeys(boolean value) {
        hasFlyingMonkeys = value;
    }

    // this prints the location details
    public void printLocationDetails() {
        System.out.println("Location " + locationNumber + ": " + description);
        System.out.print("Adjacent locations: ");
        for (int i = 0; i < adjacentLocations.length; i++) {
            System.out.print(adjacentLocations[i] + " ");
        }
        System.out.println();

        // this prints the hazard information
        if (hasLotso) {
            System.out.println("LOTSO'S HERE!");
        }
        if (hasBigBabys) {
            System.out.println("There are some Big Babys here!");
        }
        if (hasIncinerator) {
            System.out.println("There is a fiery Incinerator here!");
        }
		if (hasFlyingMonkeys) {
            System.out.println("There are crazy Flying Monkeys here!");
        }
    }
}
