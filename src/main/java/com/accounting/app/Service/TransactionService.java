package com.accounting.app.Service;

import com.accounting.app.exeption.ResourceNotFoundExeption;
import com.accounting.app.models.Transaction;
import com.accounting.app.repasitory.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public Transaction getTransactionById(long id) {
        return transactionRepository.findById(id).orElseThrow(()->new ResourceNotFoundExeption("تراکنش یافت  نشد"));
    }
}
