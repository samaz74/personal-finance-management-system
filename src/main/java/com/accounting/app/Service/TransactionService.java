package com.accounting.app.Service;

import com.accounting.app.dto.TransactionRequest;
import com.accounting.app.dto.TransactionResponse;
import com.accounting.app.dto.mapper.AccountMapper;
import com.accounting.app.dto.mapper.TransactionMapper;
import com.accounting.app.exeption.InvalidOperationExeption;
import com.accounting.app.exeption.ResourceNotFoundExeption;
import com.accounting.app.models.Account;
import com.accounting.app.models.Transaction;
import com.accounting.app.models.enums.TypeOfCategory;
import com.accounting.app.repasitory.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final UserService userService;
    private final AccountService accountService;
    private final CategoryService categoryService;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper, UserService userService, AccountService accountService, AccountMapper accountMapper, CategoryService categoryService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.userService = userService;
        this.accountService = accountService;
        this.categoryService = categoryService;
    }
    public Transaction getTransactionByIdEntity(long id) {
        return transactionRepository.findById(id).orElseThrow(()->new ResourceNotFoundExeption("تراکنش یافت  نشد"));
    }
    public TransactionResponse getTransactionById(long id) {
        return transactionMapper.toResponse(getTransactionByIdEntity(id));
    }

    @Transactional
    public TransactionResponse createTransaction(TransactionRequest transactionRequest, Long userId) {
        Transaction transaction = transactionMapper.toEntity(transactionRequest,userService.getUserByIdEntity(userId));
        Account mainAccount = transaction.getMainAccount();
        if(transaction.getCategory().getTypeOfCategory() == TypeOfCategory.INCOME) {
           BigDecimal balance = accountService.getAccountBalance(mainAccount.getId()).add(transaction.getAmount());
           accountService.updateBalance(mainAccount.getId(),balance);
           return transactionMapper.toResponse(transactionRepository.save(transaction));
        }else if(transaction.getCategory().getTypeOfCategory() == TypeOfCategory.COST){
            BigDecimal balance = accountService.getAccountBalance(mainAccount.getId());
            if(balance.compareTo(transaction.getAmount()) > 0){
                accountService.updateBalance(mainAccount.getId(),balance.subtract(transaction.getAmount()));
                Account receiverAccount = transaction.getReceiverAccount();
                if(receiverAccount != null){
                    BigDecimal balanceReceiverAccount = accountService.getAccountBalance(receiverAccount.getId()).add(transaction.getAmount());
                    accountService.updateBalance(receiverAccount.getId(),balanceReceiverAccount);
                    return transactionMapper.toResponse(transactionRepository.save(transaction));
                }
                return transactionMapper.toResponse(transactionRepository.save(transaction));
            }else throw new InvalidOperationExeption("مبلغ تراکنش بیشتر از موجودی حساب میباشد");
        }else throw new InvalidOperationExeption("تراکنش نامعتبر است.");
    }

    public List<TransactionResponse> getTransactionByAccountId(Long accountId, Long userId) {
        return transactionRepository.findTransactionByMainAccountAndUser_Id(accountService.getAccountByIdEntity(accountId),userId).stream().map(transactionMapper::toResponse).collect(Collectors.toList());
    }
    public List<TransactionResponse> getTransactionByCategoryId( Long categoryId, Long userId) {
        return  transactionRepository.findTransactionByCategoryAndUser_Id(categoryService.getCategoryByIdEntity(categoryId),userId).stream().map(transactionMapper::toResponse).collect(Collectors.toList());
    }

    public BigDecimal getTransactionsForMonthAndYear(Long userId, Integer month, Integer year) {
        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime end = start.plusMonths(1);
        List<TransactionResponse> list = transactionRepository.findTransactionsByUser_IdAndCreatedAtBetween(userId, start, end).stream().map(transactionMapper::toResponse).collect(Collectors.toList());
        BigDecimal sum = BigDecimal.ZERO;
        for(TransactionResponse transactionResponse : list){
            if (categoryService.getCategoryByIdEntity(transactionResponse.getCategoryId()).getTypeOfCategory() != TypeOfCategory.INCOME){
                sum = sum.add(transactionResponse.getAmount());
            }
        }
        return sum;
    }

}
