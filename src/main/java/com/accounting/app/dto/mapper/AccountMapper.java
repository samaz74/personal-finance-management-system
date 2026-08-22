package com.accounting.app.dto.mapper;

import com.accounting.app.dto.AccountRequest;
import com.accounting.app.dto.AccountResponse;
import com.accounting.app.models.Account;
import com.accounting.app.models.User;
import com.accounting.app.service.BankService;
import com.accounting.app.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    private final UserService userService;
    private final BankService bankService;
    public AccountMapper(UserService userService, BankService bankService) {
        this.userService = userService;
        this.bankService = bankService;
    }
    public Account toEntity(AccountRequest accountRequest, User user) {
        Account account = new Account();
        account.setAccountName(accountRequest.getAccountName());
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setAccountBank(bankService.getBankByIdentity(accountRequest.getAccountBankId()));
        account.setAccountBalance(accountRequest.getAccountBalance());
        account.setCreatorUser(user);
        account.setTypeOfAccount(accountRequest.getTypeOfAccount());
        account.setAccountCurrency(accountRequest.getAccountCurrency());
        return account;
    }
    public AccountResponse toResponse(Account account) {
        return new AccountResponse(account.getId(),account.getAccountName(),account.getAccountNumber(),account.getAccountBank().getId(),account.getAccountBank().getName(),account.getTypeOfAccount(),account.getAccountCurrency(),
                account.getCreatorUser().getId(),account.getCreatorUser().getFirstName().concat(" " + account.getCreatorUser().getLastName()),account.getAccountBalance(),account.getCreatedAt());

    }
}
