package service;

import enums.SplitType;
import model.BalanceSheet;
import model.Group;
import model.User;
import repository.GroupRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GroupService {
    private final GroupRepository groupRepository;
    private final ExpenseService expenseService;
    private final DebtSimplificationService debtSimplificationService;

    public GroupService(GroupRepository groupRepository, ExpenseService expenseService, DebtSimplificationService debtSimplificationService) {
        this.groupRepository = groupRepository;
        this.expenseService = expenseService;
        this.debtSimplificationService = debtSimplificationService;
    }

    public String createGroup(String name, List<User> members) {
        String id = UUID.randomUUID().toString();
        Group g = new Group(id, name);
        members.forEach(g::addMember);
        groupRepository.save(g);
        return id;
    }

    public void addMember(String groupId, User user) {
        get(groupId).addMember(user);
    }

    public void addExpense(String groupId, String description, double amount, User paidBy, List<User> participants, SplitType splitType, Map<User, Double> metadata) {
        expenseService.addExpense(get(groupId), description, amount, paidBy, participants, splitType, metadata);
    }

    public void simplifyDebts(String groupId) {
        debtSimplificationService.simplifyDebts(get(groupId));
    }

    public void printBalances(String groupId) {
        Group group = get(groupId);
        List<User> members = group.getMembers();
        for (User user : members) {
            BalanceSheet balanceSheet = group.getBalanceSheet(user);
            balanceSheet.print(user);
        }
    }

    private Group get(String id) {
       return groupRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Group not found " + id));
    }
}
