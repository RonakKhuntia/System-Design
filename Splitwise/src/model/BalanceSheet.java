package model;

import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {
    private double totalPaid = 0.0;
    private double totalExpense = 0.0;
    private final Map<User, Double> balances = new HashMap<>();

    public double getTotalPaid() {
        return totalPaid;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public Map<User, Double> getBalances() {
        return balances;
    }

    public void addTotalPaid(double amount) {
        this.totalPaid += amount;
    }

    public void addTotalExpense(double amount) {
        this.totalExpense += amount;
    }

    public void addBalance(User user, double amount) {
        balances.put(user, balances.getOrDefault(user, 0.0) + amount);
        if (Math.abs(balances.get(user)) < 1e-6) balances.remove(user);
    }

    public void clearBalances() {
        balances.clear();
    }

    public void print(User user) {
        System.out.println("------------------------------------------");
        System.out.println(user.name() + " Balance Sheet");
        System.out.println("Total Paid = " + totalPaid);
        System.out.println("Total Expense = " + totalExpense);
        for (Map.Entry<User, Double> entry : balances.entrySet()) {
            System.out.println("User: " + entry.getKey() + " Balance: " + entry.getValue());
        }
        System.out.println("------------------------------------------");
    }
}
