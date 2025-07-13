import enums.SplitType;
import model.User;
import repository.InMemoryGroupRepository;
import service.BalanceSheetService;
import service.DebtSimplificationService;
import service.ExpenseService;
import service.GroupService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User shubh = new User("u1", "Shubh");
        User bob = new User("u2", "Bob");
        User tom  = new User("u3", "Tom");
        User jake = new User("u4", "Jake");

        InMemoryGroupRepository repo = new InMemoryGroupRepository();
        BalanceSheetService balanceSheerService = new BalanceSheetService();
        ExpenseService expenseService = new ExpenseService(balanceSheerService);
        DebtSimplificationService debtSimplificationService = new DebtSimplificationService();

        GroupService groupService = new GroupService(repo, expenseService, debtSimplificationService);

        String goaGroupId = groupService.createGroup("Goa Trip", List.of(shubh, bob, tom));
        String miscGroupId = groupService.createGroup("Non-Group Expenses", List.of(shubh, bob, tom));

        groupService.addExpense(goaGroupId, "Lunch", 100, shubh, List.of(shubh, bob), SplitType.EQUAL, null);
        groupService.addExpense(goaGroupId, "Dinner", 100, bob, List.of(tom, bob), SplitType.EQUAL, null);

        //groupService.simplifyDebts(goaGroupId);
        groupService.printBalances(goaGroupId);
    }
}