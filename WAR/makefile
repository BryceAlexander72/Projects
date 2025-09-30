# Honor Pledge:
#
# I pledge that I have neither given nor 
# received any help on this assignment.
#
# bsalexander
WAR.exe: driver.o card.o standardDeck.o nonStandardDeck.o deck.o
	g++ driver.o card.o standardDeck.o nonStandardDeck.o deck.o -o WAR.exe

card.o: card.cpp 
	g++ -c card.cpp -o card.o

standardDeck.o: standardDeck.cpp 
	g++ -c standardDeck.cpp -o standardDeck.o

driver.o: driver.cpp 
	g++ -c driver.cpp -o driver.o

nonStandardDeck.o: nonStandardDeck.cpp 
	g++ -c nonStandardDeck.cpp -o nonStandardDeck.o

deck.o: deck.cpp 
	g++ -c deck.cpp -o deck.o

clean:
	rm -rf *.o WAR.exe

run: WAR.exe
	./WAR.exe

# Use 'make' to compile everything, use 'make run' to run the program.
