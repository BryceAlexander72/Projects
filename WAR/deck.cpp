// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// bsalexander
#include "Deck.h"

bool Deck::isEmpty() {
    return (numCards_ == 0);
}

int Deck::getNumCards() {
    return numCards_;
}

void Deck::displayCard(int i) {
    if (i >= 0 && i < numCards_) {
        std::cout << deck_[i].print() << std::endl;
    } else {
        std::cout << "Out of Bounds." << std::endl;
    }
}

void Deck::printDeck() {
    for (int i = 0; i < numCards_; i++) {
        std::cout << deck_[i].print() << std::endl;
    }
}

bool Deck::addCard(Card c) {
    deck_[numCards_] = c;
    numCards_++;
    return true;
}

void Deck::shuffle() {
    srand(time(NULL));
    int random_integer = 3 + rand() % 10;
    for (int x = 0; x < random_integer; x++) {
        std::random_device randomDevice;
        std::mt19937 randNum(randomDevice());
        std::shuffle(deck_, deck_ + numCards_, randNum);
    }
}

int Deck::getTopCard() {
    return numCards_ - 1;
}

Card Deck::dealCard() {
    if (numCards_ == 0) {
        return Card(0, 0); // returns a "blank" card
    }
    numCards_--;
    return deck_[numCards_];
}
