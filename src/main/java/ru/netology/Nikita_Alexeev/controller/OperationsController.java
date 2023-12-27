package ru.netology.Nikita_Alexeev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netology.Nikita_Alexeev.domain.operation.Operation;
import ru.netology.Nikita_Alexeev.dto.OperationDTO;
import ru.netology.Nikita_Alexeev.dto.OperationsGetResponse;
import ru.netology.Nikita_Alexeev.service.AsyncInputOperationService;
import ru.netology.Nikita_Alexeev.service.StatementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/operations/")
public class OperationsController {
    private final StatementService statementService;

    private final AsyncInputOperationService asyncInputOperationService;

    @Autowired
    public OperationsController(StatementService statementService, AsyncInputOperationService asyncInputOperationService) {
        this.statementService = statementService;
        this.asyncInputOperationService = asyncInputOperationService;
    }

    @GetMapping("{customerId}")
    public OperationsGetResponse checkOperationsByCustomerId(@PathVariable("customerId") int customerId){
        List<Operation> operations = statementService.getOperationsFromCustomer(customerId);
        List<OperationDTO> operationDTOS = operations.stream()
                .map(operation ->
                        new OperationDTO(operation.getId(),
                                operation.getCustomerId(),
                                operation.getSum(),
                                operation.getCurrency(),
                                operation.getMerchant(),
                                operation.getOperationCreditType())).collect(Collectors.toList());
        return new OperationsGetResponse(operationDTOS);
    }

    @PostMapping()
    public OperationDTO addOperation(@RequestBody Operation operation){
        asyncInputOperationService.saveOperation(operation);
        return new OperationDTO(operation.getId(),
                operation.getCustomerId(),
                operation.getSum(),
                operation.getCurrency(),
                operation.getMerchant(),
                operation.getOperationCreditType());
    }

    @DeleteMapping("/delete/{customerId}/{operationId}")
    public void deleteOperation(@PathVariable("customerId") int customerId, @PathVariable("operationId") int operationId){
        statementService.removeOperationsOnCustomerId(customerId, operationId);
    }
}
