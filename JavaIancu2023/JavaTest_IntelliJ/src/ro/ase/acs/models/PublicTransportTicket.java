package ro.ase.acs.models;

import java.util.Objects;

import ro.ase.acs.contracts.Buyable;

public abstract class PublicTransportTicket implements Buyable {
    protected String departure;
    protected String destination;
    protected int distance;

    public PublicTransportTicket(String departure, String destination, int distance) {
        this.departure = departure;
        this.destination = destination;
        this.distance = distance;
    }

    @Override
    public float getPrice() {
        return 5;
    }

    public abstract float getDiscount();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        PublicTransportTicket ticket = (PublicTransportTicket) o;

        return Objects.equals(this.departure, ticket.departure) && Objects.equals(this.destination, ticket.destination);
    }
}
