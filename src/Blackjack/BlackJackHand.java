package Blackjack;

import SimplifiedParkingLot.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//A Blackjack hand should be able to reflect the state of itself,
//  i.e. score, size, isBlackJack, isBusted...
//  and to receive new cards when it has not busted.
public class BlackJackHand {
    protected final List<Card> cards = new ArrayList<>();

    public int score(){
        List<Integer> scores = getPossibleScores();
        int maxUnder = Integer.MIN_VALUE;
        int minOver = Integer.MAX_VALUE;
        for(int s: scores){
            if(s>21 && s<minOver){
                minOver = s;
            }else if(s<=21 && s> maxUnder) maxUnder = s;
        }
        return maxUnder == Integer.MIN_VALUE?  minOver: maxUnder;
    }
    private List<Integer> getPossibleScores(){
        List<Integer> scores = new ArrayList<>();
        dfs(scores , 0, 0);
        return scores;
    }
    private void dfs(List<Integer> scores, int index, int subScore){
        if(index == cards.size()){
            scores.add(subScore);
            return;
        }
        int faceValue = cards.get(index).value();
        int[] scoreValues = getScoreValue(faceValue);
        for(int s: scoreValues){
            dfs(scores, index+1, subScore + s);
        }
    }
    private int[] getScoreValue(int faceValue){
        if(faceValue>1) return new int[]{Math.min(10, faceValue)};
        else return new int[]{1,11};
    }
    //-----------------------------------
    public void addCards(Card[] c){
        Collections.addAll(cards, c);
    }
    public int size(){
        return cards.size();
    }
    public boolean busted() {
        return score()>21;
    }
    //--------------------------------
    public boolean isBlackJack() {
        if (cards.size() != 2) return false;
        //两张牌，一张Ace，一张 10或花脸
        Card first = cards.get(0);
        Card second = cards.get(1);
        return isAce(first) && is10UpCard(second) ||
                isAce(second) && is10UpCard(first);
    }

    private boolean isAce(Card c) {
        return c.value() == 1;
    }

    private boolean is10UpCard(Card c) {
        return c.value() >= 10 && c.value() <= 13;
    }

}
