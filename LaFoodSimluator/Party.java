public class Party {
    private int arrivalTime;
    private int partySize;
    private String partyName;

    public Party(int time, int size, String name) {
        arrivalTime = time;
        partySize = size;
        partyName = name;
    }

    // getters
    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getPartySize() {
        return partySize;
    }

    public String getName() {
        return partyName;
    }

    // setters
    public void setArrivalTime(int time) {
        arrivalTime = time;
    }

    public void setPartySize(int size) {
        partySize = size;
    }

    public void setName(String name) {
        partyName = name;
    }

    @Override //  <-- this is just something i looked up online to help with errors 
    public String toString() {
        return "Party " + partyName + " of " + partySize + " people (arrived at " + arrivalTime + ")";
    }
}

