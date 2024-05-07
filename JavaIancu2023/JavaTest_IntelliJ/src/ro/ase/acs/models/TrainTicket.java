package ro.ase.acs.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TrainTicket extends PublicTransportTicket implements Cloneable {
    private TrainType trainType;
    private static List<TrainTicket> tickets = new ArrayList<>();

    public TrainTicket(String departure, String destination, int distance, TrainType trainType) {
        super(departure, destination, distance);
        this.trainType = trainType;
    }

    @Override
    public float getDiscount() {
        if (this.trainType == TrainType.REGIO) {
            return 0.5f;
        } else if (this.trainType == TrainType.INTERREGIO) {
            return 0.25f;
        }
        return 0f;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        TrainTicket clone = (TrainTicket) super.clone();
        clone.trainType = this.trainType;
        return clone;
    }

    public String getDeparture() {
        return this.departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public TrainType getTrainType() {
        return trainType;
    }

    public void setTrainType(TrainType trainType) {
        this.trainType = trainType;
    }

    @Override
    public float getPrice() {
        return super.getPrice() * Float.valueOf(this.getDistance()) * Float.valueOf(this.getDiscount());
    }

    @Override
    public String toString() {
        return "> " + this.getDeparture() + " " + this.getDestination() + " " + this.getDistance();
    }

    public static void issueTicket(TrainTicket ticket) {
        if (!tickets.contains(ticket)) {
            tickets.add(ticket);
        }
    }

    public static Collection<TrainTicket> getTickets() {
        return TrainTicket.tickets;
    }
}
