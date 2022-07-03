package entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import exceptions.EnoughException;
import enumeration.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author elouhichi
 * @created 28/06/2022 - 12:35
 * @project KataBankAccount
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private BigDecimal balance;

    private History history;

    public void withdraw(BigDecimal amountToWithdraw) throws EnoughException {
        if (this.ifEnough(amountToWithdraw)) {
            this.balance = this.balance.subtract(amountToWithdraw);
            this.history.addOperation(new Operation(LocalDate.now(),amountToWithdraw, this.balance, OperationType.WITHDRAW));
        } else {
            throw new EnoughException();
        }
    }


    public void deposit(BigDecimal amountToDeposit) {
        if (this.ifCorrectAmountToDeposit(amountToDeposit)) {
            this.balance = this.balance.add(amountToDeposit);
            this.history.addOperation(new Operation(LocalDate.now(),amountToDeposit, this.balance, OperationType.DEPOSIT));
        } else {
            throw new IllegalArgumentException("invalid amount to deposit");
        }

    }

    /**
     * check if there is enough balance in the account
     *
     * @param amount to check
     * @return true if there is enough balance in the account
     */
    private boolean ifEnough(BigDecimal amount) {
        return amount != null && this.balance.compareTo(amount) >= 0;
    }

    /**
     * check if the amount to deposit is not null and is positive
     *
     * @param amount to check
     * @return true if the amount to deposit is not null and is positive
     */
    private boolean ifCorrectAmountToDeposit(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
