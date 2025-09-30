// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// bsalexander
#include "standardDeck.h"
#include "nonStandardDeck.h"
#include <iostream>

int main(){
    int simNum = 0;
    int p1ArmyCount = 0, p2ArmyCount = 0, p1WinCount = 0, p2WinCount = 0, p1Avg = 0, p2Avg = 0;

    //deck declaration, uncomment whichever deck you want to use
    //StandardDeck deck_; 
    //StandardDeck deck2_;
    NonStandardDeck deck_;
    NonStandardDeck deck2_;

    std::cout << "How many simulations of War would you like to run? " << std::endl;
    std::cin >> simNum; //user input for number of simulations
    
    deck_.initializeDeck();
    deck2_.initializeDeck();
    //deck_.printDeck(); //uncomment to see the full deck
    deck_.shuffle(); //shuffling the main deck
    //deck_.mergeDecks(deck2_, false); //merging deck 2 with deck one if possible

    //---------------------------------------------------------------------------------------
    //WAR GAME CODE
    //---------------------------------------------------------------------------------------

    //intial dealing of cards to players 1 and 2, starting the game
    for(int k = 0; k < simNum; k++)
    {
        deck_.initializeDeck();
        deck_.shuffle();
        int deckSize = deck_.getNumCards();

        Card* player1 = new Card[deckSize];
        Card* player2 = new Card[deckSize];
        Card* battleground = new Card[deckSize];

        int player1Size = 0;
        int player2Size = 0;
        int battlegroundSize = 0;
        int turnCount = 0;
        //stops game if it goes on for too long
        const int MAX_TURNS = deckSize * 10; 

        //deals the first half of the deck to player 1
        for(int j = 0; j < deckSize / 2; j++) 
        {
            player1[player1Size++] = deck_.dealCard();
        }
        //deals the second half of the deck to player 2
        for(int j = deckSize / 2; j < deckSize; j++) 
        {
            player2[player2Size++] = deck_.dealCard();
        }

        //shuffling both players hands before starting the game, complicated syntax found online and from tutor
        std::random_device rd;
        std::mt19937 g(rd()); 
        std::shuffle(player1, player1 + player1Size, g);
        std::shuffle(player2, player2 + player2Size, g);

        //makes it so that player 1 starts the game
        bool player1Turn = true; 
        bool player2Turn = false;

        //starting the current round
        while(player1Size > 0 && player2Size > 0 && turnCount < MAX_TURNS)
        {
            turnCount++;
            //making a new card object to keep track of what card was just played
            Card playedCard; 
            if(player1Turn == true)
            {
                //sets the played card to the top card in player 1's hand, index 0
                playedCard = player1[0]; 
                // shift all cards left in the array
                for(int i = 1; i < player1Size; i++) player1[i-1] = player1[i]; 
                --player1Size;
            }
            else
            {
                playedCard = player2[0];
                for(int i = 1; i < player2Size; i++) player2[i-1] = player2[i];
                --player2Size;
            }

            //adding the played card to the battleground array
            if (battlegroundSize < deckSize) {
                battleground[battlegroundSize++] = playedCard; 
            }
        
            //checking for played card to match top card on battleground
            if(battlegroundSize >= 2)
            {   
                //just placed card
                Card& top = battleground[battlegroundSize - 1];
                //previous top card
                Card& belowTop = battleground[battlegroundSize - 2]; 

                //matching cards face values to see if they are the same
                if(top.getFace() == belowTop.getFace())
                {
                    if(player1Turn == true) 
                    {
                        //giving all the cards in the battleground to player 1
                        for (int i = 0; i < battlegroundSize; i++) {
                            if(player1Size < deckSize) 
                            {
                                player1[player1Size++] = battleground[i];
                            }
                        }
                        //switching players turn so that when the round ends, 
                        //the turn will be switched back to the player who won the round
                        player1Turn = !player1Turn;
                    }
                    else
                    {
                        for (int i = 0; i < battlegroundSize; i++) {
                            if(player2Size < deckSize) 
                            {
                                player2[player2Size++] = battleground[i];
                            }
                        }
                        player1Turn = !player1Turn;
                    }
                    //resetting the battleground for the next round
                    battlegroundSize = 0;
                }
            }
            //switches the players turn
            player1Turn = !player1Turn; 
            
            //checking card counts to see if a player has won
            if(player2Size == 0)
            {
                p1ArmyCount = player1Size + p1ArmyCount;
                //uncomment next line to see who wins each round
                //std::cout << "Player 1 Wins!" << std::endl; 
                //keeping track of total wins for averages
                p1WinCount++; 
                break;
            }
            else if(player1Size == 0)
            {
                p2ArmyCount = player2Size + p2ArmyCount;
                //std::cout << "Player 2 Wins!" << std::endl; 
                p2WinCount++;
                break;
            }
        }
        if(turnCount >= MAX_TURNS)
        {
            //uncomment to see if the game ended in a draw
            //std::cout << "Simluation " << k + 1 << " ended in a draw." << std::endl; 
        }
        //freeing memory 
        delete[] player1;
        delete[] player2;
        delete[] battleground;
    }
    
    //determing the overall winner of all the simulations played
    if(p1WinCount > p2WinCount) 
    {
        std::cout << "Player 1 is the champion with " << p1WinCount << " victories over Player 2." << std::endl;
    }
    else if(p2WinCount > p1WinCount)
    {
        std::cout << "Player 2 is the champion with " << p2WinCount << " victories over Player 1." << std::endl;
    }
    else
    {
        std::cout << "It's a tie!" << std::endl;
    }

    //preventing division by zero
    if(p1WinCount != 0 && p2WinCount != 0) 
    {
        p1Avg = p1ArmyCount / p1WinCount;
        p2Avg = p2ArmyCount / p2WinCount;
    }
    else{
        std::cout << "One of the players never won a game, so average army size cannot be calculated." << std::endl;
    }

    //printing the average army size of each player
    std::cout << "Player 1 average score: " << p1Avg << std::endl; 
    std::cout << "Player 2 average score: " << p2Avg << std::endl;
    
    //---------------------------------------------------------------------------------------
    //END OF WAR CODE
    //---------------------------------------------------------------------------------------

    return 0;
}
