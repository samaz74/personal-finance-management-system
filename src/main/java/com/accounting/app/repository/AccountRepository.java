package com.accounting.app.repository;

import com.accounting.app.models.Account;
import com.accounting.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findAccountsByCreatorUser(User creatorUser);

    List<Account> getAccountsByCreatorUser_Id(Long creatorUserId);

    Optional<Account> findAccountsById(Long id);
}
