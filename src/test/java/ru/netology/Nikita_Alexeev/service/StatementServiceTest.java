package ru.netology.Nikita_Alexeev.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.Nikita_Alexeev.OperationHistoryApiApplicationTest;
import ru.netology.Nikita_Alexeev.domain.Customer;
import ru.netology.Nikita_Alexeev.domain.operation.Currency;
import ru.netology.Nikita_Alexeev.domain.operation.OperationCreditType;
import ru.netology.Nikita_Alexeev.domain.operation.Operation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StatementServiceTest extends OperationHistoryApiApplicationTest {

    @Autowired
    StatementService statementService;

    @BeforeEach
    public void resetStorage() {
        statementService.initStorage();
    }

    @Test
    @Order(1)
    public void getOperations(){
        String operations = statementService.getStringStorage();
        assertEquals("{1=[Operations{operationCreditType=DEBIT, sum=1234.0, currency=RUB, merchant=netology'}, Operations{operationCreditType=DEBIT, sum=4378.0, currency=RUB, merchant=yandex'}]}", operations);
    }

    @Test
    @Order(2)
    public void getCustomerOperations(){
        int customerId = 2;
        List<Operation> operations = statementService.getOperationsFromCustomer(customerId);
        assertEquals(null, operations);
    }

    @Test
    @Order(3)
    public void removeOperationTest(){
        statementService.removeOperationsOnCustomerId(1,1);
        assertEquals("{1=[Operations{operationCreditType=DEBIT, sum=4378.0, currency=RUB, merchant=yandex'}]}", statementService.getStringStorage());
    }

    @Test
    @Order(4)
    public void saveOperationsTest(){
        Operation operation = new Operation(OperationCreditType.CREDIT, 120, Currency.USD, "Lsls", 0,3);
        statementService.saveOperation(operation);
        String operations = statementService.getStringStorage(0);
        assertEquals("[Operations{operationCreditType=CREDIT, sum=120.0, currency=USD, merchant=Lsls'}]", operations);
    }


}