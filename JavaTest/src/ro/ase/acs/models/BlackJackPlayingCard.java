package ro.ase.acs.models;

import java.util.ArrayList;
import java.util.List;

public class BlackJackPlayingCard extends PlayingCard implements Cloneable {

    private int score;
    private static List<BlackJackPlayingCard> cards = new ArrayList<>();

    public BlackJackPlayingCard(String rank, String suit, int quantity) throws IllegalArgumentException {
        super(rank, suit, quantity);
        if (rank.equals("T")) {
            this.score = 10;
        } else if (rank.equals("A")) {
            this.score = 11;
        } else if (rank.equals("J")) {
            this.score = 12;
        } else if (rank.equals("Q")) {
            this.score = 13;
        } else if (rank.equals("K")) {
            this.score = 14;
        } else {
            this.score = Integer.valueOf(rank);
        }
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return this.suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean isGameOver(PlayingCard card) {
        if ((this.score * this.quantity) + (card.quantity * ((BlackJackPlayingCard) card).score) <= 21) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        BlackJackPlayingCard blackJackPlayingCard = (BlackJackPlayingCard) super.clone();
        blackJackPlayingCard.score = this.score;
        return blackJackPlayingCard;
    }

    @Override
    public float getPoints() {
        return this.score;
    }

    @Override
    public String toString() {
        return "> " + this.rank + " " + this.suit + " " + this.quantity + " " + this.score;
    }

    public static void drawCard(BlackJackPlayingCard card) {
        if (cards.contains(card)) {
            cards.get(cards.indexOf(card)).quantity += card.quantity;
        } else {
            cards.add(card);
        }
    }

    public static String getStatus() {
        int sum = 0;
        String result = "";
        for (int i = 0; i < cards.size() - 1; i++) {
            sum += (cards.get(i).quantity * cards.get(i).score) + (cards.get(i + 1).quantity * cards.get(i + 1).score);
            result += cards.get(i).toString() + "\n";
        }

        result += cards.get(cards.size() - 1).toString() + "\n";
        if (sum <= 21) {
            result += "\nstill in the game";
        } else {
            result += "\ngame over";
        }

        return result;
    }

}
