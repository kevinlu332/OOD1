package Blackjack;

import java.util.List;

public class TestBJSolution {
    public void testCard() throws Exception {
        Suit arr[] = Suit.values();
        for (int i = 1; i < 14; i++) {
            for (Suit j: arr) {
                Card c = new Card(i, j);
                check(c.value() == i);
            }
        }
    }
    public void testDeck() throws Exception {
        Deck d = new Deck();
        check(d.dealHand(1000) == null);
        for (int i = 1; i < 100; i++) {
            Card c = d.dealCard();
            if (i > 52) {
                check(c == null);
            }
        }
    }


    public void testHand() throws Exception {
        BlackJackHand h = new BlackJackHand();
        h.addCards(new Card[] {
                new Card(1, Suit.Heart), new Card(11, Suit.Heart)
        });
        check(h.isBlackJack() == true);
        h.addCards(new Card[] {
                new Card(1, Suit.Club)
        });
        check(h.isBlackJack() == false);
        check(h.busted() == false);
    }

    public void testAutomator() throws Exception {
        GameAutomator ga = new GameAutomator(4);
        for (int i = 0; i < 4; i++) {
            check(ga.getScore(i) == 0);
        }
        check(ga.simulate() == true);
        List< Integer > winners = ga.getWinners();
        check(winners.size() <= 4);
        int prev = -1;
        for (Integer i: winners) {
            int score = ga.getScore(i);
            check(score < 22);
            if (prev != -1) {
                check(score == prev);
            }
            prev = score;
        }
    }
    public void check(boolean pass) {
        if (!pass) throw new RuntimeException("Test failed");
    }

        public static void main(String[] args) throws Exception {
            TestBJSolution ts = new TestBJSolution();
        ts.testCard();
    }
}
