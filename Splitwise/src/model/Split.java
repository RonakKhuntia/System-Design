package model;

public class Split {
    private double amount;
    private final User user;

    public Split(double amount, User user) {
        this.amount = amount;
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

}
