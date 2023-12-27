package ru.netology.Nikita_Alexeev.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.netology.Nikita_Alexeev.domain.operation.Currency;
import ru.netology.Nikita_Alexeev.domain.operation.Operation;
import ru.netology.Nikita_Alexeev.domain.operation.OperationCreditType;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Getter
@AllArgsConstructor
public class StatementService {

    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    @PostConstruct
    public void initStorage() {
        List<Operation> initialList = new ArrayList<>();
        initialList.add(new Operation(OperationCreditType.DEBIT, 1234, Currency.RUB, "netology", 1,1));
        initialList.add(new Operation(OperationCreditType.DEBIT, 4378, Currency.RUB, "yandex", 1,2));
        storage.put(1,initialList);
    }

    public String getStringStorage(){
        return storage.toString();
    }


    public String getStringStorage(int id){return storage.get(id).toString();}

    public void saveOperation(Operation operation){
        List<Operation> operations = storage.get(operation.getCustomerId());
        if (operations == null){
            List<Operation> customerOperations = new ArrayList<>();
            customerOperations.add(operation);
            storage.put(operation.getCustomerId(), customerOperations);
        } else {
            operations.add(operation);
        }
    }

    public List<Operation> getOperationsFromCustomer(int customerId) {
        return storage.get(customerId);
    }
    public void removeOperationsOnCustomerId(int customerId, int operationID){
        List<Operation> operations =storage.get(customerId);
        if (operations!=null){
            operations.removeIf(operation -> operation.getId() == operationID);
        }
    }
}
