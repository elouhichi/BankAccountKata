package entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import enumeration.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author elouhichi
 * @created 29/06/2022 - 18:07
 * @project KataBankAccount
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Operation {
    /**
     * ISO Date
     */
    public static final String DATE_AAAA_MM_JJ = "yyyy-MM-dd";

    private LocalDate date;

    private BigDecimal amount;

    private BigDecimal balance;

    private OperationType type;

    @Override
    public String toString() {
        return type.toString() + " | "  +
                date.format(DateTimeFormatter.ofPattern(DATE_AAAA_MM_JJ)) +  " | " +
                amount.longValue() + " | " +
                balance.longValue();
    }

}
