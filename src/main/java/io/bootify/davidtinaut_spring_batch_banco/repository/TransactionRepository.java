package io.bootify.davidtinaut_spring_batch_banco.repository;

import io.bootify.davidtinaut_spring_batch_banco.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
