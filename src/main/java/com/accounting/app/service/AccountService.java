package com.accounting.app.service;

import com.accounting.app.dto.AccountRequest;
import com.accounting.app.dto.AccountResponse;
import com.accounting.app.dto.mapper.AccountMapper;
import com.accounting.app.exeption.ResourceNotFoundExeption;
import com.accounting.app.models.Account;
import com.accounting.app.models.User;
import com.accounting.app.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserService userService;
    private final BankService bankService;


    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper, UserService userService, BankService bankService) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.userService = userService;
        this.bankService = bankService;
    }
    public Account getAccountByIdEntity(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundExeption("حساب یافت نشد."));
    }
    public AccountResponse getAccountById(Long accountId) {
        return accountRepository.findById(accountId).map(accountMapper::toResponse).orElseThrow(() -> new ResourceNotFoundExeption("حساب یافت نشد."));
    }
    public List<AccountResponse> getAccountsByUserId(Long userId) {
        return accountRepository.getAccountsByCreatorUser_Id(userId).stream().map(accountMapper::toResponse).collect(Collectors.toList());
    }
    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream().map(accountMapper::toResponse).collect(Collectors.toList());
    }


    public AccountResponse createAccount(AccountRequest accountRequest, Long userId) {
        User user = userService.getUserByIdEntity(userId);
        Account account = accountMapper.toEntity(accountRequest ,user );
        return accountMapper.toResponse(accountRepository.save(account));
    }

    public AccountResponse updateAccount(AccountRequest accountRequest, Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(()->new ResourceNotFoundExeption("اکانت یافت نشد"));
        account.setAccountName(accountRequest.getAccountName());
        account.setAccountCurrency(accountRequest.getAccountCurrency());
        account.setAccountBank(bankService.getBankByIdentity(accountRequest.getAccountBankId()));
        account.setTypeOfAccount(accountRequest.getTypeOfAccount());
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setAccountBalance(accountRequest.getAccountBalance());
        return accountMapper.toResponse(accountRepository.save(account));

    }
    public BigDecimal getAccountBalance(Long accountId) {
        return accountRepository.findById(accountId).get().getAccountBalance();
    }

    public void updateBalance(Long accountId, BigDecimal balabce) {
        Account account  = accountRepository.findById(accountId).orElseThrow(()->new ResourceNotFoundExeption("حساب یافت نشد."));
        account.setAccountBalance(balabce);
        accountRepository.save(account);
    }
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    public Boolean isAccountOwner(Long userId , Long accountId) throws ResourceNotFoundExeption{
        return accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundExeption("حساب یافت نشد")).getCreatorUser().getId().equals(userId) ? true : false;
    }


}
