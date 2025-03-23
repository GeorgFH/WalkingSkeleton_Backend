package org.example.relocationservice;

import jakarta.persistence.*;

@Entity
public class RelocationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moveDate;
    private String name;

    @Column(name = "from_location")
    private String from;

    @Column(name = "to_location")
    private String to;

    private int amount;
    private int fromFloor;
    private boolean fromElevatorAvailable;
    private int toFloor;
    private boolean toElevatorAvailable;


    public RelocationRequest() {}

    // Getter und Setter
    public String getMoveDate() { return moveDate; }
    public void setMoveDate(String moveDate) { this.moveDate = moveDate; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public int getFromFloor() { return fromFloor; }
    public void setFromFloor(int fromFloor) { this.fromFloor = fromFloor; }

    public boolean isFromElevatorAvailable() { return fromElevatorAvailable; }
    public void setFromElevatorAvailable(boolean fromElevatorAvailable) { this.fromElevatorAvailable = fromElevatorAvailable; }

    public int getToFloor() { return toFloor; }
    public void setToFloor(int toFloor) { this.toFloor = toFloor; }

    public boolean isToElevatorAvailable() { return toElevatorAvailable; }
    public void setToElevatorAvailable(boolean toElevatorAvailable) { this.toElevatorAvailable = toElevatorAvailable; }

    @Override
    public String toString() {
        return "RelocationRequest{" +
                "moveDate='" + moveDate + '\'' +
                ", name='" + name + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                ", fromFloor=" + fromFloor +
                ", fromElevatorAvailable=" + fromElevatorAvailable +
                ", toFloor=" + toFloor +
                ", toElevatorAvailable=" + toElevatorAvailable +
                '}';
    }

}
