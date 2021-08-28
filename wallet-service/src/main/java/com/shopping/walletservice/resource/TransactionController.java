package com.shopping.walletservice.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.walletservice.model.Transaction;
import com.shopping.walletservice.service.TransactionServiceImpl;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionServiceImpl transactionService;
	
	@GetMapping("/gettransactions/{userId}")
	public List<Transaction> findAllTransactionByUser(@PathVariable("userId") String userId){
		return transactionService.findTransactionsBySender(userId);
	}

}
