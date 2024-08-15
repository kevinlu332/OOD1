package Blackjack;

import Blackjack.BlackJackHand;
import Blackjack.Card;
import Blackjack.Deck;

import java.util.ArrayList;
import java.util.List;

public class GameAutomator {
    private Deck deck;
    private BlackJackHand[] hands;
    private static final int HIT_UNTIL = 16;
    public GameAutomator(int numPlayers) {
        hands = new BlackJackHand[numPlayers];
        for(int i = 0; i<hands.length; i++){
            hands[i] = new BlackJackHand();
        }
    }


    List<Integer> getWinners() {
        List<Integer> winners = new ArrayList<>();
        int winnerScore = 0;
        for(int i = 0; i<hands.length; i++){
            if(!hands[i].busted()){
                int currScore = hands[i].score();
                if(currScore > winnerScore){
                    winners.clear();
                    winners.add(i);
                    winnerScore = currScore;
                }else if(currScore == winnerScore) winners.add(i);
            }
        }
        return winners;
    }

    public int getScore(int playerID) {
        return hands[playerID].score();
    }

    // return false if the game cannot be successfully simulated.
    public boolean simulate() {
        initializeDeck();
        boolean check1  = dealInitial();
        if(!check1){
            return false;
        }
        List<Integer> blackjacks = getBlackJacks();
        if(blackjacks.size()>0){
            return true;
        }
        boolean check2 = playAllHands();
        if(!check2) return false;
        List<Integer> winners = getWinners();
        return true;
    }

    private void initializeDeck(){
        deck = new Deck();
        deck.shuffle();
    }
    private boolean dealInitial(){
        for(BlackJackHand hand: hands){
            Card[] cards = deck.dealHand(2);
            if(cards==null) return false;
            hand.addCards(cards);
        }
        return true;
    }
    private List<Integer> getBlackJacks(){
        List<Integer> winners = new ArrayList<>();
        for(int i = 0; i<hands.length; i++){
            if(hands[i].isBlackJack()) winners.add(i);
        }
        return winners;
    }
    private boolean playAllHands(){
        for(BlackJackHand hand: hands){
            if(!playHand(hand)) return false;
        }
        return true;
    }
    private boolean playHand(BlackJackHand hand){
        while(hand.score() <HIT_UNTIL){
            Card card = deck.dealCard();
            if(card==null) return false;
            hand.addCards(new Card[]{card});
        }
        return true;
    }
}