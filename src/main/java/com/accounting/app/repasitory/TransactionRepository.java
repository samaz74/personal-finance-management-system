package com.accounting.app.repasitory;

import com.accounting.app.models.Account;
import com.accounting.app.models.Category;
import com.accounting.app.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findTransactionByMainAccountAndUser_Id(Account mainAccount, Long userId);

    List<Transaction> findTransactionByCategoryAndUser_Id(Category category, Long userId);

    List<Transaction> findTransactionsByUser_IdAndCreatedAtBetween(Long userId, LocalDateTime createdAtAfter, LocalDateTime createdAtBefore);
}
