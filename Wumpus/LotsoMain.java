import java.io.*;
import java.util.*;
//-------------------------------------------------------------------
//SKIN GUIDE: (TOY STORY)
//Wumpus = Lotso
//BigBabys = BigBaby
//Incinerator = Incinerator
//Supply Location = ToyChest
//Arrow = Beam(Buzz's beam to be exact)
//Bats = FlyingMonkeys
//Locations = Location (there are Locations in toy story so not much change here lol)
//Cave = Daycare
//-------------------------------------------------------------------
public class LotsoMain {
    public static void main(String[] args) {
		
		//loading my locations, the if statement is just checking to make sure everything loads properly
        Location[] locations = loadLocations("daycare.txt");
        if (locations == null) {
            System.out.println("Error loading locations.");
            return;
        }
        
		placeHazards(locations);
		
        Scanner scanner = new Scanner(System.in);
        int currentLocation = 0;
		int beams = 3;
        
		//while the conditions are true, the game prints the current Locations details and gives you the option to exit as well. 
		//the exit option is mainly just for me while testing the game
        while (true) {
			locations[currentLocation].printLocationDetails();
            checkHazards(locations, currentLocation);
            
            // checks if the player enters the toy chest and refills the beams
            if (locations[currentLocation].isToyChest()) {
                beams = 3;
				System.out.println("Welcome to the toy chest!");
                System.out.println("Your laser beams have been refilled!");
            }
			
			// checks if the player enters the flying monkeys Location
            if (locations[currentLocation].hasFlyingMonkeys()) {
                System.out.println("Flying Monkeys swoop in and carry you away!");
                currentLocation = (int) (Math.random() * locations.length);  // moves player to a random Location
                
                System.out.println("You've been dropped in location " + (currentLocation + 1));
				locations[currentLocation].printLocationDetails();
                
                // checks if the dropped location has a hazard
                checkHazards(locations, currentLocation);
            }
			
            System.out.println("Do you want to shoot beam? (yes/no)");
            String shotChoice = scanner.nextLine();
            
			//this is pretty self explanatory
			//asks the user if they wanna shoot, then it checks beam count, asks for location #, shoots into that location and checks for Lotso
			if (shotChoice.equals("yes")) {
                if (beams > 0) {
                    System.out.println("Enter the location number to shoot into:");
                    int targetLocation = scanner.nextInt();
                    scanner.nextLine(); //creates a newline
                    
                    if (isAdjacent(locations[currentLocation], targetLocation)) {
                        beams--;
                        if (locations[targetLocation - 1].hasLotso()) {
                            System.out.println("You shot Lotso! You win!");
                            break;
                        } 
						else {
                            System.out.println("Missed! You have " + beams + " beams left.");
                        }
                    } 
					else {
                        System.out.println("You can only shoot into adjacent Locations.");
						beams--;
                        System.out.println("You have lost the ability to shoot this turn, you have " +beams+ " beams left.");
                    }
                } 
				else {
                    System.out.println("No beams left!");
                }
            }
			
			//here is just moving locations or asking to exit the cave.
			System.out.println("Enter a location number to move to or type -1 to exit:");
            int nextLocation = scanner.nextInt(); 
            scanner.nextLine();
            
            if (nextLocation == -1) {
                System.out.println("Game Exited.");
                break;
            }
            if (isAdjacent(locations[currentLocation], nextLocation)) {
                currentLocation = nextLocation - 1;
            } else {
                System.out.println("You can't go there.");
            }
            
        }
        
        scanner.close();
    }
	
	//this function is for placing the hazards randomly
	public static void placeHazards(Location[] Locations) {
		//this excludes location 1 from being chosen as a hazard.
		//you wouldnt believe how many times it happens
		int[] availableLocations = new int[Locations.length - 1]; 
		int index = 0;
		for (int i = 1; i < Locations.length; i++) {
			availableLocations[index++] = i;
		}

		//this shuffles the locations and then gives the first one to Lotso, 2nd to the big babys, 
		//3rd to the Incinerator, 4th to the toy chest, and 5th to flying monkeys
		for (int i = availableLocations.length - 1; i > 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			int temp = availableLocations[i];
			availableLocations[i] = availableLocations[j];
			availableLocations[j] = temp;
		}

		Locations[availableLocations[0]].setHasLotso(true);
		Locations[availableLocations[1]].setHasBigBabys(true);
		Locations[availableLocations[2]].setHasIncinerator(true);
		Locations[availableLocations[3]].setIsToyChest(true);
		Locations[availableLocations[3]].setHasFlyingMonkeys(true);
		}
	
	//this big chunk of code is just adding all the fun text into the game
    public static void checkHazards(Location[] Locations, int currentLocation) {
        Location curlocation = Locations[currentLocation];
        if (curlocation.hasLotso()) {
            System.out.println("Lotso got you! Game over.");
            System.exit(0);
        } 
		else if (curlocation.hasBigBabys()) {
            System.out.println("You got taken by the Big Babys! Game over.");
            System.exit(0);
        } 
		else if (curlocation.hasIncinerator()) {
            System.out.println("You fell into the Incinerator! Game over.");
            System.exit(0);
        }
        
        for (int i = 0; i < curlocation.getAdjacentLocations().length; i++) {
            int adjacent = curlocation.getAdjacentLocations()[i];
			
            if (Locations[adjacent - 1].hasLotso()) {
                System.out.println("You can feel a boss's presence nearby.");
            }
            if (Locations[adjacent - 1].hasBigBabys()) {
                System.out.println("You hear crying in one of the locations.");
            }
            if (Locations[adjacent - 1].hasIncinerator()) {
                System.out.println("You feel immense heat.");
            }
        }
    }
    
    //this whole thing reads from my file, reads the number of locations and stores it, creates an array of locations, reads the location data, 
	// in each location it reads the number and the 3 adjacent locations, my j loop reads 3 ints from the file and stores them into the adjacent array
	//then i created my location object with the LocationNumber, adjacent array, and desc which is all stored in "Locations".
    public static Location[] loadLocations(String filename) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            int numLocations = fileScanner.nextInt();
            Location[] Locations = new Location[numLocations];
            
            for (int i = 0; i < numLocations; i++) {
                int LocationNumber = fileScanner.nextInt();
                int[] adjacent = new int[3];
                for (int j = 0; j < 3; j++) {
                    adjacent[j] = fileScanner.nextInt();
                }
                fileScanner.nextLine(); 
                String description = fileScanner.nextLine();
                
                Locations[i] = new Location(LocationNumber, adjacent, description);
            }
            
            fileScanner.close();
            return Locations;
        } catch (FileNotFoundException e) { //this helps to catch any exceptions to prevent errors
            System.out.println("File not found: " + filename);
            return null;
        }
    }
    
	//the int calls my adjacent location array to get those three nums, then it loops through the adj array and
	//if nextLocation matches any of the location nums in the adjacency list, then it will return it as true.
	//Pretty much just makes sure that the player can move to the nextLocation easily peasily
    public static boolean isAdjacent(Location location, int nextLocation) {
        int[] adjacent = location.getAdjacentLocations();
        for (int i = 0; i < adjacent.length; i++) {
            if (adjacent[i] == nextLocation) {
                return true;
            }
        }
        return false;
    }
}