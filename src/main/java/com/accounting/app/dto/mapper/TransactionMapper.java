package com.accounting.app.dto.mapper;

import com.accounting.app.Service.AccountService;
import com.accounting.app.Service.CategoryService;
import com.accounting.app.dto.TransactionRequest;
import com.accounting.app.dto.TransactionResponse;
import com.accounting.app.models.Transaction;
import com.accounting.app.models.User;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    private final AccountService accountService;
    private final CategoryService categoryService;
    public TransactionMapper(AccountService accountService, CategoryService categoryService) {
        this.accountService = accountService;
        this.categoryService = categoryService;
    }

    public Transaction toEntity(TransactionRequest transactionRequest, User user)  {
        return new Transaction(
                categoryService.getCategoryByIdEntity(transactionRequest.getCategoryId()),
                accountService.getAccountByIdEntity(transactionRequest.getMainAccountId()),
                transactionRequest.getReceiverAccountId() != null ?
                        accountService.getAccountByIdEntity(transactionRequest.getReceiverAccountId()) : null,
                transactionRequest.getDescription(),
                transactionRequest.getAmount(),
                transactionRequest.getTransactionType(),
                user
        );
    }
    public TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getCategory().getId(),
                transaction.getCategory().getName(),
                transaction.getMainAccount().getId(),
                transaction.getMainAccount().getAccountName(),
                transaction.getReceiverAccount() != null ? transaction.getReceiverAccount().getId() : null,
                transaction.getReceiverAccount() != null ? transaction.getReceiverAccount().getAccountName() : null,
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getTransactionType(),
                transaction.getUser().getId(),
                transaction.getUser().getFirstName().concat(" " + transaction.getUser().getLastName()),
                transaction.getCreatedAt()
                );
    }
}
