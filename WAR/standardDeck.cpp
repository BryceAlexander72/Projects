// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// bsalexander
#include "standardDeck.h"
#define DECK_SIZE 52

StandardDeck::StandardDeck() {
    deck_ = nullptr;
    numCards_ = 0;
}

StandardDeck::StandardDeck(const StandardDeck & sd) {
    numCards_ = sd.numCards_;
    deck_ = new Card[DECK_SIZE];
    for (int i = 0; i < numCards_; i++) {
        deck_[i] = sd.deck_[i];
    }
}

StandardDeck::~StandardDeck() {
    if (deck_ != nullptr) {
        delete[] deck_;
        deck_ = nullptr;
    }
}

bool StandardDeck::mergeDecks(StandardDeck & deck2_, bool shuffle)
{
    if (numCards_ < DECK_SIZE)
    {
        int spaceLeft = DECK_SIZE - numCards_; // how many cards we can still fit
        int cardsToTransfer = (deck2_.numCards_ < spaceLeft) ? deck2_.numCards_ : spaceLeft; // only take as many as deck2_ actually has

        // copy cards over one by one
        for (int i = 0; i < cardsToTransfer; i++)
        {
            addCard(deck2_.deck_[i]);
        }
        for (int i = cardsToTransfer; i < deck2_.numCards_; i++)
        {
            deck2_.deck_[i - cardsToTransfer] = deck2_.deck_[i]; // shift deck2_'s remaining cards down
        }
        deck2_.numCards_ -= cardsToTransfer;

        // shuffle this deck if requested
        if (shuffle)
        {
            this->shuffle();
        }
        return true;
    }
    else
    {
        return false;
    }
}

void StandardDeck::initializeDeck() {
    if (deck_ != nullptr) { //clearing memory if deck already exists
        delete[] deck_;
        deck_ = nullptr;
    }
    deck_ = new Card[DECK_SIZE];
    numCards_ = DECK_SIZE;
    int i = 0;
    for (int suit = 1; suit <= 4; suit++) {
        for (int face = 1; face <= 13; face++) {
            deck_[i] = Card(suit, face);
            i++;
        }
    }
}