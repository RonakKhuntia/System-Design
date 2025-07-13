package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {
    private final String id;
    private final String name;
    private final List<User> members = new ArrayList<>();
    private final List<Expense> expenses = new ArrayList<>();
    private final Map<User, BalanceSheet> balanceSheets = new HashMap<>();

    public String getName() {
        return name;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public Map<User, BalanceSheet> getBalanceSheets() {
        return balanceSheets;
    }

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void addMember(User user) {
        members.add(user);
        balanceSheets.put(user, new BalanceSheet());
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public BalanceSheet getBalanceSheet(User user) {
        return balanceSheets.get(user);
    }
}
