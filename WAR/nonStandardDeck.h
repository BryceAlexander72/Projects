// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
// bsalexander
#ifndef NON_STANDARD_DECK_H
#define NON_STANDARD_DECK_H
#include "Deck.h"
#define DECK_SIZE 52

/**
 * @class nonStandardDeck
 *
 * The nonStandardDeck class represents a standard deck of 52 cards.
 * 
 */
class NonStandardDeck : public Deck
{
	public:
		/// Default constructor.
		NonStandardDeck();
		
		NonStandardDeck(const NonStandardDeck & sd);
		
		/// Default destructor.
		~NonStandardDeck();

        void initializeDeck() override;
};
#endif