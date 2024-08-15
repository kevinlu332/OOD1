package Blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//For a deck, it should have 52 cards.
//          ( 2-10 + J + Q + K + A) * (Heart, Diamond, Club, Spades).
//
//It should be able to be shuffled
//  to ensure each time its sequence is different.
//
//It should be able to deal several cards at a time
//          as well as deal one card per time.
public class Deck {
    private final List<Card> cards;
    private static final Random r = new Random();

    //cards shall not be deleted; but it can have a "boarder" to show
    // which side is dealt, which side is for u to still have
    private int dealIndex = 0; // boarder////////////////////////

    public Deck(){
        cards = new ArrayList<>();
        for(int i =1; i <=13; i++){
            for(Suit suit: Suit.values()){
                cards.add(new Card(i, suit));
            }
        }
    }

    public void shuffle(){
        for(int i = 0; i < cards.size()-1; i++){
            int j = r.nextInt(cards.size()-i)+i;
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public int remainCards(){
        return cards.size() - dealIndex;
    }

    public Card[] dealHand(int number){
        if(remainCards() < number) return null;
        Card[] res = new Card[number];
        for(int i = 0; i < number; i++){
            res[i] = dealCard();
        }
        return res;
    }

    public Card dealCard(){
        return remainCards()==0? null: cards.get(dealIndex++);
    }

}
