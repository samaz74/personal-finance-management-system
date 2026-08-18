package com.accounting.app.repasitory;

import com.accounting.app.models.Account;
import com.accounting.app.models.Category;
import com.accounting.app.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findTransactionsByMainAccount(Account mainAccount);

    List<Transaction> findTransactionsByCategory(Category category);
}
