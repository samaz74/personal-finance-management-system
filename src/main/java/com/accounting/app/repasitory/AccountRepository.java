package com.accounting.app.repasitory;

import com.accounting.app.dto.AccountResponse;
import com.accounting.app.models.Account;
import com.accounting.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.naming.Name;
import java.util.List;
import java.util.Optional;
import java.util.SequencedCollection;

public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findAccountsByCreatorUser(User creatorUser);

    List<Account> getAccountsByCreatorUser_Id(Long creatorUserId);

    Optional<Account> findAccountsById(Long id);
}
