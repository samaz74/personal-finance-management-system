package com.accounting.app.repasitory;

import com.accounting.app.models.Account;
import com.accounting.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findAccountsByCreatorUser(User creatorUser);
}
