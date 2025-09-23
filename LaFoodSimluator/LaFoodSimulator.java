import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LaFoodSimulator {
	//these are my variables that will hold the information given by the file
    private Queue waitingList;
    private int totalWaitingTime;
    private int totalPeopleSeated;
	
	//this sets the base values for said variables
    public LaFoodSimulator() {
        waitingList = new QueueLL();
        totalWaitingTime = 0;
        totalPeopleSeated = 0;
    }

    public void simulate(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" "); //the line.split splits the line in the file into different parts so that they can be read and stored sepertaely
				
				//this is reading the whole line in the case of "A", for parts 1 and 2 in the array of data. they are ints that are currently stored as strings. thats why im parsing them as ints...because theyre ints. part 3 doesnt need to be parsed because it needs to remain as a string
                switch (parts[0]) {
                    case "A": 
                        handleArrival(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]); 
                        break;
                    case "T":
                        handleTable(Integer.parseInt(parts[1]));
                        break;
                    case "Q":
                        printResults();
                        return;
                }
            }  //exception catching ofc
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

	//this is handling the arrival of new parties by enqueueing them
    private void handleArrival(int time, int size, String name) {
        Party party = new Party(time, size, name);
        waitingList.enqueue(party);
        System.out.println("Please wait at the bar, " + party + " (your wait time is: " + time + " minutes.)");
    }

	//checks if the waiting list is not empty and then if its true, then it dequeues a party and does all the fun math for wait times and stuff
    private void handleTable(int time) {
        if (!waitingList.isEmpty()) {
            Party seatedParty = (Party) waitingList.dequeue();
            int waitTime = time - seatedParty.getArrivalTime();
            totalWaitingTime += waitTime * seatedParty.getPartySize();
            totalPeopleSeated += seatedParty.getPartySize();
            System.out.println("Table for " + seatedParty.getName() + "! (you waited: " + time + " minutes)");
        }
    }
	
	//this calculates avg waiting time, and prints it
    private void printResults() {
        System.out.println("End of simulation. \n");
        if (totalPeopleSeated > 0) {
            double averageWaitingTime = (double) totalWaitingTime / totalPeopleSeated;
            System.out.printf("The average waiting time was: " +averageWaitingTime+ " minutes.");
        }
        
        if (!waitingList.isEmpty()) {
            System.out.println("The following parties were never seated:");
            while (!waitingList.isEmpty()) {
                System.out.println("  " + waitingList.dequeue());
            }
        }
        
        System.out.println("Have a nice meal!");
    }

	//my main function just introduces the "player" and reads all info from the file after asking for the filename
    public static void main(String[] args) {
        System.out.println("Welcome to the La Food Restaurant Simulator!");
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter data file name: ");
        String filename = inputScanner.nextLine();
        
        LaFoodSimulator simulator = new LaFoodSimulator();
        simulator.simulate(filename);
        
        inputScanner.close();
    }
}

