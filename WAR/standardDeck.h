// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// bsalexander
#ifndef STANDARD_DECK_H
#define STANDARD_DECK_H
#include "Deck.h"

class StandardDeck : public Deck
{
    public:
        StandardDeck();
        StandardDeck(const StandardDeck & sd);
        ~StandardDeck();

        bool mergeDecks(StandardDeck & other, bool shuffle);
        void initializeDeck() override;  // override instead of pure virtual (googled)
};

#endif
