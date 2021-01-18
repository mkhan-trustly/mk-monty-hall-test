package se.comhem.test.montyhall.model;

public class Door {

    private int id;
    private Deal deal;
    private enum Deal {
        GOAT,
        CAR
    }
    private Status status;
    private enum Status {
        AVAILABLE,
        CHOSEN_BY_PLAYER,
        OPENED_BY_HOST
    }

    public Door(int id) {
        this.id = id;
        this.deal = Deal.GOAT;
        this.status = Status.AVAILABLE;
    }

    public int getId() {
        return this.id;
    }

    public String getDisplayName() {
        return "[Door " + this.id + "]";
    }

    public Door replaceWithAWinningDeal() {
        this.deal = Deal.CAR;
        return this;
    }

    public Door registerSelectedByPlayer() {
        this.status = Status.CHOSEN_BY_PLAYER;
        return this;
    }

    public Door open() {
        this.status = Status.OPENED_BY_HOST;
        return this;
    }

    public boolean isChosenByPlayer() {
        return this.status == Status.CHOSEN_BY_PLAYER;
    }

    public boolean isAvailable() {
        return this.status == Status.AVAILABLE;
    }

    public boolean canBeOpenedByHost() {
        return this.status == Status.AVAILABLE && deal == Deal.GOAT;
    }

    public boolean isNotAvailable() {
        return this.status != Status.AVAILABLE;
    }

    public boolean hasWinningDeal() {
        return this.deal == Deal.CAR;
    }

    public void unmarkAsChosen() {
        this.status = Status.AVAILABLE;
    }

    public boolean equals(Object o) {
        if (o instanceof Door) {
            return ((Door) o).id == this.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + deal.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Door has a ").append(deal.toString());
        if (isChosenByPlayer()) {
            sb.append(" and is chosen by the player");
        }
        return  sb.toString();
    }
}
