package ro.ase.acs.models;

import ro.ase.acs.contracts.Playable;

public abstract class PlayingCard implements Playable {
    protected String rank;
    protected String suit;
    protected int quantity;

    public PlayingCard(String rank, String suit, int quantity) throws IllegalArgumentException {
        this.rank = rank;
        // this.suit = suit;
        if (suit.equals("S") || suit.equals("D") || suit.equals("H") ||
                suit.equals("C")) {
            this.suit = suit;
        } else {
            throw new IllegalArgumentException("Suit is invalid!");
        }

        this.quantity = quantity;

    }

    @Override
    public float getPoints() {
        return 0;
    }

    public abstract boolean isGameOver(PlayingCard card);

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayingCard other = (PlayingCard) obj;
        if (rank == null) {
            if (other.rank != null)
                return false;
        } else if (!rank.equals(other.rank))
            return false;
        if (suit == null) {
            if (other.suit != null)
                return false;
        } else if (!suit.equals(other.suit))
            return false;
        return true;
    }

}
