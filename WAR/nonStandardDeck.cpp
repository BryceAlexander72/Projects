// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// bsalexander
#include "nonStandardDeck.h"
#include <fstream>
#define DECK_SIZE 52

NonStandardDeck::NonStandardDeck() {
    deck_ = nullptr;
    numCards_ = 0;
}

NonStandardDeck::NonStandardDeck(const NonStandardDeck & sd) {
    numCards_ = sd.numCards_;
    deck_ = new Card[numCards_];
    for (int i = 0; i < numCards_; i++) {
        deck_[i] = sd.deck_[i];
    }
}

NonStandardDeck::~NonStandardDeck() {
    if (deck_ != nullptr) {
        delete[] deck_;
        deck_ = nullptr;
    }
}

//reading card values from text file called deck.txt
void NonStandardDeck::initializeDeck() 
{
    std::ifstream in("deck.txt");

    // first number in file is the deck size
    int deckSize;
    in >> deckSize;

    // clear old memory if needed
    if (deck_ != nullptr) {
        delete[] deck_;
        deck_ = nullptr;
    }

    deck_ = new Card[deckSize];
    numCards_ = deckSize;

    //reading in the values from the text file
    for (int i = 0; i < deckSize; i++)
    {
        int suit, face;
        in >> suit >> face;
        deck_[i] = Card(suit, face);
    }
    in.close();
}

