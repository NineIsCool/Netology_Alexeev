package ru.netology.Nikita_Alexeev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.netology.Nikita_Alexeev.domain.operation.Currency;
import ru.netology.Nikita_Alexeev.domain.operation.OperationCreditType;

@Data
@AllArgsConstructor
public class OperationDTO {
    private final int customerId;
    private final double sum;
    private final Currency currency;
    private final String merchant;
    private final OperationCreditType operationCreditType;
}
