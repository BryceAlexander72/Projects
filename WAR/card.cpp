// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// bsalexander
#include "card.h"

std::string Card::SUIT[] = {"No Suit", "Spades", "Hearts", "Diamonds", "Clubs"};

std::string Card::FACE[] = {"Joker", "Ace", "Two", "Three", "Four", "Five", "Six",  
                       "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

Card::Card() : suitVal_(0), faceVal_(0) {}
Card::~Card() {}

Card::Card(int suit, int face) {
    suitVal_ = suit;
    faceVal_ = face;
}
std::string Card::getSuit(){
    return SUIT[suitVal_];
}
std::string Card::getFace(){
    return FACE[faceVal_];
}
std::string Card::print(){
    return getFace() + " of " + getSuit();
}
void Card::initialize(int suit, int face){
    suitVal_ = suit;
    faceVal_ = face;
}