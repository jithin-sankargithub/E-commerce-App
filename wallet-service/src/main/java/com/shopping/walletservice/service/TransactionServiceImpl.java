package com.shopping.walletservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.walletservice.model.Transaction;
import com.shopping.walletservice.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public List<Transaction> findTransactionsBySender(String sender) {
		return transactionRepository.findTransactionsBySender(sender);
	}
	
	@Override
	public String processTransaction(Transaction transaction) {
		Transaction processTransaction = transactionRepository.save(transaction);
		return processTransaction.getId();
		
	}

}
