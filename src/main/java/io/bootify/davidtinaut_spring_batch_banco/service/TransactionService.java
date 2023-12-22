package io.bootify.davidtinaut_spring_batch_banco.service;


import io.bootify.davidtinaut_spring_batch_banco.model.Transaction;
import io.bootify.davidtinaut_spring_batch_banco.repository.TransactionRepository;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository database;

    @Autowired
    SessionFactory sessionFactory;

    public StatelessSession getSession() {
        return sessionFactory.openStatelessSession();
    }

    public List<Transaction> findAllTxs() {
        return database.findAll();
    }
}
