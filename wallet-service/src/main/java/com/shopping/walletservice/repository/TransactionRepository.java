package com.shopping.walletservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shopping.walletservice.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String>{
	public List<Transaction> findTransactionsBySender(String sender);

}
