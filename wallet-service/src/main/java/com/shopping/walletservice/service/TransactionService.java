package com.shopping.walletservice.service;

import java.util.List;
import java.util.Optional;

import com.shopping.walletservice.model.Transaction;

public interface TransactionService {
	public List<Transaction> findTransactionsBySender(String sender);
	public Optional<Transaction> findTransactionById(String id);
	public void saveTransaction(Transaction transaction);
	public String processTransaction(Transaction transaction);

}
