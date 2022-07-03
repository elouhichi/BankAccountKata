package entities;

import java.util.ArrayList;
import java.util.List;

import enumeration.OperationType;
import lombok.Getter;

/**
 * @author elouhichi
 * @created 02/07/2022 - 10:56
 * @project KataBankAccount
 */
@Getter
public class History {

    private List<Operation> statement = new ArrayList<>();


    public void addOperation(Operation operation) {
        statement.add(operation);
    }

    public String printStatement() {
        StringBuilder sb = new StringBuilder("Operation | Date | Amount | Balance").append("\n");
        for (Operation operation : this.statement) {
            sb.append(operation.toString()).append("\n");
        }
        sb.append("Total Withdraw | " + this.totalWithdraw()).append("\n");
        sb.append("Total deposit | " + this.totalDeposit()).append("\n");
        sb.append("Balance | " + statement.get(statement.size() - 1).getBalance()).append("\n");
        return sb.toString();
    }

    public double totalWithdraw() {
        return statement.stream().filter(e -> e.getType().equals(OperationType.WITHDRAW)).mapToDouble(e -> e.getAmount().doubleValue()).sum();
    }

    public double totalDeposit() {
        return statement.stream().filter(e -> e.getType().equals(OperationType.DEPOSIT)).mapToDouble(e -> e.getAmount().doubleValue()).sum();
    }

}
